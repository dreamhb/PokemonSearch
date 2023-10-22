package com.hilton.jobsearch.ui.list

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hilton.jobsearch.R
import com.hilton.jobsearch.data.Pokemon
import com.hilton.jobsearch.databinding.FragmentListBinding
import com.hilton.jobsearch.extensions.toPokemonData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * List Fragment to show pokemon search results
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    @Inject lateinit var viewModel: ListViewModel
    lateinit var binding: FragmentListBinding
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PokemonSpeciesAdapter(object: PokemonAdapter.PokemonClickListener {
            override fun onPokemonClick(pokemon: Pokemon) {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToPokemonDetailFragment(
                    pokemon.toPokemonData()
                ))
            }
        })

        binding.list.adapter = adapter

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.list.layoutManager = layoutManager

        val divider = DividerItemDecoration(context, layoutManager.orientation)
        divider.setDrawable(ColorDrawable(resources.getColor(R.color.divider)))
        binding.list.addItemDecoration(divider)

        binding.list.adapter = adapter.withLoadStateFooter(PokemonLoadStateAdapter { adapter.retry() })

        lifecycleScope.launch {
            viewModel.searchPokemon(args.query).collectLatest {
                adapter.submitData(it)
            }
        }


        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                binding.apply {
                    emptyList.isVisible = isListEmpty
                    list.isVisible = !isListEmpty
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    retryButton.isVisible = loadState.source.refresh is LoadState.Error

                    val errorState = loadState.source.append as? LoadState.Error
                        ?: loadState.source.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    errorState?.let {
                        Toast.makeText(
                            requireContext(),
                            "${it.error}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}