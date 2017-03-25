package in.djtrinity.www.newapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import static in.djtrinity.www.trinity.SetupActivity.llcoord;
//import static in.djtrinity.www.trinity.SetupActivity.llcoord_no;
//import static in.djtrinity.www.trinity.SetupActivity.lleventmonth;
//import static in.djtrinity.www.trinity.SetupActivity.lltitle;
//import static in.djtrinity.www.trinity.SetupActivity.llvenue;

//import static in.djtrinity.www.trinity.MainActivity.events;

public class ScheduleFragment extends Fragment {
    MoviesAdapter mAdapter;
    private List<Movie> movieList = new ArrayList<>();
    String eventday, eventmonth, datestr, event_type = "default";
    public static Date[] dates = new Date[55];
    EventDetailsActivity obj = new EventDetailsActivity();
    String str="";
    TextView tv;



    RecyclerView recyclerView;
    public  String eventList[]=new String[SetupActivity.lltitle.size()];
    public  String eventListDay[]=new String[SetupActivity.lleventday.size()];
    public  String eventListMonth[]=new String[SetupActivity.lleventmonth.size()];
    public  String event_coordarray[]=new String[SetupActivity.llcoord.size()];
    public  String event_coord_noarray[]=new String[SetupActivity.llcoord_no.size()];

    public  String descript[]=new String[SetupActivity.lldescrip.size()];
    public  String eventvenue[]=new String[SetupActivity.llvenue.size()];
    public  String categoryarray[]=new String[SetupActivity.llcategory.size()];
    public  String eventprice[]=new String[SetupActivity.llprice.size()];

    public  String event_poster[]=new String[SetupActivity.llposter_url.size()];
    public  String event_one_liner[]=new String[SetupActivity.llone_liners.size()];
    public  String googleform_url[]=new String[SetupActivity.llgoogle_form.size()];

    public String events[]=new String[SetupActivity.lltitle.size()];

    Calendar current=Calendar.getInstance();
    int current_day=current.get(Calendar.DAY_OF_MONTH);
    int current_month=current.get(Calendar.MONTH);

