package me.loterio.randomemoji.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.loterio.randomemoji.R
import me.loterio.randomemoji.databinding.FragmentEmojiListBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { FragmenEmojiList() }
}
class FragmenEmojiList: Fragment() {
    private val emojiListViewModel: EmojiListViewModel by viewModel()
    private lateinit var binding: FragmentEmojiListBinding

    private val observer = Observer<List<Map<String,String>>>{
        Log.w("teste",it.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmojiListBinding.inflate(inflater,container,false)
        emojiListViewModel.emojiList.observe(viewLifecycleOwner,observer)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}