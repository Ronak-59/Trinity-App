package in.djtrinity.www.newapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.wang.avi.AVLoadingIndicatorView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static in.djtrinity.www.newapp.IDPTFragment.points;
//import static in.djtrinity.www.trinity.Attractions.eventList;
//import static in.djtrinity.www.trinity.Attractions.eventListDay;
//import static in.djtrinity.www.trinity.Attractions.eventListMonth;
//import static in.djtrinity.www.trinity.Attractions.event_coord_noarray;
//import static in.djtrinity.www.trinity.Attractions.event_coordarray;
//import static in.djtrinity.www.trinity.MainActivity.events;


public class SetupActivity extends AppCompatActivity {

    private AVLoadingIndicatorView avi;

    //idpt
    public boolean idptflags = false;
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_IT = "it";
    private static final String TAG_CHEM = "chemical";
    private static final String TAG_COMPS = "comps";
    private static final String TAG_BIO_PROD = "bioprod";
    private static final String TAG_MECH = "mech";
    private static final String TAG_ELEX = "elex";
    private static final String TAG_EXTC = "extc";

    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;


    //gallery
    public boolean galleryflag = false;
    private static final String TAG_RESULTS_GALLERY = "result";
    private static final String TAG_EVENT_GALLERY = "url";
    JSONArray galleryjson = null;
    ArrayList<HashMap<String, String>> gallerylists;
    public static LinkedList ll = new LinkedList();


    //events
    public boolean eventsflag = false;
    private static final String TAG_RESULTS_EVENT = "result";
    private static final String TAG_EVENT_NAME = "title";
    private static final String TAG_EVENT_DAY = "eventday";
    private static final String TAG_EVENT_MONTH = "eventmonth";
    private static final String TAG_EVENT_COORD = "coord";
    private static final String TAG_EVENT_DESCRIP = "descrip";
    private static final String TAG_EVENT_VENUE = "venue";
    private static final String TAG_EVENT_CATEGORY = "category";
    private static final String TAG_EVENT_PRICE = "price";
    private static final String TAG_EVENT_COORD_NO = "coord_no";
    private static final String TAG_POSTER_URL = "poster_url";
    private static final String TAG_ONE_LINERS = "one_liners";
    private static final String TAG_GOOGLE_FORM = "regurl";
    private static final String TAG_START_TIME="starthour";
    private static final String TAG_END_TIME="endhour";

    public static LinkedList lltitle = new LinkedList();
    public static LinkedList lleventday = new LinkedList();
    public static LinkedList lleventmonth = new LinkedList();
    public static LinkedList llcoord = new LinkedList();
    public static LinkedList lldescrip = new LinkedList();
    public static LinkedList llvenue = new LinkedList();
    public static LinkedList llcategory = new LinkedList();
    public static LinkedList llprice = new LinkedList();
    public static LinkedList llcoord_no = new LinkedList();
    public static LinkedList llposter_url = new LinkedList();
    public static LinkedList llone_liners = new LinkedList();
    public static LinkedList llgoogle_form = new LinkedList();
    public static LinkedList llstarttime=new LinkedList();
    public static LinkedList llendtime=new LinkedList();


    public boolean sponsorsflag = false;
    private static final String TAG_RESULTS_SPONSOR = "result";
    private static final String TAG_SPONSOR_NAME = "name";
    private static final String TAG_SPONSOR_POSTER = "image";
    private static final String TAG_SPONSOR_URL = "site";
    JSONArray sponsorjson = null;
    ArrayList<HashMap<String, String>> sponsorlists;
    public static LinkedList llsponsorname = new LinkedList();
    public static LinkedList llsponsorposter = new LinkedList();
    public static LinkedList llsponsorurl = new LinkedList();


    JSONArray eventsjson = null;
    ArrayList<HashMap<String, String>> eventlists;


    public static int technicalcounter = 0;
    public static int culturalcounter = 0;
    public static int sportscounter = 0;


    public LinkedList test = new LinkedList();


