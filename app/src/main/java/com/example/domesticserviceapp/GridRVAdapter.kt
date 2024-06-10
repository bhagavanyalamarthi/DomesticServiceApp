package com.example.domesticserviceapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GridRVAdapter(private val courseList: List<GridViewModal>, private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return courseList.size
    }

    override fun getItem(position: Int): Any {
        return courseList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false)
            holder = ViewHolder()
            holder.courseNameTV = view.findViewById(R.id.idTVCourse)
            holder.courseIV = view.findViewById(R.id.idIVCourse)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val gridViewModal = courseList[position]
        holder.courseNameTV.text = gridViewModal.courseName
        holder.courseIV.setImageResource(gridViewModal.courseImg)

        return view
    }

    private class ViewHolder {
        lateinit var courseNameTV: TextView
        lateinit var courseIV: ImageView
    }
}
