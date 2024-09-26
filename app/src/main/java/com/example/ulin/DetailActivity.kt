package com.example.ulin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var location: TextView
    private lateinit var description: TextView
    private lateinit var rating: TextView
    private lateinit var link: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        image = findViewById(R.id.tv_image)
        title = findViewById(R.id.tv_title)
        description = findViewById(R.id.tv_description)
        location = findViewById(R.id.tv_location)
        rating = findViewById(R.id.tv_rating)
        link = findViewById(R.id.tv_link)

        val data = intent.getParcelableExtra<Destination>(EXTRA_DATA_DESTINATION)
        data?.let {
            image.setImageResource(it.image)
            title.text = it.title
            description.text = it.description
            location.text = it.location
            rating.text = it.rating

            link.setOnClickListener {
                openLocationInGoogleMaps(data.title)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = data?.title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun openLocationInGoogleMaps(locationName: String) {
        val encodedLocation = Uri.encode(locationName)
        val gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$encodedLocation")

        Log.d("DetailActivity", "Google Maps URI: $gmmIntentUri")

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent) // Start the intent
        } else {
            Toast.makeText(this, "Google Maps not installed", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_DATA_DESTINATION = "extra_data_destination"
    }
}
