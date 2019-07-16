package com.cdrussell.recyclerviewtransition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list.DetailToList1RecyclerViewActivity
import com.cdrussell.recyclerviewtransition.activity_to_activity_list_to_detail.ListToDetail1RecyclerViewActivity

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
    }
}
