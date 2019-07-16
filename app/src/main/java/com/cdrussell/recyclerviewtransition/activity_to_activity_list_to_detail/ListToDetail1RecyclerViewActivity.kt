package com.cdrussell.recyclerviewtransition.activity_to_activity_list_to_detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.cdrussell.recyclerviewtransition.R

class ListToDetail1RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_activity_2_recycler_view)

        val clickListener = object : ClickListener {
            override fun onClicked(viewHolder: SampleAdapter.ViewHolder) {
                launchNextActivity(viewHolder)
            }
        }

        findViewById<RecyclerView>(R.id.recyclerView).also {
            it.adapter = SampleAdapter(clickListener)
        }
    }

    private fun launchNextActivity(viewHolder: SampleAdapter.ViewHolder) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair(viewHolder.sharedImage, "t")
        )

        startActivity(Intent(this, ListToDetail2RecyclerViewActivity::class.java), options.toBundle())
    }

    private class SampleAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

        private val items = listOf("a", "b", "c")

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
            holder.rootView.setOnClickListener { clickListener.onClicked(holder) }
        }
    }

    private interface ClickListener {
        fun onClicked(viewHolder: SampleAdapter.ViewHolder)
    }
}