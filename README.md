# Movie App - Android App using Compose and TMDB API

This repository showcases a modern Android movie browsing application meticulously crafted with the latest Android development paradigms. Leveraging the power of Jetpack Compose for a declarative UI, it consumes data from the open-source The Movie Database (TMDB) API to provide a rich and engaging user experience. The app adheres to Clean Architecture principles and employs a robust tech stack for scalability and maintainability.

## Overview

Movie App offers a seamless way to explore a vast universe of movies and TV shows. Its intuitive design allows users to effortlessly discover trending titles, search for specific content, and delve into detailed information. Built with a strong emphasis on modern Android development best practices, this app exemplifies the synergy of Jetpack Compose and a well-defined architecture.

## Key Features

* **Home Screen with Tabbed Interface:** The main screen presents three distinct tabs for browsing movies:
    * **List View:** Displays movies in a vertically scrollable list format.
    * **Grid View:** Shows movies in an aesthetically pleasing grid layout, allowing for more titles to be viewed at once.
* **Movie Listings:** Displays a curated selection of movies, potentially including now playing, popular, and top-rated titles (depending on the specific tab).
* **Search Functionality:** A dedicated search screen enables users to quickly find movies or TV shows by entering keywords.
* **Detailed Movie Information:** The detail screen provides comprehensive information about a selected movie, including:
    * Synopsis/Overview
    * Release Date
    * Genre(s)
    * User Ratings
    * Cast and Crew
    * Trailers (if available via TMDB API)
    * Similar Movies
* **Visually Rich UI:** Utilizes Coil for efficient and asynchronous loading and display of high-quality movie posters and backdrop images.
* **Modern Android Architecture:** Implements Clean Architecture principles, separating concerns into distinct layers (Presentation, Domain, Data) for improved testability and maintainability.
* **Reactive Programming:** Leverages Kotlin Coroutines and Flow for handling asynchronous operations and managing data streams in a concise and efficient manner.
* **Dependency Injection:** Utilizes Hilt for seamless dependency management, promoting loose coupling and simplifying testing.
* **Declarative UI with Jetpack Compose:** Builds the entire user interface using Jetpack Compose, resulting in a more concise, expressive, and maintainable codebase.
* **MVVM (Model-View-ViewModel):** Employs the MVVM architectural pattern within the Presentation layer to separate UI logic from the UI itself, enhancing testability and maintainability.
* **Efficient Data Loading with Paging 3:** Integrates the Paging 3 library to load and display large lists of movies and search results in an efficient and performant manner, reducing memory usage and improving scrolling smoothness.

## Tech Stack

This project harnesses the power of the following cutting-edge Android technologies:

* **Kotlin:** The expressive and concise programming language chosen for Android development.
* **Jetpack Compose:** Android's modern declarative UI toolkit for building native UIs.
* **MVVM (Model-View-ViewModel):** A robust architectural pattern for structuring the presentation layer.
* **Clean Architecture:** An architectural philosophy focused on separating concerns into independent layers.
* **Kotlin Coroutines:** For managing concurrent tasks and asynchronous operations in a structured way.
* **Kotlin Flow:** For handling asynchronous data streams reactively.
* **Hilt:** A dependency injection library built on top of Dagger for Android.
* **Retrofit:** A type-safe HTTP client for making network requests to the TMDB API.
* **Coil:** An image loading library for Android backed by Kotlin Coroutines.
* **Jetpack Navigation Compose:** For declarative and type-safe navigation within the Compose UI.
* **Paging 3:** A Jetpack library for loading and displaying large sets of data efficiently and gracefully.

## Screen Breakdown

* **Home Screen:**
    * **Tab 1 (Movies - Grid):** Presents movies in a visually appealing grid layout.
    * **Tab 2 (Movies - List):** Displays a vertical scrolling list of movies.
* **Search Screen:**
    * Provides a user interface with an `Search Bar` for entering search queries.
    * Displays search results dynamically as the user types or upon submission.
    * Presents search results as a list or grid of movie/TV show items.
* **Detail Screen:**
    * Displays comprehensive information about a selected movie.
    * Includes elements to show the movie poster, backdrop image, title, release date, genre(s), user rating, synopsis, cast, crew, trailers (if available), and similar movies.

* **SNAP SHOTS**

<img src="https://github.com/user-attachments/assets/d2eab289-5eca-4de6-9091-b8b480aadf02" width="250">  <img src="https://github.com/user-attachments/assets/c15f6912-95c0-4be3-af1b-a1aa693ee554" width="250">


<img src="https://github.com/user-attachments/assets/33d6d962-f4db-4b3b-8827-60d6cf428854" width="250">  <img src="https://github.com/user-attachments/assets/38327d3e-80ff-4874-a285-86f71223cf86" width="250">


## TMDB API

This application relies on the open-source [The Movie Database (TMDB) API](https://www.themoviedb.org/documentation/api) to fetch all movie data. You will need to obtain your own API key from TMDB to run the application.

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    ```
2.  **Obtain a TMDB API Key:**
    * Sign up for a free account at [https://www.themoviedb.org/signup](https://www.themoviedb.org/signup).
    * Request an API key (v3 Auth) from your account settings.
3.  **Configure the API Key:**
    * Open the `Constants` file in your project.
    * Add the following line, replacing `<YOUR_TMDB_API_KEY>` with your actual API key:
        ```properties
        API_KEY="<YOUR_TMDB_API_KEY>"
        ```
4.  **Build and Run:**
    * Open the project in Android Studio.
    * Build and run the application on an emulator or a physical Android device.

## Architecture Overview

The application adheres to Clean Architecture, organizing the codebase into the following layers:

* **Presentation Layer (Compose UI + ViewModel):** Contains the UI components built with Jetpack Compose and the ViewModels that hold the UI state and handle user interactions. ViewModels communicate with Use Cases in the Domain Layer.
* **Domain Layer (Use Cases/Interactors + Entities):** Contains the business logic of the application. Use Cases encapsulate specific actions that can be performed. Entities represent the core data objects. This layer is independent of any framework or library.
* **Data Layer (Repositories + Data Sources):** Responsible for fetching and managing data. Repositories provide a clean interface for data access to the Domain Layer, abstracting the underlying data sources (e.g., remote API, local database). Data Sources handle the actual data retrieval and persistence.

Dependency Injection with Hilt ensures that dependencies between these layers are managed effectively. Kotlin Coroutines and Flow are used extensively for asynchronous operations and data stream management within and between these layers.

## Future Enhancements

* **TV Show Support:** Extend the application to include browsing and details for TV shows.
* **Offline Caching:** Implement local caching of movie data and images for offline access.
* **User Authentication:** Integrate user accounts for features like personalized watchlists and ratings.
* **Watchlist Functionality:** Allow users to save movies to a personal watchlist.
* **Filtering and Sorting:** Add options to filter and sort movie lists based on various criteria (e.g., genre, release date, rating).
* **Deep Linking:** Implement deep linking to allow sharing and direct navigation to specific movie details.
* **Unit and Integration Tests:** Write comprehensive tests to ensure the reliability and stability of the application.

## Contributing

Contributions are highly encouraged! If you have any suggestions, bug reports, or would like to contribute code, please feel free to open an issue or submit a pull request.
