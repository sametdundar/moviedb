package com.sametdundar.movieapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sametdundar.movieapp.AppSettings

import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.api.Status
import com.sametdundar.movieapp.base.BaseFragment
import com.sametdundar.movieapp.model.Type
import com.sametdundar.movieapp.util.loadFromURL
import com.sametdundar.movieapp.viewmodel.MovieViewModel
import com.sametdundar.movieapp.viewmodel.TvViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieAndTvDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModelMovie: MovieViewModel by activityViewModels {
        viewModelFactory
    }

    private val viewModelTv: TvViewModel by activityViewModels {
        viewModelFactory
    }

    private var movieId: Int = 0
    private var types: Type = Type.movie

    companion object {
        const val ID = "id"
        fun newInstance(id: Int, type: Type) = MovieAndTvDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ID, id)
                types = type
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        movieId = arguments?.getInt(ID) ?: 0
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (types == Type.movie) {
            viewModelMovie.onFetchMovieDetail(movieId)
            viewModelMovie.movieDetail().observe(viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    dispatchLoading()
                    ivHuge.loadFromURL(AppSettings.IMAGE_URL + it.data?.backdrop_path)
                    ivPoster.loadFromURL(AppSettings.IMAGE_URL + it.data?.poster_path)

                } else if (it.status == Status.ERROR) {
                    dispatchLoading()
                } else if (it.status == Status.LOADING) {
                    showLoading()

                }
            })
        } else if (types == Type.tv) {
            viewModelTv.onFetchTvDetail(movieId)
            viewModelTv.tvDetail().observe(viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    dispatchLoading()
                    ivHuge.loadFromURL(AppSettings.IMAGE_URL + it.data?.backdrop_path)
                    ivPoster.loadFromURL(AppSettings.IMAGE_URL + it.data?.poster_path)

                } else if (it.status == Status.ERROR) {
                    dispatchLoading()
                } else if (it.status == Status.LOADING) {
                    showLoading()

                }
            })

        }


    }

}
