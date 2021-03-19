# dvt-weather-app
### This repository contains a complete and thoroughly designed weather app for android that implements the MVVM architecture using Dagger2 for dependency injection, Retrofit for http requests, Room for offline data persistence and FusedLocationProvider for realtime location updates. The app was designed with the Agile methodology in mind.
# Overview:

* ### ___Model___
  DVT weather app uses a repository as the main model. The repository serves as the main data access layer. It creates an abstraction for the          retrieval of data from two different sources; a local source (room database) and a remote source (REST API - openweathermap). This way the actual source of data remains hidden to the rest of the app except of course the repository.

* ### ___View___
  The view comprises of a MainActivity and 3 TabLayout fragments. For brevity reasons, MainActivity in addition to managing the UI is reponsible for updating the current device location periodically and communicating data to and from its child fragments. The other 3 fragments, the Today, Forecast and Locations fragments are reponsible for displaying current weather data for specified location, displaying 5 day forecasts and displaying lists of favourite locations respectively. 
 
* ### ___ViewModel___
  I used four ViewModels. Three for sending data between model (weatherRepository) and views (fragments) and one for sending data from MainActvity to child fragments. 

# Screenshots 

<div align = "center">
  <img src = "https://github.com/aeyonblack/dvt-weather-app/blob/master/app/src/main/assets/one_weather.png?raw=true" width="300">
  <img src = "https://github.com/aeyonblack/dvt-weather-app/blob/master/app/src/main/assets/two_weather.png?raw=true" width="300">
  <img src = "https://github.com/aeyonblack/dvt-weather-app/blob/master/app/src/main/assets/three_weather.png?raw=true" width="300">
  <img src = "https://github.com/aeyonblack/dvt-weather-app/blob/master/app/src/main/assets/four_weather.png?raw=true" width="300">
  <img src = "https://github.com/aeyonblack/dvt-weather-app/blob/master/app/src/main/assets/five_weather.png?raw=true" width="300">
  <img src = "https://github.com/aeyonblack/dvt-weather-app/blob/master/app/src/main/assets/six_weather.png?raw=true" width="300">
</div>

# Technologies and Libraries used
* ### Retrofit for network requests
* ### Room for offline persitence (data caching) 
* ### Dagger for dependency injection (the D in SOLID)
* ### LiveData for implementing an observer pattern 
* ### Google Play Services for Live location updates
* ### OKhttp3 for network requests
* ### Glide for image loading 
* ### SearchView 

# Key take-aways 
* ### Dependency Injection, LiveData and MVVM are superpowers!
* ### TDD and Unit testing are inevitable (I feel bad for not implementing them here)
* ### Agile development allows for more freedom and flexibility as opposed to the Waterfall model 
* ### Using an architecture and design patterns simplifies the whole development process

# What I could Improve
### I could...
* ### write unit tests for every major feature of the app using JUnit and Mockito
* ### unit test the user interface using expresso
* ### extend the app to use RxJava for threading to reduce boilerplate (I love RxJava)
* ### decouple location finding logic from MainAcitivity
* ### add google maps and places api (couldn't do this because bank account keeps on getting declined, maps and places require a billing account)
* ### use Jenkins for CI/CD (I plan on learning this)
* ### perhaps rewrite the whole app in Kotlin
