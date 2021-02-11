package me.loterio.randomemoji.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import me.loterio.randomemoji.databinding.FragmentEmojiListBinding
import me.loterio.randomemoji.model.Emoji
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { FragmenEmojiList() }
}
class FragmenEmojiList: Fragment() {
    private val emojiListViewModel: EmojiListViewModel by viewModel()
    private lateinit var binding: FragmentEmojiListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmojiListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emojiListViewModel.emojiList.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })

        emojiListViewModel.getAllEmojis()

    }

    private fun setAdapter(emojiList: List<Emoji>) {
        binding.rvEmojiList.apply {
            adapter = EmojisListAdapter(context, emojiList)
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
    }
}