package com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.cdrussell.recyclerviewtransition.R
import com.cdrussell.recyclerviewtransition.com.cdrussell.recyclerviewtransition.fragment_to_activity_detail_to_list.FragmentDetailToList2RecyclerViewActivity as FragmentDetailToList2RecyclerViewActivity1

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    interface ClickListener {
        fun onClick(value: String?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        root.findViewById<ImageView>(R.id.sharedImage).setOnClickListener {

            val clazz = FragmentDetailToList2RecyclerViewActivity1::class.java
            val element = FragmentDetailToList2RecyclerViewActivity1.items.random()

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), it, element)
            val intent = Intent(activity, clazz)
            intent.putExtra("selected", element)
            startActivityForResult(intent, 100, options.toBundle())
        }

        root.findViewById<TextView>(R.id.title).text = arguments?.getString("selected")

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            100 -> {
                if(resultCode == Activity.RESULT_OK) {
                    val listener = activity as ClickListener
                    listener.onClick(data?.getStringExtra("selected"))
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {

        fun instance(value: String) : DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = Bundle().also {it.putString("selected", value) }
            return fragment
        }
    }
}
