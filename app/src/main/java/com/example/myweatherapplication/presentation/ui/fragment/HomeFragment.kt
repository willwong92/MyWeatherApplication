package com.example.myweatherapplication.presentation.ui.fragment

import com.example.myweatherapplication.R
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.myweatherapplication.presentation.ui.WeatherViewModel
import com.example.myweatherapplication.presentation.ui.state.WeatherUIState
import com.example.myweatherapplication.databinding.FragmentHomeBinding
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), MenuProvider {
    private val viewModel: WeatherViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        requestPermissions()
        observeViewModel()
    }

    private fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { permission ->
            if (permission) {
                requestLocationCoordinates()
            }
        }
        locationPermissionRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun requestLocationCoordinates() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY,null).addOnSuccessListener { location ->
                location?.let {
                    viewModel.getWeatherByCoordinates(it.latitude, it.longitude)
                }
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu.findItem(R.id.search_bar).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(queryText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(queryText: String?): Boolean {
                queryText?.let {
                    viewModel.getWeatherByCity(queryText)
                }
                return true
            }
        })
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.search_bar -> true
            else -> false
        }
    }

    private fun observeViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { uiState.collect { handleUiState(it) } }
            }
        }
    }

    private fun handleUiState(it: WeatherUIState) {
        it.errorMessage?.let { message ->
            Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_LONG).show()
        }
        binding.cityTv.text = getString(R.string.city_tv, it.cityName, it.country)
        binding.feelsLikeTv.text = it.feelsLike.toString()
        binding.speedTv.text = it.windSpeed.toString()
        binding.mainTv.text = it.main
        binding.tempTv.text = it.temp.toString()
        binding.iconIv.load("https://openweathermap.org/img/wn/${it.icon}@2x.png")
    }
}