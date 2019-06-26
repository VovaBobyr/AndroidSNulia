package ru.myrusakov.receivesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SMSHandler extends BroadcastReceiver {

    private String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null && ACTION.equals(intent.getAction())) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String result = "";
            for (SmsMessage sms : messages) {
                result += "SMS от: " + sms.getOriginatingAddress() + "\n";
                result += "Текст SMS: " + sms.getMessageBody() + "\n";
            }
            Intent act = new Intent(context, MainActivity.class);
            act.putExtra("SMS", result);
            act.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(act);
        }
    }
}
