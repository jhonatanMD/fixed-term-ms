package com.fixed.term.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Document(collection = "FixedTerm")
public class FixedTermEntity {

	@Id
	private String idFix;
	private String numAcc;
	private Double cash;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private Date dateReg;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date dateRet;
	
	private String profile;
	
	private Double cashEndMonth;
	
	private int numTran;
	
	private Double commi;
	
	private String status;
	
	
	private List<EntityTransaction> transactions;
	private List<HeadLineEntity> heads;
	private List<SignatoriesEntity> sigs;
	public String getIdFix() {
		return idFix;
	}
	public void setIdFix(String idFix) {
		this.idFix = idFix;
	}

	public String getNumAcc() {
		return numAcc;
	}
	public void setNumAcc(String numAcc) {
		this.numAcc = numAcc;
	}

	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Date getDateReg() {
		return dateReg;
	}
	
	public void setDateReg(Date dateReg) {
		this.dateReg = dateReg;
	}
	
	public Date getDateRet() {
		return dateRet;
	}
	public void setDateRet(Date dateRet) {
		this.dateRet = dateRet;
	}
		
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
	public Double getCashEndMonth() {
		return cashEndMonth;
	}
	public void setCashEndMonth(Double cashEndMonth) {
		this.cashEndMonth = cashEndMonth;
	}
	public int getNumTran() {
		return numTran;
	}
	public void setNumTran(int numTran) {
		this.numTran = numTran;
	}
	public Double getCommi() {
		return commi;
	}
	public void setCommi(Double commi) {
		this.commi = commi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<EntityTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<EntityTransaction> transactions) {
		this.transactions = transactions;
	}
	public List<HeadLineEntity> getHeads() {
		return heads;
	}
	public void setHeads(List<HeadLineEntity> heads) {
		this.heads = heads;
	}
	public List<SignatoriesEntity> getSigs() {
		return sigs;
	}
	public void setSigs(List<SignatoriesEntity> sigs) {
		this.sigs = sigs;
	}
	
	
	
}
