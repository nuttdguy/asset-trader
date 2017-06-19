package com.assettrader.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="USER_PROFILE")
public class UserProfile extends Person {


	@Id
	@Column( name = "USER_PROFILE_ID")
	@GeneratedValue
	private Long id;
	
	// non-owning-side, requires mappedby field name and cascade all
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfile")
	private List<Address> address = new ArrayList<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
