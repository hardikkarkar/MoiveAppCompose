// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id ("com.android.library") version "8.0.2" apply false
    id ("org.jetbrains.kotlin.kapt") version "1.7.20" apply false
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false
    //ksp
    id ("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}