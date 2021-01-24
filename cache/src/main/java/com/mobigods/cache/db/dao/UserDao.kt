package com.mobigods.cache.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mobigods.cache.models.UserCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveUser(userCacheModel: UserCacheModel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun saveAllUsers(users: List<UserCacheModel>): List<Long>

    @Query("SELECT * FROM users")
    abstract fun getAllUsers(): Flow<List<UserCacheModel>>

    @Query("SELECT * FROM users WHERE id =:userId ")
    abstract fun getUser(userId: String): Flow<UserCacheModel>

    @Update
    abstract suspend fun updateUser(userCacheModel: UserCacheModel): Int
}
