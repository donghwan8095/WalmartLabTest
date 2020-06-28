package com.base.baseapplication.ui.main

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.base.baseapplication.R
import com.base.baseapplication.data.network.models.Availability
import com.base.baseapplication.data.network.models.Car
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList

class CarListAdapter (val activity : Activity, val itemClickListener: ((Car) -> Unit)) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    val carList = ArrayList<Car>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListAdapter.CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item_layout, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(viewHolder: CarListAdapter.CarViewHolder, position: Int) {
        val car = carList[position]
        viewHolder.populateData(car)
    }

    fun updateCarList(list : ArrayList<Car>) {
        carList.clear()
        carList.addAll(list)
    }

    fun updateAvailability(id: Int, availability: Availability) {
        for (car in carList) {
            if (car.id == id) {
                car.availability = availability
            }
        }
        notifyDataSetChanged()
    }

    fun sortedWith(sortBy : Int) {
        if (sortBy == 1) {
            carList.sortWith(compareBy { it.name })
        } else{
            carList.sortWith(compareBy { it.availability })
        }
        notifyDataSetChanged()
    }

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView : ImageView = itemView.findViewById(R.id.imageView)
        private val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)
        private val makeTextView : TextView = itemView.findViewById(R.id.makeTextView)
        private val modelTextView : TextView = itemView.findViewById(R.id.modelTextView)
        private val yearTextView : TextView = itemView.findViewById(R.id.yearTextView)
        private val availabilityTextView : TextView = itemView.findViewById(R.id.availabilityTextView)

        private val buyButton : Button = itemView.findViewById(R.id.buyButton)
        private val carItemLayout : ConstraintLayout = itemView.findViewById(R.id.carItemLayout)

        fun populateData(car : Car) {
            Glide.with(activity).load(car.imgUrl).centerCrop().into(imageView)
            nameTextView.text = car.name
            makeTextView.text = car.make
            modelTextView.text = car.model
            yearTextView.text = car.year

            if (car.availability == null) {
                availabilityTextView.visibility = View.GONE
                buyButton.visibility = View.GONE
            } else {
                availabilityTextView.visibility = View.VISIBLE
                availabilityTextView.text = car.availability?.availability

                if (car.availability == Availability.IN_DEALERSHIP) {
                    buyButton.visibility = View.VISIBLE
                } else {
                    buyButton.visibility = View.GONE
                }
            }

            carItemLayout.setOnClickListener {
                itemClickListener.invoke(car)
            }

            buyButton.setOnClickListener {
                Log.d("Buy Button","buyButton clicked")
                itemClickListener.invoke(car)
            }

        }
    }

}