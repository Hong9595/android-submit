package com.example.arteum.ui.init

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arteum.R
import kotlinx.android.synthetic.main.init_pager_list.*


private const val IMAGE_ID = "param1"

class InitFragment : Fragment() {
    private var imageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageId = arguments?.getInt(IMAGE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.init_pager_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        imageId?.let {
            initImage.setImageResource(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(imageValue: Int) =
            InitFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE_ID, imageValue)
                }
            }
    }
}
