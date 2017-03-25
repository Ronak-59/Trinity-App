package in.djtrinity.www.newapp;

import android.graphics.PointF;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import java.util.Calendar;

import in.djtrinity.www.newapp.com.google.zxing.integration.android.IntentIntegrator;

public class HintFragment extends Fragment implements QRCodeReaderView.OnQRCodeReadListener {
    private IntentIntegrator qrScan;

    Calendar current=Calendar.getInstance();
    int current_day=current.get(Calendar.DAY_OF_MONTH);
    int current_month=current.get(Calendar.MONTH);

    public static boolean found=false;

    public boolean validity;
    private TextView textView;
    public static String contents;
    private QRCodeReaderView mydecoderview;
    public static String eventList[]=new String[SetupActivity.lltitle.size()];
    public static String eventListDay[]=new String[SetupActivity.lleventday.size()];
    public static String eventListMonth[]=new String[SetupActivity.lleventmonth.size()];
    public static String event_coordarray[]=new String[SetupActivity.llcoord.size()];
    public static String event_coord_noarray[]=new String[SetupActivity.llcoord_no.size()];

    public static String descript[]=new String[SetupActivity.lldescrip.size()];
    public static String eventvenue[]=new String[SetupActivity.llvenue.size()];
    public static String categoryarr[]=new String[SetupActivity.llcategory.size()];
    public static String eventprice[]=new String[SetupActivity.llprice.size()];

    public static String event_poster[]=new String[SetupActivity.llposter_url.size()];
    public static String event_one_liner[]=new String[SetupActivity.llone_liners.size()];
    public static String googleform_url[]=new String[SetupActivity.llgoogle_form.size()];

    public TextView hint_frag;
    public HintFragment() {
    }
    public boolean hide;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hint, container, false);
        hint_frag=(TextView)view.findViewById(R.id.hint_text);
        hint_frag.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                About.hidec=true;
                Toast.makeText(getContext(),"developed by infotech team",Toast.LENGTH_LONG).show();
                return true;
            }
        });

        for(int i=0;i<eventList.length;i++)
        {
            eventList[i]=SetupActivity.lltitle.get(i).toString();
            Log.d("hint frag eventlist ",eventList[i]);
        }
        for(int i=0;i<eventListDay.length;i++)
        {
            eventListDay[i]=SetupActivity.lleventday.get(i).toString();
            Log.d("hint eventlistday ",eventListDay[i]);
        }

        for(int i=0;i<eventListMonth.length;i++)
        {
            eventListMonth[i]=SetupActivity.lleventmonth.get(i).toString();
            Log.d("hint eventlistmonth ",eventListMonth[i]);
        }

        for(int i=0;i<event_coordarray.length;i++)
        {
            event_coordarray[i]=SetupActivity.llcoord.get(i).toString();
            Log.d("hint eventlist coord",event_coordarray[i]);
        }

        for(int i=0;i<event_coord_noarray.length;i++)
        {
            event_coord_noarray[i]=SetupActivity.llcoord_no.get(i).toString();
            Log.d("hint eventlist codno",event_coord_noarray[i]);
        }

        for(int i=0;i<descript.length;i++)
        {
            descript[i]=SetupActivity.lldescrip.get(i).toString();
            Log.d("hint eventlist desc ",descript[i]);
        }

        for(int i=0;i<eventvenue.length;i++)
        {
            eventvenue[i]=SetupActivity.llvenue.get(i).toString();
            Log.d("hint eventlist venue",eventvenue[i]);
        }
        for(int i=0;i<categoryarr.length;i++)
        {

            categoryarr[i]=SetupActivity.llcategory.get(i).toString();
            Log.d("hint eventlist cat ",categoryarr[i]);
        }
        for(int i=0;i<eventprice.length;i++)
        {
            eventprice[i]=SetupActivity.llprice.get(i).toString();
            Log.d("hint eventlist mrp ",eventprice[i]);
        }
        for(int i=0;i<event_poster.length;i++)
        {
            event_poster[i]=SetupActivity.llposter_url.get(i).toString();
            Log.d("hint eventlist url ",event_poster[i]);
        }
        for(int i=0;i<event_one_liner.length;i++)
        {
            event_one_liner[i]=SetupActivity.llone_liners.get(i).toString();
            Log.d("hint eventlist 1_L ",event_one_liner[i]);
        }
        for(int i=0;i<googleform_url.length;i++)
        {
            googleform_url[i]=SetupActivity.llgoogle_form.get(i).toString();
        }

        mydecoderview = (QRCodeReaderView) view.findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        mydecoderview.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        mydecoderview.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        mydecoderview.setTorchEnabled(true);

        // Use this function to set front camera preview
        mydecoderview.setFrontCamera();

        // Use this function to set back camera preview
        mydecoderview.setBackCamera();

        textView = (TextView) view.findViewById(R.id.hint_text);


        return view;
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        contents = text;
        //TextView event_name=(TextView)viewx.findViewById(R.id.tv_android);
        String date,venue,descrip,price,coord,coord_no,category,img_url,regurl;
        EventDetailsActivity obj=new EventDetailsActivity();
        Getsetclass myobj=new Getsetclass();

        for(int i=0; i< eventList.length ; i++) {

            if(contents.equalsIgnoreCase(eventList[i]))
            {
                date=eventListDay[i]+" "+obj.geteventmonth(eventListMonth[i])+" 2017";
                venue=eventvenue[i];
                descrip=descript[i];
                price=eventprice[i];
                coord=event_coordarray[i];
                coord_no=event_coord_noarray[i];
                category=categoryarr[i];

                myobj.setdate(date);
                myobj.setvenue(venue);
                myobj.setdescrip(descrip);
                myobj.setprice(price);
                myobj.setcoord(coord);
                myobj.setcoordno(coord_no);

                Log.d("setter mthod",date);
                if(category.equalsIgnoreCase("T"))
                {
                    category="Technical";
                }
                if(category.equalsIgnoreCase("C"))
                {
                    category="Cultural";
                }
                if(category.equalsIgnoreCase("S"))
                {
                    category="Sports";
                }

                img_url=event_poster[i];
                regurl=googleform_url[i];

                if(descrip.contains("<br>"))
                {
                    descrip=descrip.replace("<br>","");
                }

                if(Integer.parseInt(eventListMonth[i])>=current_month)
                {
                    if(Integer.parseInt(eventListDay[i])>=current_day)
                    {
                        //event valid
                        validity=true;
                    }
                    else
                    {
                        validity=false;
                    }
                }
                else
                {
                    validity=false;
                }

                obj.setdata(contents,date,venue,descrip,price,coord,coord_no,category,img_url,regurl,validity,"qr");
                found=true;

            }
        }

        if(found) {
            contents=null;
            found=false;
            Intent in = new Intent(getActivity(), EventDetailsActivity.class);
            startActivity(in);

        }
        else
        {
            contents=null;
           final Toast toast=new Toast(getContext());
            toast.makeText(getContext(),"qr code invalid",Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        mydecoderview.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mydecoderview.stopCamera();
    }
}
