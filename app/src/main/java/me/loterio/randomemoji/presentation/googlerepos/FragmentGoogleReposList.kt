package me.loterio.randomemoji.presentation.googlerepos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import me.loterio.randomemoji.EmojisApplication
import me.loterio.randomemoji.R
import me.loterio.randomemoji.databinding.FragmentGoogleReposBinding
import me.loterio.randomemoji.domain.model.GithubRepo
import javax.inject.Inject

class FragmentGoogleReposList: Fragment() {

    private lateinit var adapter: GoogleReposListAdapter

    @Inject
    lateinit var viewModel: GoogleReposListViewModel

    private lateinit var binding: FragmentGoogleReposBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as EmojisApplication)
            .appComponent
            .injectFragmentGoogleRepositoryList(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_google_repos, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GoogleReposListAdapter()
        binding.rvGoogleRepos.adapter = adapter
        binding.rvGoogleRepos.layoutManager = LinearLayoutManager(activity,  GridLayoutManager.VERTICAL, false)

        lifecycleScope.launchWhenCreated {
            viewModel.getAllGoogleReposPaged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}