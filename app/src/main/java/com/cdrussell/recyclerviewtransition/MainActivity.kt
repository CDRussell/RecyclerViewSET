package com.cdrussell.recyclerviewtransition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list.DetailToList1RecyclerViewActivity
import com.cdrussell.recyclerviewtransition.activity_to_activity_list_to_detail.ListToDetail1RecyclerViewActivity
import com.cdrussell.recyclerviewtransition.com.cdrussell.recyclerviewtransition.fragment_to_activity_detail_to_list.FragmentDetailToList1RecyclerViewActivity
import com.cdrussell.recyclerviewtransition.com.cdrussell.recyclerviewtransition.same_activity.SameActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureNavigationButtons()

    }

    private fun configureNavigationButtons() {
        findViewById<Button>(R.id.activityToRecyclerView).setOnClickListener {
            val clazz = DetailToList1RecyclerViewActivity::class.java
            startActivity(Intent(this, clazz))
        }

        findViewById<Button>(R.id.listToDetail).setOnClickListener {
            val clazz = ListToDetail1RecyclerViewActivity::class.java
            startActivity(Intent(this, clazz))
        }

        findViewById<Button>(R.id.fragmentToRecyclerView).setOnClickListener {
            val clazz = FragmentDetailToList1RecyclerViewActivity::class.java
            startActivity(Intent(this, clazz))
        }

        findViewById<Button>(R.id.sameActivity).setOnClickListener {
            val clazz = SameActivity::class.java
            startActivity(Intent(this, clazz))
        }
    }
}
