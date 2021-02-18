package me.loterio.randomemoji.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import me.loterio.randomemoji.EmojisApplication
import me.loterio.randomemoji.R
import me.loterio.randomemoji.databinding.FragmentMainBinding
import javax.inject.Inject

class FragmentMain: Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as EmojisApplication)
            .appComponent
            .injectFragmentMain(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentMainBinding = FragmentMainBinding.bind(view)

        binding.btnRandomEmoji.setOnClickListener {
            mainViewModel.showRandomEmoji()
        }

        binding.btnEmojiList.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmenEmojiList()
            view.findNavController().navigate(action)
        }

        binding.btnAvatarList.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmentAvatarList()
            view.findNavController().navigate(action)
        }

        binding.btnGoogleRepos.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmentUnderConstruction()
            view.findNavController().navigate(action)
        }

        binding.btnSearchGithubUser.setOnClickListener {
           mainViewModel.searchGithubUser(binding.edtGithubUsername.text.toString())
        }

        mainViewModel.randomEmoji.observe(viewLifecycleOwner, Observer {
            Glide
                .with(binding.ivRandomEmoji)
                .load(it.url)
                .centerCrop()
                .into(binding.ivRandomEmoji);
        })

    }

}