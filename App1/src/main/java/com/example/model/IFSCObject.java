package com.example.model;

public class IFSCObject {

	private String bank;
	private String ifsc;
	private Integer micr;
	private Integer contact;
	private String branch;
	private String address;
	private Integer stdCode;
	private String city;
	private String district;
	private String state;
	
	public IFSCObject() {

	}

	

	public IFSCObject(String bank, String ifsc, Integer micr, Integer contact, String branch, String address,
			Integer stdCode, String city, String district, String state) {
		super();
		this.bank = bank;
		this.ifsc = ifsc;
		this.micr = micr;
		this.contact = contact;
		this.branch = branch;
		this.address = address;
		this.stdCode = stdCode;
		this.city = city;
		this.district = district;
		this.state = state;
	}



	

	public String getBank() {
		return bank;
	}



	public void setBank(String bank) {
		this.bank = bank;
	}



	public String getIfsc() {
		return ifsc;
	}



	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}



	public Integer getMicr() {
		return micr;
	}



	public void setMicr(Integer micr) {
		this.micr = micr;
	}



	public Integer getContact() {
		return contact;
	}



	public void setContact(Integer contact) {
		this.contact = contact;
	}



	public String getBranch() {
		return branch;
	}



	public void setBranch(String branch) {
		this.branch = branch;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Integer getStdCode() {
		return stdCode;
	}



	public void setStdCode(Integer stdCode) {
		this.stdCode = stdCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	@Override
	public String toString() {
		return "IFSCObject [bank=" + bank + ", ifsc=" + ifsc + ", micr=" + micr + ", contact=" + contact + ", branch="
				+ branch + ", address=" + address + ", stdCode=" + stdCode + ", city=" + city + ", district=" + district
				+ ", state=" + state + "]";
	}



	


	
	
	
}
