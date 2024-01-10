package zikrulla.production.dictionary.ui.screen.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import zikrulla.production.dictionary.databinding.FragmentBookmarksBinding

@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        load()
        click()
        observe()
    }

    private fun load() {

    }

    private fun click() {

    }

    private fun observe() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = BookmarksFragment()
    }
}