package com.uogames.locationtest.ui.fragment.location


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.uogames.locationtest.R
import com.uogames.locationtest.databinding.FragmentLocationBinding
import com.uogames.locationtest.ui.dialog.TextDialog
import com.uogames.locationtest.ui.fragment.fullimage.FullImageFragment
import com.uogames.locationtest.utlis.Permission
import com.uogames.locationtest.utlis.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.InputStream

@AndroidEntryPoint
class LocationFragment : Fragment() {

	private val vm: LocationsViewModel by viewModels()

	private var _bind: FragmentLocationBinding? = null
	private val bind get() = _bind!!

	private val launcher = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
		val streams = it.mapNotNull { uri -> requireContext().contentResolver.openInputStream(uri) }
		vm.addImages(streams)
	}

	private val l = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
		val r = it.data?.clipData
		val array = ArrayList<InputStream>()
		repeat(r?.itemCount ?: 0) { iterator ->
			val inputStream = requireContext().contentResolver.openInputStream(r!!.getItemAt(iterator).uri)
			if (inputStream != null) array.add(inputStream)
		}
		vm.addImages(array)
	}

	private val callback by lazy {
		object : OnBackPressedCallback(false) {
			override fun handleOnBackPressed() {
				vm.editMode.value = false
			}
		}
	}

	private var observers: Job? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentLocationBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

		bind.mcvAddPhoto.setOnClickListener {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Permission.READ_MEDIA_IMAGES.requestPermission(requireActivity()) {
				if (it) {
					launcher.launch(
						PickVisualMediaRequest
							.Builder()
							.setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
							.build()
					)
				}
			} else {
				l.launch(Intent().apply {
					action = Intent.ACTION_PICK
					type = "image/*"
					putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
				})
			}
		}
		bind.rvLocationImages.adapter = vm.adapter
		bind.btnDelete.setOnClickListener { vm.delete() }
		bind.mcvStreets.setOnClickListener {
			TextDialog.show(requireContext(), R.string.streets, vm.sectionName.value) {
				vm.sectionName.value = it
			}
		}
		bind.tvLocationName.setOnClickListener {
			TextDialog.show(requireContext(), R.string.locations, vm.locationName.value) {
				vm.locationName.value = it
			}
		}
	}

	override fun onStart() {
		super.onStart()
		observers = lifecycleScope.launch {
			vm.editMode.observe(this) {
				bind.btnDelete.visibility = if (it) View.VISIBLE else View.GONE
				callback.isEnabled = it
			}
			vm.isLoading.observe(this) {
				bind.curtain.visibility = if (it) View.VISIBLE else View.GONE
			}
			vm.adapter.selectImage.observe(this) {
				if (vm.editMode.value) return@observe
				requireActivity().findNavController(R.id.nav_host_fragment).navigate(
					R.id.fullImageFragment, bundleOf(
						FullImageFragment.IMAGE_URI to it
					)
				)
			}
			vm.sectionName.observe(this) {
				bind.tvSectionName.text = it.ifEmpty { requireContext().getString(R.string.streets) }
			}
			vm.locationName.observe(this){
				bind.tvLocationName.text = it.ifEmpty { requireContext().getString(R.string.location_name) }
			}
		}
	}

	override fun onStop() {
		super.onStop()
		observers?.cancel()
	}


	override fun onDestroyView() {
		bind.rvLocationImages.adapter = null
		_bind = null
		super.onDestroyView()
	}


}