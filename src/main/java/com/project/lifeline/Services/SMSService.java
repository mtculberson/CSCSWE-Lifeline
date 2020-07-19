package com.project.lifeline.Services;

import com.twilio.rest.api.v2010.account.Message;

import java.io.File;

public interface SMSService {
    public Message sendSMS(String phone, String message);

}