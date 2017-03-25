package in.djtrinity.www.newapp;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Attractions extends Fragment {


    public static String eventList[]=new String[SetupActivity.lltitle.size()];
    public static String eventListDay[]=new String[SetupActivity.lleventday.size()];
    public static String eventListMonth[]=new String[SetupActivity.lleventmonth.size()];
    public static String event_coordarray[]=new String[SetupActivity.llcoord.size()];
    public static String event_coord_noarray[]=new String[SetupActivity.llcoord_no.size()];

    public static String descrip[]=new String[SetupActivity.lldescrip.size()];
    public static String eventvenue[]=new String[SetupActivity.llvenue.size()];
    public static String category[]=new String[SetupActivity.llcategory.size()];
    public static String eventprice[]=new String[SetupActivity.llprice.size()];

    public static String event_poster[]=new String[SetupActivity.llposter_url.size()];
    public static String event_one_liner[]=new String[SetupActivity.llone_liners.size()];
    public static String googleform_url[]=new String[SetupActivity.llgoogle_form.size()];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.attractions, container, false);
        MainActivity.toolbar.setElevation(0.01f);
       // Log.d("in attraction fragment",Integer.toString(eventList.length));


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

        for(int i=0;i<event_coordarray.length;i++)
        {
            event_coordarray[i]=SetupActivity.llcoord.get(i).toString();
            Log.d("attract eventlist coord",event_coordarray[i]);
        }

        for(int i=0;i<event_coord_noarray.length;i++)
        {
            event_coord_noarray[i]=SetupActivity.llcoord_no.get(i).toString();
            Log.d("attract eventlist codno",event_coord_noarray[i]);
        }

        for(int i=0;i<descrip.length;i++)
        {
            descrip[i]=SetupActivity.lldescrip.get(i).toString();
            Log.d("attract eventlist desc ",descrip[i]);
        }

        for(int i=0;i<eventvenue.length;i++)
        {
            eventvenue[i]=SetupActivity.llvenue.get(i).toString();
            Log.d("attract eventlist venue",eventvenue[i]);
        }
        for(int i=0;i<category.length;i++)
        {

            category[i]=SetupActivity.llcategory.get(i).toString();
            Log.d("attract eventlist cat ",category[i]);
        }
        for(int i=0;i<eventprice.length;i++)
        {
            eventprice[i]=SetupActivity.llprice.get(i).toString();
            Log.d("attract eventlist mrp ",eventprice[i]);
        }
        for(int i=0;i<event_poster.length;i++)
        {
            event_poster[i]=SetupActivity.llposter_url.get(i).toString();
            Log.d("attract eventlist url ",event_poster[i]);
        }
        for(int i=0;i<event_one_liner.length;i++)
        {
            event_one_liner[i]=SetupActivity.llone_liners.get(i).toString();
            Log.d("attract eventlist 1_L ",event_one_liner[i]);
        }
        for(int i=0;i<googleform_url.length;i++)
        {
            googleform_url[i]=SetupActivity.llgoogle_form.get(i).toString();
        }

       // Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       // setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Cultural"));
        tabLayout.addTab(tabLayout.newTab().setText("Technical"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}