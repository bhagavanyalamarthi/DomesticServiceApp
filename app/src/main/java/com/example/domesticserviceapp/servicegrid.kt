package com.example.domesticserviceapp

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class servicegrid : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicegrid)

        // Initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList()

        // Adding data to the course list with image and course name.
        courseList = courseList + GridViewModal("plumber", R.drawable.plumber)
        courseList = courseList + GridViewModal("Cook", R.drawable.cooking)
        courseList = courseList + GridViewModal("Cleaning", R.drawable.cleaning_service)
        courseList = courseList + GridViewModal("Electrician", R.drawable.electrician)
        courseList = courseList + GridViewModal("Ac cleaning", R.drawable.ac_repairing)
        courseList = courseList + GridViewModal("Maid", R.drawable.maid)
        courseList = courseList + GridViewModal("Painter", R.drawable.painter)
        courseList = courseList + GridViewModal("Refridgerator repair", R.drawable.refridgerator_repair)
        courseList = courseList + GridViewModal("carpenter", R.drawable.carpenter)
        courseList = courseList + GridViewModal("saloonr", R.drawable.saloon)


        // Initializing the course adapter and passing course list and context.
        val courseAdapter = GridRVAdapter(courseList, this)

        // Setting the adapter to the grid view.
        courseGRV.adapter = courseAdapter

        // Adding on item click listener for the grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Displaying a toast message with course name.
            Toast.makeText(applicationContext, "${courseList[position].courseName} selected", Toast.LENGTH_SHORT).show()
        }
    }
}
