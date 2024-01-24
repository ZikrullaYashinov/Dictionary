package zikrulla.production.dictionary.ui.dialog.folder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.databinding.FragmentFolderDialogBinding
import zikrulla.production.dictionary.ui.adapter.BottomSheetAdapter
import zikrulla.production.dictionary.ui.model.BottomSheetModel
import zikrulla.production.dictionary.ui.model.BottomSheetModelList
import zikrulla.production.dictionary.utils.Constants

class FolderDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bottomSheetModelList =
                (requireArguments().getSerializable(Constants.ARG_BOTTOM_SHEET_ARGS) as BottomSheetModelList).list
        }
    }

    private lateinit var binding: FragmentFolderDialogBinding
    private var bottomSheetModelList: List<BottomSheetModel>? = null
    private val adapter by lazy { BottomSheetAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFolderDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
    }

    private fun load() {
        binding.apply {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(requireContext())
            adapter.submit(bottomSheetModelList ?: emptyList())
        }
    }

    fun click() {

    }

}
