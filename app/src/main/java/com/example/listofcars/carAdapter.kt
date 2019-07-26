package com.example.listofcars

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.car_item.view.*

class CarAdapter(private val cars: ArrayList<Car>): RecyclerView.Adapter<CarHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val inflatedView = parent.inflate(R.layout.car_item, false)
        return CarHolder(inflatedView)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val car = cars[position]
        holder.bindCar(car)
    }

}

class CarHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    private var car: Car? = null

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        Log.d("RecyclerView", "Click!")
    }

    companion object {
        private val CAR_KEY = "CAR"
    }

    fun bindCar(car: Car) {
        view.licensePlate.text = car.plate
        Picasso.get().load(car.imageURL).into(view.carImage);
    }

}
