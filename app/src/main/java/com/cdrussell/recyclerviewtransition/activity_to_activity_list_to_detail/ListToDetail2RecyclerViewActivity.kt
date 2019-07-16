package com.cdrussell.recyclerviewtransition.activity_to_activity_list_to_detail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.cdrussell.recyclerviewtransition.R

class ListToDetail2RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_activity_1_recycler_view)

        ViewCompat.setTransitionName(findViewById<ImageView>(R.id.sharedImage), "t")
    }
}
