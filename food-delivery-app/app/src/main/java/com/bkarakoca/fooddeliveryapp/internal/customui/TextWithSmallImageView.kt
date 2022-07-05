package com.bkarakoca.fooddeliveryapp.internal.customui

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.databinding.ViewTextWithSmallImageBinding

class TextWithSmallImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = ViewTextWithSmallImageBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                it,
                R.styleable.TextWithSmallImageView,
                0,
                0
            )

            try {
                val imageResId = typedArray.getResourceId(
                    R.styleable.TextWithSmallImageView_componentImageResId,
                    R.color.black
                )
                binding.imageViewComponent.setImageResource(imageResId)
            } finally {
                typedArray.recycle()
            }
        }
    }
}

@BindingAdapter("setComponentText")
fun setComponentText(view: TextWithSmallImageView, text: String) {
    view.findViewById<TextView>(R.id.text_view_component).text = text
}

@BindingAdapter("setComponentTextColor")
fun setComponentTextColor(view: TextWithSmallImageView, colorId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        view.findViewById<TextView>(R.id.text_view_component).setTextColor(
            view.resources.getColor(colorId, null)
        )
    } else {
        view.findViewById<TextView>(R.id.text_view_component).setTextColor(Color.BLACK)
    }
}