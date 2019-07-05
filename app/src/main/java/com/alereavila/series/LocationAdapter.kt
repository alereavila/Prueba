package com.alereavila.series

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
class LocationAdapter(private val context: Context, arrayList: ArrayList<LocationModel>)
    : RecyclerView.Adapter<LocationAdapter.ListItemViewHolder>() {
    internal var locationsList = ArrayList<LocationModel>()
    private val listItemLayoutInflater: LayoutInflater
    init {
        listItemLayoutInflater = LayoutInflater.from(context)
        this.locationsList = arrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = listItemLayoutInflater.inflate(R.layout.item_layout, parent, false)
        return ListItemViewHolder(view)
    }
    override fun onBindViewHolder(listItemViewHolder: ListItemViewHolder, position: Int) {
        listItemViewHolder.id.setText(locationsList[position].id)
        listItemViewHolder.name.setText(locationsList[position].name)
        listItemViewHolder.season.setText(locationsList[position].season)
        listItemViewHolder.number.setText(locationsList[position].number)
        listItemViewHolder.airtime.setText(locationsList[position].airtime)
        listItemViewHolder.airdate.setText(locationsList[position].airdate)
    }
    override fun getItemCount(): Int {
        return locationsList.size
    }
    inner class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var name: TextView
        var season: TextView
        var number: TextView
        var airtime: TextView
        var airdate: TextView
        init {
            id = itemView.findViewById(R.id.lbl_id) as TextView
            name = itemView.findViewById(R.id.lbl_name) as TextView
            season = itemView.findViewById(R.id.lbl_season) as TextView
            number = itemView.findViewById(R.id.lbl_number) as TextView
            airtime = itemView.findViewById(R.id.lbl_airtime) as TextView
            airdate = itemView.findViewById(R.id.lbl_airdate) as TextView
        }
    }
}