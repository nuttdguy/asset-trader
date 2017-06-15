package com.assettrader.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProfile extends Person {

	@Id
	private Long id;
	private Address address;
	private Contact contact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
