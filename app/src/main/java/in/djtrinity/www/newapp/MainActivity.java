package in.djtrinity.www.newapp;

import android.app.AlarmManager;
import android.content.pm.ApplicationInfo;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import static in.djtrinity.www.trinity.IDPTFragment.points;
//import static in.djtrinity.www.trinity.SetupActivity.llcoord;
//import static in.djtrinity.www.trinity.SetupActivity.llcoord_no;
//import static in.djtrinity.www.trinity.SetupActivity.lleventmonth;
//import static in.djtrinity.www.trinity.SetupActivity.llvenue;

public class MainActivity extends AppCompatActivity implements NavDrawerAdapter.ClickListener {

    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        Log.d("AndroidBash", "Subscribed");
        //Toast.makeText(MainActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();

        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        Log.d("AndroidBash", token);
        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
    }
    public static boolean backtrace=false;
    public static boolean backtracesnap=false;
    public static boolean backtraceqr=false;
    public static boolean backtracesch=false;

    public static String[] elementName = {
            "Home",
            "Attractions",
            "IDPT",
            "Schedule",
            "Gallery",
            "The Team",
            "About",
            "Sponsors",
            "Scan a Code",
    };

    public static Integer iconIds[] = {
            R.drawable.ic_home_black_18dp,
            R.drawable.ic_stars_black_18dp,
            R.drawable.ic_trophy_outline_black_18dp,
            R.drawable.ic_calendar,
            R.drawable.ic_insert_photo_black_18dp,
            R.drawable.ic_people_black_18dp,
            R.drawable.ic_info_outline_black_18dp,
            R.drawable.ic_account_balance_black_18dp,
            R.drawable.ic_qrcode_scan_black_18dp,
            R.drawable.ic_qrcode_scan_black_18dp,
            R.drawable.ic_library_books_black_18dp
    };

    public static boolean scheduleReturnFlag = false;
    public static boolean attractionsReturnFlag = false;
    public static boolean backPressed = false;

    public RecyclerView recyclerView;

    public static int selected_item_number;
    private DrawerLayout drawerLayout;
    public static Toolbar toolbar;
    String myJSON;


    private GoogleApiClient client;
    ImageView sitedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main);

        subscribeToPushService();
        Log.d("in main activity ", "true");
        //Log.d("event array length: ",Integer.toString(eventList.length));
        //Log.d("event array length: ",Integer.toString(mThumbsIds.length));

        sitedirect=(ImageView)findViewById(R.id.sitedirect);








        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        //toolbar.setBackground("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));


        NavDrawerFragment navDrawerFragment = (NavDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_drawer_fragment);
        navDrawerFragment.setup(R.id.nav_drawer_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.nav_drawer_recycler_view);
        NavDrawerAdapter navDrawerAdapter = new NavDrawerAdapter(this, getData());
        navDrawerAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(navDrawerAdapter);

        scheduleNotifAlarm();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_for_fragments, new HomeFragment()).commit();

        // TextView jsontextview = (TextView) findViewById(R.id.jsontextview);
        //load from php-mysql database

        //new BackgroundTask().execute();
        //databse part ended
        // jsontextview.setText("kj");
        //checkEventsForNotif();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        if(backtrace)
        {
            toolbar.setTitle("Attractions");
            // toolbar.setElevation(0.0f);
            toolbar.setBackgroundColor(getResources().getColor(R.color.mydarkcolor));
            sitedirect.setVisibility(View.GONE);

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new Attractions()).commit();
            //Intent intent = new Intent(MainActivity.this, Attractions.class);
            //startActivity(intent);
            selected_item_number = 1;
        }
        if(backtracesnap)
        {
            toolbar.setTitle("About");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            sitedirect.setVisibility(View.GONE);



            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new About()).commit();

            selected_item_number = 6;
        }
        if(backtraceqr)
        {
            toolbar.setTitle("Scan for a Hint");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            sitedirect.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new HintFragment()
            ).commit();
            selected_item_number = 8;
        }
        if(backtracesch)
        {
            toolbar.setTitle("Schedule");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            sitedirect.setVisibility(View.GONE);

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new ScheduleFragment()).commit();

            selected_item_number = 3;
        }
    }
    public void setmainactivityvalues()
    {
        Log.d("in main accctivity ","true");

    }

    public static List<NavDrawerElements> getData() {
        List<NavDrawerElements> data = new ArrayList<>();
        for (int i = 0; i < elementName.length; i++) {
            NavDrawerElements current = new NavDrawerElements();
            current.elementName = elementName[i];
            current.iconId = iconIds[i];
            data.add(current);
        }
        return data;
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            if (selected_item_number != 0) {
                toolbar.setTitle("");
                toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                        .replace(R.id.frame_for_fragments, new HomeFragment()).commit();
                sitedirect.setVisibility(View.VISIBLE);

                selected_item_number = 0;
                NavDrawerAdapter navDrawerAdapter = new NavDrawerAdapter(this, getData());
                navDrawerAdapter.setClickListener(this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(navDrawerAdapter);

                backPressed = true;
            } else {
                //startActivity(new Intent(this,SetupActivity.class));
                SetupActivity obj=new SetupActivity();
                obj.reset();
                super.onBackPressed();

            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                toolbar.setTitle("");
                toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                sitedirect.setVisibility(View.VISIBLE);

                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new HomeFragment()).commit();

                selected_item_number = 0;
                break;
            case 1:
                toolbar.setTitle("Attractions");
               // toolbar.setElevation(0.0f);
                toolbar.setBackgroundColor(getResources().getColor(R.color.mydarkcolor));
                sitedirect.setVisibility(View.GONE);

                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new Attractions()).commit();
                //Intent intent = new Intent(MainActivity.this, Attractions.class);
                //startActivity(intent);
                selected_item_number = 1;
                break;
            case 2:
                toolbar.setTitle("IDPT");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new IDPTFragment()).commit();

                selected_item_number = 2;
                break;
            case 3:
                toolbar.setTitle("Schedule");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);

                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new ScheduleFragment()).commit();

                selected_item_number = 3;
                break;
            case 4:
                toolbar.setTitle("Gallery");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);

                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new GalleryFragment()).commit();
                selected_item_number = 4;
                break;
            case 5:
                toolbar.setTitle("The Team");
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new TeamFragment()).commit();
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);


                selected_item_number = 5;
                break;
            case 6:
                toolbar.setTitle("About");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);



                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new About()).commit();

                selected_item_number = 6;
                break;
            case 7:
                toolbar.setTitle("Sponsors");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);

                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new SponsorsFragment()).commit();

                selected_item_number = 7;
                break;
            case 8:

                toolbar.setTitle("Scan for a Hint");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sitedirect.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame_for_fragments, new HintFragment()
                ).commit();
                selected_item_number = 8;
                break;
          // case 9:



        }
        drawerLayout.closeDrawers();
    }

    public void openInstagram(View view) {
        Uri uri = Uri.parse("https://instagram.com/_u/trinity.djsce/");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://instagram.com/trinity.djsce/")));
        }
    }
    public static boolean facebook_not_found=false;

    public void openYoutube(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/user/djscetrinity"));
        startActivity(intent);
    }

    public void openFacebook(View view) {
        Intent i=newFacebookIntent(getPackageManager(), "https://www.facebook.com/djscetrinity/");
        startActivity(i);

        if(facebook_not_found==true) {
            Uri uri = Uri.parse("https://www.facebook.com/djscetrinity/");
            Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
            facebook.setPackage("com.facebook.android");

            if (isIntentAvailable(this, facebook)) {
                startActivity(facebook);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/djscetrinity/")));
            }
        }
    }
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href="+url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            facebook_not_found=true;
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
    public void openTwitter(View view) {
        Uri uri = Uri
                .parse("https://twitter.com/djscetrinity?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor");
        Intent twitter = new Intent(Intent.ACTION_VIEW, uri);
        twitter.setPackage("com.twitter.android");

        if (isIntentAvailable(this, twitter)) {
            startActivity(twitter);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/djscetrinity?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor")));
        }
    }
    public static Intent newTwitterFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.twitter.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href="+url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            facebook_not_found=true;
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public void openTrinity(View view) {
        Uri uri = Uri.parse("http://www.djtrinity.in/home.php");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void culture(View view) {
        Intent i = new Intent(getApplicationContext(), CulturalActivity.class);
        startActivity(i);
    }

    public void tech(View view) {
        Intent i = new Intent(getApplicationContext(), TechActivity.class);
        startActivity(i);
    }

    public void sport(View view) {
        Intent i = new Intent(getApplicationContext(), SportsActivity.class);
        startActivity(i);
    }

    public void navigateToDJ(View view) {
        Uri uri = Uri.parse("google.navigation:q=19.107556, 72.837472");
        Intent navi = new Intent(Intent.ACTION_VIEW, uri);
        navi.setPackage("com.google.android.apps.maps");
        startActivity(navi);
    }

    private boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    public void openSnap(View view) {
        Intent intent = new Intent(this, SnapActivity.class);
        startActivity(intent);
    }

    public void scheduleNotifAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, NotifAlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE,39);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
        Log.d("mainactivity","true");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());
        calendar1.set(Calendar.HOUR_OF_DAY, 12);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    }
    public void closesheetaa(View view)
    {
        if(About.mBottomSheetBehavior1.getState()== BottomSheetBehavior.STATE_EXPANDED)
        {
            About.mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://in.djtrinity.www.newapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://in.djtrinity.www.newapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}