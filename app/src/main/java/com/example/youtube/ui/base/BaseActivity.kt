package com.example.youtube.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) :
    AppCompatActivity(
    ) {
    protected abstract val viewModel: VM
    private var lolBinding: VB? = null
    protected val binding: VB
        get() = lolBinding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lolBinding = bindingInflater.invoke(layoutInflater)
        lolBinding.run { setContentView(binding.root) }
        checkInternet()
        initViewModel()
        setupUi()
        setupObservers()
    }

    open fun checkInternet() {}

    open fun setupObservers() {}

    abstract fun setupUi()

    open fun initViewModel() {}
}