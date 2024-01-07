package zikrulla.production.dictionary.ui.screen.input_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.model.Dictionary
import zikrulla.production.dictionary.data.model.DictionaryList
import zikrulla.production.dictionary.databinding.FragmentInputDetailsBinding
import zikrulla.production.dictionary.ui.adapter.DictionaryAdapter
import zikrulla.production.dictionary.utils.Constants
import zikrulla.production.dictionary.utils.Constants.TAG

class InputDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dictionaryList = (requireArguments().getSerializable(Constants.DICTIONARY_LIST) as DictionaryList).list
            Log.d(TAG, "onCreate: ${dictionaryList.size}")
        }
    }

    private lateinit var binding: FragmentInputDetailsBinding
    private lateinit var dictionaryList: List<Dictionary>
    private val dictionaryAdapter by lazy {
        DictionaryAdapter(dictionaryList)
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
                findNavController().popBackStack(R.id.homeFragment, false)
//                TODO("database add data")
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = InputDetailsFragment()
    }
}