    public void reset() {
        lltitle.clear();
        lleventday.clear();
        lleventmonth.clear();
        llcoord.clear();
        lldescrip.clear();
        llvenue.clear();
        llcategory.clear();
        llprice.clear();
        llcoord_no.clear();
        llposter_url.clear();
        llone_liners.clear();
        llgoogle_form.clear();
        llstarttime.clear();
        llendtime.clear();


        technicalcounter = 0;
        culturalcounter = 0;
        sportscounter = 0;


        ll.clear();
        llsponsorurl.clear();
        llsponsorposter.clear();
        llsponsorurl.clear();

        About.hidec = false;

        MainActivity.backtrace = false;
        MainActivity.backtracesnap = false;
        MainActivity.backtraceqr = false;
        MainActivity.backtracesch = false;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.show();
        Log.d("in setup activity ", " true ");
        test.add("ada");
        test.add("gdsgs");
        Log.d("test data", test.get(0).toString());
        Log.d("test data", test.get(1).toString());
        Log.d("test counter ", Integer.toString(test.size()));


        NetworkInfo info = (NetworkInfo) ((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null)// net is connnected and wifi is not svkm wifi// djscoe wifi
        {
            //idpt
            personList = new ArrayList<HashMap<String, String>>();
            getDatasql();

            //gallery image data


            //event data





            //-----------


            //---------

                /*final int SPLASH_TIME_OUT = 10000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        avi.hide();
                        //if()



                    }
                }, SPLASH_TIME_OUT);*/
            Log.d("check if loop","true");
            if (sponsorsflag)
            {

            }



        }


    else

    {
        finish();
        Toast.makeText(this, "internet connection required", Toast.LENGTH_LONG).show();
    }

}



    public void getDatasql(){
        class GetidptDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://djtrinity.in/app/api/idpt.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
                //idptflags=true;
                gallerylists = new ArrayList<HashMap<String, String>>();
                getImageDatasql();
                Log.d("idpt","true");


            }
        }
        GetidptDataJSON g = new GetidptDataJSON();
        try {
            g.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);






            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String it = c.getString(TAG_IT).trim();
                String chem = c.getString(TAG_CHEM).trim();
                String comps = c.getString(TAG_COMPS).trim();
                String bio_prod=c.getString(TAG_BIO_PROD).trim();
                String mech=c.getString(TAG_MECH).trim();
                String elex=c.getString(TAG_ELEX).trim();
                String extc=c.getString(TAG_EXTC).trim();

