package com.project.lifeline.Services;

import com.twilio.converter.Promoter;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.io.File;
import java.net.URI;

@Service
public class SMSServiceTwilio implements SMSService{
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC39af82b652dcef414d45b82ba228e2a3";
    public static final String AUTH_TOKEN = "e8c88c7813bf4a12aafbb14a01171042";
    public static final String TWILIO_PHONE = "+12563635744";

    @Override
    public Message sendSMS(String phone, String messg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phone),//The phone number you are sending text to
                new com.twilio.type.PhoneNumber(TWILIO_PHONE),//The Twilio phone number
                messg)
                .create();

        return message;
    }
}