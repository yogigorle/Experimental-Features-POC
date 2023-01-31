package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.INVISIBLE
import android.view.ViewGroup.MarginLayoutParams
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Space
import androidx.appcompat.app.WindowDecorActionBar.TabImpl
import androidx.core.view.isVisible
import androidx.core.view.setMargins
import com.example.experimentalfeaturespoc.databinding.ActivityBusSeatMapBinding
import com.example.experimentalfeaturespoc.databinding.BusSeatLayoutBinding
import com.example.experimentalfeaturespoc.databinding.LayoutDividerSpaceBinding
import com.example.experimentalfeaturespoc.databinding.SleeperSeatLayoutBinding
import timber.log.Timber
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class BusSeatMapActivity : AppCompatActivity() {

    private var busSeatMapBinding: ActivityBusSeatMapBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        busSeatMapBinding = ActivityBusSeatMapBinding.inflate(layoutInflater)
        setContentView(busSeatMapBinding?.root)

        setUpData()
    }

    @OptIn(ExperimentalTime::class)
    private fun setUpData() {

        val totalRows = 4
        val totalCols = 12
        val dividerRow = 2
        val lowerDeckSeaterSeatsList = listOf(
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("4", 1, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("8", 1, 2),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("12", 1, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("16", 1, 4),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("20", 1, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("24", 1, 6),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("28", 1, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("32", 1, 8),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("36", 1, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("40", 1, 10),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("44", 1, 11),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("48", 1, 12),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("53", 1, 13),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("3", 2, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("7", 2, 2),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("11", 2, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("15", 2, 4),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("19", 2, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("23", 2, 6),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("26", 2, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("31", 2, 8),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("35", 2, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("39", 2, 10),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("43", 2, 11),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("47", 2, 12),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("52", 2, 13),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("51", 3, 13),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("2", 4, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("6", 4, 2),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("10", 4, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("14", 4, 4),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("18", 4, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("22", 4, 6),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("26", 4, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("30", 4, 8),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("34", 4, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("38", 4, 10),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("42", 4, 11),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("46", 4, 12),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("50", 4, 13),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("1", 5, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("5", 5, 2),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("9", 5, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("13", 5, 4),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("17", 5, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("21", 5, 6),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("25", 5, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("29", 5, 8),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("33", 5, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("37", 5, 10),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("41", 5, 11),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("45", 5, 12),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("49", 5, 13),
        )
        val lowerDeckSleeperSeatsList = listOf(
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L1", 1, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L4", 1, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L7", 1, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L10", 1, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L13", 1, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L16", 1, 11),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L2", 3, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L5", 3, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L8", 3, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L11", 3, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L14", 3, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L17", 3, 11),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L3", 4, 1),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L6", 4, 3),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L9", 4, 5),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L12", 4, 7),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L15", 4, 9),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("L18", 4, 11),
        )


        val totalRowsSleeperCumSeater = 5
        val totalColsSleeperCumSeater = 12
        val dividerRowSleeperCumSeater = 3
        val lowerDeckSleeperCumSeaterList = listOf(
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("2", 1, 1, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("4", 1, 2, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("6", 1, 3, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("8", 1, 4, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("N", 1, 5, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("P", 1, 7, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("R", 1, 9, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("T", 1, 11, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("1", 2, 1, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("3", 2, 2, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("5", 2, 3, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("7", 2, 4, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("M", 2, 5, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("O", 2, 7, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("Q", 2, 9, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("S", 2, 11, "SS"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D2", 4, 3, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D4", 4, 5, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D6", 4, 7, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D8", 4, 9, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D10", 4, 11, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("DS1", 5, 1, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D1", 5, 3, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D3", 5, 5, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D5", 5, 7, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D7", 5, 9, "LB"),
            BusLayoutInfo.TotalSeatList.LowerdeckSeatNo("D9", 5, 11, "LB"),
        )

        busSeatMapBinding?.run {

            tvLowerDeckSeatNoTxt.text = "2+1 AC Sleeper"

            llLowerDeckSeats.removeAllViews()
            var cachedPreviousSeatType: String? = null
            val timeTaken = measureTime {

                addEmptyCellsToGrid()

//                for (i in 1..totalRowsSleeperCumSeater) {
//                    val isDividerRow =
//                        lowerDeckSleeperCumSeaterList.firstOrNull { dividerRowSleeperCumSeater == i }
//                            ?.let { true }
//                            ?: false
//
//                    val linearLayout = LinearLayout(this@BusSeatMapActivity).apply {
//                        orientation = LinearLayout.VERTICAL
//                    }
//                    linearLayout.removeAllViews()
//                    for (j in 1..totalColsSleeperCumSeater) {
////                    val isEmptySpacePresent =
////                        lowerDeckSeaterSeatsList.firstOrNull {  && it.ColNo == j }?.let { true }
////                            ?: false
//                        val seatInfo =
//                            lowerDeckSleeperCumSeaterList.firstOrNull { it.RowNo == i && it.ColNo == j }
//
//                        cachedPreviousSeatType = seatInfo?.Availability ?: cachedPreviousSeatType
//
//                        val view = if (seatInfo?.Availability == "SS") {
//                            //add
//                            val seaterSeatLayoutBinding = BusSeatLayoutBinding.inflate(
//                                layoutInflater, root, false
//                            )
//                            val layoutParams =
//                                seaterSeatLayoutBinding.rlBusSeater.layoutParams as MarginLayoutParams
//                            layoutParams.setMargins(dpToPx(8))
//                            seaterSeatLayoutBinding.rlBusSeater.layoutParams = layoutParams
//                            seaterSeatLayoutBinding.root
//                        } else if (seatInfo?.Availability == "LB") {
//                            val sleeperSeatLayoutBinding = SleeperSeatLayoutBinding.inflate(
//                                layoutInflater, root, false
//                            )
//                            val layoutParams =
//                                sleeperSeatLayoutBinding.rlBusSleeper.layoutParams as MarginLayoutParams
//                            layoutParams.setMargins(dpToPx(8))
//                            sleeperSeatLayoutBinding.rlBusSleeper.layoutParams = layoutParams
//                            sleeperSeatLayoutBinding.root
//                        } else {
////                            if(isDividerRow){
////                            if (cachedPreviousSeatType == "LB"){
////                                null
////                            }else{
//                                Timber.e("empty seat at row $i and at col $j and seat type is SS")
//                                val seaterSeatLayoutBinding = BusSeatLayoutBinding.inflate(
//                                    layoutInflater, root, false
//                                )
//                                val layoutParams =
//                                    seaterSeatLayoutBinding.rlBusSeater.layoutParams as MarginLayoutParams
//                                layoutParams.setMargins(dpToPx(8))
//                                seaterSeatLayoutBinding.rlBusSeater.layoutParams = layoutParams
//                                seaterSeatLayoutBinding.rlBusSeater.visibility = INVISIBLE
//                                seaterSeatLayoutBinding.root
////                            }
//
////                            }else{
////                                null
////                            }
//
//                        }
//
//                        view?.let { linearLayout.addView(it) }
//
////                    val sleeperSeatLayoutBinding =
////                        SleeperSeatLayoutBinding.inflate(layoutInflater, root, false)
////                    val layoutParams =
////                        sleeperSeatLayoutBinding.rlBusSleeper.layoutParams as MarginLayoutParams
////                    layoutParams.setMargins(dpToPx(8))
////                    sleeperSeatLayoutBinding.rlBusSleeper.layoutParams = layoutParams
////
////                    with(sleeperSeatLayoutBinding) {
////                        rlBusSleeper.setOnClickListener {
////                            showToast(seatNo.toString())
////                        }
////                        if (seatNo != 0) {
////                            rlBusSleeper.visibility = View.VISIBLE
////                            tvSeatNo.text = seatNo.toString()
////                        } else if (isDividerRow) {
////                            rlBusSleeper.visibility = View.INVISIBLE
////                        } else {
////                            rlBusSleeper.visibility = View.GONE
////                        }
////                        linearLayout.addView(sleeperSeatLayoutBinding.root)
////
////                    }
//
//                    }
//                    llLowerDeckSeats.addView(linearLayout)
//                }
            }

            Timber.e("time taken by linear layout is $timeTaken")
        }
    }

    private fun addEmptyCellsToGrid() {
        busSeatMapBinding?.run {
            for (c in 0 until glSeatMap.columnCount) {
                val layoutParams = GridLayout.LayoutParams()
                layoutParams.apply {
                    rowSpec = GridLayout.spec(0, 0, 1f)
                    columnSpec = GridLayout.spec(c, 1, 0.5f)
                    setGravity(Gravity.FILL)
                    width = 0
                    glSeatMap.addView(Space(this@BusSeatMapActivity), this)
                }
            }

            for (r in 0 until glSeatMap.rowCount) {
                val layoutParams = GridLayout.LayoutParams()
                layoutParams.apply {
                    rowSpec = GridLayout.spec(r, 1, 0.5f)
                    columnSpec = GridLayout.spec(0, 0, 0f)
                    setGravity(Gravity.FILL)
                    width = 0
                    glSeatMap.addView(Space(this@BusSeatMapActivity), this)
                }
            }
        }

    }

    private fun drawSeatMap(noOfRows: Int, noOfCols: Int){
         busSeatMapBinding?.run {
             glSeatMap.apply {
                 rowCount = noOfRows
                 columnCount = noOfCols
                 for(i in 0 until noOfRows){
                     for(j in 0..noOfCols) {

                     }
                 }
             }
         }
    }



}