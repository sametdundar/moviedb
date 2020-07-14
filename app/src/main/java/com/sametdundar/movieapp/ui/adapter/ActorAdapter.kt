package com.sametdundar.movieapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sametdundar.movieapp.AppSettings
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.model.ActorResponse
import com.sametdundar.movieapp.model.Cast
import com.sametdundar.movieapp.util.inflate
import com.sametdundar.movieapp.util.loadFromURL
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import kotlinx.android.synthetic.main.list_item_actor.view.*

class ActorAdapter (
    private val actorList: MutableList<Cast>
): RecyclerView.Adapter<ActorAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_actor))
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(actorList[position])
    }



    fun submitList(list: MutableList<Cast>?) {
        this.actorList.clear()
        if (list != null) {
            this.actorList.addAll(list)
            notifyDataSetChanged()
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val view = view

        fun bind(modal: Cast) {

            view.tvActorName.text = modal.name
            view.ivActor.loadFromURL(AppSettings.IMAGE_URL+modal.profile_path)
            view.tvDirector.text = modal.character

        }

    }

}