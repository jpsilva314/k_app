package com.example.kaizentest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kaizentest.R
import com.example.kaizentest.extention.hide
import com.example.kaizentest.extention.show
import com.example.kaizentest.model.Sports
import kotlinx.android.synthetic.main.item_sports.view.*


class SportsAdapter() :
    RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {

    class SportsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var context: Context
    private var sports: ArrayList<Sports> = ArrayList()
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sports, parent, false)
        return SportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        eventsAdapter = EventsAdapter(context)
        holder.itemView.sport_name.text = sports[position].description
        holder.itemView.eventsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.itemView.eventsRecycler.adapter = eventsAdapter
        eventsAdapter.addNewItems(sports[position].events)
        holder.itemView.arrow.setOnClickListener {
            toggleArrow(it, sports[position].isExpanded)
            if (sports[position].isExpanded)
                holder.itemView.eventsRecycler.hide()
            else holder.itemView.eventsRecycler.show()
            sports[position].isExpanded = !sports[position].isExpanded
        }
    }

    override fun getItemCount() = sports.size

    fun addNewItems(list: ArrayList<Sports>) {
        sports.addAll(list)
        notifyDataSetChanged()
    }

    fun toggleArrow(view: View, isExpanded: Boolean): Boolean {
        return if (isExpanded) {
            view.animate().setDuration(200).rotation(180F)
            true
        } else {
            view.animate().setDuration(200).rotation(0F)
            false
        }
    }
}