package com.stainberg.sloth.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import com.stainberg.sloth.R
import com.stainberg.sloth.core.ui.SlothActivity
import kotlinx.android.synthetic.main.blank_activity.*
import java.lang.reflect.Field
import java.lang.reflect.Method


class BlankActivity : SlothActivity() {

    var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blank_activity)

        val lp = WindowManager.LayoutParams()
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE and WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        window.decorView.layoutParams = lp

        blank_coordinator_layout?.let {
            Snackbar.make(blank_coordinator_layout, "Blank Activity Show", Snackbar.LENGTH_LONG)
//                .addCallback(object : Snackbar.Callback() {
//                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                        finish()
//                    }
//                })
                .show()
        }
        Log.d("BlankActivity", "onCreate")
//        val allActivity = getAllActivity()
//        if(allActivity.isNotEmpty()) {
//            activity = allActivity[0]
//        }
//        for (act in allActivity) {
//            Log.d("BlankActivity", act::class.java.name)
//        }
    }


    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    fun getAllActivity(): List<Activity> {
        val list: MutableList<Activity> = ArrayList()
        try {
            val activityThread = Class.forName("android.app.ActivityThread")
            val currentActivityThread: Method =
                activityThread.getDeclaredMethod("currentActivityThread")
            currentActivityThread.isAccessible = true
            //获取主线程对象
            val activityThreadObject: Any? = currentActivityThread.invoke(null)
            val mActivitiesField: Field = activityThread.getDeclaredField("mActivities")
            mActivitiesField.isAccessible = true
            val mActivities = mActivitiesField.get(activityThreadObject) as Map<*, *>
            for ((_, value) in mActivities) {
                value?.let {
                    val activityClientRecordClass: Class<*> = it.javaClass
                    val activityField: Field =
                        activityClientRecordClass.getDeclaredField("activity")
                    activityField.isAccessible = true
                    val o: Any? = activityField.get(it)
                    o?.let {
                        list.add(o as Activity)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BlankActivity", "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d("BlankActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("BlankActivity", "onStop")
    }

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        activity?.dispatchTouchEvent(ev)
//        return false
//
//    }
}