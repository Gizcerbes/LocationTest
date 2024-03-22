package com.uogames.locationtest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.uogames.locationtest.R
import com.uogames.locationtest.SplashEnd

class EmptyFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return RelativeLayout(requireContext())
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val navHost = requireActivity().findNavController(R.id.nav_host_fragment)
		val graph = navHost.navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(R.id.bottomFragment) }
		navHost.setGraph(graph, null)
		(requireContext() as SplashEnd).splashEnd()
	}

}