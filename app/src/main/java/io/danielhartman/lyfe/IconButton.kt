package io.danielhartman.lyfe

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.icon_button.view.*

class IconButton : FrameLayout {

    var root:View? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.icon_button, this, true)
    }
    fun setImage(@DrawableRes image:Int){
        imageView.setImageResource(image)
    }
    fun setText(@StringRes text:Int) {
        setText(context.getString(text))
    }
    fun setText(text:String){
        textView.text = text
    }
}