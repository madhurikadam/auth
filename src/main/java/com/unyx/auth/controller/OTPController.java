package com.unyx.auth.controller;

import com.unyx.auth.model.PhoneVerification;
import com.unyx.auth.service.OTPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OTPController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OTPService otpService;
	
	@PostMapping("/generate-otp")
	public void generateOTP(@RequestBody Map<String,String> requestBody) {
		String phoneNumber= requestBody.get("phoneNumber");
		int otp = otpService.generateOTP();
		logger.info("OTP generated : {} for phone no: {}", otp, phoneNumber);
		String verificationRef = otpService.generateVerificationRef();
		otpService.savePhoneVerification(new PhoneVerification(phoneNumber, verificationRef, String.valueOf(otp), 0));
		otpService.sendOTP(String.valueOf(otp),phoneNumber);
		Map <String,String> resp = new HashMap<>();
		resp.put("Opt",String.valueOf(otp));
	}

	@PostMapping("/verify-otp")
	public Map<String,String> validateOtp(@RequestBody Map<String,String> requestMap){
		String verificationRef = otpService.verifyOpt(requestMap.get("phoneNumber"),requestMap.get("otp"));
		Map <String,String> resp = new HashMap<>();
		resp.put("verificationRef",String.valueOf(verificationRef));
		return resp;
	}
	
}
