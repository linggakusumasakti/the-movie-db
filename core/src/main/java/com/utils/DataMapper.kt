package com.utils

import com.data.source.local.entity.MovieEntity
import com.data.source.local.entity.MovieFavoriteEntity
import com.data.source.local.entity.TvShowEntity
import com.data.source.local.entity.TvShowFavoriteEntity
import com.data.source.remote.response.CreditResponse
import com.data.source.remote.response.movie.MovieResponse
import com.data.source.remote.response.tvshow.TvShowResponse
import com.domain.model.Credit
import com.domain.model.Movie
import com.domain.model.TvShow

object DataMapper {

    fun responseToEntitiesMovie(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id ?: 0,
                backdropPath = it.backdropPath,
                isFavorite = false,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                type = "popular"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun responseToEntitiesNowPlayingMovie(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id ?: 0,
                backdropPath = it.backdropPath,
                isFavorite = false,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                type = "nowplaying"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun responseToEntitiesUpcomingMovie(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id ?: 0,
                backdropPath = it.backdropPath,
                isFavorite = false,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                type = "upcoming"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun responseToDomainMovies(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id ?: 0,
                backdropPath = it.backdropPath,
                isFavorite = false,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                popularity = it.popularity
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun responseToDomainTvShow(input: List<TvShowResponse>): List<TvShow> {
        val list = ArrayList<TvShow>()
        input.map {
            val data = TvShow(
                id = it.id ?: 0,
                backdropPath = it.backdropPath,
                isFavorite = false,
                overview = it.overview,
                posterPath = it.posterPath,
                firstAirDate = it.firsAirDate,
                name = it.name,
                voteAverage = it.voteAverage
            )
            list.add(data)
        }
        return list
    }

    fun responseToDomainCreditMovie(input: List<CreditResponse>): List<Credit> {
        val list = ArrayList<Credit>()
        input.map {
            val data = Credit(
                castId = it.castId,
                character = it.character,
                name = it.name,
                profilePath = it.profilePath
            )
            list.add(data)
        }
        return list
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
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                type = it.type,
            )
        }

    fun mapEntityToDomainMovie(input: MovieFavoriteEntity?) = Movie(
        id = input?.id,
        backdropPath = input?.backdropPath,
        voteAverage = input?.voteAverage,
        isFavorite = input?.isFavorite,
        posterPath = input?.posterPath,
        overview = input?.overview,
        releaseDate = input?.releaseDate,
        title = input?.title,
        popularity = input?.popularity,
        type = input?.type
    )

    fun mapTvShowFavoriteToDomainTvShow(input: TvShowFavoriteEntity?) = TvShow(
        id = input?.id,
        backdropPath = input?.backdropPath,
        voteAverage = input?.voteAverage,
        isFavorite = input?.isFavorite,
        posterPath = input?.posterPath,
        overview = input?.overview,
        firstAirDate = input?.firstAirDate,
        name = input?.name
    )

    fun domainToEntityMovieFavorite(input: Movie) = MovieFavoriteEntity(
        id = input.id ?: 0,
        backdropPath = input.backdropPath,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite,
        popularity = input.popularity,
        type = input.type
    )

    fun domainToTvShowFavorite(input: TvShow) = TvShowFavoriteEntity(
        id = input.id ?: 0,
        backdropPath = input.backdropPath,
        overview = input.overview,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite,
        voteAverage = input.voteAverage,
        name = input.name,
        firstAirDate = input.firstAirDate
    )

    fun responseToEntitiesTvShow(input: List<TvShowResponse>): List<TvShowEntity> =
        input.map {
            TvShowEntity(
                id = it.id ?: 0,
                voteAverage = it.voteAverage,
                isFavorite = false,
                posterPath = it.posterPath,
                overview = it.overview,
                firstAirDate = it.firsAirDate,
                backdropPath = it.backdropPath,
                name = it.name,
                type = "popular"
            )
        }

    fun responseToEntitiesAiringTodayTvShow(input: List<TvShowResponse>): List<TvShowEntity> =
        input.map {
            TvShowEntity(
                id = it.id ?: 0,
                voteAverage = it.voteAverage,
                isFavorite = false,
                posterPath = it.posterPath,
                overview = it.overview,
                firstAirDate = it.firsAirDate,
                backdropPath = it.backdropPath,
                name = it.name,
                type = "airing"
            )
        }

    fun responseToEntitiesLatestTvShow(input: List<TvShowResponse>): List<TvShowEntity> =
        input.map {
            TvShowEntity(
                id = it.id ?: 0,
                voteAverage = it.voteAverage,
                isFavorite = false,
                posterPath = it.posterPath,
                overview = it.overview,
                firstAirDate = it.firsAirDate,
                backdropPath = it.backdropPath,
                name = it.name,
                type = "top_rated"
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


    fun responseToDomainMovie(input: MovieResponse) = Movie(
        id = input.id,
        genres = input.genres,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        isFavorite = false,
        posterPath = input.posterPath,
        overview = input.overview,
        backdropPath = input.backdropPath,
        title = input.title,
        releaseDate = input.releaseDate
    )

    fun responseToDomainTvShow(input: TvShowResponse) = TvShow(
        id = input.id,
        genres = input.genres,
        backdropPath = input.backdropPath,
        overview = input.overview,
        posterPath = input.posterPath,
        isFavorite = false,
        voteAverage = input.voteAverage,
        firstAirDate = input.firsAirDate,
        name = input.name
    )
}