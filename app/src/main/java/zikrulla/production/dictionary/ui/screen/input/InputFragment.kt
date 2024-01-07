package zikrulla.production.dictionary.ui.screen.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.model.Dictionary
import zikrulla.production.dictionary.data.model.DictionaryList
import zikrulla.production.dictionary.databinding.FragmentInputBinding
import zikrulla.production.dictionary.utils.Constants

class InputFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    private lateinit var binding: FragmentInputBinding

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
                findNavController().navigate(R.id.inputDetailsFragment, Bundle().apply {
                    this.putSerializable(Constants.DICTIONARY_LIST, DictionaryList(dictionaries))
                })
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