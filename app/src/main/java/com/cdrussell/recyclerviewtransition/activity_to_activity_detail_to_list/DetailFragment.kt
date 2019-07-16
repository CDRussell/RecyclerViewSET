package com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.cdrussell.recyclerviewtransition.R

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        root.findViewById<ImageView>(R.id.sharedImage).setOnClickListener {
            val clazz = DetailToList2RecyclerViewActivity::class.java
            val element = DetailToList2RecyclerViewActivity.items.random()

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), it, element)
            val intent = Intent(activity, clazz)
            intent.putExtra("selected", element)
            startActivity(intent, options.toBundle())
        }

        return root
    }


}
