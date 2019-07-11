package com.unyx.auth.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AwsSesService {

    @Value("${aws.access.key}")
    String access_key;

    @Value("${aws.secret.key}")
    String secret_key;

    @Value("${aws.region}")
    String region;

    public String sendSMSMessage(String message, String phoneNumber) {
        AWSCredentials credentials = new BasicAWSCredentials(access_key, secret_key);
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        return sendSMSMessage(snsClient, message, phoneNumber);
    }

    private String sendSMSMessage(AmazonSNS snsClient,
                                  String message, String phoneNumber) {
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("UNYX").withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
        PublishRequest request = new PublishRequest();
        request.withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes);
        PublishResult result = snsClient.publish(request);
        System.out.println(result.getMessageId());
        return result.getMessageId();
    }

}