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

class FragmentUnderConstruction: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_under_consrtuction, container, false)
    }

}