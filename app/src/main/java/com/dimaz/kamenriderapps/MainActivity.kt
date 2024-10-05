package com.dimaz.kamenriderapps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvSeries: RecyclerView
    private var list = ArrayList<Series>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSeries = findViewById(R.id.rv_series)
        rvSeries.setHasFixedSize(true)

        list.addAll(getListSeries())
        showRecyclerList()

    }

    private fun getListSeries(): ArrayList<Series>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataTheme = resources.getStringArray(R.array.data_theme)
        val dataSynopsis = resources.getStringArray(R.array.data_synopsis)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
//        val detailPhoto = resources.getStringArray(R.array.data_photo_detail)
        val listSeries = ArrayList<Series>()
        val minLength = minOf(dataName.size, dataTheme.size, dataSynopsis.size)

        for (i in 0 until minLength) {
            val series = Series(dataName[i], dataTheme[i], dataSynopsis[i], dataPhoto[i])
            listSeries.add(series)
        }
        return listSeries
    }

    private fun showRecyclerList() {
        rvSeries.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListSeriesAdapter(list)
        rvSeries.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListSeriesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Series) {
                showSelected(data)
            }
        })
    }

    private fun showSelected(series: Series) {
        Toast.makeText(this, "Kamu memilih " + series.nameSeries, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}