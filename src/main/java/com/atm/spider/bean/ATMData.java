package com.atm.spider.bean;

/**
 * 需要爬取的信息
 */
public class ATMData {
	private Oid _id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 银行
	 */
	private String bankType;
	
	/**
	 * 网点编号
	 */
	private String branchCode;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 地理坐标
	 */
	private String longitude;
	
	/**
	 * 转入金额
	 */
	private double zrMoney;
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 网点电话
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
	 * Mongodb自动生成ObjectId
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
