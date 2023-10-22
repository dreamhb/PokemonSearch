# Project Description

This quiz uses the MVVM architecture and has 3 pages, the core fragment is **ListFragment**.
*   UI layer
  * SplashActivity
  * MainActivity
    * MainFragment(Input and Search)
    * ListFragment
    * PokemonDetailFragment
*   Data layer
  * PokemonRepository

And uses **Hilt** for dependency injection for easy scale up and testing,
uses **Jetpack Navigation** for single Activity navigation,
uses **Apollo** for GraphQL networking,
uses **Jetpack Paging** for paginating results.

also uses data binding and view binding to speed up the development.
