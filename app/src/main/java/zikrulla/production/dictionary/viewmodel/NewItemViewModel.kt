package zikrulla.production.dictionary.viewmodel

import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

interface NewItemViewModel {

    fun insertFolder(folderEntity: FolderEntity)
    fun updateFolder(folderEntity: FolderEntity)
    fun deleteFolder(folderEntity: FolderEntity)
    fun insertDictionary(dictionaryEntity: DictionaryEntity)
}