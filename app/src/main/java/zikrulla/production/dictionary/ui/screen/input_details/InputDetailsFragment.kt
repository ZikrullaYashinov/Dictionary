package zikrulla.production.dictionary.ui.screen.input_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.Dictionary
import zikrulla.production.dictionary.data.model.DictionaryList
import zikrulla.production.dictionary.databinding.FragmentInputDetailsBinding
import zikrulla.production.dictionary.ui.adapter.DictionarySelectorAdapter
import zikrulla.production.dictionary.utils.Constants
import zikrulla.production.dictionary.utils.Constants.TAG
import zikrulla.production.dictionary.viewmodel.InputDetailsViewModel
import zikrulla.production.dictionary.viewmodel.impl.InputDetailsViewModelImpl

@AndroidEntryPoint
class InputDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            folder = requireArguments().getSerializable(Constants.ARG_FOLDER) as FolderEntity
            isMakeFolder = folder?.isFolderEnd == true
            dictionaryList =
                (requireArguments().getSerializable(Constants.DICTIONARY_LIST) as DictionaryList).list
        }
    }

    private lateinit var binding: FragmentInputDetailsBinding
    private lateinit var dictionaryList: List<Dictionary>
    private var folder: FolderEntity? = null
    private var isMakeFolder = true
    private val viewModel by viewModels<InputDetailsViewModelImpl>()
    private val dictionaryAdapter by lazy {
        DictionarySelectorAdapter(dictionaryList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
        observe()
    }

    private fun load() {
        binding.apply {
            rv.adapter = dictionaryAdapter
            rv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun click() {
        binding.apply {
            back.setOnClickListener {
                findNavController().popBackStack()
            }
            previous.setOnClickListener {
                findNavController().popBackStack()
            }
            next.setOnClickListener {
                folder?.let {
                    if (isMakeFolder)
                        viewModel.insertFolder(it.copy(isFolderEnd = true))
                    else {
                        viewModel.updateFolder(it.copy(isFolderEnd = true))
                        insertDictionaryList(it.id)
                    }
                }
            }
        }

    }

    private fun observe() {
        viewModel.stateFolderId.onEach {
            if (it != -1L) {
                if (isMakeFolder)
                    insertDictionaryList(it)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun insertDictionaryList(id: Long) {
        val list = dictionaryList.map { it.toDictionaryEntity() }
        viewModel.insertDictionaryList(id, list)
        finish()
    }

    private fun finish() {
        findNavController().popBackStack(R.id.dictionaryFragment, false)
        activity?.findViewById<View>(R.id.bottom_navigation_view)?.isVisible = true
    }

    companion object {
        @JvmStatic
        fun newInstance() = InputDetailsFragment()
    }
}