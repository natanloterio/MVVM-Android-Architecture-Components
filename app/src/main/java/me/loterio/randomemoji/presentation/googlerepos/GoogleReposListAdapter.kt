package me.loterio.randomemoji.presentation.googlerepos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.loterio.randomemoji.databinding.GoogleRepoListItemBinding
import me.loterio.randomemoji.domain.model.GithubRepo

class GoogleReposListAdapter() :  PagingDataAdapter<GithubRepo, GoogleReposListAdapter.GoogleReposViewHolder>(DiffUtilCallBack()) {

    inner class GoogleReposViewHolder(val viewBiding: GoogleRepoListItemBinding) :

        RecyclerView.ViewHolder(viewBiding.root) {
            fun onBind(githubRepo: GithubRepo) {
                viewBiding.txtRepo.text = githubRepo.fullName
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleReposViewHolder {
        val viewBinding: GoogleRepoListItemBinding = GoogleRepoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return GoogleReposViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: GoogleReposViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<GithubRepo>() {
        override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
            return oldItem == newItem
        }
    }
}