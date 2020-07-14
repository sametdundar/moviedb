package com.sametdundar.movieapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sametdundar.movieapp.AppSettings
import com.sametdundar.movieapp.MainActivity

import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.api.Status
import com.sametdundar.movieapp.base.BaseFragment
import com.sametdundar.movieapp.base.fragment_ops.TransactionType
import com.sametdundar.movieapp.model.Cast
import com.sametdundar.movieapp.model.Type
import com.sametdundar.movieapp.ui.adapter.ActorAdapter
import com.sametdundar.movieapp.ui.adapter.MoviesNowPlayingAdapter
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

    private val actorAdapter by lazy {
         ActorAdapter(mutableListOf())
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
        initAdapterActor()
        observeData()
        tvBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        ivShare.setOnClickListener {
            share()
        }
        if (types == Type.movie) {
            viewModelMovie.onFetchMovieDetail(movieId)
            viewModelMovie.movieDetail().observe(viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    dispatchLoading()
                    ivHuge.loadFromURL(AppSettings.IMAGE_URL + it.data?.backdrop_path)
                    ivPoster.loadFromURL(AppSettings.IMAGE_URL + it.data?.poster_path)
                    tvTitle.text = it.data?.original_title
                    tvDetail.text = it.data?.overview
                    tvPopularity.text = it.data?.popularity.toString() + " People watching"
                    var genres = ""
                    it.data?.genres?.forEach { genres = genres + " " + it.name }
                    tvGenres.text = genres
                    tvRate.text = it.data?.vote_average.toString()
                    var star= it.data?.vote_average?.div(2)?.toInt()

                    when(star){
                        1->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                        }
                        2->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                        }
                        3->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                            ivStar3.setImageResource(R.drawable.ic_icstarselected)
                        }
                        4->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                            ivStar3.setImageResource(R.drawable.ic_icstarselected)
                            ivStar4.setImageResource(R.drawable.ic_icstarselected)
                        }
                        5->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                            ivStar3.setImageResource(R.drawable.ic_icstarselected)
                            ivStar4.setImageResource(R.drawable.ic_icstarselected)
                            ivStar5.setImageResource(R.drawable.ic_icstarselected)
                        }
                    }

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
                    tvTitle.text = it.data?.original_name
                    tvDetail.text = it.data?.overview
                    tvPopularity.text = it.data?.popularity.toString() + " People watching"
                    var genres = ""
                    it.data?.genres?.forEach { genres = genres + " " + it.name }
                    tvGenres.text = genres
                    tvRate.text = it.data?.vote_average.toString()
                    var star = it.data?.vote_average?.div(2)?.toInt()

                    when(star){
                        1->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                        }
                        2->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                        }
                        3->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                            ivStar3.setImageResource(R.drawable.ic_icstarselected)
                        }
                        4->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                            ivStar3.setImageResource(R.drawable.ic_icstarselected)
                            ivStar4.setImageResource(R.drawable.ic_icstarselected)
                        }
                        5->{
                            ivStar1.setImageResource(R.drawable.ic_icstarselected)
                            ivStar2.setImageResource(R.drawable.ic_icstarselected)
                            ivStar3.setImageResource(R.drawable.ic_icstarselected)
                            ivStar4.setImageResource(R.drawable.ic_icstarselected)
                            ivStar5.setImageResource(R.drawable.ic_icstarselected)
                        }
                    }

                } else if (it.status == Status.ERROR) {
                    dispatchLoading()
                } else if (it.status == Status.LOADING) {
                    showLoading()

                }
            })

        }


    }

    private fun observeData() {
        if (types == Type.movie) {
            viewModelMovie.movieActor().observe(viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    dispatchLoading()
                    actorAdapter.submitList(it.data?.cast as MutableList<Cast>)

                }else if(it.status == Status.ERROR){
                    dispatchLoading()

                }else if(it.status == Status.LOADING){
                    showLoading()

                }
            })
        }else if(types == Type.tv){
            viewModelTv.tvActor().observe(viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    dispatchLoading()
                    actorAdapter.submitList(if (it.data?.cast?.isEmpty()?: false) null else it.data?.cast as MutableList<Cast>?)


                }else if(it.status == Status.ERROR){
                    dispatchLoading()

                }else if(it.status == Status.LOADING){
                    showLoading()

                }
            })
        }

    }

    private fun initAdapterActor() {
        rvActor.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvActor.adapter = actorAdapter
    }

    private fun share(){

        val intent= Intent()
        intent.action=Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
        intent.type="text/plain"
        startActivity(Intent.createChooser(intent,"Share To:"))

    }


}
