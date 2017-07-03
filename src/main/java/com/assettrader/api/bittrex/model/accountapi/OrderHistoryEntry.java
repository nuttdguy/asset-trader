package com.assettrader.api.bittrex.model.accountapi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.assettrader.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ORDER_HISTORY_ENTRY")
public class OrderHistoryEntry implements Serializable {

	private static final long serialVersionUID = -3261426735268961664L;
		
	@Transient
	private String logoUrl;
	
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@JsonIgnore
	@GeneratedValue( strategy=GenerationType.TABLE )
	@Column( name = "ORDER_HISTORY_ID", columnDefinition="serial" )
	private Long id;
	
	@Id
	@Column( name = "ORDER_UUID")
	private String orderUuid;
	
	@Column( name = "EXCHANGE_CURRENCY_MARKET")
	private String exchange;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "TIME_STAMP")
	private Date timeStamp;
	
	@Column( name = "ORDER_TYPE")
	private String orderType;
	
	@Column( name = "STOP_LIMIT" )
	private Double limit;
	
	@Column( name = "QUANTITY" )
	private Double quantity;
	
	@Column( name = "QUANTITY_REMAINING")
	private Double quantityRemaining;
	
	@Column( name = "COMMISSION")
	private Double commission;
	
	@Column( name = "PRICE" )
	private Double price;
	
	@Column( name = "PRICE_PER_UNIT" )
	private Double pricePerUnit;
	
	@Column( name = "IS_CONDITIONAL" )
	private Boolean isConditional;
	
	@Column( name = "CONDITIONS_OF_EXCHANGE" )
	private String condition;
	
	@Column( name = "CONDITION_OF_EXCHANGE_TARGET" )
	private String conditionTarget;
	
	@Column( name = "IMMEDIATE_OR_CANCEL" )
	private Boolean immediateOrCancel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "CLOSED" )
	private Date closed;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName="CURRENCY", insertable=false, updatable=false),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName="EXCHANGE_NAME", insertable=false, updatable=false),
		@JoinColumn(name = "USER_PROFILE_ID", referencedColumnName="USER_PROFILE_ID", insertable=false, updatable=false)})
	private Account account;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getQuantityRemaining() {
		return quantityRemaining;
	}

	public void setQuantityRemaining(Double quantityRemaining) {
		this.quantityRemaining = quantityRemaining;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Boolean getIsConditional() {
		return isConditional;
	}

	public void setIsConditional(Boolean conditional) {
		isConditional = conditional;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConditionTarget() {
		return conditionTarget;
	}

	public void setConditionTarget(String conditionTarget) {
		this.conditionTarget = conditionTarget;
	}

	public Boolean getImmediateOrCancel() {
		return immediateOrCancel;
	}

	public void setImmediateOrCancel(Boolean immediateOrCancel) {
		this.immediateOrCancel = immediateOrCancel;
	}

	public Boolean getConditional() {
		return isConditional;
	}

	public void setConditional(Boolean conditional) {
		isConditional = conditional;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
