package com.example.ku_rum.Notice

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.ku_rum.Notice.adapter.NoticeRVAdapter
import com.ku_rum.front_end.BaseFragment
import com.ku_rum.front_end.databinding.FragmentNoticeSearchBinding
import com.ku_rum.front_end.ui.theme.Announcement.data.NoticeData


class NoticeSearchFragment : BaseFragment<FragmentNoticeSearchBinding>(FragmentNoticeSearchBinding::inflate) {

    private lateinit var noticeRVAdapter: NoticeRVAdapter
    private var noticeList: ArrayList<NoticeData> = arrayListOf()
    private var searchedList: ArrayList<NoticeData> = arrayListOf()

    override fun initAfterBinding() {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentNoticeSearchBinding.inflate(layoutInflater)

        binding.etNoticeSearch.setOnEditorActionListener{textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH){
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etNoticeSearch.windowToken, 0)
                binding.etNoticeSearch.clearFocus()

                val query = binding.etNoticeSearch.toString()
                searchNotice(query)

                true
            }
            else
            {
                false
            }
        }

        noticeRVAdapter = NoticeRVAdapter(requireActivity(), searchedList)
        binding.rvNoticeSearched.adapter = noticeRVAdapter

        return binding.root
    }

    private fun searchNotice(query: String){
        searchedList.clear()
        if(query.isNotEmpty()) {
            val result = noticeList.filter { it.title.contains(query) }
            searchedList.addAll(result)
        } else {
            searchedList.addAll(noticeList)
        }
        noticeRVAdapter.notifyDataSetChanged()
    }


}