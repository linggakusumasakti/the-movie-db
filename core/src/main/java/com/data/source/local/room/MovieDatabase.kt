package com.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.source.local.entity.MovieEntity
import com.data.source.local.entity.MovieFavoriteEntity
import com.data.source.local.entity.TvFavoriteEntity
import com.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, MovieFavoriteEntity::class, TvFavoriteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}