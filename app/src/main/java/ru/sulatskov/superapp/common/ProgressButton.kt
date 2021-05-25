package ru.sulatskov.superapp.common

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import ru.sulatskov.superapp.R


class ProgressButton : FrameLayout {

    private lateinit var textPB: TextView
    private lateinit var progressPB: ProgressBar
    private var textOnButton: String? = ""
    private var textColor: Int = 0
    private var progressColor: Int = 0

    constructor(context: Context) : super(context) {
        prepare(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        prepare(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        prepare(attrs)
    }


    private fun prepare(attrs: AttributeSet?) {
        initAttrs(attrs)
        val view = LayoutInflater.from(context).inflate(R.layout.progress_button, this, true)
        textPB = view.findViewById(R.id.button)
        progressPB = view.findViewById(R.id.progress)
        textPB.text = textOnButton
        textPB.setTextColor(textColor)
        progressPB.indeterminateTintList = ColorStateList.valueOf(progressColor)
        setState(State.COMPLETE)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        if (attrs != null) {
            val attribute = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProgressButton,
                0, 0
            )

            try {
                textOnButton = attribute.getString(R.styleable.ProgressButton_text)
                textColor = when {
                    attribute.hasValue(R.styleable.ProgressButton_text_color) -> {
                        attribute.getColor(
                            R.styleable.ProgressButton_text_color,
                            resources.getColor(R.color.color_black)
                        )
                    }
                    else -> {
                        resources.getColor(R.color.color_black)
                    }
                }

                progressColor = when {
                    attribute.hasValue(R.styleable.ProgressButton_progress_color) -> {
                        attribute.getColor(
                            R.styleable.ProgressButton_progress_color,
                            resources.getColor(R.color.color_black)
                        )
                    }
                    else -> {
                        resources.getColor(R.color.color_black)
                    }
                }
            } finally {
                attribute.recycle()
            }
        }
    }

    fun setState(state: State) {
        when (state) {
            State.COMPLETE -> {
                textPB.visible()
                progressPB.gone()
            }
            State.IN_PROGRESS -> {
                textPB.gone()
                progressPB.visible()
            }
        }
    }

    fun setText(text: String){
        textPB.text = text
    }

    enum class State() {
        IN_PROGRESS,
        COMPLETE
    }
}