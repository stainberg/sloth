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
import kotlinx.android.synthetic.main.container1_fragment.*

class MainFragment1(private val name: String) : SlothFragment() {

    private lateinit var viewModel: ContainerViewModel1
    private var index = 0

    companion object {
        fun newInstance(tag: String) = MainFragment1(tag)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onActivityCreated(savedInstanceState)
        val view = DataBindingUtil.inflate<Container1FragmentBinding>(
            inflater,
            R.layout.container1_fragment,
            container,
            false
        )
        activity?.let {
            viewModel = ViewModelProvider(it).get(ContainerViewModel1::class.java)
            view.containerData = viewModel.model1
        }
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model1.title.set("$index $name")
        c2t.text = "$name $index"
        b1t.setOnClickListener {
            index++
            viewModel.model1.title.set("$index $name")
            c2t.text = "$name $index"
        }
    }
}
