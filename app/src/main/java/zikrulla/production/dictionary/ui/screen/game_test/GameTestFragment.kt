package zikrulla.production.dictionary.ui.screen.game_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import zikrulla.production.dictionary.R
import zikrulla.production.dictionary.databinding.FragmentGameTestBinding
import zikrulla.production.dictionary.viewmodel.impl.GameTestViewModelImpl

@AndroidEntryPoint
class GameTestFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    private lateinit var binding: FragmentGameTestBinding
    private val viewModel by viewModels<GameTestViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentGameTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
    }

    private fun load() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = GameTestFragment()
    }
}