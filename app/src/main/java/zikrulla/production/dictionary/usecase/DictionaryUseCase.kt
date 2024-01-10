package zikrulla.production.dictionary.usecase

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface DictionaryUseCase {

//    fun insertDictionaryList(id: Long, list: List<DictionaryEntity>)

    suspend fun insertFolder(folderEntity: FolderEntity)

    fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>>
    fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>>
}