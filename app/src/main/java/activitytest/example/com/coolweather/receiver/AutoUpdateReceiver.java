package activitytest.example.com.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import activitytest.example.com.coolweather.service.AutoUpdateService;

/**
 * Created by lyx on 2016/2/21.
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override

    public void onReceive(Context context,Intent intent){
        Intent i=new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
