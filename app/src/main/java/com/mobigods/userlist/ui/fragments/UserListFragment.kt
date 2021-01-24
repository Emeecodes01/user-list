package com.mobigods.userlist.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobigods.userlist.R
import com.mobigods.userlist.base.BaseFragment
import com.mobigods.userlist.databinding.FragmentUserListBinding
import com.mobigods.userlist.ui.adapters.UserListAdapter
import com.mobigods.userlist.ui.states.UserListState
import com.mobigods.userlist.viemodels.UserListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>(),
    UserListAdapter.UserListInterface {
    private val userListViewModel: UserListFragmentViewModel by viewModels()

    @Inject
    lateinit var usersAdapter: UserListAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_user_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.getUsers()

        usersAdapter.listener = this

        binding.usersRv.apply {
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
                changeDuration = 300
            }
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
    }

    override fun observeViewModel() {
        with(userListViewModel) {
            userList.observe(viewLifecycleOwner) { resource ->
                when (resource.state) {
                    UserListState.SUCCESS -> {
                        resource.data?.let {
                            usersAdapter.users = it
                        }
                    }
                    else -> {
                    }
                }
            }

            userListRemote.observe(viewLifecycleOwner) { resource ->
                when (resource.state) {
                    UserListState.LOADING -> {
                        showLoading()
                    }
                    UserListState.FAILED -> {
                        hideLoading()
                        resource.message?.let {
                            showSnackMessage(it)
                        }
                    }
                    UserListState.SUCCESS -> {
                        hideLoading()
                    }
                }
            }
        }
    }

    override fun onUserClicked(userId: String) {
        val direction =
            UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(userId)
        navigateTo(direction)
    }
}
