package com.developersbreach.webviewandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.developersbreach.webviewandroid.interactive.InteractiveWebViewFragment
import com.developersbreach.webviewandroid.simple.SimpleWebViewFragment
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

        view.findViewById<MaterialCardView>(R.id.simple_fragment_card_view).setOnClickListener {
            val fragment = SimpleWebViewFragment.newInstance()
            showSelectedFragment(fragment)
        }

        view.findViewById<MaterialCardView>(R.id.interactive_fragment_card_view).setOnClickListener {
            val fragment = InteractiveWebViewFragment.newInstance()
            showSelectedFragment(fragment)
        }
    }

    private fun showSelectedFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}