                points[0]=Float.parseFloat(it);
                points[1]=Float.parseFloat(comps);
                points[2]=Float.parseFloat(bio_prod);
                points[3]=Float.parseFloat(extc);
                points[4]=Float.parseFloat(elex);
                points[5]=Float.parseFloat(mech);
                points[6]=Float.parseFloat(chem);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //gallery image data
    public void getImageDatasql(){
        class GetimageDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://djtrinity.in/app/api/gallery.php");
               // Log.d("in link class","true");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showimageList();
                //galleryflag=true;
                eventlists = new ArrayList<HashMap<String, String>>();
                getEventDatasql();
                Log.d("gallery","true");


            }
        }
        GetimageDataJSON g = new GetimageDataJSON();
        try {
            g.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void showimageList(){
        //Log.d("in method","true");
        try {
            JSONObject jsonObj = new JSONObject(myJSON);

            galleryjson = jsonObj.getJSONArray(TAG_RESULTS_GALLERY);
            String urlstr="";
            for(int i=0;i<galleryjson.length();i++){
                JSONObject c = galleryjson.getJSONObject(i);

                urlstr = c.getString("url");
                Log.d("url str",urlstr);
                //mThumbIds[i]=urlstr;
                ll.add(urlstr);


                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_EVENT_GALLERY,urlstr);


                gallerylists.add(persons);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //events data
    public void getEventDatasql(){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://djtrinity.in/app/api/events.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showeventList();
               // eventsflag=true;
                sponsorlists = new ArrayList<HashMap<String, String>>();
                getSponsorDatasql();
                Log.d("events","true");


            }
        }
        GetDataJSON g = new GetDataJSON();
        try {
            g.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void showeventList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            eventsjson = jsonObj.getJSONArray(TAG_RESULTS_EVENT);

            for(int i=0;i<eventsjson.length();i++){
                JSONObject c = eventsjson.getJSONObject(i);
                String event_name = c.optString(TAG_EVENT_NAME);
                lltitle.add(event_name);
                Log.d("link list entries ",lltitle.get(i).toString());
                Log.d("link size in setup",Integer.toString(lltitle.size()));
               // len++;
                //eventList[i]=event_name;
                //events[i]=event_name;
               // Log.d("database entry",event_name);
               // Log.d("events" ,eventList[i]);
               // Log.d("events replica",events[i]);
                String event_day = c.getString(TAG_EVENT_DAY);
                if(event_day.length()==1)
                {
                    //eventListDay[i]="0"+event_day;
                    lleventday.add("0"+event_day);
                }
                else
                {
                   // eventListDay[i]=event_day;
                    lleventday.add(event_day);
                }
                String event_month = c.getString(TAG_EVENT_MONTH);
                if(event_month.length()==1)
                {
                    //eventListMonth[i]="0"+event_month;
                    lleventmonth.add("0"+event_month);
                }
                else
                {
                    //eventListMonth[i]=event_month;
                    lleventmonth.add("0"+event_month);
                }
               // Log.d("eventlistmonth",eventListMonth[i]);
                String event_coord=c.getString(TAG_EVENT_COORD);
                //event_coordarray[i]=event_coord;
                if(event_coord==null)
                {
                    llcoord.add("-");
                }
                else{
                    llcoord.add(event_coord);
                }
                Log.d("event coord",llcoord.get(i).toString());

                String event_coord_no=c.getString(TAG_EVENT_COORD_NO);
                //event_coord_noarray[i]=event_coord_no;
                if(event_coord_no==null)
                {
                    llcoord_no.add("-");
                }
                else
                {
                    llcoord_no.add(event_coord_no);
                }

                String descip=c.getString(TAG_EVENT_DESCRIP);
                lldescrip.add(descip);

                String venue=c.getString(TAG_EVENT_VENUE);
                llvenue.add(venue);

                String category=c.getString(TAG_EVENT_CATEGORY);
                if(category.equalsIgnoreCase("c"))culturalcounter=culturalcounter+1;
                if(category.equalsIgnoreCase("t"))technicalcounter=technicalcounter+1;
                if(category.equalsIgnoreCase("s"))sportscounter=sportscounter+1;
                llcategory.add(category);

                String price=c.getString(TAG_EVENT_PRICE);
                if(price==null)
                {
                   llprice.add("-");
                }
                else {
                    llprice.add(price);
                }

                String poster=c.getString(TAG_POSTER_URL);
                String oneliners=c.getString(TAG_ONE_LINERS);

                llposter_url.add(poster);
                llone_liners.add(oneliners);

                String googleformurl=c.getString(TAG_GOOGLE_FORM);
                llgoogle_form.add(googleformurl);
                Log.d("google fom ",llgoogle_form.get(i).toString());

                String starttime=c.getString(TAG_START_TIME);
                String endtime=c.getString(TAG_END_TIME);

                llstarttime.add(starttime);
                llendtime.add(endtime);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_EVENT_NAME,event_name);
                persons.put(TAG_EVENT_DAY,event_day);
                persons.put(TAG_EVENT_MONTH,event_month);
                persons.put(TAG_EVENT_COORD,event_coord);
                persons.put(TAG_EVENT_COORD_NO,event_coord_no);


                eventlists.add(persons);
            }

           /* ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item_tp,
                    new String[]{TAG_EVENT_NAME,TAG_EVENT_DAY,TAG_EVENT_MONTH,TAG_EVENT_COORD,TAG_EVENT_COORD_NO},
                    new int[]{R.id.event_name, R.id.event_day, R.id.event_month, R.id.event_coord, R.id.event_coord_no}
            );

            list.setAdapter(adapter);*/
            Log.d("link list size ",Integer.toString(lltitle.size()));
            MainActivity obj=new MainActivity();
            obj.setmainactivityvalues();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getSponsorDatasql(){
        class GetimageDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://djtrinity.in/app/api/sponsor.php");
                // Log.d("in link class","true");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showsponsorlist();
               // Intent intent = new Intent(SetupActivity.this, WelcomeActivity.class);
                //startActivity(intent);
                sponsorsflag=true;

                Log.d("sponsors flag","true");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Intent in = new Intent(SetupActivity.this, WelcomeActivity.class);
                        startActivity(in);



                    }
                }, 2600);

            }
        }
        GetimageDataJSON g = new GetimageDataJSON();
        try {
            g.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void showsponsorlist(){
        //Log.d("in method","true");
        try {
            JSONObject jsonObj = new JSONObject(myJSON);

            galleryjson = jsonObj.getJSONArray(TAG_RESULTS_SPONSOR);
            String name,poster,url;
            for(int i=0;i<galleryjson.length();i++){
                JSONObject c = galleryjson.getJSONObject(i);

                name = c.optString(TAG_SPONSOR_NAME);
                poster=c.optString(TAG_SPONSOR_POSTER);
                url=c.optString(TAG_SPONSOR_URL);
                llsponsorname.add(name);
                llsponsorposter.add(poster);
                llsponsorurl.add(url);

                Log.d("Setup sponsor ",llsponsorname.get(i).toString());



                HashMap<String,String> persons = new HashMap<String,String>();

               // persons.put(TAG_EVENT_GALLERY,urlstr);


                sponsorlists.add(persons);
                //sponsorsflag=true;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
