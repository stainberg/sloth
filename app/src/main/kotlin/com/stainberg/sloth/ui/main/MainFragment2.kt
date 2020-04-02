package com.stainberg.sloth.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.stainberg.sloth.R
import com.stainberg.sloth.core.ui.SlothFragment
import com.stainberg.sloth.databinding.Container1FragmentBinding
import com.stainberg.sloth.databinding.Container2FragmentBinding
import kotlinx.android.synthetic.main.container2_fragment.*

class MainFragment2(private val name: String) : SlothFragment() {

    private lateinit var viewModel: ContainerViewModel1

    companion object {
        fun newInstance(tag: String) = MainFragment2(tag)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onActivityCreated(savedInstanceState)
        val view = DataBindingUtil.inflate<Container2FragmentBinding>(
            inflater,
            R.layout.container2_fragment,
            container,
            false
        )
        activity?.let {
            viewModel = ViewModelProvider(it).get(ContainerViewModel1::class.java)
            view.containerData = viewModel.model1
        }
        return view.root
    }
}
