package zikrulla.production.dictionary.ui.screen.dictionary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.enums.Type
import zikrulla.production.dictionary.databinding.FragmentDictionaryBinding
import zikrulla.production.dictionary.ui.adapter.DictionaryAdapter
import zikrulla.production.dictionary.ui.adapter.FolderAdapter
import zikrulla.production.dictionary.ui.dialog.folder.FolderDialogFragment
import zikrulla.production.dictionary.ui.model.BottomSheetModel
import zikrulla.production.dictionary.ui.model.BottomSheetModelList
import zikrulla.production.dictionary.utils.Constants
import zikrulla.production.dictionary.utils.Constants.TAG
import zikrulla.production.dictionary.viewmodel.impl.DictionaryViewModelImpl

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            baseId = requireArguments().getLong(Constants.ARG_BASE_ID, 0)
            if (requireArguments().getSerializable(Constants.ARG_FOLDER) != null) {
                baseFolderEntity =
                    requireArguments().getSerializable(Constants.ARG_FOLDER) as FolderEntity
                baseId = baseFolderEntity?.id!!
                visibleBN = false
            }
        }
    }

    private lateinit var binding: FragmentDictionaryBinding
    private var baseId = 0L
    private var visibleBN = true
    private var baseFolderEntity: FolderEntity? = null
    private val viewModel by viewModels<DictionaryViewModelImpl>()
    private val folderAdapter: FolderAdapter by lazy {
        FolderAdapter(
            emptyList(),
            onClick = {
                navigate(
                    R.id.dictionaryFragment, bundleOf(
                        Constants.ARG_BASE_ID to it.id,
                        Constants.ARG_FOLDER to it,
                    )
                )
            },
            onClickSetting = {
                val run = getString(R.string.run)
                val setting = getString(R.string.settings)
                val bottomSheetFragment = FolderDialogFragment().apply {
                    bundleOf(
                        Constants.ARG_BOTTOM_SHEET_ARGS to BottomSheetModelList(
                            listOf(
                                BottomSheetModel(R.drawable.ic_runner, run) {
                                    navigate(
                                        R.id.settingsRunFragment, bundleOf(
                                            Constants.ARG_FOLDER to it
                                        )
                                    )
                                    this.dismiss()
                                },
                                BottomSheetModel(R.drawable.ic_settings, setting) {
                                    navigate(
                                        R.id.newItemFragment, bundleOf(
                                            Constants.ARG_TYPE to Type.EDIT_FOLDER,
                                            Constants.ARG_OBJECT to it,
                                        )
                                    )
                                    this.dismiss()
                                },
                            )
                        )
                    ).also { arguments = it }
                }
                bottomSheetFragment.show(
                    requireActivity().supportFragmentManager,
                    "my_bottom_sheet_tag"
                )

            })
    }
    private val dictionaryAdapter by lazy { DictionaryAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
        observe()
    }

    private fun load() {
        activity?.findViewById<View>(R.id.bottom_navigation_view)?.isVisible = visibleBN
        binding.apply {
            rv.layoutManager = LinearLayoutManager(requireContext())
            if (baseFolderEntity != null) {
                if (baseFolderEntity!!.isFolderEnd) {
                    // dictionaries
                    rv.adapter = dictionaryAdapter
                    add.setImageResource(R.drawable.ic_add_circle)
                } else {
                    // folders base not null
                    rv.adapter = folderAdapter
                    add.setImageResource(R.drawable.ic_folder_add)
                }
                screenTitle.text = baseFolderEntity?.name
            } else {
                // folders base 0
                rv.adapter = folderAdapter
            }
            back.visibility = if (visibleBN) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun click() {
        binding.apply {
            add.setOnClickListener {
                if (baseFolderEntity != null) {
                    if (baseFolderEntity!!.isFolderEnd) {
                        navigate(
                            R.id.newItemFragment, bundleOf(
                                Constants.ARG_TYPE to Type.NEW_DICTIONARY,
                                Constants.ARG_OBJECT to baseFolderEntity,
                            )
                        )
                    } else {
                        navigate(
                            R.id.newItemFragment, bundleOf(
                                Constants.ARG_TYPE to Type.NEW_FOLDER,
                                Constants.ARG_OBJECT to baseFolderEntity,
                            )
                        )
                    }
                } else {
                    navigate(
                        R.id.newItemFragment, bundleOf(
                            Constants.ARG_TYPE to Type.NEW_FOLDER_BASE_0,
                        )
                    )
                }
            }
            back.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun navigate(id: Int, bundle: Bundle? = null) {
        if (bundle == null)
            findNavController().navigate(id)
        else
            findNavController().navigate(id, bundle)
        activity?.findViewById<View>(R.id.bottom_navigation_view)?.isVisible = false
    }

    private fun observe() {
        if (baseFolderEntity == null) {
            viewModel.getAllFolderByBaseId(baseId).onEach {
                folderAdapter.submit(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        } else {
            if (baseFolderEntity!!.isFolderEnd) {
                viewModel.getAllDictionariesByBaseId(baseId).onEach {
                    dictionaryAdapter.submit(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            } else {
                viewModel.getAllFolderByBaseId(baseId).onEach {
                    folderAdapter.submit(it)
                }.launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryFragment()
    }
}