package com.app.architectureapp.core

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

typealias activityInflater<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel>(
    private val activityInflater: activityInflater<VB>
) : AppCompatActivity(), OnClickListener {

    private var _binding: ViewBinding? = null
    lateinit var viewModel: VM
        private set
    private var mLastClickTime: Long = 0

    @Suppress("UNCHECKED_CAST")
    val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = activityInflater.invoke(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        onActivityCreated()
    }

    private fun initViewModel(){
        val parameterizedType = javaClass.genericSuperclass as? ParameterizedType
        @Suppress("UNCHECKED_CAST")
        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(1) as? Class<VM>?
        if (vmClass != null)
            viewModel = ViewModelProvider(this)[vmClass]
        else
            Log.i("BaseActivity", "could not find VM class for $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun onActivityCreated()

    abstract fun onViewClick(view: View?)

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        onViewClick(view)
    }

}