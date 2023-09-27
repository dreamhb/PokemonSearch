# Project Description

This demo uses the MVVM architecture:
* UI layer
  * HomeFragment
  * HomeViewModel 
  * JobListFragment
  * JobDetailFragment
  * MainActivity
  * WelcomeFragment
* Data layer
  * JobSearchRepository

and uses Hilt for dependency injection for easy scale up and testing,
uses Jetpack Navigation for single Activity navigation,
uses Retrofit for networking,
also uses data binding and view binding to speed up the development.

Because https://api.graphql.jobs is down, so use **Retrofit** to mock the api instead. We adopt the MVVM architecture,
so we can easily replace **Retrofit** in the Data layer with other tools when the api is online.
