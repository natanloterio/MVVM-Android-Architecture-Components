package me.loterio.randomemoji.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import me.loterio.randomemoji.R
import me.loterio.randomemoji.databinding.FragmentMainBinding

class FragmentMain: Fragment() {


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
            val action =
                FragmentMainDirections.actionFragmentMainToFragmentUnderConstruction()
            view.findNavController().navigate(action)
        }

        binding.btnEmojiList.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmenEmojiList()
            view.findNavController().navigate(action)
        }

        binding.btnAvatarList.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmentUnderConstruction()
            view.findNavController().navigate(action)
        }

        binding.btnGoogleRepos.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmentUnderConstruction()
            view.findNavController().navigate(action)
        }

        binding.btnSearchGithubUser.setOnClickListener {
            val action =
                FragmentMainDirections.actionFragmentMainToFragmentUnderConstruction()
            view.findNavController().navigate(action)
        }

    }

}