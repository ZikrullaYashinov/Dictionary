package zikrulla.production.dictionary.viewmodel

import kotlinx.coroutines.flow.Flow
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface InputDetailsViewModel {

    fun insertDictionaryList(id: Long, list: List<DictionaryEntity>)

    fun insertFolder(folderEntity: FolderEntity)
    fun updateFolder(folderEntity: FolderEntity)

    fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>>
}