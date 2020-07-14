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
import kotlinx.android.synthetic.main.list_item_tv_popular.view.*

class TvPopularAdapter(
    private val onClickDetailCallback: (Int) -> Unit
) : PagedListAdapter<TvListResultObject, TvPopularAdapter.TvPopularViewHolder>(TvPopularDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvPopularViewHolder {
        return TvPopularViewHolder(parent.inflate(R.layout.list_item_tv_popular))
    }

    override fun onBindViewHolder(holder: TvPopularViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class TvPopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(modal: TvListResultObject) {
            itemView.ivPhoto.loadFromURL(AppSettings.IMAGE_URL+modal.backdrop_path)
            itemView.tvMovie.text = modal.original_name
            itemView.tvRate.text = modal.vote_average.toString()
        }
    }
}

object TvPopularDiffUtil : DiffUtil.ItemCallback<TvListResultObject>() {
    override fun areItemsTheSame(oldItem: TvListResultObject, newItem: TvListResultObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvListResultObject, newItem: TvListResultObject): Boolean {
        return oldItem == newItem
    }
}