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
    private var lastFocusX = 0f
    private var lastFocusY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.save()
        canvas?.scale(scaleFactor, scaleFactor, width / 2f, height / 2f)
        super.onDraw(canvas)
        canvas?.restore()
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            lastFocusX = detector.focusX
            lastFocusY = detector.focusY
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f))
            invalidate()
            return true
        }
    }
}
