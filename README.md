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
  <img src = "https://github.com/Urdzik/MovieApp/blob/master/readme/Screenshot_1582798408_framed.png?raw=true" width="330">
</div>
  
