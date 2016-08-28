package com.recycle.pad.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.recycle.pad.main.MainActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {
    static final String action_boot="android.intent.action.BOOT_COMPLETED";
 
    @Override
    public void onReceive(Context context, Intent intent) {
    	Log.e("BootBroadcastReceiver", "BootBroadcastReceiver receiver");
        if (intent.getAction().equals(action_boot)){
            Intent ootStartIntent=new Intent(context,MainActivity.class);
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ootStartIntent);
        }
 
    }
 
}
