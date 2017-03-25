package in.djtrinity.www.newapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class TechActivity extends Fragment {



    public String[] TechData=new String[SetupActivity.technicalcounter];
    public String[] tech_eventdesc_array=new String[SetupActivity.technicalcounter];
    public String[] tech_android_image_urls=new String[SetupActivity.technicalcounter];
    RecyclerView recyclerView;
    DataAdapter adapter;

    Calendar current=Calendar.getInstance();
    int current_day=current.get(Calendar.DAY_OF_MONTH);
    int current_month=current.get(Calendar.MONTH);

    public boolean validity;

    public String techvenue="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_tech, container, false);
        int techinsert=0;
        for(int i=0;i<Attractions.eventList.length;i++)
        {
            if(SetupActivity.llcategory.get(i).toString().equalsIgnoreCase("T"))
            {
                TechData[techinsert]=SetupActivity.lltitle.get(i).toString();
                tech_eventdesc_array[techinsert]=SetupActivity.llone_liners.get(i).toString();
                tech_android_image_urls[techinsert]=SetupActivity.llposter_url.get(i).toString();
                Log.d("Technical data ",TechData[techinsert]);
                techinsert++;
            }
        }
        techinsert=0;

        recyclerView= (RecyclerView)view.findViewById(R.id.card_recycler_view);

        initViews();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View viewx, int position) {
                TextView event_name=(TextView)viewx.findViewById(R.id.tv_android);
                String date,venue,descrip,price,coord,coord_no,category,img_url,regurl;
                EventDetailsActivity obj=new EventDetailsActivity();
                Getsetclass myobj=new Getsetclass();

                for(int i=0; i< Attractions.eventList.length ; i++) {

                    if(event_name.getText().toString().equalsIgnoreCase(Attractions.eventList[i]))
                    {
                        date=Attractions.eventListDay[i]+" "+obj.geteventmonth(Attractions.eventListMonth[i])+" 2017";
                        venue=Attractions.eventvenue[i];
                        descrip=Attractions.descrip[i];
                        price=Attractions.eventprice[i];
                        coord=Attractions.event_coordarray[i];
                        coord_no=Attractions.event_coord_noarray[i];
                        category=Attractions.category[i];

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


                        if(descrip.contains("<br>"))
                        {
                            descrip=descrip.replace("<br>","");
                        }
                        img_url=Attractions.event_poster[i];
                        regurl=Attractions.googleform_url[i];

                        if(Integer.parseInt(Attractions.eventListMonth[i])>=current_month)
                        {
                            if(Integer.parseInt(Attractions.eventListDay[i])>=current_day)
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





                        obj.setdata(event_name.getText().toString(),date,venue,descrip,price,coord,coord_no,category,img_url,regurl,validity,"attr");
                    }
                }
                Intent in=new Intent(getActivity(),EventDetailsActivity.class);
                startActivity(in);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }



        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void initViews(){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        adapter = new DataAdapter(getActivity(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList prepareData(){

        String eventday="",eventmonth="";

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<TechData.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(TechData[i]);
            androidVersion.setAndroid_image_url(tech_android_image_urls[i]);
            androidVersion.setEventdesc(tech_eventdesc_array[i]);
            for(int x=0;x<Attractions.eventList.length;x++)
            {
                if(TechData[i].equals(Attractions.eventList[x]))
                {
                    eventday=Attractions.eventListDay[x];
                    eventmonth=Attractions.eventListMonth[x];
                    techvenue=Attractions.eventvenue[x];
                    EventDetailsActivity obj=new EventDetailsActivity();
                    String date =  eventday+ " " + obj.geteventmonth(eventmonth) + " 2017";
                    androidVersion.seteventdates(date);
                    androidVersion.setvenue(techvenue);
                }
            }
            android_version.add(androidVersion);
        }
        return android_version;
    }
}

