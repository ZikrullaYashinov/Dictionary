package zikrulla.production.dictionary.usecase.impl

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.repository.DictionariesRepository
import zikrulla.production.dictionary.usecase.NewItemUseCase
import javax.inject.Inject

class NewItemUseCaseImpl @Inject constructor(
    private val repository: DictionariesRepository
) : NewItemUseCase {
    override fun getFolders(folderEntity: FolderEntity): Flow<List<FolderEntity>> {
        return repository.getAllFolderByBaseId(folderEntity.id)
    }

    override suspend fun insertFolder(folderEntity: FolderEntity) {
        repository.insertFolder(folderEntity)
    }

    override suspend fun updateFolder(folderEntity: FolderEntity) {
        repository.updateFolder(folderEntity)
    }

    override suspend fun deleteFolder(folderEntity: FolderEntity) {
        repository.deleteFolder(folderEntity)
    }

    override suspend fun insertDictionary(dictionaryEntity: DictionaryEntity) {
        repository.insertDictionary(dictionaryEntity)
    }

}