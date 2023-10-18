package com.hilton.jobsearch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hilton.jobsearch.databinding.FragmentPokemonDetailBinding

/**
 * Fragment for job detail page
 */
class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemon = args.pokemon

        val adapter = PokemonAbilitiesAdapter(args.pokemon.pokemonAbilities)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.abilities.layoutManager = layoutManager
        binding.abilities.adapter = adapter
    }
}