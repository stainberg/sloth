package com.stainberg.sloth.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stainberg.sloth.R
import com.stainberg.sloth.core.ui.SlothFragment
import kotlinx.android.synthetic.main.container3_fragment.*

class MainFragment3(private val name: String) : SlothFragment() {

    companion object {
        fun newInstance(tag: String) = MainFragment3(tag)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onActivityCreated(savedInstanceState)
        return inflater.inflate(R.layout.container3_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        c3t.text = name
    }
}
