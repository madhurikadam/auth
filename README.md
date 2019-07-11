## Facebook and Mobile OTP based authentication using Spring Boot 

* Authentication using facebook or mobile authentication. 
## Pre-Requisite
* Spring-boot
* MongoDB
* Facebook developer account
* AWS account. SNS service

## APIs
- Generate OTP on number you want to register
* /generate-otp
- Varify the OTP
* /verify-otp
- Create JWT custom token either using facebook token or otp verification 
* /token

## Deploy
- Set the facebook api key and secret and aws api key and secret 
- gradle build 
- run AuthApplication.java 

