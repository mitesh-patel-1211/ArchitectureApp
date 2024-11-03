package com.app.architectureapp.ui

import android.util.Log
import android.view.View
import com.app.architectureapp.core.BaseActivity
import com.app.architectureapp.databinding.ActivityMainBinding
import com.app.architectureapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {
    override fun onActivityCreated() {
        Log.d("TAG", "onActivityCreated: ${viewModel.testString}")
        binding.apply {
            tvTest.text = viewModel.testString
            btnTest.setOnClickListener(this@MainActivity)
        }
    }

    override fun onViewClick(view: View?) {
        binding.apply {
            when (view) {
                btnTest -> {
                    btnTest.showSnackBar("Test")
                }
            }
        }
    }
}