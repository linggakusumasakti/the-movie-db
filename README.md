# the-movie-db

[![linggakusuma](https://circleci.com/gh/linggakusuma/the-movie-db.svg?style=shield)](https://circleci.com/gh/linggakusuma/the-movie-db)

Submission for Menjadi Android Developer Expert dicoding with clean architecture(seperation of model) and modularization.

Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][3] - An Android testing framework for unit and runtime UI tests.
* [Architecture][4] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][5] - Declaratively bind observable data to UI elements.
  * [Lifecycles][6] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][7] - Build data objects that notify views when the underlying database changes.
  * [Navigation][8] - Handle everything needed for in-app navigation.
  * [Room][9] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][10] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][11] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][12] - Move widgets and transition between screens.
  * [Fragment][13] - A basic unit of composable UI.
  * [Layout][14] - Lay out widgets using different algorithms.
  * [MotionLayout][18] for manage motion and widget animation.
* Third party
  * [Glide][15] for image loading
  * [Dagger 2][16] for Dependency Injection
  * [Retrofit][17] for Networking
  * [Coroutine Flow][19] for asynchronous process and reduce callback.
  * [Shimmer][20] provides an easy way to add a shimmer effect to any view in your Android app.

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[3]: https://developer.android.com/training/testing/
[4]: https://developer.android.com/jetpack/arch/
[5]: https://developer.android.com/topic/libraries/data-binding/
[6]: https://developer.android.com/topic/libraries/architecture/lifecycle
[7]: https://developer.android.com/topic/libraries/architecture/livedata
[8]: https://developer.android.com/topic/libraries/architecture/navigation/
[9]: https://developer.android.com/topic/libraries/architecture/room
[10]: https://developer.android.com/topic/libraries/architecture/viewmodel
[11]: https://developer.android.com/guide/topics/ui
[12]: https://developer.android.com/training/animation/
[13]: https://developer.android.com/guide/components/fragments
[14]: https://developer.android.com/guide/topics/ui/declaring-layout
[15]: https://bumptech.github.io/glide/
[16]: https://github.com/google/dagger
[17]: https://square.github.io/retrofit/
[18]: https://developer.android.com/training/constraint-layout/motionlayout
[19]: https://kotlinlang.org/docs/reference/coroutines/flow.html
[20]: https://github.com/facebook/shimmer-android

License
-------

```
MIT License

Copyright (c) [2020] [Lingga Kusuma Sakti]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```