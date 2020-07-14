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
import com.sametdundar.movieapp.model.TvListResultObject
import com.sametdundar.movieapp.ui.adapter.MoviesNowPlayingAdapter
import com.sametdundar.movieapp.ui.adapter.TvPopularAdapter
import com.sametdundar.movieapp.ui.adapter.TvTopRatedAdapter
import com.sametdundar.movieapp.viewmodel.MovieViewModel
import com.sametdundar.movieapp.viewmodel.TvViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.rvPopular
import kotlinx.android.synthetic.main.fragment_tv.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TvViewModel by viewModels {
        viewModelFactory
    }


    companion object {
        fun newInstance() = TvFragment()
    }

    private val tvTopRatedAdapter by lazy {
        TvTopRatedAdapter {
            //            navigationManager?.onReplace(
////                LotteryDetailFragment.newInstance(it), TransactionType.Replace
//            )
        }
    }

    private val tvPopularAdapter by lazy {
        TvPopularAdapter {
            //            navigationManager?.onReplace(
////                LotteryDetailFragment.newInstance(it), TransactionType.Replace
//            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterTvTopRated()
        initAdapterMoviesNowPlaying()
        observeData()
    }

    private fun observeData() {

        viewModel.tvTopRated?.removeObservers(viewLifecycleOwner)
        viewModel.onFetchTvTopRate()
        viewModel.tvTopRated?.observe(viewLifecycleOwner, observerTvTopRated)

        viewModel.tvPopular?.removeObservers(viewLifecycleOwner)
        viewModel.onFetchTvPopular()
        viewModel.tvPopular?.observe(viewLifecycleOwner, observerTvPopular)

    }

    private val observerTvTopRated = Observer<PagedList<TvListResultObject>> {
        tvTopRatedAdapter.submitList(if (it.isEmpty()) null else it)
    }

    private val observerTvPopular = Observer<PagedList<TvListResultObject>> {
        tvPopularAdapter.submitList(if (it.isEmpty()) null else it)
    }

    private fun initAdapterTvTopRated() {
        rvTopRated.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvTopRated.adapter = tvTopRatedAdapter
    }

    private fun initAdapterMoviesNowPlaying() {
        rvPopular.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvPopular.adapter = tvPopularAdapter
    }


}
