package com.rickrip.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val btnDatePicker: Button = findViewById<Button>(R.id.btnDatePicker)
        //val tvSelectedDate: TextView = findViewById<TextView>(R.id.tvSelectedDate)
        //val tvSelectedDateInMinutes: TextView = findViewById<TextView>(R.id.tvSelectedDateInMinutes)



        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            //Toast.makeText(this,"Button works",Toast.LENGTH_SHORT).show()
        }


    }// end of main fun

    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth -> Toast.makeText(this,
            "The chosen year is $selectedYear, the month is ${selectedMonth+1} and the day is $selectedDayOfMonth",
            Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            val selectedDateInDays = theDate.time / 86400000
            val currentDateInDays = currentDate.time / 86400000
            val differenceInDays = currentDateInDays - selectedDateInDays
            val selectedDateInYears = theDate.time / 31536000000
            val currentDateInYears = currentDate.time / 31536000000
            val differenceInYears = currentDateInYears - selectedDateInYears
            tvSelectedDateInYears.text = differenceInYears.toString()
            tvSelectedDateInDays.text = differenceInDays.toString()
            tvSelectedDateInMinutes.text = differenceInMinutes.toString()
            //tvSelectedDateInMinutes.text = getString(R.string.error1)

        },year,month,day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }

}