# Movie App
This is a native Android app that uses the The Open Movie Database API and display list of movies. This application uses Clean Architecture based on MVVM and Repository patterns. The application is written entirely in Kotlin. Dagger 2 is used for dependency injection. Unit tests are written using JUnit and Mockito.

## How to run the app
Please use latest version of Android studio and run the project in an emulator or in actual device.

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* Android Support Libraries
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
* [Dagger 2](https://github.com/google/dagger)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)

## Modules
This app consist of multiple modules.
* **app**: this is the main app and entry point. app module has the AppComponent from where other modules can get any external dependencies through Dagger.
* **basekit**: this module contains contract for all the utilities like SchedulerProvider, ImageDownloader, ToastMaker that entire app can use.
* **basekitimpl**: implementation of the basekit module contracts.
* **landingscreen**: this module has the actual landing screen (MovieListActivity) that user will see after app launch. This module has viewmodel which will use the repository contract to get the data from the APIs.
* **models**: this module has the data models for the entire app.
* **remotedata**: this module contains the API contact (MovieApi) and will use Retrofit to fetch the data.
* **repositorykit**: Repository contract for the app.
* **repositorykitimpl**: implementation of the repositorykit. Instance of this will be available through the AppComponent. Repository will use the APIs inside MovieApi (remotedata module) to fetch the data.

With this architecture we can easily maintain the app as we have separated module contract and implementation in separate modules. Any module if it has dependency on another module will be dependent on the contract and not the actual implementation. Example - landingscreen module depend on the repositorykit and not the actual implementation, so we can easily swap the implementation without any code change on the dependent modules. Also with this approach we will never face circular dependency problem, multiple teams can work together without breaking each other code. Also using gradle cache we can have faster build time.

## Architecture
The architecture of the project follows the principles of Clean Architecture. Here's how this project implements it:
<br/>
<br/>
![architecture](images/App_Architecture.png)

## App Demo
<br/>

![app demp](images/movie_app_demo_2.gif)
