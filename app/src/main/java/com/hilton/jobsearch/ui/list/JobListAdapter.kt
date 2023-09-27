package com.hilton.jobsearch.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilton.jobsearch.data.Job
import com.hilton.jobsearch.databinding.JobItemBinding

class JobListAdapter(private val jobs: Array<Job>, private val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<JobListViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(item: Job)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        val binding = JobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int) {
        val job = jobs[position]
        holder.bind(job, itemClickListener)
    }
}

class JobListViewHolder(private val binding: JobItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(job: Job, listener: JobListAdapter.ItemClickListener) {

        binding.jobTitle.text = job.title
        binding.root.setOnClickListener {
            listener.onItemClick(job)
        }
    }
}