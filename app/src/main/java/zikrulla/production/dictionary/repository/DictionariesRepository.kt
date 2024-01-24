package zikrulla.production.dictionary.repository

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface DictionariesRepository {

    suspend fun insertDictionaryList(list: List<DictionaryEntity>)
    suspend fun insertDictionary(dictionary: DictionaryEntity)
    suspend fun updateDictionary(dictionary: DictionaryEntity)
    suspend fun deleteDictionary(dictionary: DictionaryEntity)
    suspend fun insertFolder(folderEntity: FolderEntity): Long
    suspend fun updateFolder(folderEntity: FolderEntity)
    suspend fun deleteFolder(folderEntity: FolderEntity)
    fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>>
    fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>>

}