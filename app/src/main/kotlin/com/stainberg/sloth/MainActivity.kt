package com.stainberg.sloth

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.stainberg.sloth.core.ui.SlothActivity
import com.stainberg.sloth.ui.main.MainFragment1
import com.stainberg.sloth.ui.main.MainFragment2
import com.stainberg.sloth.ui.main.MainFragment3


class MainActivity : SlothActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, MainFragment1.newInstance("container1"))
                .replace(R.id.container2, MainFragment2.newInstance("container2"))
                .replace(R.id.container3, MainFragment3.newInstance("container3"))
                .commitNowAllowingStateLoss()
        }
        requestOverlayPermission()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestOverlayPermission() {
        if (Settings.canDrawOverlays(this)) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            intent.data = Uri.parse("package:$packageName")
            startActivityForResult(intent, 1122)
        }
    }
}
