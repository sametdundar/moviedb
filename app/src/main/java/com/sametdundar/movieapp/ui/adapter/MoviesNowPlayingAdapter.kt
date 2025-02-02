package com.sametdundar.movieapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sametdundar.movieapp.AppSettings
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.model.MovieListResultObject
import com.sametdundar.movieapp.util.inflate
import com.sametdundar.movieapp.util.loadFromURL
import kotlinx.android.synthetic.main.list_item_movie_now_playing.view.*

class MoviesNowPlayingAdapter(
    private val onClickDetailCallback: (Int) -> Unit
) : PagedListAdapter<MovieListResultObject, MoviesNowPlayingAdapter.MoviesNowPlayingViewHolder>(MovieTopRatedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesNowPlayingViewHolder {
        return MoviesNowPlayingViewHolder(parent.inflate(R.layout.list_item_movie_now_playing))
    }

    override fun onBindViewHolder(holder: MoviesNowPlayingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MoviesNowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(modal: MovieListResultObject) {
            itemView.tvMovie.text = modal.original_title
            itemView.ivPhoto.loadFromURL(AppSettings.IMAGE_URL+modal.poster_path)
            itemView.setOnClickListener {
                onClickDetailCallback.invoke(modal.id)
            }
        }
    }
}

object MovieNowPlayingDiffUtil : DiffUtil.ItemCallback<MovieListResultObject>() {
    override fun areItemsTheSame(oldItem: MovieListResultObject, newItem: MovieListResultObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieListResultObject, newItem: MovieListResultObject): Boolean {
        return oldItem == newItem
    }
}