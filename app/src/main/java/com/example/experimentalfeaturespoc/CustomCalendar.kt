package com.example.experimentalfeaturespoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.experimentalfeaturespoc.databinding.ActivityCustomCalendarBinding
import kotlinx.coroutines.selects.select
import java.util.Date

class CustomCalendar : AppCompatActivity() {
    private lateinit var calendarActivityBinding: ActivityCustomCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendarActivityBinding = ActivityCustomCalendarBinding.inflate(layoutInflater)
        setContentView(calendarActivityBinding.root)


    }
}