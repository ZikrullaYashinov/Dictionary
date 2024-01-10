package zikrulla.production.dictionary.usecase.impl

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.repository.DictionariesRepository
import zikrulla.production.dictionary.usecase.InputDetailsUseCase
import javax.inject.Inject

class InputDetailsUseCaseImpl @Inject constructor(
    private val repository: DictionariesRepository
) : InputDetailsUseCase {
    override suspend fun insertDictionaryList(list: List<DictionaryEntity>) {
        repository.insertDictionaryList(list)
    }

    override suspend fun insertFolder(folderEntity: FolderEntity): Long {
        return repository.insertFolder(folderEntity)
    }

    override fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>> {
        return repository.getAllDictionariesByBaseId(baseId)
    }
}