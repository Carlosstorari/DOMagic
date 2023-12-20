package com.project.catalogingmtgcards.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import coil.decode.SvgDecoder
import coil.load
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.ManaValueContainerBinding

class ManaValueContainer @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var listUri: List<String> = emptyList()
    private val binding = ManaValueContainerBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    fun setUriList(uriList: List<String>?) {
        uriList?.let { list ->
            for (uri in list) {
                val image = ImageView(context)
                image.layoutParams = LinearLayout.LayoutParams(50, 50)
                val l = image.layoutParams as LinearLayout.LayoutParams
                l.setMargins(10, 5, 20, 25)
                image.x = 20F
                image.y = 20F
                image.load(uri) {
                    decoderFactory {result, options, _ -> SvgDecoder(result.source, options)}
                }
                binding.root.addView(image)
            }
        }
    }

    fun verifyCardId(idListCard: Set<String>, itemId: String): Boolean {
        return !(idListCard.contains(itemId))
    }


    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.ManaValueContainer
            )
            val listImage = attributes.getResourceId(R.styleable.ManaValueContainer_list_uri, 0)
            if (listImage != 0) {
                listUri = listOf(context.getString(listImage))
            }
        }
    }
}