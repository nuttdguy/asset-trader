package com.assettrader.api.bittrex.model.accountapi;

import java.io.Serializable;
import java.time.LocalDateTime;
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

import com.assettrader.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	private static final long serialVersionUID = 349867175439622084L;

	@JsonIgnore
	@GeneratedValue( strategy=GenerationType.TABLE)
	@Column( name = "ORDER_ID", columnDefinition="serial" )
	private String orderId;
	
	@Id
	@Column( name = "ORDER_UUID" )
	private String orderUuid;
	
	@Column( name = "EXCHANGE_CURRENCY_MARKET" )
	private String exchange;
	
	@Column( name = "ORDER_TYPE" )
	private String type;
	
	@Column( name = "ORDER_QUANTITY" )
	private Double quantity;
	
	@Column( name = "ORDER_QUANTITY_REMAINING" )
	private Double quantityRemaining;
	
	@Column( name = "STOP_LIMIT" ) 
	private Double limit;
	
	@Column( name = "IN_RESERVED" )
	private Double reserved;
	
	@Column( name = "IN_RESERVE_REMAINING" )
	private Double reserveRemaining;
	
	@Column( name = "COMMISSION_RESERVED" )
	private Double commissionReserved;
	
	@Column( name = "COMMISSION_RESERVED_REMANING" )
	private Double commissionReserveRemaining;
	
	@Column( name = "COMMISSION_PAID" )
	private Double commissionPaid;
	
	@Column( name = "PRICE" )
	private Double price;
	
	@Column( name = "PRICE_PER_UNIT" )
	private Double pricePerUnit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "DATE_OPENED" )
	private Date opened;
	
	@Column( name = "DATE_CLOSED" )
	private Boolean closed;
	
	@Column( name = "IS_OPEN" )
	private Boolean isOpen;
	
	@Column( name = "SENTINEL" )
	private String sentinel;
	
	@Column( name = "CANCEL_INITIATED" )
	private Boolean cancelInitiated;
	
	@Column( name = "IMMEDIATE_OR_CANCEL" )
	private Boolean immediateOrCancel;
	
	@Column( name = "IS_CONDITIONAL" )
	private Boolean isConditional;
	
	@Column( name = "CONDITIONS_OF_EXCHANGE" )
	private String condition;
	
	@Column( name = "CONDITION_OF_EXCHANGE_TARGET" )
	private String conditionTarget;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CURRENCY", referencedColumnName="CURRENCY", insertable=false, updatable=false),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName="EXCHANGE_NAME", insertable=false, updatable=false),
		@JoinColumn(name = "USER_PROFILE_ID", referencedColumnName="USER_PROFILE_ID", insertable=false, updatable=false)})
	private Account account;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public Double getReserved() {
		return reserved;
	}

	public void setReserved(Double reserved) {
		this.reserved = reserved;
	}

	public Double getReserveRemaining() {
		return reserveRemaining;
	}

	public void setReserveRemaining(Double reserveRemaining) {
		this.reserveRemaining = reserveRemaining;
	}

	public Double getCommissionReserved() {
		return commissionReserved;
	}

	public void setCommissionReserved(Double commissionReserved) {
		this.commissionReserved = commissionReserved;
	}

	public Double getCommissionReserveRemaining() {
		return commissionReserveRemaining;
	}

	public void setCommissionReserveRemaining(Double commissionReserveRemaining) {
		this.commissionReserveRemaining = commissionReserveRemaining;
	}

	public Double getCommissionPaid() {
		return commissionPaid;
	}

	public void setCommissionPaid(Double commissionPaid) {
		this.commissionPaid = commissionPaid;
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

	public Date getOpened() {
		return opened;
	}

	public void setOpened(Date opened) {
		this.opened = opened;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getSentinel() {
		return sentinel;
	}

	public void setSentinel(String sentinel) {
		this.sentinel = sentinel;
	}

	public Boolean getCancelInitiated() {
		return cancelInitiated;
	}

	public void setCancelInitiated(Boolean cancelInitiated) {
		this.cancelInitiated = cancelInitiated;
	}

	public Boolean getImmediateOrCancel() {
		return immediateOrCancel;
	}

	public void setImmediateOrCancel(Boolean immediateOrCancel) {
		this.immediateOrCancel = immediateOrCancel;
	}

	public Boolean getIsConditional() {
		return isConditional;
	}

	public void setIsConditional(Boolean isConditional) {
		this.isConditional = isConditional;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
