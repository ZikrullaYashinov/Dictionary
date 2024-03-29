package zikrulla.production.dictionary.usecase

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface InputDetailsUseCase {

    suspend fun insertDictionaryList(list: List<DictionaryEntity>)
    suspend fun insertFolder(folderEntity: FolderEntity): Long
    suspend fun updateFolder(folderEntity: FolderEntity)
    fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>>

}