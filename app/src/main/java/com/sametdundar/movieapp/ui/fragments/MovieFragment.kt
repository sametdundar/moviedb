package com.sametdundar.movieapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.base.BaseFragment
import com.sametdundar.movieapp.model.MovieListResultObject
import com.sametdundar.movieapp.ui.adapter.MoviesNowPlayingAdapter
import com.sametdundar.movieapp.ui.adapter.MoviesPopularAdapter
import com.sametdundar.movieapp.ui.adapter.MoviesTopRatedAdapter
import com.sametdundar.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    private val moviesTopRatedAdapter by lazy {
        MoviesTopRatedAdapter {
//            navigationManager?.onReplace(
////                LotteryDetailFragment.newInstance(it), TransactionType.Replace
//            )
        }
    }

    private val moviesNowPlayingAdapter by lazy {
        MoviesNowPlayingAdapter {
            //            navigationManager?.onReplace(
////                LotteryDetailFragment.newInstance(it), TransactionType.Replace
//            )
        }
    }

    private val moviesPopularAdapter by lazy {
        MoviesPopularAdapter {
            //            navigationManager?.onReplace(
////                LotteryDetailFragment.newInstance(it), TransactionType.Replace
//            )
        }
    }

    companion object {
        fun newInstance() = MovieFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterMoviesTopRated()
        initAdapterMoviesNowPlaying()
        initAdapterMoviesPopular()
        observeData()
    }

    private fun observeData(){

        viewModel.moviesTopRated?.removeObservers(viewLifecycleOwner)
        viewModel.onFetchMovieTopRate()
        viewModel.moviesTopRated?.observe(viewLifecycleOwner, observerMoviesTopRated)

        viewModel.moviesNowPlaying?.removeObservers(viewLifecycleOwner)
        viewModel.onFetchMovieNowPlaying()
        viewModel.moviesNowPlaying?.observe(viewLifecycleOwner,observerMoviesNowPlaying)

        viewModel.moviesPopular?.removeObservers(viewLifecycleOwner)
        viewModel.onFetchMoviePopular()
        viewModel.moviesPopular?.observe(viewLifecycleOwner,observerMoviesPopular)
    }

    private val observerMoviesTopRated = Observer<PagedList<MovieListResultObject>> {
        moviesTopRatedAdapter.submitList(if (it.isEmpty()) null else it)
    }

    private val observerMoviesNowPlaying = Observer<PagedList<MovieListResultObject>> {
        moviesNowPlayingAdapter.submitList(if (it.isEmpty()) null else it)
    }

    private val observerMoviesPopular = Observer<PagedList<MovieListResultObject>> {
        moviesPopularAdapter.submitList(if (it.isEmpty()) null else it)
    }

    private fun initAdapterMoviesTopRated() {
        rvMovieTopRated.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvMovieTopRated.adapter = moviesTopRatedAdapter
    }

    private fun initAdapterMoviesNowPlaying() {
        rvNowPlaying.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvNowPlaying.adapter = moviesNowPlayingAdapter
    }

    private fun initAdapterMoviesPopular() {
        rvPopular.layoutManager = GridLayoutManager(context, 2)
        rvPopular.adapter = moviesNowPlayingAdapter
    }

}
