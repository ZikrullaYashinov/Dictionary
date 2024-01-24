package zikrulla.production.dictionary.ui.screen.settingrun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.data.local.entity.FolderEntity
import zikrulla.production.dictionary.databinding.FragmentSettingsRunBinding
import zikrulla.production.dictionary.ui.screen.settingrun.model.RunType
import zikrulla.production.dictionary.utils.Constants

@AndroidEntryPoint
class SettingsRunFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            folderEntity = requireArguments().getSerializable(Constants.ARG_FOLDER) as FolderEntity
        }
    }

    private lateinit var binding: FragmentSettingsRunBinding
    private var folderEntity: FolderEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsRunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
    }

    private fun load() {
        binding.apply {

        }
    }

    private fun click() {
        binding.apply {
            test.setOnClickListener {
                check(RunType.TEST)
            }
            card.setOnClickListener {
                check(RunType.CARD)
            }
            go.setOnClickListener {
                findNavController().navigate(
                    R.id.gameTestFragment, bundleOf(
                        Constants.ARG_FOLDER to folderEntity
                    )
                )
            }
        }
    }

    private fun check(type: RunType) {
        binding.apply {
            testIcon.setImageResource(R.drawable.ic_radio_button_off)
            cardIcon.setImageResource(R.drawable.ic_radio_button_off)
            when (type) {
                RunType.TEST -> {
                    testIcon.setImageResource(R.drawable.ic_radio_button_on)
                }

                RunType.CARD -> {
                    cardIcon.setImageResource(R.drawable.ic_radio_button_on)
                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = SettingsRunFragment()
    }
}