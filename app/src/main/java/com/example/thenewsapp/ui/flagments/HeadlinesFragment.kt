package com.example.thenewsapp.ui.flagments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thenewsapp.R
import com.example.thenewsapp.api.ApiService
import com.example.thenewsapp.api.RetrofitClient
import com.example.thenewsapp.databinding.FragmentHeadlinesBinding
import com.example.thenewsapp.model.News
import com.example.thenewsapp.model.TopNewResponse
import com.example.thenewsapp.ui.activities.NewsDetailAct
import com.example.thenewsapp.ui.adapter.NewsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HeadlinesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val listNew = mutableListOf<News>()
    private lateinit var newAdapter: NewsAdapter
    private lateinit var binding: FragmentHeadlinesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    private fun initData() {
        val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
        apiService.fetchData("vi", "pub_42045ddca4905cd301e552ee7e03f781fa608").enqueue(object :
            Callback<TopNewResponse> {
            override fun onResponse(
                call: Call<TopNewResponse>,
                response: Response<TopNewResponse>
            ) {
                binding.paginationProgressBar.isVisible = false
                if(response.isSuccessful) {
                    newAdapter.update(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<TopNewResponse>, t: Throwable) {
                binding.paginationProgressBar.isVisible = false
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initViews() {
        binding.paginationProgressBar.isVisible = true
        newAdapter = NewsAdapter(requireContext(), listNew) { new, _ ->
            val intent = Intent(requireContext(), NewsDetailAct::class.java)
            intent.putExtra("data", new)
            startActivity(intent)
        }
        binding.recyclerHeadlines.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerHeadlines.adapter = newAdapter

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeadlinesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}