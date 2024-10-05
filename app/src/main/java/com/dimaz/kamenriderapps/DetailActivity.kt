package com.dimaz.kamenriderapps

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide



class DetailActivity : AppCompatActivity() {

    private lateinit var imgPhotoDetail: ImageView
    private lateinit var tvNameSeries: TextView
    private lateinit var tvThemeSeries: TextView
    private lateinit var tvSynopsisSeries: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        imgPhotoDetail = findViewById(R.id.img_item_photo_detail)
        tvNameSeries = findViewById(R.id.tv_item_name)
        tvThemeSeries = findViewById(R.id.tv_item_theme)
        tvSynopsisSeries = findViewById(R.id.tv_item_synopsis)

        val photoSeries = intent.getStringExtra("SERIES_PHOTO_DETAIL")
        val seriesName = intent.getStringExtra("SERIES_NAME")
        val seriesTheme = intent.getStringExtra("SERIES_THEME")
        val seriesSynopsis = intent.getStringExtra("SERIES_SYNOPSIS")

        Glide.with(this)
            .load(photoSeries)
            .into(imgPhotoDetail)
        tvNameSeries.text = seriesName
        tvThemeSeries.text = seriesTheme
        tvSynopsisSeries.text = seriesSynopsis

        val actionShare = findViewById<Button>(R.id.action_share)
        actionShare.setOnClickListener {
            val shareContent = "Check out this series!\n\nName: $seriesName\nTheme: $seriesTheme\nSynopsis: $seriesSynopsis"
            shareContent(shareContent)
        }
    }

    private fun shareContent(content: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    

}