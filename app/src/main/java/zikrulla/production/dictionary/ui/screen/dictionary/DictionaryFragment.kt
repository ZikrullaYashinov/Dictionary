package zikrulla.production.dictionary.ui.screen.dictionary

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.enums.Type
import zikrulla.production.dictionary.databinding.FragmentDictionaryBinding
import zikrulla.production.dictionary.ui.adapter.FolderAdapter
import zikrulla.production.dictionary.utils.Constants
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
                visibleBN = false
            }
        }
    }

    private lateinit var binding: FragmentDictionaryBinding
    private var baseId = 0L
    private var visibleBN = true
    private var baseFolderEntity: FolderEntity? = null
    private val viewModel by viewModels<DictionaryViewModelImpl>()
    private val adapter: FolderAdapter by lazy {
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

            })
    }

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
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun click() {
        binding.apply {
            addFolder.setOnClickListener {
                if (baseId == 0L) {
                    navigate(
                        R.id.newItemFragment, bundleOf(
                            Constants.ARG_TYPE to Type.NULL,
                        )
                    )
                } else
                    navigate(
                        R.id.newItemFragment, bundleOf(
                            Constants.ARG_TYPE to Type.FOLDER,
                            Constants.ARG_OBJECT to baseFolderEntity,
                        )
                    )
            }
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
        viewModel.getAllFolderByBaseId(baseId).onEach {
            adapter.submit(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryFragment()
    }
}