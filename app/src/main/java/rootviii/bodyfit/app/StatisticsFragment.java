package rootviii.bodyfit.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;

import rootviii.bodyfit.app.activities.MyMarkerView;

/**
 * Created by andrii on 05.04.15.
 */
public class StatisticsFragment extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart mChart;

    public static StatisticsFragment newInstance(int tabPos) {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_PARAM_TAB_POS, tabPos);
//        fragment.setArguments(args);
        return fragment;
    }

    public StatisticsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mTabPos = getArguments().getInt(ARG_PARAM_TAB_POS);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        mChart = (LineChart) view.findViewById(R.id.chart1);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);
        mChart.setHighlightIndicatorEnabled(false);

        // add data
        setData(7, 2200);

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        return view;
    }

    private void setData(int count, float range) {
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

//        for (int i = 0; i < count; i++) {
//
//            float mult = (range + 1);
//            float val = (float) (Math.random() * mult) + 3;// + (float)

//            yVals.add(new Entry(val, i));
            yVals.add(new Entry(1878, 0));
            yVals.add(new Entry(1925, 1));
            yVals.add(new Entry(1856, 2));
            yVals.add(new Entry(2003, 3));
            yVals.add(new Entry(1847, 4));
            yVals.add(new Entry(1954, 5));
            yVals.add(new Entry(1845, 6));
//        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Calories level");

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleSize(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setFillAlpha(65);
        set1.setFillColor(Color.BLACK);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        LimitLine ll1 = new LimitLine(2000f, "Upper Limit");
        ll1.setLineWidth(5f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll1.setTextSize(10f);

//            LimitLine ll2 = new LimitLine(1500f, "Lower Limit");
//            ll2.setLineWidth(4f);
//            ll2.enableDashedLine(10f, 10f, 0f);
//            ll2.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
//            ll2.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

            leftAxis.addLimitLine(ll1);
//            leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaxValue(2200f);
        leftAxis.setAxisMinValue(1400f);
        leftAxis.setStartAtZero(false);


        mChart.getAxisRight().setEnabled(false);

        // set data
        mChart.setData(data);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

//        @Override
//        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
//            Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
//        }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        //Log.i("", "low: " + mChart.getLowestVisibleXIndex() + ", high: " + mChart.getHighestVisibleXIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