    public boolean validity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        Log.d("link size",Integer.toString(SetupActivity.lltitle.size()));
        for(int i=0;i<SetupActivity.lltitle.size();i++)
        {
            events[i]=SetupActivity.lltitle.get(i).toString();
            eventList[i]=SetupActivity.lltitle.get(i).toString();
            Log.d("sch frag eventlist ",events[i]);


            eventList[i]=SetupActivity.lltitle.get(i).toString();
            Log.d("sch frag eventlist ",eventList[i]);


            eventListDay[i]=SetupActivity.lleventday.get(i).toString();
            Log.d("sch eventlistday ",eventListDay[i]);


            eventListMonth[i]=SetupActivity.lleventmonth.get(i).toString();
            Log.d("sch eventlistmonth ",eventListMonth[i]);


            event_coordarray[i]=SetupActivity.llcoord.get(i).toString();
            Log.d("sch eventlist coord",event_coordarray[i]);


            event_coord_noarray[i]=SetupActivity.llcoord_no.get(i).toString();
            Log.d("sch eventlist codno",event_coord_noarray[i]);


            descript[i]=SetupActivity.lldescrip.get(i).toString();
            Log.d("sch eventlist desc ",descript[i]);


            eventvenue[i]=SetupActivity.llvenue.get(i).toString();
            Log.d("sch eventlist venue",eventvenue[i]);



            categoryarray[i]=SetupActivity.llcategory.get(i).toString();
            Log.d("sch eventlist cat ",categoryarray[i]);

            eventprice[i]=SetupActivity.llprice.get(i).toString();
            Log.d("sch eventlist mrp ",eventprice[i]);

            event_poster[i]=SetupActivity.llposter_url.get(i).toString();
            Log.d("sch eventlist url ",event_poster[i]);


            event_one_liner[i]=SetupActivity.llone_liners.get(i).toString();
            Log.d("sch eventlist 1_L ",event_one_liner[i]);

            googleform_url[i]=SetupActivity.llgoogle_form.get(i).toString();
            Log.d("sch regurl  ",googleform_url[i]);
        }


        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);


        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));


        prepareMovieData();
        recyclerView.addOnItemTouchListener(new TechActivity.RecyclerTouchListener(getContext(), recyclerView, new TechActivity.ClickListener() {
            @Override
            public void onClick(View viewx, int position) {

                TextView event_name = (TextView) viewx.findViewById(R.id.title);
                String date, venue, descrip, price, coord, coord_no, category, img_url, regurl;
                EventDetailsActivity obj = new EventDetailsActivity();
                Getsetclass myobj = new Getsetclass();

                for (int i = 0; i < eventList.length; i++) {

                    if (event_name.getText().toString().equalsIgnoreCase(eventList[i])) {
                        date = eventListDay[i] + " " + geteventmonth(eventListMonth[i]) + " 2017";
                        venue = eventvenue[i];
                        descrip = descript[i];
                        price = eventprice[i];
                        coord = event_coordarray[i];
                        coord_no = event_coord_noarray[i];
                        category = categoryarray[i];

                        myobj.setdate(date);
                        myobj.setvenue(venue);
                        myobj.setdescrip(descrip);
                        myobj.setprice(price);
                        myobj.setcoord(coord);
                        myobj.setcoordno(coord_no);

                        Log.d("setter mthod", date);
                        if (category.equalsIgnoreCase("T")) {
                            category = "Technical";
                        }
                        if (category.equalsIgnoreCase("C")) {
                            category = "Cultural";
                        }
                        if (category.equalsIgnoreCase("S")) {
                            category = "Sports";
                        }


                        if (descrip.contains("<br>")) {
                            descrip = descrip.replace("<br>", "");
                        }
                        img_url = event_poster[i];
                        regurl = googleform_url[i];
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


                        obj.setdata(event_name.getText().toString(), date, venue, descrip, price, coord, coord_no, category, img_url, regurl,validity,"sch");
                    }
                }
                Intent in = new Intent(getActivity(), EventDetailsActivity.class);
                startActivity(in);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

    private void prepareMovieData() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < eventList.length; i++) {
            String startDateString = eventListMonth[i] + "/" + eventListDay[i] + "/" + "2017";
            Log.d("startdatestring ", startDateString);
            try {
                Date startDate = df.parse(startDateString);
                dates[i] = startDate;
                Log.d("startdate", startDate.toString());
                Log.d("dates", dates[i].toString());
            } catch (Exception e) {
                System.err.println(e);
            }

        }
        Date temp;
        String temp_event;
        for (int i = 0; i < eventList.length; i++) {
            for (int j = i + 1; j < eventList.length; j++) {
                if (dates[i].before(dates[j])) {
                    temp = dates[i];
                    dates[i] = dates[j];
                    dates[j] = temp;

                    temp_event = events[i];
                    events[i] = events[j];
                    events[j] = temp_event;

                }
            }
        }
        for (int j = 0; j < events.length; j++){
            Log.d("events check",events[j]);
        }

        String type;
        for (int i = 0; i < events.length; i++) {
            for (int j = 0; j < eventList.length; j++) {
                if (events[i].equalsIgnoreCase(eventList[j])) {
                    eventday = eventListDay[j];
                    eventmonth = eventListMonth[j];
                    type=categoryarray[j];
                    if(type.equalsIgnoreCase("T"))
                    {
                        event_type="Technical";
                    }
                    if(type.equalsIgnoreCase("C"))
                    {
                        event_type="Cultural";
                    }
                    if(type.equalsIgnoreCase("S"))
                    {
                        event_type="Sports";
                    }

                }
            }
            datestr = eventday + " " + geteventmonth(eventmonth) + " 2017";

            Movie movie = new Movie(events[i], event_type, datestr);
            movieList.add(movie);
        }


        mAdapter.notifyDataSetChanged();
    }
    public static String geteventmonth(String eventmonth)
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
}