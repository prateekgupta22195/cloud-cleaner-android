package com.pg.cloudcleaner.data.db.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.pg.cloudcleaner.data.model.LocalFile
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalFilesDao {

    @Query("SELECT * FROM localfile")
    fun getAll(): Flow<List<LocalFile>>

    @Query("SELECT * FROM localfile WHERE id == (:id)")
    suspend fun get(id: String): LocalFile?

    @RawQuery(observedEntities = [LocalFile::class])
    fun getFilesViaQuery(query: SupportSQLiteQuery): Flow<List<LocalFile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(files: List<LocalFile>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(file: LocalFile)

    @Delete
    suspend fun delete(file: LocalFile)

    @Query("DELETE FROM localfile WHERE id == (:id)")
    fun delete(id: String)

    @Query("DELETE FROM localfile WHERE id in (:id)")
    fun delete(id: List<String>)

    @Query("SELECT EXISTS(SELECT 1 FROM localfile WHERE md5 = :md5)")
    fun fileExists(md5: String): Boolean


    @Query("SELECT id FROM localfile WHERE duplicate = 1 AND (mimeType LIKE '%image%' OR mimeType LIKE '%video%')")
    fun getDuplicateFilesId(): Flow<List<String>>


}