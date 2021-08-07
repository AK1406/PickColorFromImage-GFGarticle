package com.gfg.article.pickcolor

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

lateinit var image:ImageView
lateinit var bitmap: Bitmap
lateinit var colorView : View
lateinit var colorString : TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image = findViewById(R.id.pickColorImage)
        colorView = findViewById(R.id.fillColor)
        colorString = findViewById(R.id.colorInHex)

        image.isDrawingCacheEnabled = true
        image.buildDrawingCache(true)

        //on touch listener on image view
        image.setOnTouchListener { _, event ->
            if(event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                bitmap = image.drawingCache

                //get touched pixel
                val pixel = bitmap.getPixel(event.x.toInt(),event.y.toInt())

                //get RGB values from the touched pixel
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)

                //color name in Hexadecimal(#RRGGBB)
                colorString.text ="#${Integer.toHexString(pixel)}"

                //fill the color in the view
                colorView.setBackgroundColor(Color.rgb(r,g,b))
            }
            true
        }
    }
}