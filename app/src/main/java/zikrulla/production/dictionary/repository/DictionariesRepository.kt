package zikrulla.production.dictionary.repository

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface DictionariesRepository {

    suspend fun insertDictionaryList(list: List<DictionaryEntity>)
    suspend fun insertDictionary(dictionary: DictionaryEntity)
    suspend fun insertFolder(folderEntity: FolderEntity): Long
    fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>>
    fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>>

}