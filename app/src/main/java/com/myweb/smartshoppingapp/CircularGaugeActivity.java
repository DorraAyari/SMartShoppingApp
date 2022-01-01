package com.myweb.smartshoppingapp;


import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.CircularGauge;
import com.anychart.core.axes.Circular;
import com.anychart.core.gauge.pointers.Bar;
import com.anychart.enums.Anchor;
import com.anychart.graphics.vector.Fill;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.anychart.graphics.vector.text.VAlign;

public class CircularGaugeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_common);
     CheckBox txtcheckBox1=(CheckBox)findViewById(R.id.txtcheckBox1);
     CheckBox txtcheckBox2=(CheckBox)findViewById(R.id.txtcheckBox2);
     CheckBox txtcheckBox3=(CheckBox)findViewById(R.id.txtcheckBox3);
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);


        CircularGauge circularGauge = AnyChart.circular();
        circularGauge.data(new SingleValueDataSet(new String[] {String.valueOf(R.id.txtcheckBox1),String.valueOf(R.id.txtcheckBox2)}));
        circularGauge.fill("#fff")
                .stroke(null)
                .padding(0d, 0d, 0d, 0d)
                .margin(100d, 100d, 100d, 100d);
        circularGauge.startAngle(0d);
        circularGauge.sweepAngle(270d);

        Circular xAxis = circularGauge.axis(0)
                .radius(100d)
                .width(1d)
                .fill((Fill) null);
        xAxis.scale()
                .minimum(0d)
                .maximum(100d);
        xAxis.ticks("{ interval: 1 }")
                .minorTicks("{ interval: 1 }");
        xAxis.labels().enabled(false);
        xAxis.ticks().enabled(false);
        xAxis.minorTicks().enabled(false);

    circularGauge.label(0d)
            .text("Temazepam, <span style=\"\">32%</span>")
            .useHtml(true)
            .hAlign(HAlign.CENTER)
            .vAlign(VAlign.MIDDLE);
    circularGauge.label(0d)
            .anchor(Anchor.RIGHT_CENTER)
            .padding(0d, 10d, 0d, 0d)
            .height(17d / 2d + "%")
            .offsetY(100d + "%")
            .offsetX(0d);
    Bar bar0 = circularGauge.bar(0d);
    bar0.dataIndex(0d);
    bar0.radius(100d);
    bar0.width(17d);
    bar0.fill(new SolidFill("#64b5f6", 1d));
    bar0.stroke(null);
    bar0.zIndex(5d);
    Bar bar100 = circularGauge.bar(100d);
    bar100.dataIndex(5d);
    bar100.radius(100d);
    bar100.width(17d);
    bar100.fill(new SolidFill("#F5F4F4", 1d));
    bar100.stroke("1 #e5e4e4");
    bar100.zIndex(4d);

    circularGauge.label(1d)
            .text("Guaifenesin, <span style=\"\">34%</span>")
            .useHtml(true)
            .hAlign(HAlign.CENTER)
            .vAlign(VAlign.MIDDLE);
    circularGauge.label(1d)
            .anchor(Anchor.RIGHT_CENTER)
            .padding(0d, 10d, 0d, 0d)
            .height(17d / 2d + "%")
            .offsetY(80d + "%")
            .offsetX(0d);
    Bar bar1 = circularGauge.bar(1d);
    bar1.dataIndex(1d);
    bar1.radius(80d);
    bar1.width(17d);
    bar1.fill(new SolidFill("#1976d2", 1d));
    bar1.stroke(null);
    bar1.zIndex(5d);
    Bar bar101 = circularGauge.bar(101d);
    bar101.dataIndex(5d);
    bar101.radius(80d);
    bar101.width(17d);
    bar101.fill(new SolidFill("#F5F4F4", 1d));
    bar101.stroke("1 #e5e4e4");
    bar101.zIndex(4d);

    circularGauge.label(2d)
            .text("Salicylic Acid, <span style=\"\">67%</span>")
            .useHtml(true)
            .hAlign(HAlign.CENTER)
            .vAlign(VAlign.MIDDLE);
    circularGauge.label(2d)
            .anchor(Anchor.RIGHT_CENTER)
            .padding(0d, 10d, 0d, 0d)
            .height(17d / 2d + "%")
            .offsetY(60d + "%")
            .offsetX(0d);
    Bar bar2 = circularGauge.bar(2d);
    bar2.dataIndex(2d);
    bar2.radius(60d);
    bar2.width(17d);
    bar2.fill(new SolidFill("#ef6c00", 1d));
    bar2.stroke(null);
    bar2.zIndex(5d);
    Bar bar102 = circularGauge.bar(102d);
    bar102.dataIndex(5d);
    bar102.radius(60d);
    bar102.width(17d);
    bar102.fill(new SolidFill("#F5F4F4", 1d));
    bar102.stroke("1 #e5e4e4");
    bar102.zIndex(4d);

        anyChartView.setChart(circularGauge);
    }
}