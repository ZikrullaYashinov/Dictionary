package zikrulla.production.dictionary.repository.impl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import zikrulla.production.dictionary.data.local.database.AppDatabase
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.repository.DictionariesRepository
import zikrulla.production.dictionary.utils.Constants.TAG
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

    override suspend fun updateDictionary(dictionary: DictionaryEntity) {
        appDatabase.dictionaryDao().updateDictionary(dictionary)
    }

    override suspend fun deleteDictionary(dictionary: DictionaryEntity) {
        appDatabase.dictionaryDao().deleteDictionary(dictionary)
    }

    override suspend fun insertFolder(folderEntity: FolderEntity): Long {
        return appDatabase.folderDao().insertFolder(folderEntity)
    }

    override suspend fun updateFolder(folderEntity: FolderEntity) {
        appDatabase.folderDao().updateFolder(folderEntity)
    }

    override suspend fun deleteFolder(folderEntity: FolderEntity) {

        appDatabase.folderDao().deleteFolder(folderEntity)
//        if (!folderEntity.isFolderEnd) {
//            appDatabase.folderDao().getFolderByBaseId(folderEntity.id).collect { folders ->
//                folders.map {
//                    Log.d(TAG, "deleteFolder: 111")
//                    deleteFolder(it)
//                }
//            }
////            foldersFlow.map { folders ->
////                folders.forEach {
////                    deleteFolder(it)
////                }
//////                deleteFolder(folderEntity.copy(isFolderEnd = true))
////                folders
////            }
//        }
    }

    override fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>> {
        return appDatabase.folderDao().getFolderByBaseId(baseId)
    }

    override fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>> {
        return appDatabase.dictionaryDao().getDictionaryByBaseId(baseId)
    }


}