package com.ku_rum.front_end

import android.util.Log
import com.kakao.sdk.common.util.Utility
import com.ku_rum.front_end.databinding.FragmentAnnouncementBinding

class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding>(FragmentAnnouncementBinding::inflate){
    override fun initAfterBinding() {
        var keyHash = Utility.getKeyHash(requireContext())

        Log.d("key", keyHash)
    }

}