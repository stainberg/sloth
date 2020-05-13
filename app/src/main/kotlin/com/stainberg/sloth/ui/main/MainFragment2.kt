package com.stainberg.sloth.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stainberg.sloth.R
import com.stainberg.sloth.core.ui.SlothFragment
import com.stainberg.sloth.databinding.Container2FragmentBinding
import com.stainberg.sloth.toast.SlothToastService
import com.stainberg.sloth.ui.activity.BlankActivity
import kotlinx.android.synthetic.main.blank_activity.*
import kotlinx.android.synthetic.main.container2_fragment.*

class MainFragment2(private val name: String) : SlothFragment() {

    private lateinit var viewModel: ContainerViewModel1

    companion object {
        fun newInstance(tag: String) = MainFragment2(tag)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start_blank?.let {
            it.setOnClickListener {
                context?.let { ctx ->
//                    SlothNotificationService().build(ctx).show()
                    SlothToastService().build(ctx).show()
                }

//                startActivity(Intent(). apply {
//                    context?. let {ctx ->
//                        setClass(ctx, BlankActivity::class.java)
//                    }
//                })

//                Snackbar.make(view, "MainFragment2 SnackBar Show", Snackbar.LENGTH_LONG)
//                .addCallback(object : Snackbar.Callback() {
//                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                        finish()
//                    }
//                })
//                    .show()

//                Handler().postDelayed( {
//                    startActivity(Intent().apply {
//                        context?.let { ctx ->
//                            setClass(ctx, BlankActivity::class.java)
//                        }
//                    })
//                }, 100)
            }
        }
    }
}
