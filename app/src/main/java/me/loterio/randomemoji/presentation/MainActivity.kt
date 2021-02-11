package me.loterio.randomemoji.presentation

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import me.loterio.randomemoji.R
import me.loterio.randomemoji.network.EmojiAPI

class MainActivity :  FragmentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}