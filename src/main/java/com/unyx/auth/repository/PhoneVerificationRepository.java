package com.unyx.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.unyx.auth.model.PhoneVerification;

public interface PhoneVerificationRepository extends MongoRepository<PhoneVerification, String>{

	public PhoneVerification findByPhoneNumber(String phoneNumber);
	public PhoneVerification findByVerificationRef(String verificationRef);
	public void deleteByVerificationRef(String verificationRef);
	public PhoneVerification findByPhoneNumberAndOtp(String phoneNumber,String opt);


}
