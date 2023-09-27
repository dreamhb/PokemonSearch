package com.hilton.jobsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hilton.jobsearch.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * This is the entry fragment
 */
class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            delay(1500)
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment())
        }
    }

}