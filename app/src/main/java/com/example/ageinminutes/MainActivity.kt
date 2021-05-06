package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{view->
            clickDatePicker(view)}
    }

    fun clickDatePicker(view: View){

        //Get the current calender
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        //Open up a date picker dialog
       val dpd =  DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The year chosen is $selectedYear, month is $selectedMonth and day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"


            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            tvselectedDate.text = selectedDate

            val selectedDate_in_minutes = theDate!!.time/ 60000

            val current_date = sdf.parse(sdf.format(System.currentTimeMillis()))

            val current_date_in_minutes = current_date!!.time/60000
            val difference_in_minutes = current_date_in_minutes - selectedDate_in_minutes

            tvselectedDateInMinutes.setText(difference_in_minutes.toString())

        }, year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 860000)
        dpd.show()
    }
}