package com.atm.spider.bean.net;

/**
 * ��ȡҳ����ӵ�е���Ϣ
 */
public class ATMLinkTypeData {
	private String id;
	
	/**
	 * ����
	 */
	private String name;
	
	/**
	 * ����
	 */
	private String bankType;
	
	/**
	 * ��ַ
	 */
	private String address;
	
	/**
	 * ʡ��
	 */
	private String province;
	
	/**
	 * ����
	 */
	private String city;
	
	/**
	 * ����绰
	 */
	private String telNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
}
