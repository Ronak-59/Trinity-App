package in.djtrinity.www.newapp;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventDetailsActivity extends AppCompatActivity {

    public static String eventName;
    public static String description;
    public static String event_coordno;
    public static String date;
    public static String Desc;
    public static String textviewdate;
    public static String eventday,eventmonth;
    public static String contact;
    private static boolean noNotif = false;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private NestedScrollView parentView;
    private String keyForPrefs = "";
    private android.support.design.widget.CollapsingToolbarLayout img;
    public  static Dialog nagDialog;
    TextView regurltextview;
    public String start_time,end_time;
    private TextView tptext;




    public static String price;
    public static String venue;
    public static String header;
    public static String eventPoster;
    public static String regurl;
    public static String entry;
    public Long eventId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //eventName = CulturalActivity.e_name;
        toolbar.setTitle(eventName);
        setSupportActionBar(toolbar);
        tptext=(TextView)findViewById(R.id.tp);
        tptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.djtrinity.in/home.php");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        String tp;
        if(noNotif)tp="true";
        else tp="false";
        Log.d(eventName,tp);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        FloatingActionButton toolbarFab  = (FloatingActionButton) findViewById(R.id.schedule_details_fab);
        parentView = (NestedScrollView) findViewById(R.id.schedule_details_nested_scroll_view);
        img= (android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        collapsingToolbarLayout.setTitle(eventName);
        Picasso.with(EventDetailsActivity.this).load(eventPoster).into(new com.squareup.picasso.Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // mainLayout.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                img.setBackgroundDrawable(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
                img.setBackgroundResource(R.drawable.error);
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
                img.setBackgroundResource(R.drawable.loading);
            }
        });
        TextView fees=(TextView)findViewById(R.id.fees);
        regurltextview=(TextView)findViewById(R.id.register);

        fees.setText(price);

