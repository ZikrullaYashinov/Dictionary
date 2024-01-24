package zikrulla.production.dictionary.ui.screen.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.Dictionary
import zikrulla.production.dictionary.data.model.DictionaryList
import zikrulla.production.dictionary.databinding.FragmentInputBinding
import zikrulla.production.dictionary.utils.Constants

@AndroidEntryPoint
class InputFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            folderName = requireArguments().getString(Constants.ARG_FOLDER_NAME).toString()
            baseId = requireArguments().getLong(Constants.ARG_BASE_ID)
            folderId = requireArguments().getLong(Constants.ARG_FOLDER_ID)
            isMakeFolder = requireArguments().getBoolean(Constants.ARG_IS_MAKE_FOLDER, true)
        }
    }

    private lateinit var binding: FragmentInputBinding
    private var folderName = "new folder"
    private var baseId = 0L
    private var folderId = 0L
    private var isMakeFolder = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
    }

    private fun load() {
        setVisibleBottomNavBar(false)
    }

    private fun click() {
        binding.apply {
            next.setOnClickListener {
                val input = etInput.text.toString()
                val stringList = input.split("\n")
                val dictionaries = ArrayList<Dictionary>()
                stringList.map {
                    val list = it.split("/")
                    if (list.size == 2)
                        dictionaries.add(Dictionary(list[0].trim(), list[1].trim()))
                }
                val folderEntity = FolderEntity(
                    id = folderId,
                    name = folderName,
                    baseId = baseId,
                    isFolderEnd = isMakeFolder
                )
                findNavController().navigate(
                    R.id.inputDetailsFragment, bundleOf(
                        Constants.DICTIONARY_LIST to DictionaryList(dictionaries),
                        Constants.ARG_FOLDER to folderEntity,
                    )
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
            setVisibleBottomNavBar(true)
        }
    }

    private fun setVisibleBottomNavBar(isVisible: Boolean) {
        activity?.findViewById<View>(R.id.bottom_navigation_view)?.isVisible = isVisible
    }

    companion object {
        @JvmStatic
        fun newInstance() = InputFragment()
    }
}