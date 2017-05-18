package com.atm.spider.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.atm.spider.bean.ATMData;
import com.atm.spider.bean.excel.ATMExcelData;
import com.atm.spider.bean.net.ATMLinkTypeData;
import com.atm.spider.rule.Rule;
import com.atm.spider.util.MongoDBUtil;

/**
 * 具体爬取的实现方法
 */
public class ATMExtractService {
	
	public static String __VIEWSTATE;

	public static String __EVENTVALIDATION;
	
	/**
	 * 数据爬取
	 * @param rule 规则
	 * @return
	 */
	public static List<ATMLinkTypeData> extract(Rule rule) {
		
		List<ATMLinkTypeData> datas = new ArrayList<ATMLinkTypeData>();
		ATMLinkTypeData data = null;
		try {
			// 解析rule
			String url = rule.getUrl();
			String[] params = rule.getParams();
			String[] values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestMethod = rule.getRequestMethod();
			String pageNoStr = "Page$1";
			
			Connection conn = Jsoup.connect(url);
			
			if(params != null) {
				// 设置查询的参数
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
				
				pageNoStr = values[1];
			}
			
			
			// 设置请求的类型
			Document doc = null;
			switch (requestMethod) {
			case Rule.GET:
				doc = conn.timeout(10000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(10000).post();
				break;
			}
			
			// 获取隐藏表单参数信息
			getHiddenInfo(doc);
			
			// 处理返回的数据
			Elements results = new Elements();
			switch (type) {
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				// 当resultTagName为null默认去body标签
				results = doc.getElementsByTag("body");
				break;
			}
			
			for(Element result : results) {
				Elements trDatas = result.getElementsByTag("tr");
				for (int i = 1; i < trDatas.size() - 2; i++) {
					String id = pageNoStr + ":" + trDatas.get(i).child(0).text();
					String bankType = trDatas.get(i).child(1).text();
					String province = trDatas.get(i).child(2).text();
					String city = trDatas.get(i).child(3).text();
					String name = trDatas.get(i).child(4).text();
					String address = trDatas.get(i).child(5).text();
					String telNumber = trDatas.get(i).child(6).text();
					
					data = new ATMLinkTypeData();
					data.setId(id);
					data.setName(name);
					data.setBankType(bankType);
					data.setAddress(address);
					data.setProvince(province);
					data.setCity(city);
					data.setTelNumber(telNumber);
					
					datas.add(data);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return datas;
	}
	
	
	/**
	 * 获取当前页面隐藏的表单信息
	 * 作为form表单的提交的数据
	 * @param doc 请求返回内容
	 */
	private static void getHiddenInfo(Document doc) {
		Element state = doc.getElementById("__VIEWSTATE");
		Element validation = doc.getElementById("__EVENTVALIDATION");
		__VIEWSTATE = state.val();
		__EVENTVALIDATION = validation.val();
//		System.out.println(__VIEWSTATE);
//		System.out.println(__EVENTVALIDATION);
	}
	
	
	/**
	 * 数据入MongoDB
	 * @param extracts 爬取的数据
	 */
	public static void storage(List<ATMData> atmDataList) {
		boolean result = MongoDBUtil.dataLoading(atmDataList);
		if(result == false) {
			System.out.println("数据存入MongoDB失败!");
		} else {
			System.out.println("数据存入MongoDB成功!");
		} 
	}
	
	
	/**
	 * 匹配获取对于的终端编号
	 * @param netData	网页爬取的数据
	 * @param excelData	excel表格中的数据
	 */
	public static List<ATMData> matchAndGetBranchCode(List<ATMLinkTypeData> netDatas, List<ATMExcelData> excelDatas) {
		
		List<ATMData> atmDataList = new ArrayList<ATMData>();
		
		if(netDatas != null && netDatas.size() > 0) {
			for (ATMLinkTypeData netData : netDatas) {
				
				ATMData ad = new ATMData();
				
				String netBankType = netData.getBankType();
				String netAddress = netData.getAddress();
				
				for(ATMExcelData excelData : excelDatas) {
					String excelAddress = excelData.getAddress();
					
					if(excelAddress == null) {
						continue;
					}
					
					if(netAddress.indexOf(excelAddress) != -1 && netBankType.equals(excelData.getBankType())) {
						// 获得对应的终端编号
						ATMData atmData = new ATMData();
						atmData.setName(netData.getName());
						atmData.setBankType(netData.getBankType());
						atmData.setBranchCode(excelData.getBranchCode());	// 页面没有该信息
						atmData.setAddress(netData.getAddress());
						atmData.setLongitude("");	// 页面没有该信息
						atmData.setZrMoney(0);		// 页面没有该信息
						atmData.setProvince(netData.getProvince());
						atmData.setCity(netData.getCity());
						atmData.setTelNumber(netData.getTelNumber());
						
						atmDataList.add(atmData);
					}
				}
			}
		}
		return atmDataList;
	}
}
