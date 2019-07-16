package com.cdrussell.recyclerviewtransition.activity_to_activity_detail_to_list

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.cdrussell.recyclerviewtransition.R

class DetailToList1RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_activity_1_recycler_view)

        findViewById<ImageView>(R.id.sharedImage).setOnClickListener {
            val clazz = DetailToList2RecyclerViewActivity::class.java
            val element = DetailToList2RecyclerViewActivity.items.random()

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, it, element)
            val intent = Intent(this, clazz)
            intent.putExtra("selected", element)
            startActivity(intent, options.toBundle())
        }
    }
}
