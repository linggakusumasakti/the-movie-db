package com.lingga.themoviedb.core.utils

import com.lingga.themoviedb.core.data.source.local.entity.MovieEntity
import com.lingga.themoviedb.core.data.source.local.entity.TvShowEntity
import com.lingga.themoviedb.core.data.source.remote.response.movie.MovieResponse
import com.lingga.themoviedb.core.data.source.remote.response.tvshow.TvShowResponse
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.model.TvShow

object DataMapper {

    fun responseToEntitiesMovie(input: List<MovieResponse>): List<MovieEntity> {
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

    fun mapEntitiesToDomainMovie(input: List<MovieEntity>): List<Movie> =
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

    fun domainToEntityMovie(input: Movie) = MovieEntity(
        id = input.id ?: "",
        backdropPath = input.backdropPath,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite
    )

    fun responseToEntitiesTvShow(input: List<TvShowResponse>): List<TvShowEntity> =
        input.map {
            TvShowEntity(
                id = it.id ?: "",
                voteAverage = it.voteAverage,
                isFavorite = false,
                posterPath = it.posterPath,
                overview = it.overview,
                firstAirDate = it.firsAirDate,
                backdropPath = it.backdropPath,
                name = it.name
            )
        }


    fun mapEntitiesToDomainTvShow(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                name = it.name,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite,
                voteAverage = it.voteAverage
            )
        }
}