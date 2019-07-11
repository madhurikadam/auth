package com.unyx.auth.service;

import com.unyx.auth.exception.OTPException;
import com.unyx.auth.model.PhoneVerification;
import com.unyx.auth.repository.PhoneVerificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class OTPService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhoneVerificationRepository phoneVerificationRepository;

	@Autowired
	private AwsSesService awsSesService;
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int EXPIRE_MINS = 10;

	public int generateOTP() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return otp;
	}

	public String generateVerificationRef() {
		int count =10; // Generating a 10-letter verificationRef
		StringBuilder stringBuilder = new StringBuilder();
		while(count-- != 0) {
			int character = (int) (Math.random()*ALPHA_NUMERIC_STRING.length());
			stringBuilder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return stringBuilder.toString();
	}
	
	public PhoneVerification savePhoneVerification(PhoneVerification phoneVerification) {
		phoneVerification.setExpiryTime(generateExpiryTime());
		logger.info("Saving: {} ", phoneVerification);
		return phoneVerificationRepository.save(phoneVerification);
	}

	private long generateExpiryTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, EXPIRE_MINS);
		return calendar.getTimeInMillis();
	}
	private long getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.getTimeInMillis();
	}

	public String sendOTP(String otp,String phoneNumber){
		String optStr = otp +" is your Unyx verification code. Code is valid for 10 minutes only,one time use. Please do not share this OTP with anyone";
		return awsSesService.sendSMSMessage(optStr,phoneNumber);
	}

	public String verifyOpt(String phoneNumber, String otp){
		PhoneVerification phoneVerification = phoneVerificationRepository.findByPhoneNumberAndOtp(phoneNumber,otp);
		if(phoneVerification != null){
			long expTime = phoneVerification.getExpiryTime();
			if (otp.equals(phoneVerification.getOtp()) && expTime - getCurrentTime() <600000 && phoneVerification.getPhoneNumber().equals(phoneNumber)){
				return phoneVerification.getVerificationRef();
			}
			else {
				throw new OTPException("Invalid OTP. Please check opt phone number");
			}
		}
		else {
			throw new OTPException("Invalid OTP. Please check opt phone number");
		}
	}

	public boolean validateOtp(String verificationRef,String phoneNumber){
		PhoneVerification phoneVerification = phoneVerificationRepository.findByVerificationRef(verificationRef);
		long expTime = phoneVerification.getExpiryTime();
		if (expTime - getCurrentTime() <600000 && phoneVerification.getPhoneNumber().equals(phoneNumber)){
			phoneVerificationRepository.deleteByVerificationRef(verificationRef);
			return true;
		}
		return false;
	}
}
