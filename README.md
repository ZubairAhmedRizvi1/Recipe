# Recipe App - Clean Architecture with Kotlin & Retrofit

A modern Android recipe application built with **Clean Architecture**, **Kotlin**, **Retrofit**, and **Hilt Dependency Injection**.

## Features

✅ **Dashboard** with recipe list and categories
✅ **Search** recipes by name
✅ **Filter** recipes by category (Seafood, Dessert, Vegetarian, Breakfast)
✅ **Recipe Details** view with instructions
✅ **External Links** (YouTube, Source URL)
✅ **Image Loading** with Glide
✅ **MVVM Architecture**
✅ **Dependency Injection** with Hilt
✅ **Coroutines** for async operations
✅ **Navigation** with Fragment Navigation Component

## Project Structure

```
app/src/main/java/com/recipe/app/
├── di/                          # Dependency Injection
│   ├── RecipeApplication.kt
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
├── data/                        # Data Layer
│   ├── remote/
│   │   ├── RecipeApiService.kt
│   │   └── dto/
│   │       └── RecipeDto.kt
│   └── repository/
│       └── RecipeRepositoryImpl.kt
├── domain/                      # Domain Layer (Business Logic)
│   ├── model/
│   │   └── Recipe.kt
│   ├── repository/
│   │   └── RecipeRepository.kt
│   ├── usecase/
│   │   ├── SearchRecipesUseCase.kt
│   │   ├── GetRecipesByCategoryUseCase.kt
│   │   └── GetRecipeDetailUseCase.kt
│   └── util/
│       └── Result.kt
└── presentation/                # Presentation Layer (UI)
    ├── ui/
    │   ├── MainActivity.kt
    │   ├── fragments/
    │   │   ├── DashboardFragment.kt
    │   │   └── RecipeDetailFragment.kt
    │   └── adapter/
    │       └── RecipeAdapter.kt
    └── viewmodel/
        └── RecipeViewModel.kt
```

## Clean Architecture Layers

### 1. **Data Layer** (data/)
- Handles all data operations
- Implements Repository pattern
- Contains API services, DTOs, and local database

### 2. **Domain Layer** (domain/)
- Contains business logic
- Defines use cases
- Platform independent

### 3. **Presentation Layer** (presentation/)
- UI components (Activities, Fragments)
- ViewModels
- Adapters and UI logic

## Technologies Used

- **Kotlin** - Primary language
- **Retrofit** - API calls
- **OkHttp** - HTTP client with logging
- **Hilt** - Dependency Injection
- **Coroutines** - Asynchronous programming
- **MVVM** - Architecture pattern
- **LiveData** - Reactive data binding
- **Navigation Component** - Fragment navigation
- **Glide** - Image loading
- **RecyclerView** - List display

## API Used

**TheMealDB API** - Free recipe database
- Base URL: `https://www.themealdb.com/api/json/v1/1/`

## How to Run

1. Clone the repository
```bash
git clone https://github.com/ZubairAhmedRizvi1/Recipe.git
```

2. Open in Android Studio

3. Build and run on an emulator or device

## Dependencies

Key dependencies are configured in `app/build.gradle.kts`:
- Retrofit 2.9.0
- Hilt 2.48
- Coroutines 1.7.1
- Room 2.5.2
- Navigation 2.7.4
- Glide 4.16.0

## License

MIT License - feel free to use this project for learning purposes.
