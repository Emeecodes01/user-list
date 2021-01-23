package com.mobigods.userlist.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mobigods.userlist.R
import com.mobigods.userlist.base.BaseFragment
import com.mobigods.userlist.databinding.FragmentUserDetailBinding
import com.mobigods.userlist.ui.adapters.UserListAdapter
import com.mobigods.userlist.ui.states.UserListState
import com.mobigods.userlist.viemodels.UserDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment: BaseFragment<FragmentUserDetailBinding>() {

    private val userDetailViewModel: UserDetailFragmentViewModel by viewModels()
    private val userDetailFragmentArgs: UserDetailFragmentArgs by navArgs()

    override val layoutRes: Int
        get() = R.layout.fragment_user_detail

    override fun observeViewModel() {
        with(userDetailViewModel) {
            user.observe(viewLifecycleOwner) { resource ->
                when(resource.state) {
                    UserListState.SUCCESS -> {
                        resource.data?.let { user ->
                            binding.user = user
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userDetailViewModel.getUserDetail(userDetailFragmentArgs.userId)
        binding.materialToolbar2.setNavigationOnClickListener {
            goBack()
        }
    }

}