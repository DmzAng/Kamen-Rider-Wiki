package com.dimaz.kamenriderapps

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ListSeriesAdapter(private val listSeries: ArrayList<Series>): RecyclerView.Adapter<ListSeriesAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView =itemView.findViewById(R.id.img_item_photo)
        val tvNameSeries: TextView =itemView.findViewById(R.id.tv_item_name)
        val tvThemeSeries: TextView =itemView.findViewById(R.id.tv_item_theme)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSeriesAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSeriesAdapter.ListViewHolder, position: Int) {
        val series = listSeries[position]
        holder.tvNameSeries.text = series.nameSeries
        holder.tvThemeSeries.text = series.theme
        Glide.with(holder.itemView.context)
            .load(series.image)
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("SERIES_PHOTO_DETAIL", series.image)
            intentDetail.putExtra("SERIES_NAME", series.nameSeries)
            intentDetail.putExtra("SERIES_THEME", series.theme)
            intentDetail.putExtra("SERIES_SYNOPSIS", series.synopsis)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return listSeries.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Series)
    }
}