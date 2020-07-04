package com.developersbreach.webviewandroid.simple

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developersbreach.webviewandroid.R


class SimpleWebViewFragment : Fragment() {

    companion object {
        fun newInstance() = SimpleWebViewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_web_view, container, false)
    }
}