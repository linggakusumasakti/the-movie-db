package com.lingga.themoviedb.di

import com.di.CoreComponent
import com.lingga.themoviedb.MainActivity
import com.lingga.themoviedb.MyApplication
import com.lingga.themoviedb.ui.detailmovie.DetailFragment
import com.lingga.themoviedb.ui.detailtvshow.DetailTvShowFragment
import com.lingga.themoviedb.ui.movie.MovieFragment
import com.lingga.themoviedb.ui.movie.MovieNowPlayingFragment
import com.lingga.themoviedb.ui.movie.MoviePopularFragment
import com.lingga.themoviedb.ui.searchmovie.SearchMovieFragment
import com.lingga.themoviedb.ui.searchtvshow.SearchTvShowFragment
import com.lingga.themoviedb.ui.setting.SettingFragment
import com.lingga.themoviedb.ui.tvshow.TvFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: DetailTvShowFragment)
    fun inject(fragment: SearchMovieFragment)
    fun inject(fragment: SearchTvShowFragment)
    fun inject(fragment: SettingFragment)
    fun inject(fragment: TvFragment)
    fun inject(fragment: MoviePopularFragment)
    fun inject(fragment: MovieNowPlayingFragment)
    fun inject(application: MyApplication)
}