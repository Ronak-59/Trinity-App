package in.djtrinity.www.newapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class NotifAlarmReceiver extends BroadcastReceiver {

    private String currentDay;
    private String currentMonth;
    private Context context;
    private int counterForEventsForNotif;
    private int counterForCurrentEventsForNotif;


    public  String eventList[]=new String[SetupActivity.lltitle.size()];
    public  String eventListDay[]=new String[SetupActivity.lleventday.size()];
    public  String eventListMonth[]=new String[SetupActivity.lleventmonth.size()];


    private String[] eventsForNotif = new String[SetupActivity.lltitle.size()];
    private EventData[] events = new EventData[SetupActivity.lltitle.size()];
    private String[] currentEventsForNotif = new String[SetupActivity.lltitle.size()];


    @Override
    public void onReceive(Context context, Intent intent) {
        setEventData();
        Log.d("checkpoints1","true");
        for(int i=0;i<eventList.length;i++)
        {
            eventList[i]=SetupActivity.lltitle.get(i).toString();
            Log.d("attract frag eventlist ",eventList[i]);
        }
        for(int i=0;i<eventListDay.length;i++)
        {
            eventListDay[i]=SetupActivity.lleventday.get(i).toString();
            Log.d("attract eventlistday ",eventListDay[i]);
        }

        for(int i=0;i<eventListMonth.length;i++)
        {
            eventListMonth[i]=SetupActivity.lleventmonth.get(i).toString();
            Log.d("attract eventlistmonth ",eventListMonth[i]);
        }
        getCurrentDate();
        Log.d("checkpoints2","true");
        this.context = context;
        counterForEventsForNotif = 0;
        counterForCurrentEventsForNotif = 0;
        boolean notif;
        Log.d("checkpoints3","true");
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences("NotifPref", 0);
        for (int i = 0; i < eventList.length; i++) {
            notif = preferences.getBoolean(eventList[i], false);
            if (notif) {
                eventsForNotif[counterForEventsForNotif] = eventList[i];
                counterForEventsForNotif++;
            }
        }
        Log.d("checkpoints4","true");
        if (counterForEventsForNotif != 0) {
            Log.d("checkpoints4","true");

            for (int i = 0; i < counterForEventsForNotif; i++) {
                for (int j = 0; j < eventList.length; j++) {
                    Log.d("events for notif",eventsForNotif[i]);
                    Log.d("events",eventList[j]);
                    if (eventsForNotif[i].equals(eventList[j])) {
                        Log.d("checkpoints6","true");

                        if (eventListMonth[j].equals(currentMonth)) {
                            Log.d("checkpoints7","true");

                            if (eventListDay[j].equals(currentDay)) {
                                Log.d("checkpoints8","true");

                                currentEventsForNotif[i] = eventsForNotif[i];
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putBoolean(eventsForNotif[i], false);
                                editor.apply();

                                Log.d("checkpoints5","true");
                                createNotif();
                                counterForCurrentEventsForNotif++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void setEventData() {
        for (int i = 0; i < eventList.length; i++) {
            events[i] = new EventData();
            //Log.d("set event name",eventList[i]);
            events[i].setEventName(eventList[i]);
            events[i].setEventMonth(eventListMonth[i]);
            events[i].setEventDay(eventListDay[i]);
        }
    }

    public void getCurrentDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        currentDay = String.valueOf(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH));
        currentMonth = String.valueOf(gregorianCalendar.get(GregorianCalendar.MONTH) + 1);
        Log.d("current month",currentMonth);
        Log.d("current day",currentDay);
    }

    public void createNotif() {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        String eventName = eventsForNotif[0];
        if (counterForCurrentEventsForNotif > 1) {
            for (int i = 1; i < counterForEventsForNotif; i++) {
                if (i == counterForCurrentEventsForNotif - 1)
                    eventName = eventName + " and " + currentEventsForNotif[i];
                else
                    eventName = eventName + ", " + currentEventsForNotif[i];
            }
        }

        Uri notifRing = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone =RingtoneManager.getRingtone(context, notifRing);
        ringtone.play();
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

        Log.d("alarm","ture");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_calendar)
                .setContentTitle("Trinity")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText(eventName + " scheduled for today.");
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int mNotifId = 1;
        manager.notify(mNotifId, builder.build());
    }
}
