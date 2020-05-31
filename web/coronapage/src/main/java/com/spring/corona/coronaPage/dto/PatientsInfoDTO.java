package com.spring.corona.coronaPage.dto;

public class PatientsInfoDTO {
	private int idx;
	private int age;
	private String gender;
	private String address;
	private String lat;
	private String lng;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "PatientsInfoDTO [idx=" + idx + ", age=" + age + ", gender=" + gender + ", address=" + address + ", lat="
				+ lat + ", lng=" + lng + "]";
	}
}
