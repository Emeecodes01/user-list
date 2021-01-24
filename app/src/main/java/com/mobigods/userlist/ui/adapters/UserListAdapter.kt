package com.mobigods.userlist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobigods.userlist.databinding.LayoutUserItemBinding
import com.mobigods.userlist.models.UserModel
import com.mobigods.userlist.utils.AutoUpdateRV
import com.mobigods.userlist.utils.ImageLoader
import com.mobigods.userlist.utils.extensions.onClick
import javax.inject.Inject
import kotlin.properties.Delegates

class UserListAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(),
    AutoUpdateRV {

    var listener: UserListInterface? = null

    var users: List<UserModel> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new ->
            old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = LayoutUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = users.size

    inner class UserListViewHolder(private val binding: LayoutUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(pos: Int) {
            val user = users[pos]
            binding.fullName.text = "${user.firstName} ${user.lastName}"
            binding.email.text = user.email
            imageLoader.loadImage(binding.profileImage, user.picture)
            binding.card.onClick {
                listener?.onUserClicked(user.id)
            }
        }
    }

    interface UserListInterface {
        fun onUserClicked(userId: String)
    }
}
