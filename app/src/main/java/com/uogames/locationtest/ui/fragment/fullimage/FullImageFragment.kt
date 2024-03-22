package com.uogames.locationtest.ui.fragment.fullimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.uogames.locationtest.databinding.FragmentFullImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullImageFragment : Fragment() {

	companion object {
		const val IMAGE_URI = "FullImageFragment.IMAGE_URI"
	}

	private var _bind: FragmentFullImageBinding? = null
	private val bind get() = _bind!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentFullImageBinding.inflate(inflater, container, false)
		return _bind?.root

	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val uri = arguments?.getString(IMAGE_URI) ?: findNavController().popBackStack()

		Glide.with(requireContext()).load(uri).into(bind.img)
		bind.back.setOnClickListener { findNavController().popBackStack() }

	}

	override fun onDestroyView() {
		_bind = null
		super.onDestroyView()
	}


}