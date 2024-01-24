package zikrulla.production.dictionary.usecase

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface NewItemUseCase {

    fun getFolders(folderEntity: FolderEntity): Flow<List<FolderEntity>>
    suspend fun insertFolder(folderEntity: FolderEntity)
    suspend fun updateFolder(folderEntity: FolderEntity)
    suspend fun deleteFolder(folderEntity: FolderEntity)
    suspend fun insertDictionary(dictionaryEntity: DictionaryEntity)
}