package com.example.submission1app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1app.databinding.CardGithubRecyclerBinding

class CardViewAdapter(private val listUserDatas: ArrayList<UserDatas>) :
    RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {
    private lateinit var context: Context
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setContext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_github_recycler, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = listUserDatas[position]
        holder.binding.imageDisplayPicture.setImageResource(
            context.resources.getIdentifier(
                user.avatar?.substring(
                    1
                ), null, context.packageName
            )
        )
        holder.binding.textUsername.text = user.username
        holder.binding.textReplaceableFollowers.text = user.follower.toString()
        holder.binding.textReplaceableFollowing.text = user.following.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserDatas)
    }

    override fun getItemCount(): Int {
        return listUserDatas.size
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding = CardGithubRecyclerBinding.bind(itemView)
    }
}