package me.loterio.randomemoji.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import me.loterio.randomemoji.R
import me.loterio.randomemoji.core.log
import me.loterio.randomemoji.databinding.FragmentEmojiListBinding
import me.loterio.randomemoji.model.Emoji
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { FragmenEmojiList() }
}
class FragmenEmojiList: Fragment() {
    private var shortAnimationDuration: Int = 0
    private val emojiListViewModel: EmojiListViewModel by viewModel()
    private lateinit var binding: FragmentEmojiListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_emoji_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emojiListViewModel = emojiListViewModel

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