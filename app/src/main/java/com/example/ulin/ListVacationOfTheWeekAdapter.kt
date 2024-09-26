package com.example.ulin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulin.databinding.ItemVacationBinding

class ListVacationOfTheWeekAdapter(private val listVacation: List<Destination>) : RecyclerView.Adapter<ListVacationOfTheWeekAdapter.VacationViewHolder>() {

    class VacationViewHolder(binding: ItemVacationBinding) : RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacationViewHolder {
        val binding = ItemVacationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacationViewHolder, position: Int) {
        holder.bind(listVacation[position])

    }

    override fun getItemCount(): Int = listVacation.takeLast(3).size
}
