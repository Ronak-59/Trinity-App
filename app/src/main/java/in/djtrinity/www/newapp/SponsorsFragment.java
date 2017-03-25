package in.djtrinity.www.newapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SponsorsFragment extends Fragment {

    public static String sponsorname[]=new String[SetupActivity.llsponsorname.size()];
    public static String sponsorposter[]=new String[SetupActivity.llsponsorname.size()];
    public static String sponsorurl[]=new String[SetupActivity.llsponsorname.size()];

    RecyclerView recyclerView;
    public SponsorsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        for(int i=0;i<sponsorname.length;i++)
        {
            sponsorname[i]=SetupActivity.llsponsorname.get(i).toString();
            sponsorposter[i]=SetupActivity.llsponsorposter.get(i).toString();
            sponsorurl[i]=SetupActivity.llsponsorurl.get(i).toString();
            Log.d("sponsor name",sponsorname[i]);
        }
        recyclerView=(RecyclerView)view.findViewById(R.id.sponsor_recycler_view);

        initViews();

        recyclerView.addOnItemTouchListener(new TechActivity.RecyclerTouchListener(getContext(), recyclerView, new TechActivity.ClickListener() {
            @Override
            public void onClick(View viewx, int position) {
                TextView text=(TextView)viewx.findViewById(R.id.sponsor_name);
                for(int i=0;i<sponsorname.length;i++)
                {
                    if(text.getText().toString().equalsIgnoreCase(sponsorname[i]))
                    {
                        Uri uri = Uri.parse(sponsorurl[i]);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }


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
        Sponsoradapter adapter = new Sponsoradapter(getActivity(),androidVersions);
        recyclerView.setAdapter(adapter);
    }
    private ArrayList prepareData(){

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<sponsorname.length;i++){
            Sponsorgettersetter androidVersion = new Sponsorgettersetter();
            androidVersion.setSponsorname(sponsorname[i]);
            androidVersion.setSponsorposter(sponsorposter[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }


}
