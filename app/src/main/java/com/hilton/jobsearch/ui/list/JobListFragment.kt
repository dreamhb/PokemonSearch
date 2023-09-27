package com.hilton.jobsearch.ui.list

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hilton.jobsearch.data.Job
import com.hilton.jobsearch.databinding.FragmentJobListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for job list page
 */
@AndroidEntryPoint
class JobListFragment : Fragment() {

    private lateinit var binding: FragmentJobListBinding
    private val args : JobListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = JobListAdapter(args.jobs, object : JobListAdapter.ItemClickListener {
            override fun onItemClick(item: Job) {
                findNavController().navigate(JobListFragmentDirections.actionJobListFragmentToJobDetailFragment(item))
            }
        })

        binding.list.adapter = adapter

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.list.layoutManager = layoutManager

        val divider = DividerItemDecoration(context, layoutManager.orientation)
        divider.setDrawable(ColorDrawable(Color.parseColor("#ffcccccc")))
        binding.list.addItemDecoration(divider)
    }
}