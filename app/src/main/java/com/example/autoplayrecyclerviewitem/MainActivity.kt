package com.example.autoplayrecyclerviewitem

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Timer
import java.util.TimerTask


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 0..2) {
            data.add(ItemsViewModel(R.drawable.my_image, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        var position = data.size
        val audio = listOf<Int>(R.raw.fly, R.raw.rose,R.raw.rose)

        val timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
                if ((recyclerview.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() < adapter.itemCount - 1) {
                    position--
                    if (position != -1) {
                        val mediaPlayer = MediaPlayer.create(this@MainActivity, audio[position])
                        mediaPlayer.start()
                    }
                    Log.d("data", position.toString())
                    (recyclerview.layoutManager as LinearLayoutManager).smoothScrollToPosition(
                        recyclerview, RecyclerView.State(),
                        (recyclerview.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() + 1
                    )

                }
//                else if ((recyclerview.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
//                    (recyclerview.layoutManager as LinearLayoutManager).smoothScrollToPosition(
//                        recyclerview,
//                        RecyclerView.State(),
//                        0
//                    )
//                }
            }
        }, 0, 3000)


    }
}


