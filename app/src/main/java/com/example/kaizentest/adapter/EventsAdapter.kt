package com.example.kaizentest.adapter

import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kaizentest.R
import com.example.kaizentest.model.Events
import kotlinx.android.synthetic.main.item_event.view.*
import java.util.*


class EventsAdapter(private val context: Context) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    class EventsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var events: ArrayList<Events> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {

        timer(events[position].time, holder.itemView.date).start()
        holder.itemView.favorite.isChecked = events[position].isFavorite
        holder.itemView.favorite.setOnCheckedChangeListener { button, _ ->
            events[position].isFavorite = button.isChecked
            rearrangePositions()
        }
        holder.itemView.event_team1.text = events[position].description.split(" - ").first()
        holder.itemView.event_team2.text = events[position].description.split(" - ").last()
    }

    override fun getItemCount() = events.size

    fun addNewItems(list: ArrayList<Events>) {
        events.addAll(list)
        notifyDataSetChanged()
    }

    fun rearrangePositions(){
        val favorites = events.filter { it.isFavorite }
        val others = events.filter { !it.isFavorite}
        events.clear()
        events.addAll(favorites)
        events.addAll(others)
        val mainHandler = Handler(context.mainLooper);
        val myRunnable = Runnable { notifyDataSetChanged() }
        mainHandler.post(myRunnable)
    }

    fun getTime(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60) % 60)
        val hours = (milliseconds / (1000 * 60 * 60) % 24)
        return String.format("%d:%02d:%02d", hours, minutes, seconds)
    }

    fun timer(startTime: Long, view: TextView): CountDownTimer {
       return object : CountDownTimer(startTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                view.text = getTime(millisUntilFinished)
            }

            override fun onFinish() {
                view.text = getTime(0)
            }
        }
    }
}