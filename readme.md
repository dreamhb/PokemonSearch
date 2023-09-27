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

## Used Libraries
### Hilt
uses Hilt for dependency injection for easy scale up and testing
### Jetpack Navigation
uses Jetpack Navigation for single Activity navigation
### Retrofit
Because https://api.graphql.jobs is down, so use **Retrofit** to mock the api instead. We adopt the MVVM architecture,
so we can easily replace **Retrofit** in the Data layer with other tools when the api is online.

### View binding & Data binding


