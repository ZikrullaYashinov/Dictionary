package zikrulla.production.dictionary.ui.screen.dictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.databinding.FragmentDictionaryBinding
import zikrulla.production.dictionary.ui.adapter.FolderAdapter
import zikrulla.production.dictionary.ui.dialog.folder.FolderDialogFragment
import zikrulla.production.dictionary.viewmodel.DictionaryViewModel
import zikrulla.production.dictionary.viewmodel.impl.DictionaryViewModelImpl

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    private lateinit var binding: FragmentDictionaryBinding
    private val viewModel by viewModels<DictionaryViewModelImpl>()
    private val adapter: FolderAdapter by lazy { FolderAdapter(emptyList()) }

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
        binding.apply {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun click() {
        binding.apply {
            addFolder.setOnClickListener {
                findNavController().navigate(R.id.newItemFragment)
            }
        }
    }

    private fun observe() {
        viewModel.getAllFolderByBaseId(0).onEach {
            adapter.submit(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryFragment()
    }
}