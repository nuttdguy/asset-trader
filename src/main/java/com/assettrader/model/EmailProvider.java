package com.assettrader.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.assettrader.model.utils.EmailProviderName;

@Entity
@Table(name = "EMAIL_PROVIDER")
public class EmailProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMAIL_PROVIDER_ID")
	private Long id;

	@Transient
	private Long userId;

	@Column(name = "EMAIL_FROM")
	private String emailFrom;

	@Column(name = "EMAIL_FROM_PASSWORD")
	private String emailFromPassword;

	@Enumerated(EnumType.STRING)
	@Column(name = "PROVIDER_NAME")
	private EmailProviderName emailProviderName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ADD_DATE")
	private Date addDate;

	@Column(name = "ACCOUNT_NOTE")
	private String accountNote;

	@Transient
	@Column(name = "EMAIL_TO")
	private String emailTo;

	@Transient
	@Column(name = "BODY_MESSAGE")
	private String bodyMessage;

	@Transient
	@Column(name = "MESSAGE_SUBJECT")
	private String msgSubject;

	@ManyToOne
	@JoinColumn(name = "USER_PROFILE_ID", referencedColumnName = "USER_PROFILE_ID", insertable = false, updatable = false)
	private UserProfile userProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNote() {
		return accountNote;
	}

	public void setAccountNote(String accountNote) {
		this.accountNote = accountNote;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailFromPassword() {
		return emailFromPassword;
	}

	public void setEmailFromPassword(String emailFromPassword) {
		this.emailFromPassword = emailFromPassword;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public String getMsgSubject() {
		return msgSubject;
	}

	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public EmailProviderName getEmailProviderName() {
		return emailProviderName;
	}

	public void setEmailProviderName(EmailProviderName emailProviderName) {
		this.emailProviderName = emailProviderName;
	}

}
