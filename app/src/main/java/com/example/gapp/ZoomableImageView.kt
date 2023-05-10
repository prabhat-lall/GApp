package com.example.gapp
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView


class ZoomableImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    private val scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    private var scaleFactor = 1.0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event!!)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.save()
        canvas?.scale(scaleFactor, scaleFactor)
        super.onDraw(canvas)
        canvas?.restore()
    }


    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            detector?.let {
                scaleFactor *= it.scaleFactor
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f))
                invalidate()
            }
            return true
        }
    }
}