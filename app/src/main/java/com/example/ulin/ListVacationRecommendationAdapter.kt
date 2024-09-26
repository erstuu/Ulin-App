package com.example.ulin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulin.databinding.ItemVacationRecommendationBinding

class ListVacationRecommendationAdapter(private val listRecommendation: List<Destination>) : RecyclerView.Adapter<ListVacationRecommendationAdapter.RecommendationViewHolder>() {

    class RecommendationViewHolder(binding: ItemVacationRecommendationBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvTitle: TextView = binding.title
        private val tvLocation: TextView = binding.location
        private val imgVacation = binding.imgVacation
        private val tvRating: TextView = binding.rating

        fun bind(destination: Destination) {
            tvTitle.text = destination.title
            tvLocation.text = destination.location
            tvRating.text = destination.rating
            imgVacation.setImageResource(destination.image)

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, DetailActivity::class.java). apply {
                    putExtra(DetailActivity.EXTRA_DATA_DESTINATION, destination)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding = ItemVacationRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(listRecommendation[position])

    }

    override fun getItemCount(): Int = listRecommendation.size
}
