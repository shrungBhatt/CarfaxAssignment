package com.shrungbhatt.carfaxassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shrungbhatt.carfaxassignment.data.models.Car
import com.shrungbhatt.carfaxassignment.data.models.Dealer
import com.shrungbhatt.carfaxassignment.databinding.ListItemCarBinding

class CarListAdapter constructor(
    private val callBack: ICarListAdapterCallback
) : ListAdapter<Car, CarViewHolder>(GalleryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ListItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = getItem(position)
        car?.let {
            holder.bind(it)
            holder.binding.cardView.setOnClickListener {
                callBack.onCarClick(car)
            }
            holder.binding.callDealer.setOnClickListener {
                car.dealer?.let { dealer -> callBack.onCarDealerClick(dealer) }
            }
        }
    }
}

class CarViewHolder(
    val binding: ListItemCarBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Car) {
        binding.apply {
            car = item
            executePendingBindings()
        }
    }
}

private class GalleryDiffCallback : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}

interface ICarListAdapterCallback{
    fun onCarClick(car: Car)
    fun onCarDealerClick(dealer: Dealer)
}