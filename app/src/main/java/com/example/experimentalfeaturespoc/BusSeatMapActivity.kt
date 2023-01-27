package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.experimentalfeaturespoc.databinding.ActivityBusSeatMapBinding
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

        val totalRows = 13
        val totalCols = 5
        val lowerDeckSeatsList = listOf(
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

        val lowerDeckSeatsMatrix =
            Array(totalRows) { Array<BusLayoutInfo.TotalSeatList.LowerdeckSeatNo>(totalCols) }


        busSeatMapBinding?.run {

            tvLowerDeckSeatNoTxt.text = "Volvo Ac Semi sleeper"


            //cols
            for (i in 1..13) {
                //rows
                for (j in 1..5) {
                    lowerDeckSeatsMatrix[i][j] =
                }
            }

        }
    }
}