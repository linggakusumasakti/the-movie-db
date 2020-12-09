package com.lingga.themoviedb.ui.ticket

import android.content.Context
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentTicketBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class TicketFragment : BaseFragment<FragmentTicketBinding>(R.layout.fragment_ticket) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}