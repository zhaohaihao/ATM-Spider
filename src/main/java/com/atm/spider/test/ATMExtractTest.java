package com.atm.spider.test;

import java.util.List;

import com.atm.spider.bean.ATMData;
import com.atm.spider.bean.net.ATMLinkTypeData;
import com.atm.spider.rule.Rule;
import com.atm.spider.service.ATMExtractService;
import com.atm.spider.util.ExcelUtil;


public class ATMExtractTest {
	
	public static int netTotalNum = 0;
	
	public static int matchTotalNum = 0;
	
	public static void main(String[] args) {
		
		// 页面总页数
		int pageTotalNum = 89;
		// 首页请求url, 请求方式
		String firstUrl = "http://www.atmji.com/all-list-%E4%B8%8A%E6%B5%B7.html";
		int firstMethod = Rule.GET;
		// 分页请求url, 请求方式
		String pagingUrl = "http://www.atmji.com/all-list-%E4%B8%8A%E6%B5%B7.html?s=%u4e0a%u6d77";
		int pagingMethod = Rule.POST;
		// excel表格路径
		String filePath = "C:/Users/Administrator/Desktop/上海ATM表.xls";
		
		
		
		
		getFirstData(firstUrl, firstMethod, filePath);
		
		for (int i = 2; i <= pageTotalNum; i++) {
			StringBuilder sb = new StringBuilder("Page$");
			sb.append(i);
			getPagingData(pagingUrl, sb.toString(), pagingMethod, filePath);
			sb.delete(0, sb.length());
		}
		
		System.out.println("netTotalNum: " + netTotalNum);
		System.out.println("matchTotalNum: " + matchTotalNum);
	}
	
	
	/**
	 * 爬取首页数据
	 * @param url	爬取的url地址
	 * @param method	请求方式
	 */
	public static void getFirstData(String url, int method, String filePath) {
		Rule rule = new Rule(url,
							null,
							null, 
							"ContentPlaceHolder1_GridView1", 
							Rule.ID, 
							method);
		List<ATMLinkTypeData> extracts = ATMExtractService.extract(rule);
		netTotalNum = extracts.size();
//		printf(extracts);
		List<ATMData> atmDataList = ATMExtractService.matchAndGetBranchCode(extracts, ExcelUtil.getData(filePath));
		matchTotalNum = atmDataList.size();
//		System.out.println("size1: " + atmDataList.size());
		
		for (ATMData atmData : atmDataList) {
			System.out.println(atmData.getBankType() + " : " 
							 + atmData.getProvince() + " ： "
							 + atmData.getCity() + " : "
							 + atmData.getName() + " : " 
							 + atmData.getBranchCode() + " : " 
							 + atmData.getAddress() + " : "
							 + atmData.getTelNumber());
		}
		
		ATMExtractService.storage(atmDataList);
	}
	
	
	/**
	 * 爬取分页信息
	 * @param url	爬取的url地址
	 * @param pageInfo	当前页数
	 * @param method	请求方式
	 */
	public static void getPagingData(String url, String pageInfo, int method, String filePath) {
		Rule rule = new Rule(url,
				new String[] {
								"__EVENTTARGET", 
								"__EVENTARGUMENT", 
								"__VIEWSTATE", 
								"__VIEWSTATEGENERATOR", 
								"__VIEWSTATEENCRYPTED",
								"__EVENTVALIDATION"
							},
				new String[] {
						   "ctl00$ContentPlaceHolder1$GridView1", 
						   pageInfo,
						   ATMExtractService.__VIEWSTATE,
						   "502EB780", 
						   "",
						   ATMExtractService.__EVENTVALIDATION
						   }, 
				"ContentPlaceHolder1_GridView1", 
				Rule.ID, 
				method);
		List<ATMLinkTypeData> extracts = ATMExtractService.extract(rule);
		netTotalNum += extracts.size();
//		printf(extracts);
		List<ATMData> atmDataList = ATMExtractService.matchAndGetBranchCode(extracts, ExcelUtil.getData(filePath));
		matchTotalNum += atmDataList.size();
//		System.out.println("size2: " + atmDataList.size());
		
		for (ATMData atmData : atmDataList) {
			System.out.println(atmData.getBankType() + " : " 
					 + atmData.getProvince() + " ： "
					 + atmData.getCity() + " : "
					 + atmData.getName() + " : " 
					 + atmData.getBranchCode() + " : " 
					 + atmData.getAddress() + " : "
					 + atmData.getTelNumber());
		}
		
		ATMExtractService.storage(atmDataList);
	}
	
	
	/**
	 * 打印输出信息
	 * @param datas 爬取的数据
	 */
	private static void printf(List<ATMLinkTypeData> datas) {
		for (ATMLinkTypeData data : datas) {
			System.out.println(data.getId());
			System.out.println(data.getName());
			System.out.println(data.getBankType());
			System.out.println(data.getAddress());
			System.out.println(data.getProvince());
			System.out.println(data.getCity());
			System.out.println(data.getTelNumber());
			System.out.println("******************************");
		}
	}
	
}
