package com.hilton.pokemonsearch.extensions

import android.graphics.Color
import com.hilton.pokemonsearch.PokemonListByNameQuery
import com.hilton.pokemonsearch.data.Ability
import com.hilton.pokemonsearch.data.PokemonAbility
import com.hilton.pokemonsearch.data.PokemonData

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