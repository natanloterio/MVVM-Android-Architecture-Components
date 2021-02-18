package me.loterio.randomemoji.presentation.avatarlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.loterio.randomemoji.databinding.AvatarListItemBinding
import me.loterio.randomemoji.domain.model.GithubUser

class AvatarListAdapter(
    val context: Context?,
    val avatarList: List<GithubUser>
) : RecyclerView.Adapter<AvatarListAdapter.AvatarViewHolder>() {

    inner class AvatarViewHolder(val viewBiding: AvatarListItemBinding) :

        RecyclerView.ViewHolder(viewBiding.root) {
        fun onBind(githubUser: GithubUser) {
            Glide
                .with(viewBiding.root)
                .load(githubUser.avatar_url)
                .centerCrop()
                .into(viewBiding.ivAvatar);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val viewBinding: AvatarListItemBinding = AvatarListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return AvatarViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return avatarList.size
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.onBind(avatarList[position])
    }


}