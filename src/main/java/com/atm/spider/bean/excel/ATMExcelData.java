package com.atm.spider.bean.excel;

/**
 * Excel表数据
 */
public class ATMExcelData {
	/**
	 * 银行名称
	 */
	private String bankType;
	
	/**
	 * 终端编号
	 */
	private String branchCode;
	
	/**
	 * 所属机构
	 */
	private String ssjg;
	
	/**
	 * 地址
	 */
	private String address;

	

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

	public String getSsjg() {
		return ssjg;
	}

	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
