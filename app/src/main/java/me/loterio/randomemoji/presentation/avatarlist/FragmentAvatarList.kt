package me.loterio.randomemoji.presentation.avatarlist

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
import me.loterio.randomemoji.databinding.FragmentAvatarListBinding
import me.loterio.randomemoji.domain.model.Emoji
import me.loterio.randomemoji.domain.model.GithubUser
import javax.inject.Inject

class FragmentAvatarList: Fragment() {

    @Inject
    lateinit var avatarListViewModel: AvatarListViewModel

    private lateinit var binding: FragmentAvatarListBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as EmojisApplication)
            .appComponent
            .injectFragmentAvatarList(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_avatar_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatarListViewModel.avatarList.observe(viewLifecycleOwner, Observer {

            setAdapter(it)
        })

        avatarListViewModel.getAllAvatars()


    }

    private fun setAdapter(usersList: List<GithubUser>) {
        binding.rvAvatarList.apply {
            adapter =
                AvatarListAdapter(
                    context,
                    usersList
                )
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
    }
}