package com.sametdundar.movieapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sametdundar.movieapp.AppSettings
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.model.TvListResultObject
import com.sametdundar.movieapp.util.inflate
import com.sametdundar.movieapp.util.loadFromURL
import kotlinx.android.synthetic.main.list_item_movie_now_playing.view.*

class TvTopRatedAdapter(
    private val onClickDetailCallback: (Int) -> Unit
) : PagedListAdapter<TvListResultObject, TvTopRatedAdapter.TvTopRatedViewHolder>(TvTopRatedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvTopRatedViewHolder {
        return TvTopRatedViewHolder(parent.inflate(R.layout.list_item_movie_now_playing))
    }

    override fun onBindViewHolder(holder: TvTopRatedViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class TvTopRatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(modal: TvListResultObject) {
            itemView.tvMovie.text = modal.original_name
            itemView.ivPhoto.loadFromURL(AppSettings.IMAGE_URL+modal.poster_path)
        }
    }
}

object TvTopRatedDiffUtil : DiffUtil.ItemCallback<TvListResultObject>() {
    override fun areItemsTheSame(oldItem: TvListResultObject, newItem: TvListResultObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvListResultObject, newItem: TvListResultObject): Boolean {
        return oldItem == newItem
    }
}