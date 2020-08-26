package com.lingga.themoviedb.core.utils

import com.lingga.themoviedb.core.data.source.local.entity.MovieEntity
import com.lingga.themoviedb.core.data.source.remote.response.movie.MovieResponse
import com.lingga.themoviedb.core.domain.model.Movie

object DataMapper {

    fun responseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id ?: "",
                backdropPath = it.backdropPath,
                isFavorite = false,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                voteAverage = it.voteAverage,
                title = it.title,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                overview = it.overview,
                isFavorite = it.isFavorite,
                backdropPath = it.backdropPath
            )
        }

    fun domainToEntity(input: Movie) = MovieEntity(
        id = input.id ?: "",
        backdropPath = input.backdropPath,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite
    )
}