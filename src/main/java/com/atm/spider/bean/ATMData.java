package com.atm.spider.bean;

/**
 * ��Ҫ��ȡ����Ϣ
 */
public class ATMData {
	private Oid _id;
	
	/**
	 * ����
	 */
	private String name;
	
	/**
	 * ����
	 */
	private String bankType;
	
	/**
	 * ������
	 */
	private String branchCode;
	
	/**
	 * ��ַ
	 */
	private String address;
	
	/**
	 * ��������
	 */
	private String longitude;
	
	/**
	 * ת����
	 */
	private double zrMoney;
	
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

	
	
	public Oid get_id() {
		return _id;
	}



	public void set_id(Oid _id) {
		this._id = _id;
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



	public String getBranchCode() {
		return branchCode;
	}



	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public double getZrMoney() {
		return zrMoney;
	}



	public void setZrMoney(double zrMoney) {
		this.zrMoney = zrMoney;
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

	/**
	 * Mongodb�Զ�����ObjectId
	 */
	public class Oid {
		private String $oid;

		public String get$oid() {
			return $oid;
		}

		public void set$oid(String $oid) {
			this.$oid = $oid;
		}
		
	}
	
}
