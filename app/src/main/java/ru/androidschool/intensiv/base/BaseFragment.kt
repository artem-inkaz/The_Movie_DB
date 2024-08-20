package ru.androidschool.intensiv.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding> : BaseRxFragment() {

    protected val binding: Binding get() = _binding!!
    private var _binding: Binding? = null

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}