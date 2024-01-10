package zikrulla.production.dictionary.viewmodel

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface DictionaryViewModel {

//    fun insertDictionaryList(id: Long, list: List<DictionaryEntity>)

    fun insertFolder(folderEntity: FolderEntity)

    fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>>
    fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>>
}