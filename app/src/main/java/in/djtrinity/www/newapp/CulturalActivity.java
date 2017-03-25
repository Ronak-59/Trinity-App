package in.djtrinity.www.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CulturalActivity extends Fragment {


    public static String e_name;
    RecyclerView recyclerView;

    Calendar current=Calendar.getInstance();
    int current_day=current.get(Calendar.DAY_OF_MONTH);
    int current_month=current.get(Calendar.MONTH)+1;

    public boolean validity;







    public  String[] CulturalData=new String[SetupActivity.culturalcounter];
    public  String[] eventdesc_array=new String[SetupActivity.culturalcounter];
    public String[] android_image_urls=new String[SetupActivity.culturalcounter];

    public String cultvenue="";
    public TextView event_name;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("cultural counter ",Integer.toString(SetupActivity.culturalcounter));
        Log.d("cultural data size ",Integer.toString(CulturalData.length));
        int cultinsert=0;
        for(int i=0;i<SetupActivity.lltitle.size();i++)
        {
            if(SetupActivity.llcategory.get(i).toString().equalsIgnoreCase("C"))
            {
                CulturalData[cultinsert]=SetupActivity.lltitle.get(i).toString();
                eventdesc_array[cultinsert]=SetupActivity.llone_liners.get(i).toString();
                android_image_urls[cultinsert]=SetupActivity.llposter_url.get(i).toString();
                Log.d("cultural data ",CulturalData[cultinsert]);
                cultinsert++;
            }
        }
        cultinsert=0;
        super.onCreate(savedInstanceState);
        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        View view = inflater.inflate(R.layout.activity_cultural, container, false);
//        Log.d("cultural data ",CulturalData[0]);
        for(int i=0;i<CulturalData.length;i++)
        {
            Log.d("culrtural data",CulturalData[i]);
        }
        recyclerView= (RecyclerView)view.findViewById(R.id.card_recycler_view);


        initViews();
        recyclerView.addOnItemTouchListener(new TechActivity.RecyclerTouchListener(getContext(), recyclerView, new TechActivity.ClickListener() {
            @Override
            public void onClick(View viewx, int position) {

                event_name=(TextView)viewx.findViewById(R.id.tv_android);
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

                        img_url=Attractions.event_poster[i];
                        regurl=Attractions.googleform_url[i];

                        if(descrip.contains("<br>"))
                        {
                            descrip=descrip.replace("<br>","");
                        }

                        if(Integer.parseInt(Attractions.eventListMonth[i])>=current_month)
                        {
                            Log.d("eventlist month",Attractions.eventListMonth[i]);
                            Log.d("current month",Integer.toString(current_month));
                            if(Integer.parseInt(Attractions.eventListDay[i])>=current_day)
                            {
                                Log.d("eventlist month",Attractions.eventListDay[i]);
                                Log.d("current month",Integer.toString(current_day));
                                //event valid
                                validity=true;
                            }
                            else
                            {
                                Log.d("eventlist month",Attractions.eventListDay[i]);
                                Log.d("current month",Integer.toString(current_day));
                                validity=false;
                            }
                        }
                        else
                        {
                            Log.d("eventlist month",Attractions.eventListMonth[i]);
                            Log.d("current month",Integer.toString(current_month));

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
    private void initViews(){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getActivity(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList prepareData(){
        String eventday="",eventmonth="";

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<CulturalData.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(CulturalData[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            androidVersion.setEventdesc(eventdesc_array[i]);
            for(int x=0;x<Attractions.eventList.length;x++)
            {
                if(CulturalData[i].equals(Attractions.eventList[x]))
                {
                    eventday=Attractions.eventListDay[x];
                    eventmonth=Attractions.eventListMonth[x];
                    cultvenue=Attractions.eventvenue[x];
                    EventDetailsActivity obj=new EventDetailsActivity();
                    String date =  eventday+ " " + obj.geteventmonth(eventmonth) + " 2017";
                    androidVersion.seteventdates(date);
                    androidVersion.setvenue(cultvenue);
                }
            }
            android_version.add(androidVersion);
        }
        return android_version;
    }
}


