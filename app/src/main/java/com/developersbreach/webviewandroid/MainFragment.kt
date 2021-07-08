package com.developersbreach.webviewandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val simple = view.findViewById<MaterialCardView>(R.id.simple_fragment_card_view)
        val interactive = view.findViewById<MaterialCardView>(R.id.interactive_fragment_card_view)

        simple.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.mainToSimpleWebView()
            )
        }

        interactive.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.mainToInteractiveWebView()
            )
        }
    }
}