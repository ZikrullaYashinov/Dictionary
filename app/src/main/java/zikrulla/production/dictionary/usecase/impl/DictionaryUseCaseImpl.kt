package zikrulla.production.dictionary.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.repository.DictionariesRepository
import zikrulla.production.dictionary.usecase.DictionaryUseCase
import zikrulla.production.dictionary.viewmodel.DictionaryViewModel
import javax.inject.Inject

class DictionaryUseCaseImpl @Inject constructor(
    private val repository: DictionariesRepository
) : DictionaryUseCase {
    override suspend fun insertFolder(folderEntity: FolderEntity) {
        repository.insertFolder(folderEntity)
    }

    override fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>> {
        return repository.getAllDictionariesByBaseId(baseId)
    }

    override fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>> {
        return repository.getAllFolderByBaseId(baseId).map { folderList ->
            val list1 = ArrayList<FolderEntity>()
            val list2 = ArrayList<FolderEntity>()
            folderList.map {
                if (it.isFolderEnd)
                    list2.add(it)
                else
                    list1.add(it)
            }
            list1.addAll(list2)
            list1
        }
    }
}