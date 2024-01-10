package zikrulla.production.dictionary.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.usecase.DictionaryUseCase
import zikrulla.production.dictionary.viewmodel.DictionaryViewModel
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModelImpl @Inject constructor(
    private val useCase: DictionaryUseCase
) : ViewModel(), DictionaryViewModel {
    override fun insertFolder(folderEntity: FolderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertFolder(folderEntity)
        }
    }

    override fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>> {
        return useCase.getAllDictionariesByBaseId(baseId)
    }

    override fun getAllFolderByBaseId(baseId: Long): Flow<List<FolderEntity>> {
        return useCase.getAllFolderByBaseId(baseId)
    }
}