package com.hilton.jobsearch.data

import android.os.Parcelable
import com.hilton.jobsearch.PokemonListByNameQuery
import kotlinx.parcelize.Parcelize


typealias PokemonSpecies = PokemonListByNameQuery.Pokemon_v2_pokemonspecy
typealias Pokemon = PokemonListByNameQuery.Pokemon_v2_pokemon

@Parcelize
data class PokemonData(
    val id: Int,
    val name: String,
    val pokemonAbilities: List<PokemonAbility>,
): Parcelable

@Parcelize
data class PokemonAbility(
    val id: Int,
    val ability: Ability?
): Parcelable

@Parcelize
data class Ability(
    val name: String
): Parcelable