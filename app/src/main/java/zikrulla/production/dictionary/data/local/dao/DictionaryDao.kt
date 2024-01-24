package zikrulla.production.dictionary.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

@Dao
interface DictionaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionary(dictionaryEntity: DictionaryEntity)

    @Update
    suspend fun updateDictionary(dictionaryEntity: DictionaryEntity)

    @Delete
    suspend fun deleteDictionary(dictionaryEntity: DictionaryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionaryList(list: List<DictionaryEntity>)

    @Query("SELECT * FROM dictionary WHERE baseId = :baseId ")
    fun getDictionaryByBaseId(baseId: Long): Flow<List<DictionaryEntity>>


}