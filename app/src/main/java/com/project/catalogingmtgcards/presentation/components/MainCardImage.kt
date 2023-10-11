package com.project.catalogingmtgcards.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.MainCardImageBinding

class MainCardImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var uri: String? = null

    private val binding = MainCardImageBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)

    }

    fun setUri(uriImage:String?) {
        binding.apply {
            Glide.with(context)
                .load(uriImage)
                .into(cardIv)
        }
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.MainCardImage
            )

            val uriImage = attributes.getResourceId(R.styleable.MainCardImage_image_uri, 0)
            if (uriImage != 0) {
                uri = context.getString(uriImage )
            }

            attributes.recycle()
        }
    }

}