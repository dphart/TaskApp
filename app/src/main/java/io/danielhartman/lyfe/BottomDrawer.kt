package io.danielhartman.lyfe

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout

class BottomDrawer : LinearLayout {

    var root : View? = null

    constructor(context:Context) : super(context) {init(context)}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {init(context)}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {init(context)}


    private fun init(context: Context) {
        root = View.inflate(context, R.layout.bottom_drawer_root, this)
    }

}