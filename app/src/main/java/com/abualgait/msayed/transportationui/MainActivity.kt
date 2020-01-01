package com.abualgait.msayed.transportationui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val count = 12
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpChart()
    }

    private fun setUpChart() {

        chart.setDrawGridBackground(false)

        // no description text
        chart.description.isEnabled = false

        // enable touch gestures
        chart.setTouchEnabled(true)

        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true)

        // set an alternative background color
        // chart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it

        val xl = chart.xAxis
        xl.setAvoidFirstLastClipping(true)
        xl.axisMinimum = 0f

        val leftAxis = chart.axisLeft
        leftAxis.isEnabled = false


        val rightAxis = chart.axisRight
        rightAxis.isEnabled = false



        // get the legend (only possible after setting data)
        val l = chart.legend

        // modify the legend ...
        l.form = LegendForm.LINE



        chart.data = generateLineData()
        // don't forget to refresh the drawing
        chart.invalidate()

    }

    private fun generateLineData(): LineData {

        val d = LineData()

        val entries = mutableListOf<Entry>()

        for (index in 0 until count)
            entries.add(Entry(index + 0.5f, getRandomDoubleBetweenRange(15.0, 5.0).toFloat()))

        val set = LineDataSet(entries, "Line DataSet")
        set.color = Color.rgb(240, 238, 70)
        set.lineWidth = 2.5f
        set.setCircleColor(Color.rgb(240, 238, 70))
        set.circleRadius = 5f
        set.fillColor = Color.rgb(240, 238, 70)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 10f
        set.valueTextColor = Color.rgb(240, 238, 70)

        set.axisDependency = YAxis.AxisDependency.LEFT
        d.addDataSet(set)

        return d
    }


    fun getRandomDoubleBetweenRange(min: Double, max: Double): Double {
        return Math.random() * (max - min + 1) + min
    }
}
