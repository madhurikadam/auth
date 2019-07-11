package com.unyx.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phone_verification")
public class PhoneVerification {

	@Id
	private String phoneNumber;
	
	private String verificationRef;
	
	private String otp;
	
	private long expiryTime;

	public PhoneVerification() {
	
	}

	public PhoneVerification(String phoneNumber, String verificationRef, String otp, long expiryTime) {
		this.phoneNumber = phoneNumber;
		this.verificationRef = verificationRef;
		this.otp = otp;
		this.expiryTime = expiryTime;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVerificationRef() {
		return verificationRef;
	}

	public void setVerificationRef(String verificationRef) {
		this.verificationRef = verificationRef;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public long getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}

	@Override
	public String toString() {
		return "PhoneVerification [phoneNumber=" + phoneNumber + ", verificationRef=" + verificationRef + ", otp=" + otp
				+ ", expiryTime=" + expiryTime + "]";
	}
}
