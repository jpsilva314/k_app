package com.example.kaizentest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaizentest.R
import com.example.kaizentest.adapter.SportsAdapter
import com.example.kaizentest.extention.hide
import com.example.kaizentest.extention.show
import com.example.kaizentest.factory.SportsViewModelFactory
import com.example.kaizentest.model.Resource
import com.example.kaizentest.model.Sports
import com.example.kaizentest.model.Status
import com.example.kaizentest.repository.SportsRepository
import com.example.kaizentest.viewModel.SportsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment: Fragment() {

    private lateinit var sportsViewModel: SportsViewModel
    private lateinit var sportsAdapter: SportsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()

        val sportsViewModelFactory = SportsViewModelFactory(SportsRepository())
        sportsViewModel = ViewModelProvider(requireActivity(), sportsViewModelFactory)[SportsViewModel::class.java]
        sportsViewModel.sports.observe(requireActivity(), sportsObserver)
        sportsViewModel.getSports()

        sportsAdapter = SportsAdapter()
        fragRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        fragRecycler.adapter = sportsAdapter

    }

    private val sportsObserver = Observer { response: Resource<ArrayList<Sports>?> ->
        when (response.status) {
            Status.Loading -> {
                loading.show()
                error_message_container.hide()
                fragRecycler.hide()
            }
            Status.Success -> {
                loading.hide()
                fragRecycler.show()
                response.data?.let { sportsAdapter.addNewItems(it) }
            }
            Status.Error -> {
                loading.hide()
                error_message_container.show()
            }
        }
    }
}
