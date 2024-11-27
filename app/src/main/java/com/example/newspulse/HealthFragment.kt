package com.example.newspulse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newspulse.Adapters.NewsAdapter
import com.example.newspulse.ClickListners.OnclickListener
import com.example.newspulse.ViewModels.HealthViewModel
import com.example.newspulse.databinding.FragmentEntertainmentBinding
import com.example.newspulse.databinding.FragmentHealthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HealthFragment : Fragment() , OnclickListener {
lateinit var binding: FragmentHealthBinding
lateinit var adapter: NewsAdapter
val viewModel : HealthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHealthBinding.bind(view)
        viewModel.getHealthNews()

        //observe news
        viewModel.news.observe(viewLifecycleOwner){
            adapter = NewsAdapter(it.articles,this)
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

    override fun onclick(url: String) {
        val fragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString("url",url)
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentsContainer , fragment)
            .commit()
    }
}