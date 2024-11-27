package com.example.newspulse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newspulse.Adapters.NewsAdapter
import com.example.newspulse.ViewModels.BusinessViewModel
import com.example.newspulse.databinding.FragmentBusinessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessFragment : Fragment() {
lateinit var binding: FragmentBusinessBinding
val viewModel:BusinessViewModel by viewModels()
    lateinit var adapter:NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBusinessBinding.bind(view)
        viewModel.getBusinessNews()

        //observe news
        viewModel.news.observe(viewLifecycleOwner){
            adapter = NewsAdapter(it.articles)
            binding.newsRecyclerView.adapter = adapter
        }


        //observe progress
        viewModel.progress.observe(viewLifecycleOwner){
            if (it){
                binding.progress.visibility = View.GONE
            }
        }
    }
}