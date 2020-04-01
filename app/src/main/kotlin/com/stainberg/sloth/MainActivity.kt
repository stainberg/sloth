package com.stainberg.sloth

import android.os.Bundle
import com.stainberg.sloth.core.ui.SlothActivity
import com.stainberg.sloth.ui.main.MainFragment

class MainActivity : SlothActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNowAllowingStateLoss()
        }
    }

}
