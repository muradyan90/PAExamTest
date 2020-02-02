package com.aram.paexamtest.ui


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.aram.paexamtest.R
import com.aram.paexamtest.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var masage = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        val bundle = arguments
        bundle?.let {
            binding.article = DetailsFragmentArgs.fromBundle(it).article
        }

        binding.sendBtn.setOnClickListener{
            masage = binding.editText.text.toString()

            if (masage.trim().isEmpty()){
                binding.editText.error = "no itput"
                return@setOnClickListener
            }

            with(Intent()){

                type = ("text/plane")
                putExtra(Intent.EXTRA_TEXT,masage)

                activity?.packageManager?.let {

                    resolveActivity(it)?.let {

                        startActivity(this)
                    } ?: Toast.makeText(activity,"no such activity",Toast.LENGTH_SHORT).show()

                }

            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,findNavController()) || super.onOptionsItemSelected(item)
    }

}
