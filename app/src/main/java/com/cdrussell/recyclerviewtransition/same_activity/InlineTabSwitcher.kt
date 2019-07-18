package com.cdrussell.recyclerviewtransition.same_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cdrussell.recyclerviewtransition.R
import timber.log.Timber

class InlineTabSwitcher : Fragment() {

    private val listener: InlineTabSwitcherListener?
        get() = activity as InlineTabSwitcherListener

    lateinit var rootView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_inline_tab_switcher, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rootView.findViewById<RecyclerView>(R.id.recyclerView).also {
            val adapter = SampleAdapter(object : InlineTabSwitcherListener {
                override fun onTabSelected(value: String, viewHolder: SampleAdapter.ViewHolder) {
                    listener?.onTabSelected(value, viewHolder)
                }
            })
            it.adapter = adapter

            val index = adapter.adapterPositionForItem(arguments?.getString("selected"))
            it.layoutManager?.scrollToPosition(index)

            it.doOnPreDraw { recycler ->

                Timber.i("onPreDraw... triggering animation")
                startPostponedEnterTransition()

            }
        }
    }

    interface InlineTabSwitcherListener {
        fun onTabSelected(value: String, viewHolder: SampleAdapter.ViewHolder)
    }

    companion object {

        fun newInstance(value: String?): InlineTabSwitcher {
            return InlineTabSwitcher().apply {
                arguments = Bundle().apply {
                    putString("selected", value)
                }
            }
        }
    }
}

class SampleAdapter(private val tabSwitcherListener: InlineTabSwitcher.InlineTabSwitcherListener) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    data class ViewHolder(val rootView: View, val title: TextView, val sharedImage: ImageView) : RecyclerView.ViewHolder(rootView)


    private var viewHolders = mutableListOf<ViewHolder>()

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

        Timber.i("Setting transition name '$item' to image with id ${holder.sharedImage.id}")
        ViewCompat.setTransitionName(holder.sharedImage, item)

        holder.rootView.setOnClickListener {

            // clear all existing transition names
            Timber.i("Selected a tab. Tab ID is $item and transition name for this image is ${holder.sharedImage.transitionName}")

            //ViewCompat.setTransitionName(holder.sharedImage, item)

            tabSwitcherListener.onTabSelected(item, holder)
        }

        viewHolders.add(holder)
    }

    fun adapterPositionForItem(value: String?): Int {
        return items.indexOf(value)
    }

    companion object {
        val items = listOf("a", "b", "c", "d", "e", "f", "g")
    }
}