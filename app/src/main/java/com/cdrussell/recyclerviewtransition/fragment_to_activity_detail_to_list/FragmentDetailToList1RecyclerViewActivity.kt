package com.cdrussell.recyclerviewtransition.com.cdrussell.recyclerviewtransition.fragment_to_activity_detail_to_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cdrussell.recyclerviewtransition.R
import com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list.DetailFragment

class FragmentDetailToList1RecyclerViewActivity : AppCompatActivity(), DetailFragment.ClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_to_activity_1_recycler_view)

        switchFragment("a")
    }

    private fun switchFragment(value: String?) {
        if (value == null) throw IllegalArgumentException("Invalid value - can't be null")

        val fm = supportFragmentManager
        val fragment = DetailFragment.instance(value)

        val tx = fm.beginTransaction()
        tx.replace(R.id.fragmentContainer, fragment)
        tx.commit()

        //startPostponedEnterTransition()
    }

    override fun onClick(value: String?) {
        switchFragment(value)
    }

}
