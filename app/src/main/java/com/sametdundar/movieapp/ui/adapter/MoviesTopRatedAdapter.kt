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
import kotlinx.android.synthetic.main.list_item_movie_top_rated.view.*

class MoviesTopRatedAdapter(
    private val onClickDetailCallback: (Int) -> Unit
) : PagedListAdapter<MovieListResultObject, MoviesTopRatedAdapter.MoviesTopRatedViewHolder>(MovieTopRatedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesTopRatedViewHolder {
        return MoviesTopRatedViewHolder(parent.inflate(R.layout.list_item_movie_top_rated))
    }

    override fun onBindViewHolder(holder: MoviesTopRatedViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MoviesTopRatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(modal: MovieListResultObject) {
            itemView.ivTopRated.loadFromURL(AppSettings.IMAGE_URL+modal.backdrop_path)
//            itemView.tvMovie.text = modal.title
//            itemView.ivPhoto.loadFromURL(AppSettings.IMAGE_URL+modal.poster_path)
        }
    }
}

object MovieTopRatedDiffUtil : DiffUtil.ItemCallback<MovieListResultObject>() {
    override fun areItemsTheSame(oldItem: MovieListResultObject, newItem: MovieListResultObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieListResultObject, newItem: MovieListResultObject): Boolean {
        return oldItem == newItem
    }
}