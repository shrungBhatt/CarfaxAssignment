package com.shrungbhatt.carfaxassignment.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.DecimalFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow


//@Todo: Add a placeholder for error
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
fun bindModelName(view: TextView, year: Int?, make: String?, model: String?, trim: String?) {
    val builder = StringBuilder()

    builder.apply {
        year.let {
            append(it)
            append(" ")
        }
        make.let {
            append(it)
            append(" ")
        }
        model.let {
            append(it)
            append(" ")
        }
        append(trim)
    }

    view.text = builder.toString()
}


//@Todo: The locale should be passed from the viewModel here as binding adapter parameter
@BindingAdapter("price", "mileage")
fun carPriceAndMileage(view: TextView, price: Double?, mileage: Double?) {
    val builder = StringBuilder()
    if (price != null) {
        builder.append("${Currency.getInstance(Locale.CANADA).symbol} ")
        builder.append(convertCurrency(price))
    }

    if (mileage != null) {
        builder.append(" | ")
        builder.append(convertMileage(mileage))
        builder.append(" mi")
    }

    view.text = builder.toString()
}

private fun convertCurrency(price: Double): String {
    val priceDec = price.toBigDecimal()
    return DecimalFormat("#,###").format(priceDec)
}

private fun convertMileage(mileage: Double): String {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val value = floor(log10(mileage)).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            mileage / 10.0.pow((base * 3).toDouble())
        ) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(mileage)
    }
}

@BindingAdapter("city", "state")
fun carLocation(view: TextView, city: String?, state: String?) {
    view.text = StringBuilder().apply {
        append(city)
        if (!city.isNullOrBlank()) append(", ")
        append(state)
    }.toString()
}

