package ru.androidschool.intensiv.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding> : BaseRxFragment() {

    protected val binding: Binding get() = _binding!!
    protected var _binding: Binding? = null

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

    protected fun doInScopeCreated(
        state: Lifecycle.State = Lifecycle.State.CREATED,
        action: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(state) {
                action()
            }
        }
    }

    protected fun doInScopeResume(
        state: Lifecycle.State = Lifecycle.State.RESUMED,
        action: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(state) {
                action()
            }
        }
    }
}