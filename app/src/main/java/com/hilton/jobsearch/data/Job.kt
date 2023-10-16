package com.hilton.jobsearch.data

import android.os.Parcelable
import com.hilton.jobsearch.PokemonListByNameQuery
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    val title: String,
    val company: Company,
    val description: String
): Parcelable


@Parcelize
data class Company(
    val name: String,
    val logoUrl: String
): Parcelable

typealias PokemonSpec = PokemonListByNameQuery.Pokemon_v2_pokemonspecy