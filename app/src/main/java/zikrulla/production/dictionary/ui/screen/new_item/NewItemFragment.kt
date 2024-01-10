package zikrulla.production.dictionary.ui.screen.new_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.data.model.enums.Type
import zikrulla.production.dictionary.databinding.FragmentNewItemBinding
import zikrulla.production.dictionary.utils.Constants

class NewItemFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = requireArguments().getSerializable(Constants.ARG_TYPE) as Type
            when(type){
                Type.FOLDER -> {
                    obj = requireArguments().getSerializable(Constants.ARG_OBJECT) as FolderEntity
                }

                Type.NULL -> Unit
            }
        }
    }

    private var obj: Any? = null
    private lateinit var type: Type
    private lateinit var binding: FragmentNewItemBinding

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
    }

    private fun load() {
        binding.apply {
            when(type){
                Type.FOLDER -> {
                    screenTitle.text = (obj as FolderEntity).name
                    btnImport.isVisible = true
                }

                Type.NULL -> {
                    screenTitle.text = getString(R.string.new_folder)
                    btnImport.isVisible = true
                }
            }

        }
    }

    private fun click() {
        binding.apply {
            btnImport.setOnClickListener {
                findNavController().navigate(
                    R.id.inputFragment, bundleOf(
                        Constants.ARG_FOLDER_NAME to etInput.text.toString().trim(),
                        Constants.ARG_BASE_ID to (obj as FolderEntity).id,
                    )
                )
            }
            next.setOnClickListener {

            }
            cancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewItemFragment()
    }
}