package me.loterio.randomemoji.presentation.googlerepos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.loterio.randomemoji.databinding.GoogleRepoListItemBinding
import me.loterio.randomemoji.domain.model.GithubRepo

class GoogleReposListAdapter(
    private val context: Context?,
    private val githubRepos: List<GithubRepo>
) : RecyclerView.Adapter<GoogleReposListAdapter.GoogleReposViewHolder>() {

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

    override fun getItemCount(): Int {
        return githubRepos.size
    }

    override fun onBindViewHolder(holder: GoogleReposViewHolder, position: Int) {
        holder.onBind(githubRepos[position])
    }


}