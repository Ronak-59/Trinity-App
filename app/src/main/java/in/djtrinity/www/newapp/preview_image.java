package in.djtrinity.www.newapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class preview_image extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.preview_image);
    }

    public void onBackPressed()
    {
        EventDetailsActivity.nagDialog.dismiss();
     //   Intent i=new Intent(preview_image.this, EventDetailsActivity.class)  ;
       // startActivity(i);
    }
}
