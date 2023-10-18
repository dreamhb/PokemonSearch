package com.hilton.jobsearch.extensions

import android.graphics.Color
import com.hilton.jobsearch.PokemonListByNameQuery
import com.hilton.jobsearch.data.Ability
import com.hilton.jobsearch.data.PokemonAbility
import com.hilton.jobsearch.data.PokemonData

fun String.asColor(): Int {
    return when(this) {
        "blue" -> Color.BLUE
        "red"  -> Color.RED
        "purple" -> Color.parseColor("#ffaa66cc")
        "yellow" -> Color.YELLOW
        "brown" -> Color.parseColor("#ff5c4033")
        "gray" -> Color.GRAY
        "green" -> Color.GREEN
        else -> Color.WHITE
    }
}

fun PokemonListByNameQuery.Pokemon_v2_pokemon.toPokemonData(): PokemonData {
    return PokemonData(id, name, pokemon_v2_pokemonabilities.map { it.toPokemonAbility() })
}

fun PokemonListByNameQuery.Pokemon_v2_pokemonability.toPokemonAbility(): PokemonAbility {
    return PokemonAbility(id, pokemon_v2_ability?.toAbility())
}

fun PokemonListByNameQuery.Pokemon_v2_ability.toAbility(): Ability {
    return Ability(name)
}