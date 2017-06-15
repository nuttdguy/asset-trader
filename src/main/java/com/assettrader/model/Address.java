package com.assettrader.model;

public class Address {

	private Long id;
	private String streetAddress;
	private City city;
	private State state;
	private Zip zip;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Zip getZip() {
		return zip;
	}

	public void setZip(Zip zip) {
		this.zip = zip;
	}


}
