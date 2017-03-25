package in.djtrinity.www.newapp;


import android.os.AsyncTask;
import android.util.Log;

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

public  class GetDataJSON extends AsyncTask<String, Void, String> {

    public boolean eventsflag = false;
    String myJSON;
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


    JSONArray eventsjson = null;
    ArrayList<HashMap<String, String>> eventlists;

    public static int technicalcounter = 0;
    public static int culturalcounter = 0;
    public static int sportscounter = 0;


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
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();
        } catch (Exception e) {
            // Oops
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        myJSON = result;
        showeventList();
    }


    protected void showeventList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            eventsjson = jsonObj.getJSONArray(TAG_RESULTS_EVENT);

            for (int i = 0; i < eventsjson.length(); i++) {
                JSONObject c = eventsjson.getJSONObject(i);
                String event_name = c.optString(TAG_EVENT_NAME);
                lltitle.add(event_name);
                Log.d("link list entries ", lltitle.get(i).toString());
                // len++;
                //eventList[i]=event_name;
                //events[i]=event_name;
                // Log.d("database entry",event_name);
                // Log.d("events" ,eventList[i]);
                // Log.d("events replica",events[i]);
                String event_day = c.getString(TAG_EVENT_DAY);
                if (event_day.length() == 1) {
                    //eventListDay[i]="0"+event_day;
                    lleventday.add("0" + event_day);
                } else {
                    // eventListDay[i]=event_day;
                    lleventday.add("0" + event_day);
                }
                String event_month = c.getString(TAG_EVENT_MONTH);
                if (event_month.length() == 1) {
                    //eventListMonth[i]="0"+event_month;
                    lleventmonth.add("0" + event_month);
                } else {
                    //eventListMonth[i]=event_month;
                    lleventmonth.add("0" + event_month);
                }
                // Log.d("eventlistmonth",eventListMonth[i]);
                String event_coord = c.getString(TAG_EVENT_COORD);
                //event_coordarray[i]=event_coord;
                if (event_coord == null) {
                    llcoord.add("-");
                } else {
                    llcoord.add(event_coord);
                }

                String event_coord_no = c.getString(TAG_EVENT_COORD_NO);
                //event_coord_noarray[i]=event_coord_no;
                if (event_coord_no == null) {
                    llcoord_no.add("-");
                } else {
                    llcoord_no.add(event_coord_no);
                }

                String descip = c.getString(TAG_EVENT_DESCRIP);
                lldescrip.add(descip);

                String venue = c.getString(TAG_EVENT_VENUE);
                llvenue.add(venue);

                String category = c.getString(TAG_EVENT_CATEGORY);
                llcategory.add(category);

                String price = c.getString(TAG_EVENT_PRICE);
                if (price == null) {
                    llprice.add("-");
                } else {
                    llprice.add(price);
                }

                String poster = c.getString(TAG_POSTER_URL);
                String oneliners = c.getString(TAG_ONE_LINERS);

                llposter_url.add(poster);
                llone_liners.add(oneliners);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_EVENT_NAME, event_name);
                persons.put(TAG_EVENT_DAY, event_day);
                persons.put(TAG_EVENT_MONTH, event_month);
                persons.put(TAG_EVENT_COORD, event_coord);
                persons.put(TAG_EVENT_COORD_NO, event_coord_no);


//                eventlists.add(persons);
                eventsflag = true;
            }

           /* ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item_tp,
                    new String[]{TAG_EVENT_NAME,TAG_EVENT_DAY,TAG_EVENT_MONTH,TAG_EVENT_COORD,TAG_EVENT_COORD_NO},
                    new int[]{R.id.event_name, R.id.event_day, R.id.event_month, R.id.event_coord, R.id.event_coord_no}
            );

            list.setAdapter(adapter);*/
            Log.d("link list size ", Integer.toString(lltitle.size()));
            MainActivity obj = new MainActivity();
            obj.setmainactivityvalues();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

