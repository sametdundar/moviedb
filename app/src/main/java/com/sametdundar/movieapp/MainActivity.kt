package com.sametdundar.movieapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sametdundar.movieapp.base.BaseActivity
import com.sametdundar.movieapp.base.ConnectionManager
import com.sametdundar.movieapp.base.fragment_ops.NavigationManager
import com.sametdundar.movieapp.customview.NavigationBarOnClickListener
import com.sametdundar.movieapp.ui.adapter.NavigationPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationBarOnClickListener {

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            (context as Activity).finish()
        }

    }

    @Inject
    lateinit var connectionManager: ConnectionManager
    @Inject
    lateinit var navManager: NavigationManager
    private lateinit var pagerAdapter: NavigationPagerAdapter


    override fun layoutId(): Int? = R.layout.activity_main

    override fun initialize() {
        pagerAdapter =
            NavigationPagerAdapter(
                supportFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        navigationBar.setOnClickListener(this)
        navigationPager.adapter = pagerAdapter
        navigationPager.offscreenPageLimit = pagerAdapter.count
        connectionManager.setActivity(this)
    }

    override fun onItemSelected(position: Int) {
        navManager.setConsumerName(pagerAdapter.getClassName(position))
        navigationPager.setCurrentItem(position, false)
    }

    override fun onItemReselected(position: Int) {
        navManager.activeFragment()?.let { stackOwner ->

            navManager.consumeReselect()

        }
    }

    override fun onBackPressed() {
        if (!navManager.consumeBackPress()) {

            if (navigationBar.currentSelectedItem != navigationBar.movies) {
                navigationBar.changeActiveTab(0)
            } else {
//                GeneralUtil.dialogWithOneOptions(
//                    this,
//                    "",
//                    "Are you sure you want to quit PressTR?",
//                    "Yes",
//                    "Cancel",
//                    exitRunnable
//                )
            }
        }
    }

    private val exitRunnable = Runnable {
        finishAffinity()
    }

}
