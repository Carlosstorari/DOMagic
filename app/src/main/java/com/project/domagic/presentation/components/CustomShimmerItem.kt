package com.project.domagic.presentation.components

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.project.domagic.R
import com.project.domagic.databinding.CustomShimmerItemBinding


class CustomShimmerItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = CustomShimmerItemBinding
        .inflate(LayoutInflater.from(context), this, true)
    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    val Float.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    fun createCardViewShimmer(quantity: Int) {

        for (index in 1..quantity-2) {
            val cardview = CardView(context)
            val layoutparams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                100.dp
            )
            layoutparams as ViewGroup.MarginLayoutParams
            layoutparams.setMargins(5.dp, 5.dp, 5.dp, 0.dp)

            cardview.layoutParams = layoutparams
            cardview.radius = 8f.dp.toFloat()
            cardview.setPadding(25, 25, 25, 25)
            cardview.setCardBackgroundColor(context.getColor(R.color.shimmer_color))
            cardview.maxCardElevation = 30f
            cardview.maxCardElevation = 6f

            binding.shimmerContainer.addView(cardview)
        }
    }
}