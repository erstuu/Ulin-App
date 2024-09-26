package com.example.ulin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ulin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvVacationOfTheWeek: RecyclerView
    private lateinit var rvRecommedation: RecyclerView
    private val list = mutableListOf<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvVacationOfTheWeek = findViewById(R.id.rv_vacation_week)
        rvVacationOfTheWeek.setHasFixedSize(true)

        rvRecommedation = findViewById(R.id.rv_recommedation)
        rvRecommedation.setHasFixedSize(true)

        list.addAll(getListVacation())
        showRecyclerList()
    }

    private fun getListVacation(): ArrayList<Destination> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescription = resources.getStringArray(R.array.data_description)

        val listVacation = ArrayList<Destination>()
        for (i in dataTitle.indices) {
            val destination = Destination(dataTitle[i], dataDescription[i], dataLocation[i], dataRating[i], dataPhoto.getResourceId(i, -1), true)
            listVacation.add(destination)
        }

        return listVacation
    }

    private fun showRecyclerList() {
        rvVacationOfTheWeek.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listVacationAdapter = ListVacationOfTheWeekAdapter(list)
        rvVacationOfTheWeek.adapter = listVacationAdapter


        rvRecommedation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listVacationRecommendationAdapter = ListVacationRecommendationAdapter(list)
        rvRecommedation.adapter = listVacationRecommendationAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_setting -> {
                Intent(this, ProfileActivity::class.java).also {
                    startActivity(it)
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

}