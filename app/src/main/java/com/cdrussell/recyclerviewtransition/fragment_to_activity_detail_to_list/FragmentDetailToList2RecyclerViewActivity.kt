package com.cdrussell.recyclerviewtransition.com.cdrussell.recyclerviewtransition.fragment_to_activity_detail_to_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.RecyclerView
import com.cdrussell.recyclerviewtransition.R
import com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list.DetailToList2RecyclerViewActivity.Companion.items
import timber.log.Timber

class FragmentDetailToList2RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate - pausing animation")
        postponeEnterTransition()
        setContentView(R.layout.activity_to_activity_2_recycler_view)

        findViewById<RecyclerView>(R.id.recyclerView).also {
            val adapter = SampleAdapter()
            it.adapter = adapter

            val index = adapter.adapterPositionForItem(intent.getStringExtra("selected"))
            it.layoutManager?.scrollToPosition(index)

            it.doOnPreDraw {recycler ->

                Timber.i("onPreDraw... triggering animation")
                startPostponedEnterTransition()

            }
        }
    }

    companion object {
        val items = listOf("a", "b", "c", "d", "e", "f", "g")
    }
}

private class SampleAdapter : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    data class ViewHolder(val rootView: View, val title: TextView, val sharedImage: ImageView) : RecyclerView.ViewHolder(rootView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val title = root.findViewById<TextView>(R.id.title)
        val sharedImage = root.findViewById<ImageView>(R.id.listImage)
        return ViewHolder(root, title, sharedImage)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item

        ViewCompat.setTransitionName(holder.sharedImage, item)
    }

    fun adapterPositionForItem(value: String): Int {
        return items.indexOf(value)
    }
}
