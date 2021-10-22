package com.rabsstract.gifapp

object Libs {

    object Android {
        const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.Android.TOOLS_BUILD_GRADLE}"
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Android.KOTLIN_VERSION}"

        const val CORE_KTX = "androidx.core:core-ktx:${Versions.Android.CORE_KTX}"
        const val APP_COMPACT = "androidx.appcompat:appcompat:${Versions.Android.APP_COMPACT}"
        const val APP_MATERIAL =
            "com.google.android.material:material:${Versions.Android.APP_MATERIAL}"
        const val APP_CONSTRAINT =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.APP_CONSTRAINT}"
        const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.KTX}"
        const val VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.KTX}"



    }

    object Asynchronous {
    }

    object DependencyInversion {
    }


    object Testing {
        const val JUNIT = "junit:junit:${Versions.Testing.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Testing.JUNIT_EXT}"
        const val ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${Versions.Testing.ESPRESSO_CORE}"

    }

    object UI {

    }

    object KOTLIN {

    }
    object Network {
        const val RETROFIT_JSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT_JSON_CONVERTER}"
    }
}
