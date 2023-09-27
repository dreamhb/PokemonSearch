package com.hilton.jobsearch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hilton.jobsearch.databinding.FragmentJobDetailBinding

/**
 * Fragment for job detail page
 */
class JobDetailFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailBinding
    private val args: JobDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.job = args.job
    }
}