package zikrulla.production.dictionary.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.usecase.InputDetailsUseCase
import zikrulla.production.dictionary.viewmodel.InputDetailsViewModel
import javax.inject.Inject

@HiltViewModel
class InputDetailsViewModelImpl @Inject constructor(
    private val useCase: InputDetailsUseCase
) : ViewModel(), InputDetailsViewModel {

    private val _stateFolderId = MutableStateFlow<Long>(-1)
    val stateFolderId get() = _stateFolderId.asStateFlow()

    override fun insertDictionaryList(id: Long, list: List<DictionaryEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertDictionaryList(list.map {
                it.baseId = id
                it
            })
        }
    }

    override fun insertFolder(folderEntity: FolderEntity) {
        viewModelScope.launch {
            val id = useCase.insertFolder(folderEntity)
            _stateFolderId.emit(id)
        }
    }

    override fun getAllDictionariesByBaseId(baseId: Long): Flow<List<DictionaryEntity>> {
        return useCase.getAllDictionariesByBaseId(baseId)
    }

}