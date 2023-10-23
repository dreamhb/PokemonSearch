package com.hilton.pokemonsearch.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilton.pokemonsearch.data.Ability
import com.hilton.pokemonsearch.data.PokemonAbility
import com.hilton.pokemonsearch.databinding.PokemonAbilityItemBinding

class PokemonAbilitiesAdapter(private val pokemonAbilities: List<PokemonAbility>):
    RecyclerView.Adapter<PokemonAbilitiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAbilitiesViewHolder {
        val binding = PokemonAbilityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonAbilitiesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonAbilities.size
    }

    override fun onBindViewHolder(holder: PokemonAbilitiesViewHolder, position: Int) {
        val ability = pokemonAbilities[position]
        holder.bind(ability.ability)
    }
}

class PokemonAbilitiesViewHolder(private val binding: PokemonAbilityItemBinding):
    RecyclerView.ViewHolder(binding.root) {
        fun bind(ability: Ability?) {
            ability?.let {
                binding.ab = it
            }
        }
}