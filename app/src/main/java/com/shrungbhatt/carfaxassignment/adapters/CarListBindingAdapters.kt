package com.shrungbhatt.carfaxassignment.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import org.w3c.dom.Text
import kotlin.text.StringBuilder


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("year", "make", "model", "trim")
fun bindModelName(view: TextView, year: Long?, make: String?, model: String?, trim: String?) {
    val builder = StringBuilder()

    builder.apply {
        year.let{
            append(year)
            append(" ")
        }
        make.let{
            append(make)
            append(" ")
        }
        model.let{
            append(model)
            append(" ")
        }
        trim.let{
            append(trim)
        }
    }

    view.text = builder.toString()
}

@BindingAdapter("price", "mileage")
fun carPriceAndMileage(view: TextView, price: Long?, mileage: Long?){

}

@BindingAdapter("city", "state")
fun carLocation(view: TextView, city: String?, state: String?){
    view.text = StringBuilder().apply {
        append(city)
        if(!city.isNullOrBlank()) append(", ")
        append(state)
    }.toString()
}

