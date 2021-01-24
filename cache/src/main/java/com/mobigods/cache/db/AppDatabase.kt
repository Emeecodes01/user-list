package com.mobigods.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobigods.cache.db.dao.UserDao
import com.mobigods.cache.models.UserCacheModel
import com.mobigods.cache.utils.constants.CacheConstants

@Database(
    entities = [UserCacheModel::class],
    version = CacheConstants.DB_VERSION,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
