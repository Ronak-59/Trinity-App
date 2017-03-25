package in.djtrinity.www.newapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment{
 private WebView mWebview;
private ImageButton down;
    private BottomSheetBehavior mBottomSheetBehavior1;
    private ImageView triangle;
    RelativeLayout clicklayout;
    Button clickbutton;
    android.support.design.widget.CoordinatorLayout main_layout;

View bottomSheet;



    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        clicklayout=(RelativeLayout)view.findViewById(R.id.clicklayout);
        mWebview=(WebView)view.findViewById(R.id.web);
        WebSettings webs=mWebview.getSettings();
        triangle=(ImageView)view.findViewById(R.id.triangle);
         bottomSheet = view.findViewById(R.id.bottom_sheet1);
        clickbutton=(Button)view.findViewById(R.id.clickbutton);



       // sitedirect=(ImageButton)view.findViewById(R.id.sitedirect);


        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Loading...", true);

        mWebview .loadUrl("http://www.djtrinity.in/app/index/");

        webs.setJavaScriptEnabled(true); // enable javascript


        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setBuiltInZoomControls(true);


        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                pd.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();

                String webUrl = mWebview.getUrl();

            }



    });





       // mWebView.loadUrl("");

        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);



        down=(ImageButton)view.findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);

                }
                else {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }

            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_UP){

                    if(mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                    else {
                        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    }
                    return true;
                }

                return false;
            }
        });


        clickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"true ",Toast.LENGTH_LONG).show();
                if(mBottomSheetBehavior1.getState()==BottomSheetBehavior.STATE_EXPANDED)
                {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });
        clickbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_UP){

                    if(mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                    else {
                        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    }
                    return true;
                }

                return false;
            }
        });




        return view;

    }





}
