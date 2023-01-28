package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.core.view.setMargins
import com.example.experimentalfeaturespoc.databinding.ActivityBusSeatMapBinding
import com.example.experimentalfeaturespoc.databinding.BusSeatLayoutBinding
import com.example.experimentalfeaturespoc.databinding.SleeperSeatLayoutBinding
import timber.log.Timber

class BusSeatMapActivity : AppCompatActivity() {

    private var busSeatMapBinding: ActivityBusSeatMapBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        busSeatMapBinding = ActivityBusSeatMapBinding.inflate(layoutInflater)
        setContentView(busSeatMapBinding?.root)

        setUpData()
    }

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


        busSeatMapBinding?.run {

            tvLowerDeckSeatNoTxt.text = "2+1 AC Sleeper"

            llLowerDeckSeats.removeAllViews()

            for (i in 1..totalRows) {
                val isDividerRow =
                    lowerDeckSleeperSeatsList.firstOrNull { dividerRow == i }?.let { true }
                        ?: false
                val linearLayout = LinearLayout(this@BusSeatMapActivity).apply {
                    orientation = LinearLayout.VERTICAL
                }
                linearLayout.removeAllViews()
                for (j in 1..totalCols) {
//                    val isEmptySpacePresent =
//                        lowerDeckSeaterSeatsList.firstOrNull {  && it.ColNo == j }?.let { true }
//                            ?: false
                    val seatNo =
                        lowerDeckSleeperSeatsList.firstOrNull { it.RowNo == i && it.ColNo == j }?.SeatNo
                            ?: 0

                    val sleeperSeatLayoutBinding =
                        SleeperSeatLayoutBinding.inflate(layoutInflater, root, false)
                    val layoutParams =
                        sleeperSeatLayoutBinding.rlBusSleeper.layoutParams as MarginLayoutParams
                    layoutParams.setMargins(dpToPx(8))
                    sleeperSeatLayoutBinding.rlBusSleeper.layoutParams = layoutParams

                    with(sleeperSeatLayoutBinding) {
                        rlBusSleeper.setOnClickListener {
                            showToast(seatNo.toString())
                        }
                        if (seatNo != 0) {
                            rlBusSleeper.visibility = View.VISIBLE
                            tvSeatNo.text = seatNo.toString()
                        } else if (isDividerRow) {
                            rlBusSleeper.visibility = View.INVISIBLE
                        } else {
                            rlBusSleeper.visibility = View.GONE
                        }
                        linearLayout.addView(sleeperSeatLayoutBinding.root)

                    }

                }
                llLowerDeckSeats.addView(linearLayout)
            }
        }
    }

}