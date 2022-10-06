package com.example.experimentalfeaturespoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.experimentalfeaturespoc.databinding.SampleItemBinding

class MonthlyPackFragment : Fragment() {

    private lateinit var sampleItemBinding: SampleItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sampleItemBinding = SampleItemBinding.inflate(layoutInflater)
        return sampleItemBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(sampleItemBinding){
            tvProductName.text = "$packPersonsInfo persons monthly pack"
        }
    }

    companion object {

        private var packPersonsInfo: Int = 0

        fun newInstance(
            packPersonInfo: Int
        ) = MonthlyPackFragment().apply {
            this@Companion.packPersonsInfo = packPersonInfo
        }
    }
}