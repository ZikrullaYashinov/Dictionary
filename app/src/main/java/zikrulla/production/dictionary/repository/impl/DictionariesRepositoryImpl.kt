package zikrulla.production.dictionary.repository.impl

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.database.AppDatabase
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.repository.DictionariesRepository
import javax.inject.Inject

class DictionariesRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : DictionariesRepository {
    override suspend fun insertDictionaryList(list: List<DictionaryEntity>) {
        appDatabase.dictionaryDao().insertDictionaryList(list)
    }

    override suspend fun insertDictionary(dictionary: DictionaryEntity) {
        appDatabase.dictionaryDao().insertDictionary(dictionary)
    }

    override suspend fun insertFolder(folderEntity: FolderEntity): Long {
        return appDatabase.folderDao().insertFolder(folderEntity)
    }

    override fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>> {
        return appDatabase.folderDao().getFolderByBaseId(baseId)
    }

    override fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>> {
        return appDatabase.dictionaryDao().getDictionaryByBaseId(baseId)
    }


}