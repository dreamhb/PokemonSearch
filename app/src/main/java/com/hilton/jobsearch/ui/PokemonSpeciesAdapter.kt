package com.hilton.jobsearch.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hilton.jobsearch.data.Pokemon
import com.hilton.jobsearch.data.PokemonSpecies
import com.hilton.jobsearch.databinding.PokemonItemBinding
import com.hilton.jobsearch.databinding.PokemonSpeciesItemBinding
import com.hilton.jobsearch.extensions.asColor

class PokemonSpeciesAdapter(private val listener: PokemonAdapter.PokemonClickListener): PagingDataAdapter<PokemonSpecies,
        PokemonSpeciesViewHolder>(diffCallback = diffCallback) {
    override fun onBindViewHolder(holder: PokemonSpeciesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSpeciesViewHolder {
        val binding = PokemonSpeciesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonSpeciesViewHolder(binding)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PokemonSpecies>() {
            override fun areItemsTheSame(oldItem: PokemonSpecies, newItem: PokemonSpecies): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: PokemonSpecies, newItem: PokemonSpecies): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class PokemonSpeciesViewHolder(private val binding: PokemonSpeciesItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PokemonSpecies?, listener: PokemonAdapter.PokemonClickListener) {
        binding.pokemon = item
        binding.root.setBackgroundColor(item?.pokemon_v2_pokemoncolor?.name?.asColor() ?: Color.WHITE)

        val layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
        binding.pokemons.layoutManager = layoutManager
        val adapter = PokemonAdapter(item?.pokemon_v2_pokemons ?: emptyList(), listener)
        binding.pokemons.adapter = adapter
    }
}

class PokemonAdapter(private val pokemons: List<Pokemon>, private val listener: PokemonClickListener):
    RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon, listener)
    }

    interface PokemonClickListener {
        fun onPokemonClick(pokemon: Pokemon)
    }
}

class PokemonViewHolder(private val binding: PokemonItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemon: Pokemon, listener: PokemonAdapter.PokemonClickListener) {
        binding.pokemon = pokemon
        binding.root.setOnClickListener {
            listener.onPokemonClick(pokemon)
        }
    }
}