# MyApplication – NIT3213 Students' Various Content Explorer

MyApplication is a dynamic Android app built using Kotlin, Jetpack components, and MVVM architecture. The app displays a list of different attributes of a subject depending on the student's login details.

---

## 🚀 Features

- View a list of a certain subject in a dashboard-style UI
- Tap on a card to view detailed information
- Uses modern Android architecture components (ViewModel, Navigation, Hilt)
- Data-driven UI using RecyclerView
- Clean Material Design UI with CardViews

---

## 🛠️ Built With

- Kotlin
- Jetpack Navigation Component
- ViewModel + LiveData
- Hilt for Dependency Injection
- RecyclerView + CardView
- Gson (for JSON deserialization)
- ViewBinding
- AndroidX Libraries

---

## 📦 Requirements

- Android Studio **Giraffe or newer**
- Android SDK **34+**
- Gradle **8.0+**
- Kotlin **1.9.0+**
- Internet connection (to fetch any remote dependencies)

---

## 📥 Open in Android Studio

1. Open **Android Studio**
2. Select **Open an existing project**
3. Navigate to the folder you cloned
4. Let Gradle sync & download dependencies

---

## ✅ Build & Run

1. Make sure you have a connected **emulator** or **physical device**
2. Click ▶️ **Run 'app'** from the top menu

---

## 🧩 Dependencies

Here are the key dependencies used in this project:

```kotlin
// Hilt for Dependency Injection
implementation("com.google.dagger:hilt-android:2.50")
kapt("com.google.dagger:hilt-android-compiler:2.50")

// JSON Deserialization
implementation("com.google.code.gson:gson:2.10.1")

// Jetpack Components
implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

// RecyclerView + CardView
implementation("androidx.recyclerview:recyclerview:1.3.1")
implementation("androidx.cardview:cardview:1.0.0")
```
## 🔧 To enable ViewBinding, add this to your build.gradle:
```
android {
    ...
    buildFeatures {
        viewBinding true
    }
} 
```

## 🧹 Troubleshooting
❗ If the build fails due to .kt or .xml issues:

- Clean project: Build > Clean Project
- Rebuild: Build > Rebuild Project
- Ensure your Entity model is annotated with @Parcelize if you're using navArgs to pass data between fragments.

---

# 👩‍💻 Author
Sally Kim
Built with 💜 using Android Studio

---

# 📄 License
This project is licensed under the MIT License – see the [LICENSE](app/LICENSE) file for details.