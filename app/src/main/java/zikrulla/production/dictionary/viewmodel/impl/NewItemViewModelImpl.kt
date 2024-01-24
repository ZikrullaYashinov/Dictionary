package zikrulla.production.dictionary.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.usecase.NewItemUseCase
import zikrulla.production.dictionary.utils.Constants.TAG
import zikrulla.production.dictionary.viewmodel.NewItemViewModel
import javax.inject.Inject

@HiltViewModel
class NewItemViewModelImpl @Inject constructor(
    private val newItemUseCase: NewItemUseCase
) : ViewModel(), NewItemViewModel {

    private val _stateCheck = MutableStateFlow(false)
    val stateCheck = _stateCheck.asStateFlow()

    fun setCheck(param: Boolean? = null) {
        if (param == null) _stateCheck.value = !_stateCheck.value else _stateCheck.value = param
    }

    override fun insertFolder(folderEntity: FolderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            newItemUseCase.insertFolder(folderEntity)
        }
    }

    override fun updateFolder(folderEntity: FolderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            newItemUseCase.updateFolder(folderEntity)
        }
    }

    override fun deleteFolder(folderEntity: FolderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            newItemUseCase.deleteFolder(folderEntity)
            newItemUseCase.getFolders(folderEntity).collect {
                it.forEach {
                    deleteFolder(it)
                    Log.d(TAG, "deleteFolder: 111")
                }
            }
            Log.d(TAG, "deleteFolder: 111")
        }
    }

    override fun insertDictionary(dictionaryEntity: DictionaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            newItemUseCase.insertDictionary(dictionaryEntity)
        }
    }

}