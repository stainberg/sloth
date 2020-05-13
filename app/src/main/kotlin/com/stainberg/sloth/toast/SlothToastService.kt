package com.stainberg.sloth.toast

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.stainberg.sloth.R

class SlothToastService {

    private lateinit var param: WindowManager.LayoutParams
    private lateinit var context: Context
    private lateinit var content: View

    companion object {
        const val SHORT_DURATION_TIMEOUT: Long = 4000
        const val LONG_DURATION_TIMEOUT: Long = 7000
    }

    fun build(context: Context): SlothToastService {
        this.context = context
        content = LayoutInflater.from(context).inflate(R.layout.pop_container, null)
        content.findViewById<View>(R.id.pop_container_text).setOnClickListener {
            Toast.makeText(context, "SlothToastService", Toast.LENGTH_SHORT).show()
        }
        param = WindowManager.LayoutParams().apply {
            type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                WindowManager.LayoutParams.TYPE_PHONE
            }
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            format = PixelFormat.TRANSLUCENT
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.TOP
        }
        return this
    }

    fun show() {
        val wmm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wmm.addView(content, param)
        delayDismiss()
    }

    private fun delayDismiss() {
        content.postDelayed({
            val wmm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wmm.removeView(content)
        }, SHORT_DURATION_TIMEOUT)
    }

}