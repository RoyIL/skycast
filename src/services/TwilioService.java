package services;

import use_case.createNotification.CreateNotificationServiceInterface;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioService implements CreateNotificationServiceInterface {

    public static final String ACCOUNT_SID = "AC9ebde5384468883d3d5bff5997e99fd7";
    public static final String AUTH_TOKEN = System.getenv("auth_token");
    public static final PhoneNumber SKYCAST_SEND = new com.twilio.type.PhoneNumber("+17205065722");

    public TwilioService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void sendTwilioMessage(String message, String phoneNumber) {
        Message notification = Message.creator(new com.twilio.type.PhoneNumber("+1" + phoneNumber),
                SKYCAST_SEND, message).create();
    }
}
