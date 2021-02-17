package me.loterio.randomemoji.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import me.loterio.randomemoji.EmojisApplication
import me.loterio.randomemoji.R
import me.loterio.randomemoji.databinding.FragmentEmojiListBinding
import me.loterio.randomemoji.di.DaggerAppComponent
import me.loterio.randomemoji.domain.model.Emoji
import javax.inject.Inject

class FragmenEmojiList: Fragment() {
    private var shortAnimationDuration: Int = 0

    @Inject
    lateinit var emojiListViewModel: EmojiListViewModel

    private lateinit var binding: FragmentEmojiListBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as EmojisApplication)
            .appComponent
            .inject(this)
    }

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