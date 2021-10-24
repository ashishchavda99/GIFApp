package com.rabstract.data.di


/**
 * Main Koin DI component.
 */
fun configureAndroidTestAppComponent()
        = listOf(
    repositoryAndroidTestModule,
    databaseModule
    )

