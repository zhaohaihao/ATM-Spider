package com.atm.spider.util;

import java.util.List;

import com.atm.spider.bean.ATMData;
import com.atm.spider.bean.net.ATMLinkTypeData;
import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * ��ȡ�����ݴ���MongoDB������
 */
public class MongoDBUtil {
	
	public static boolean dataLoading(List<ATMData> datas) {
		
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			DB psdoc = mongoClient.getDB("analysis");
			DBCollection atm = psdoc.getCollection("ATM");

			for (ATMData data : datas) {
				Gson gson = new Gson();
				DBObject dbObject = (DBObject) JSON.parse(gson.toJson(data));
				// �������ݿ�
				atm.insert(dbObject);
				
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
}
