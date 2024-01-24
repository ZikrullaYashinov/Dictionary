package zikrulla.production.dictionary.ui.screen.new_item

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.enums.Type
import zikrulla.production.dictionary.databinding.FragmentNewItemBinding
import zikrulla.production.dictionary.utils.Constants
import zikrulla.production.dictionary.utils.Constants.TAG
import zikrulla.production.dictionary.viewmodel.impl.NewItemViewModelImpl

@AndroidEntryPoint
class NewItemFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = requireArguments().getSerializable(Constants.ARG_TYPE) as Type
            when (type) {
                Type.NEW_FOLDER -> {
                    folderEntity =
                        requireArguments().getSerializable(Constants.ARG_OBJECT) as FolderEntity

                }

                Type.EDIT_FOLDER -> {
                    folderEntity =
                        requireArguments().getSerializable(Constants.ARG_OBJECT) as FolderEntity
                }

                Type.NEW_DICTIONARY -> {
                    folderEntity =
                        requireArguments().getSerializable(Constants.ARG_OBJECT) as FolderEntity

                }

                Type.EDIT_DICTIONARY -> {
                    TODO()
                }

                Type.NEW_FOLDER_BASE_0 -> Unit
            }
        }
    }

    private var folderEntity: FolderEntity? = null
    private var dictionaryEntity: DictionaryEntity? = null
    private lateinit var type: Type

    private lateinit var binding: FragmentNewItemBinding
    private val viewModel by viewModels<NewItemViewModelImpl>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
        observe()
    }

    private fun observe() {
        viewModel.stateCheck.onEach {
            Log.d(TAG, "observe: $it")
            val resId = if (it) R.drawable.ic_checked else R.drawable.ic_unchecked
            binding.isFolderCheck.setImageResource(resId)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun load() {
        binding.apply {
            when (type) {
                Type.NEW_FOLDER -> {
                    screenTitle.text = getString(R.string.new_folder)
                    btnImport.isVisible = true
                    isFolder.isVisible = true
                }

                Type.EDIT_FOLDER -> {
                    screenTitle.text = folderEntity?.name
                    etInput0.setText(folderEntity?.name)
                    btnImport.isVisible = folderEntity?.isFolderEnd == true
                    isFolder.isVisible = false
                    delete.isVisible = true
                    viewModel.setCheck(!folderEntity?.isFolderEnd!!)
                }

                Type.NEW_DICTIONARY -> {
                    screenTitle.text = getString(R.string.new_dictionary)
                    btnImport.isVisible = false
                    input0.isVisible = false
                    input1.isVisible = true
                    input2.isVisible = true
                    input3.isVisible = true
                    input4.isVisible = true
                }

                Type.EDIT_DICTIONARY -> {
                    delete.isVisible = true
                }

                Type.NEW_FOLDER_BASE_0 -> {
                    screenTitle.text = getString(R.string.new_folder)
                    btnImport.isVisible = true
                    isFolder.isVisible = true
                }
            }

        }
    }

    private fun click() {
        binding.apply {
            btnImport.setOnClickListener {
                var name = etInput0.text.toString().trim()
                if (name.isEmpty())
                    name = getString(R.string.new_folder)
                findNavController().navigate(
                    R.id.inputFragment, bundleOf(
                        Constants.ARG_FOLDER_NAME to name,
                        Constants.ARG_BASE_ID to folderEntity?.baseId,
                        Constants.ARG_FOLDER_ID to folderEntity?.id,
                        Constants.ARG_IS_MAKE_FOLDER to (type != Type.EDIT_FOLDER),
                    )
                )
            }
            isFolder.setOnClickListener {
                viewModel.setCheck()
                Log.d(TAG, "observe: setCheck")
            }
            delete.setOnClickListener {
                when (type) {
                    Type.NEW_FOLDER -> Unit
                    Type.NEW_FOLDER_BASE_0 -> Unit
                    Type.EDIT_FOLDER -> {
                        folderEntity?.let {
                            viewModel.deleteFolder(it)
                            findNavController().popBackStack()
                        }
                    }

                    Type.NEW_DICTIONARY -> TODO()
                    Type.EDIT_DICTIONARY -> {

                    }
                }
            }
            next.setOnClickListener {
                when (type) {
                    Type.EDIT_FOLDER -> {
                        val name = etInput0.text.toString().trim()
                        viewModel.updateFolder(
                            FolderEntity(
                                folderEntity?.id ?: 0L,
                                name.ifEmpty { folderEntity?.name ?: "" },
                                folderEntity?.baseId ?: 0L,
                                folderEntity?.isFolderEnd == true,

                                )
                        )
                        findNavController().popBackStack()
                    }

                    Type.NEW_FOLDER -> {
                        val name = etInput0.text.toString().trim()
                        if (name.isEmpty())
                            insertFolder(
                                baseId = folderEntity?.id,
                                isFolder = viewModel.stateCheck.value
                            )
                        else
                            insertFolder(name, folderEntity?.id, viewModel.stateCheck.value)
                    }

                    Type.NEW_DICTIONARY -> {
                        val key = etInput1.text.toString().trim()
                        val value = etInput2.text.toString().trim()
                        val keyDesc = etInput3.text.toString().trim()
                        val valueDesc = etInput4.text.toString().trim()
                        if (key.isEmpty() or value.isEmpty())
                            message(getString(R.string.key_value_empty))
                        else {
                            viewModel.insertDictionary(
                                DictionaryEntity(
                                    0,
                                    folderEntity?.id!!,
                                    key,
                                    value,
                                    keyDesc,
                                    valueDesc
                                )
                            )
                            clearField()
                        }
                    }

                    Type.EDIT_DICTIONARY -> TODO()
                    Type.NEW_FOLDER_BASE_0 -> {
                        val name = etInput0.text.toString().trim()
                        if (name.isEmpty())
                            insertFolder(isFolder = viewModel.stateCheck.value)
                        else
                            insertFolder(name, isFolder = viewModel.stateCheck.value)
                    }
                }

            }
            cancel.setOnClickListener {
                findNavController().popBackStack()
            }
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun clearField() {
        binding.apply {
            etInput1.setText("")
            etInput2.setText("")
            etInput3.setText("")
            etInput4.setText("")
        }
    }

    private fun message(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun insertFolder(
        name: String? = null,
        baseId: Long? = null,
        isFolder: Boolean = false
    ) {
        viewModel.insertFolder(
            FolderEntity(
                0, name ?: getString(R.string.new_folder), baseId ?: 0, !isFolder
            )
        )
        findNavController().popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewItemFragment()
    }
}