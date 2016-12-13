package fun;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;


public class notifications {

	public static void notify(String title , String text , int icon,  Activity activity , Class mainActivity){


        Intent resultIntent = new Intent(activity, mainActivity);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        activity,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

            NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(activity);

            mBuilder.setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setContentIntent(resultPendingIntent);

            NotificationManager mNotifyMgr =
                    (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(8, mBuilder.build());
	}
	
}
