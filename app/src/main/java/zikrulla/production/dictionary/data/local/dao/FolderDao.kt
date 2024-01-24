package zikrulla.production.dictionary.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.FolderEntity

@Dao
interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folderEntity: FolderEntity): Long

    @Update
    suspend fun updateFolder(folderEntity: FolderEntity)

    @Delete
    suspend fun deleteFolder(folderEntity: FolderEntity)

    @Query("SELECT * FROM folder WHERE baseId = :baseId ")
    fun getFolderByBaseId(baseId: Long): Flow<List<FolderEntity>>


}