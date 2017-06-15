package com.assettrader.model;

public class Email {

	private Long id;
	private Short importance;
	private EmailType emailType;
	private String emailAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getImportance() {
		return importance;
	}

	public void setImportance(Short importance) {
		this.importance = importance;
	}

	public EmailType getEmailType() {
		return emailType;
	}

	public void setEmailType(EmailType emailType) {
		this.emailType = emailType;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
