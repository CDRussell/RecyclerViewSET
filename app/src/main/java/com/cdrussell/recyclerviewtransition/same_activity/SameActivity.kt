package com.cdrussell.recyclerviewtransition.com.cdrussell.recyclerviewtransition.same_activity

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.cdrussell.recyclerviewtransition.R
import com.cdrussell.recyclerviewtransition.same_activity.InlineDetailFragment
import com.cdrussell.recyclerviewtransition.same_activity.InlineTabSwitcher
import com.cdrussell.recyclerviewtransition.same_activity.SampleAdapter
import timber.log.Timber


class SameActivity : AppCompatActivity(), InlineDetailFragment.TabLaunchClickListener, InlineTabSwitcher.InlineTabSwitcherListener {

    private var currentTab: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.same_activity_layout)

        switchTab("a")
    }

    private fun switchTab(value: String?, viewHolder: SampleAdapter.ViewHolder? = null) {
        if (value == null) throw IllegalArgumentException("Invalid value - can't be null")

        val fm = supportFragmentManager
        val newFragment = InlineDetailFragment.instance(value)
        val existingFragment = fm.findFragmentById(R.id.fragmentContainer)
        val tx = fm.beginTransaction()
        tx.setReorderingAllowed(true)
        tx.replace(R.id.fragmentContainer, newFragment)
        //tx.addToBackStack(null)

        //  exit for previous fragment
        existingFragment?.exitTransition = exitFadeTransition()

        // shared element transitions
        newFragment.sharedElementEnterTransition = sharedElementTransition()

        // entrance for new fragment
        newFragment.enterTransition = entranceFadeTransition()

        if (existingFragment is InlineTabSwitcher && viewHolder != null) {
            viewHolder.sharedImage.transitionName = "image"
            tx.addSharedElement(viewHolder.sharedImage, viewHolder.sharedImage.transitionName)
        }

        tx.commitAllowingStateLoss()

        currentTab = value
    }

    private fun showTabSwitcher() {
        val fm = supportFragmentManager
        val newFragment = InlineTabSwitcher.newInstance(currentTab)
        val existingFragment = fm.findFragmentById(R.id.fragmentContainer)
        val tx = fm.beginTransaction()
        tx.setReorderingAllowed(true)
        tx.replace(R.id.fragmentContainer, newFragment)
        //tx.addToBackStack(null)

        //  exit for previous fragment
        existingFragment?.exitTransition = exitFadeTransition()

        // shared element transitions
        newFragment.sharedElementEnterTransition = sharedElementTransition()

        // entrance for new fragment
        newFragment.enterTransition = entranceFadeTransition()

        if (existingFragment is InlineDetailFragment) {
            existingFragment.rootView?.findViewById<ImageView>(R.id.sharedImage)?.let {
                it.transitionName = currentTab
                tx.addSharedElement(it, it.transitionName)
            }
        }

        existingFragment?.enterTransition = entranceFadeTransition()

        tx.commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.fragmentContainer)) {
            is InlineDetailFragment -> {
                Timber.i("Currently in browser view")
            }
            is InlineTabSwitcher -> {
                Timber.i("Currently in tab switcher view")
                switchTab(currentTab)
            }
            else -> super.onBackPressed()
        }
    }

    private fun exitFadeTransition(): Fade {
        return Fade().apply {
            duration = FADE_DEFAULT_TIME
        }
    }

    private fun entranceFadeTransition(): Fade {
        return Fade().apply {
            startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
            duration = FADE_DEFAULT_TIME
        }
    }

    private fun sharedElementTransition(): TransitionSet {
        return TransitionSet().apply {
            addTransition(TransitionInflater.from(this@SameActivity).inflateTransition(android.R.transition.move))
            duration = MOVE_DEFAULT_TIME
            startDelay = FADE_DEFAULT_TIME
        }
    }

//    override fun onSelectedTab(value: String?) {
//        switchTab(value)
//    }

    override fun launchTabSelection() {
        showTabSwitcher()
    }

    override fun onTabSelected(value: String, viewHolder: SampleAdapter.ViewHolder) {
        switchTab(value, viewHolder)
    }

    companion object {
        private const val MOVE_DEFAULT_TIME: Long = 500
        private const val FADE_DEFAULT_TIME: Long = 300
    }
}

