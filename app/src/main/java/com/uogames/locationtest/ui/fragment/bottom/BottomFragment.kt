package com.uogames.locationtest.ui.fragment.bottom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.uogames.locationtest.databinding.FragmentNavigationBinding
import com.uogames.locationtest.utlis.addOnStateCallback
import com.uogames.locationtest.utlis.observe
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BottomFragment : Fragment() {


	private var _bind: FragmentNavigationBinding? = null
	private val bind get() = _bind!!

	private var observers: Job? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentNavigationBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onStart() {
		observers = lifecycleScope.launch {

		}
		super.onStart()
	}

	override fun onStop() {
		observers?.cancel()
		super.onStop()
	}


	override fun onDestroyView() {
		_bind = null
		super.onDestroyView()
	}

}