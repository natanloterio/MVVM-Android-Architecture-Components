package me.loterio.randomemoji.presentation.emojilist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.loterio.randomemoji.databinding.EmojiListItemBinding
import me.loterio.randomemoji.domain.model.Emoji

class EmojisListAdapter(
    val context: Context?,
    val emojisList: List<Emoji>
) : RecyclerView.Adapter<EmojisListAdapter.EmojiViewHolder>() {

    inner class EmojiViewHolder(val viewBiding: EmojiListItemBinding) :

        RecyclerView.ViewHolder(viewBiding.root) {
        fun onBind(emoji: Emoji) {
            Glide
                .with(viewBiding.root)
                .load(emoji.url)
                .centerCrop()
                .into(viewBiding.ivEmoji);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        val viewBinding: EmojiListItemBinding = EmojiListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return EmojiViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return emojisList.size
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        holder.onBind(emojisList[position])
    }


}