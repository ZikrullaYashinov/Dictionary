package zikrulla.production.dictionary.ui.screen.new_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.databinding.FragmentNewItemBinding
import zikrulla.production.dictionary.utils.Constants

class NewItemFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

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

    }

    private fun click() {
        binding.apply {
            btnImport.setOnClickListener {
                findNavController().navigate(
                    R.id.inputFragment, bundleOf(
                        Constants.ARG_FOLDER_NAME to etInput.text.toString().trim()
                    )
                )
            }
            next.setOnClickListener {
                setFragmentResultListener(Constants.SET_FRAGMENT_RESULT) { requestKey, bundle ->
                    val result = bundle.getString(Constants.ARG_NAME)
                    TODO("add database folder")
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewItemFragment()
    }
}