package com.lingga.themoviedb.core.data.source.remote

import com.lingga.themoviedb.core.data.source.remote.network.MovieApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor (private val movieApiService: MovieApiService) {

}