package com.cdrussell.recyclerviewtransition.same_activity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cdrussell.recyclerviewtransition.R

/**
 * A simple [Fragment] subclass.
 */
class InlineDetailFragment : Fragment() {

    var rootView: View? = null

    private val listenerTabLaunch: TabLaunchClickListener
        get() = activity as TabLaunchClickListener

    interface TabLaunchClickListener {
        fun launchTabSelection()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        root.findViewById<ImageView>(R.id.sharedImage).setOnClickListener {
            listenerTabLaunch.launchTabSelection()
        }

        root.findViewById<TextView>(R.id.title).text = arguments?.getString("selected")

        rootView = root
        return root
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        when(requestCode) {
//            100 -> {
//                if(resultCode == Activity.RESULT_OK) {
//                    val listenerTabLaunch = activity as TabLaunchClickListener
//                    listenerTabLaunch.onSelectedTab(data?.getStringExtra("selected"))
//                }
//            }
//            else -> super.onActivityResult(requestCode, resultCode, data)
//        }
//    }

    companion object {

        fun instance(value: String) : InlineDetailFragment {
            val fragment = InlineDetailFragment()
            fragment.arguments = Bundle().also {it.putString("selected", value) }
            return fragment
        }
    }
}
