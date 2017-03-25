package in.djtrinity.www.newapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class IDPTFragment extends Fragment {


    /*private WebView webView;
    private ProgressDialog progressDialog;

    public IDPTFragment() {

    }*/
    public static Float points[] = new Float[7];

    //********** mysql database part**********************


    ListView list;
    //******************************************************

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idpt, container, false);

        BarChart chart = (BarChart) view.findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());

        //legend coding
        Legend l=chart.getLegend();
        l.setFormSize(10f);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f);
        l.setYEntrySpace(5f);
        //l.setCustom();

        XAxis xaxis=chart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setTextSize(7f);
        xaxis.setTextColor(Color.parseColor("#212121"));
        xaxis.setDrawAxisLine(true);
        xaxis.setDrawGridLines(false);


        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();
       // chart.zoom(100, 10, 10, 10);


        return view;


    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        Log.d("it points: ",points[0].toString());
        BarEntry v1e1 = new BarEntry(points[0], 0);// IT
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(points[1], 1); // Bio
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(points[2], 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(points[3], 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(points[4], 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(points[5], 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(points[6], 6); // Jun
        valueSet1.add(v1e7);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Department");
        barDataSet1.setColors(new int[]{R.color.cit,R.color.ccomps,R.color.cbiomed,R.color.cextc,R.color.celects,R.color.cmech,R.color.cchem},getContext());

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        //  dataSets.add(barDataSet2);<code></code>
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("I.T");
        xAxis.add("COMPS");
        xAxis.add("BIO-PROD");
        xAxis.add("EXTC");
        xAxis.add("ELEX");
        xAxis.add("MECH");
        xAxis.add("CHEM");
        return xAxis;
    }
}

