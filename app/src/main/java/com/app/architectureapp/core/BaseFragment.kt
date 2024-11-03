package com.app.architectureapp.core

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>() : Fragment(), OnClickListener {

    private var _binding: ViewBinding? = null
    lateinit var viewModel: VM
        private set
    lateinit var mContext: Context
    lateinit var mActivity: Activity
    private lateinit var inflate: activityInflater<VB>
    private var mLastClickTime: Long = 0

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    constructor(inflate: activityInflater<VB>) : this() {
        this.inflate = inflate
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(layoutInflater)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        onViewCreated()
    }

    private fun initViewModel() {
        val parameterizedType = javaClass.genericSuperclass as? ParameterizedType
        @Suppress("UNCHECKED_CAST")
        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(1) as? Class<VM>?
        if (vmClass != null)
            viewModel = ViewModelProvider(this)[vmClass]
        else
            Log.i("BaseFragment", "could not find VM class for $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun onViewCreated()

    abstract fun onViewClick(view: View?)

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        onViewClick(view)
    }
}