//        SpannableString content=new SpannableString(regurl);
       // content.setSpan(new UnderlineSpan(),0,content.length(),0);
        regurltextview.setText(regurl);


        regurltextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(regurltextview.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });




        nagDialog = new Dialog(EventDetailsActivity.this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(false);
        nagDialog.setContentView(R.layout.preview_image);
        ImageButton imagecancel=(ImageButton)nagDialog.findViewById(R.id.preview);
        imagecancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagDialog.dismiss();
            }
        });
        nagDialog.setCancelable(false);
        nagDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        final ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);


        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                        Picasso.with(EventDetailsActivity.this).load(eventPoster).into(new com.squareup.picasso.Target() {

                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                // mainLayout.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                                ivPreview.setBackgroundDrawable(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                            }

                            @Override
                            public void onBitmapFailed(final Drawable errorDrawable) {
                                Log.d("TAG", "FAILED");
                                img.setBackgroundResource(R.drawable.error);
                            }

                            @Override
                            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                                Log.d("TAG", "Prepare Load");
                                img.setBackgroundResource(R.drawable.loading);
                            }
                        });

                nagDialog.show();
            }
        });
        nagDialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                    nagDialog.dismiss();
                }
                return true;
            }
        });

        MainActivity.scheduleReturnFlag = true;

        keyForPrefs = eventName;
        setText();
        toolbarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noNotif==true) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    addEvent(getApplicationContext());
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder= new AlertDialog.Builder(new ContextThemeWrapper(EventDetailsActivity.this, R.style.myDialog));
                    builder.setMessage("Do you want to set reminder for this event?").setPositiveButton("Yes",dialogClickListener).setNegativeButton("No",dialogClickListener).show();
                }






                    //addEvent(getApplicationContext());
                    //setDataForNotif();
                if(noNotif==false)
                    Toast.makeText(getApplicationContext(), eventName + " has already been completed.", Toast.LENGTH_SHORT).show();
            }
        });
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setdata(String event_name,String date,String venue,String descrip,String price,String coord,String coord_no,String category,String img_url,String regurl,Boolean validity,String entry)
    {
        this.eventName=event_name;
        this.header=category;
        this.textviewdate=date;
        this.date=venue;
        this.description=descrip;
        this.contact=coord;
        this.event_coordno=coord_no;
        this.eventPoster=img_url;
        this.price=price;
        this.regurl=regurl;
        this.noNotif=validity;
        this.entry=entry;

        eventday=textviewdate.substring(0,2);
        eventmonth=textviewdate.substring(3,textviewdate.length()-5);
        eventmonth=eventmonth.toLowerCase().trim();
        switch (eventmonth)
        {
            case "january":eventmonth="0";
                break;
            case "february":eventmonth="1";
                break;
            case "march":eventmonth="2";
                break;
            case "april":eventmonth="3";
                break;
            case "may":eventmonth="4";
                break;
            case "june":eventmonth="5";
                break;
            case "july":eventmonth="6";
                break;
            case "august":eventmonth="7";
                break;
            case "september":eventmonth="8";
                break;
            case "october":eventmonth="9";
                break;
            case "november":eventmonth="10";
                break;
            case "december":eventmonth="11";
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Event Name: "+eventName + "\n\n" + "Date: "+textviewdate + "\n\n" + "Venue: "+date+"\n\n"+"Event Description: \n "+description+"\n\n" + " For More Details Contact:\n" + contact + "\n" + event_coordno);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case android.R.id.home:
                //getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new Attractions()).commit();
                //EventDetailsActivity.this.finish();
                if(entry.equalsIgnoreCase("attr")) {
                    MainActivity.backtrace = true;
                }
                if(entry.equalsIgnoreCase("sch")) {
                    MainActivity.backtracesch = true;
                }

                if(entry.equalsIgnoreCase("qr")) {
                MainActivity.backtraceqr = true;
            }



                Intent in=new Intent(this,MainActivity.class);
                    startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void setText() {
        TextView textHeader = (TextView) findViewById(R.id.schedule_details_text_header);
        TextView textView1 = (TextView) findViewById(R.id.schedule_details_text_info1);
        // TextView textView2 = (TextView) findViewById(R.id.schedule_details_text_info2);
        TextView contactTextView = (TextView) findViewById(R.id.schedule_details_contact);
        TextView textview3=(TextView)findViewById(R.id.dates);
        TextView textview4=(TextView)findViewById(R.id.venue);
        TextView event_coord_no=(TextView)findViewById(R.id.event_coord_no);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        /*Getsetclass object=new Getsetclass();
        header=object.getcategory();
        textviewdate=object.getdate();
        date=object.getvenue();
        description=object.getdescrip();
        contact=object.getcoord();
        event_coordno=object.getcoordno();

        Log.d("getter methods",date);*/



        switch (header) {
            case "Technical":
                if (Build.VERSION.SDK_INT >= 21) {
                    window.setStatusBarColor(Color.parseColor("#000000"));
                }
                parentView.setBackgroundColor(getResources().getColor(R.color.white));
                textHeader.setTextColor(Color.parseColor("#00e676"));
                //collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#303F9F"));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#00e676"));
                break;

            case "Cultural":
                if (Build.VERSION.SDK_INT >= 21) {
                    window.setStatusBarColor(Color.parseColor("#000000"));
                }
                parentView.setBackgroundColor(getResources().getColor(R.color.white));
                textHeader.setTextColor(Color.parseColor("#4dd0e1"));
                //collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#00BCD4"));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#4dd0e1"));
                break;

            case "Sports":
                if (Build.VERSION.SDK_INT >= 21) {
                    window.setStatusBarColor(Color.parseColor("#000000"));
                }
                parentView.setBackgroundColor(getResources().getColor(R.color.white));
                textHeader.setTextColor(Color.parseColor("#f50057"));
                //collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#607D8B"));
                collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#f50057"));
                break;

        }

        //description = description ;
        textHeader.setText(header);
        textview4.setText(date);
        textview3.setText(textviewdate);
        textView1.setText(description);
        //textView2.setText(date);
        contactTextView.setText(contact);
        event_coord_no.setText(event_coordno);
    }

    public void setDataForNotif() {
        boolean notifCheck;
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("NotifPref", 0);
        notifCheck = preferences.getBoolean(keyForPrefs, false);
        DialogNotif DialogNotif = new DialogNotif();
        DialogNotif.getData(keyForPrefs, notifCheck);
        DialogNotif.show(getFragmentManager(), "DIALOG TAG");
    }

    public void openTrinity(View view) {
        Uri uri = Uri.parse("http://www.djtrinity.in/home.php");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public String geteventmonth(String eventmonth)
    {
        String returnst="";
        switch (eventmonth)
        {
            case "01": returnst="January";
                break;
            case "02": returnst= "February";
                break;
            case "03": returnst= "March";
                break;
            case "04": returnst= "April";
                break;
            case "05": returnst= "May";
                break;
            case "06": returnst= "June";
                break;
            case "07": returnst= "July";
                break;
            case "08": returnst= "August";
                break;
            case "09": returnst= "September";
                break;
            case "10": returnst= "October";
                break;
            case "11": returnst= "November";
                break;
            case "12": returnst= "December";
                break;
            default:  returnst= "error";
                break;
        }
        return returnst;
    }
    public void addEvent(Context context) {
        Log.d("eventmonth",eventmonth);
        Log.d("eventday",eventday);

        for(int i=0;i<SetupActivity.llstarttime.size();i++)
        {
            if(eventName.equalsIgnoreCase(SetupActivity.lltitle.get(i).toString()))
            {
                start_time=SetupActivity.llstarttime.get(i).toString();
                end_time=SetupActivity.llendtime.get(i).toString();
            }

        }
        Log.d("event start time",start_time);
        Log.d("event end time",end_time);
        GregorianCalendar start = new GregorianCalendar(2017, Integer.parseInt(eventmonth), Integer.parseInt(eventday), Integer.parseInt(start_time), 0);
        GregorianCalendar end = new GregorianCalendar(2017, Integer.parseInt(eventmonth), Integer.parseInt(eventday), Integer.parseInt(end_time), 0);
        GregorianCalendar defaultcal = new GregorianCalendar(2017, Integer.parseInt(eventmonth), Integer.parseInt(eventday), Integer.parseInt(start_time)+6, 0);

        try {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            Log.d("start time",Long.toString(start.getTimeInMillis()));
            values.put(CalendarContract.Events.DTSTART, start.getTimeInMillis());
           Log.d("end time",Long.toString(end.getTimeInMillis()));
            if(Integer.parseInt(end_time)!=0 && Integer.parseInt(end_time)>Integer.parseInt(start_time))
            {
                values.put(CalendarContract.Events.DTEND, end.getTimeInMillis());
            }
            else
            {
                values.put(CalendarContract.Events.DTEND,defaultcal.getTimeInMillis());
            }

           /// values.put(CalendarContract.Events.DTEND, end.getTimeInMillis());
            values.put(CalendarContract.Events.TITLE, eventName);
            values.put(CalendarContract.Events.CALENDAR_ID, 1);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance()
                    .getTimeZone().getID());
  //          values.put(CalendarContract.Events.CALENDAR_COLOR,"red");
            values.put(CalendarContract.Events.EVENT_LOCATION,date);
            values.put(CalendarContract.Events.ORGANIZER,"trinity.publicity2017@gmail.com");
          //  values.put(CalendarContract.Events.CAN_INVITE_OTHERS,true);
            values.put(CalendarContract.Events.DESCRIPTION,description);
         //   values.put(CalendarContract.Events.CALENDAR_COLOR,R.color.bg_screen1);
         //   values.put(CalendarContract.Events.EVENT_COLOR_KEY,);

//            values.put(CalendarContract.Events.EVENT_COLOR,"green");
            //System.out.println(Calendar.getInstance().getTimeZone().getID());
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

            // Save the eventId into the Task object for possible future delete.
            this.eventId = Long.parseLong(uri.getLastPathSegment());
            // Add a 5 minute, 1 hour and 1 day reminders (3 reminders)
            setReminder(cr, this.eventId, 5);
            setReminder(cr, this.eventId, 60);
            setReminder(cr, this.eventId, 1440);

            Toast.makeText(context,"Reminder set in Calendars",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // routine to add reminders with the event
    public void setReminder(ContentResolver cr, long eventID, int timeBefore) {
        try {
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Reminders.MINUTES, timeBefore);
            values.put(CalendarContract.Reminders.EVENT_ID, eventID);
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            Uri uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
            Cursor c = CalendarContract.Reminders.query(cr, eventID,
                    new String[]{CalendarContract.Reminders.MINUTES});
            if (c.moveToFirst()) {
                System.out.println("calendar"
                        + c.getInt(c.getColumnIndex(CalendarContract.Reminders.MINUTES)));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // function to remove an event from the calendar using the eventId stored within the Task object.
    /*public void removeEvent(Context context) {
        ContentResolver cr = context.getContentResolver();

        int iNumRowsDeleted = 0;

        Uri eventsUri = Uri.parse(CALENDAR_URI_BASE+"events");
        Uri eventUri = ContentUris.withAppendedId(eventsUri, this._eventId);
        iNumRowsDeleted = cr.delete(eventUri, null, null);

        //Log.i(DEBUG_TAG, "Deleted " + iNumRowsDeleted + " calendar entry.");
    }


    public int updateEvent(Context context) {
        int iNumRowsUpdated = 0;
        GregorianCalendar calDate = new GregorianCalendar(this._year, this._month, this._day, this._hour, this._minute);

        ContentValues event = new ContentValues();

        event.put(CalendarContract.Events.TITLE, this._title);
        event.put("hasAlarm", 1); // 0 for false, 1 for true
        event.put(CalendarContract.Events.DTSTART, calDate.getTimeInMillis());
        event.put(CalendarContract.Events.DTEND, calDate.getTimeInMillis()+60*60*1000);

        Uri eventsUri = Uri.parse(CALENDAR_URI_BASE+"events");
        Uri eventUri = ContentUris.withAppendedId(eventsUri, this._eventId);

        iNumRowsUpdated = context.getContentResolver().update(eventUri, event, null,
                null);

        // TODO put text into strings.xml
        ///Log.i(DEBUG_TAG, "Updated " + iNumRowsUpdated + " calendar entry.");

        return iNumRowsUpdated;*/
}
