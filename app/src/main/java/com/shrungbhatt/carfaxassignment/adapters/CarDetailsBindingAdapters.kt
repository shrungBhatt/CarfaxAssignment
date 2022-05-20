package com.shrungbhatt.carfaxassignment.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.StringBuilder

@BindingAdapter("engine","displacement")
fun bindCarFuelType(view: TextView, engine: String?, displacement: String?){
    val builder = StringBuilder()

    if(!engine.isNullOrBlank()){
        builder.apply {
            append(engine)
            append(" ")
        }
    }

    if(!displacement.isNullOrBlank()){
        builder.apply {
            append(displacement)
        }
    }

    view.text = builder.toString()
}