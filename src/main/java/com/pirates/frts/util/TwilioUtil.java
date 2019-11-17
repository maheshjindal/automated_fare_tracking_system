package com.pirates.frts.util;

import com.pirates.frts.service.RfidService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TwilioUtil {
    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public static final String FROM_PHONE="";
    protected static final Logger LOGGER = LoggerFactory.getLogger(TwilioUtil.class);

    public String sendMessage(String toPhone,String fromPhone,String textmessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(toPhone), new PhoneNumber(fromPhone), textmessage).create();

        LOGGER.info("Generated message sid as " + message.getSid());
        return message.getSid();
    }
}
