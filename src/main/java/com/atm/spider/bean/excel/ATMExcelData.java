package com.atm.spider.bean.excel;

/**
 * Excel������
 */
public class ATMExcelData {
	/**
	 * ��������
	 */
	private String bankType;
	
	/**
	 * �ն˱��
	 */
	private String branchCode;
	
	/**
	 * ��������
	 */
	private String ssjg;
	
	/**
	 * ��ַ
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
