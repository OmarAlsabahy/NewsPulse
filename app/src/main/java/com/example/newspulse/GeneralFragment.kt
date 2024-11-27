package com.example.newspulse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newspulse.databinding.FragmentGeneralBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.newspulse.Adapters.NewsAdapter
import com.example.newspulse.Models.GeneralViewModel


@AndroidEntryPoint
class GeneralFragment : Fragment() {
    lateinit var binding:FragmentGeneralBinding
    lateinit var adapter: NewsAdapter
    val viewModel : GeneralViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentGeneralBinding.bind(view)
        viewModel.getGeneralNews()

        //observer news
        viewModel.news.observe(viewLifecycleOwner){
            adapter = NewsAdapter(it.articles)
            binding.newsRecyclerView.adapter = adapter
        }

        //observe progress
        viewModel.progress.observe(viewLifecycleOwner){
            if (!it){
                binding.progress.visibility = View.VISIBLE
            }else{
                binding.progress.visibility = View.GONE
            }
        }
    }
}