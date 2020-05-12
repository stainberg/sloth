package com.stainberg.sloth.ui.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.stainberg.sloth.R
import com.stainberg.sloth.core.ui.SlothActivity
import kotlinx.android.synthetic.main.blank_activity.*

class BlankActivity: SlothActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blank_activity)
        blank_coordinator_layout?. let {
            Snackbar.make(blank_coordinator_layout, "Blank Activity Show", Snackbar.LENGTH_LONG).show()
        }
        Log.d("BlankActivity", "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BlankActivity", "onDestroy")
    }
}