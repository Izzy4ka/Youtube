package com.example.youtube.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) :
    AppCompatActivity(
    ) {
    protected abstract val viewModel: VM
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _binding.run { setContentView(binding.root) }
        checkInternet()
        setupUI()
        setupObservers()
    }

    open fun checkInternet() {}

    open fun setupObservers() {}

    abstract fun setupUI()

}