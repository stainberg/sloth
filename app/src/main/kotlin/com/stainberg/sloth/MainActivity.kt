package com.stainberg.sloth

import android.os.Bundle
import com.stainberg.sloth.core.ui.SlothActivity
import com.stainberg.sloth.ui.main.MainFragment1
import com.stainberg.sloth.ui.main.MainFragment2
import com.stainberg.sloth.ui.main.MainFragment3

class MainActivity : SlothActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, MainFragment1.newInstance("container1"))
                .replace(R.id.container2, MainFragment2.newInstance("container2"))
                .replace(R.id.container3, MainFragment3.newInstance("container3"))
                .commitNowAllowingStateLoss()
        }
    }

}
