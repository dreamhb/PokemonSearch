package com.hilton.jobsearch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hilton.jobsearch.R
import com.hilton.jobsearch.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.uiState.collect {
                when(it) {
                    is UiState.Success -> {
                        findNavController().apply {
                            if (currentDestination?.id != R.id.jobListFragment) {
//                                navigate(HomeFragmentDirections.actionHomeFragmentToJobListFragment(
//                                ))
                            }
                        }

                    }
                    else -> {}
                }

            }
        }


        binding.search.setOnClickListener {
            lifecycleScope.launch {
                viewModel.searchPokemon(binding.searchBox.text.toString().trim())
            }
        }


        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.search.isEnabled = s?.isNotEmpty() == true && s.isNotBlank() == true
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}