package com.example.submission1app

import android.content.Context
import android.media.MediaRouter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1app.databinding.CardGithubRecyclerBinding

class CardViewAdapter(private val context: Context) : RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val mData = ArrayList<UsersData>()

    fun setData(items: ArrayList<UsersData>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_github_recycler, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = mData[position]
//        holder.binding.imageDisplayPicture.setImageResource(
//            context.resources.getIdentifier(
//                user.avatar?.substring(
//                    1
//                ), null, context.packageName
//            )
//        )
        holder.binding.textUsername.text = user.login
        Glide.with(context).load(user.avatar_url).into(holder.binding.imageDisplayPicture)
//        holder.binding.textReplaceableFollowers.text = user.follower.toString()
//        holder.binding.textReplaceableFollowing.text = user.following.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UsersData)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding = CardGithubRecyclerBinding.bind(itemView)
    }
}