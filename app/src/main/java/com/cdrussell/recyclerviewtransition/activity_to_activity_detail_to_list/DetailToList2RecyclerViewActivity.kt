package com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list

import android.app.Activity
import android.content.Intent
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

class DetailToList2RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate - pausing animation")
        postponeEnterTransition()
        setContentView(R.layout.activity_to_activity_2_recycler_view)

        findViewById<RecyclerView>(R.id.recyclerView).also {
            val adapter = SampleAdapter(object: SampleAdapter.AdapterClickListener {
                override fun onItemSelected(value: String) {
                    val returnIntent = Intent()
                    returnIntent.putExtra("selected", "value")
                    setResult(Activity.RESULT_OK, returnIntent)
                }
            })
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

private class SampleAdapter(val clickListener: AdapterClickListener) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    data class ViewHolder(val rootView: View, val title: TextView, val sharedImage: ImageView) : RecyclerView.ViewHolder(rootView)

    interface AdapterClickListener {
        fun onItemSelected(value: String)
    }

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

        holder.rootView.setOnClickListener {
            Timber.i("tapped on $item")
            clickListener.onItemSelected(item)
        }
    }

    fun adapterPositionForItem(value: String): Int {
        return items.indexOf(value)
    }
}
