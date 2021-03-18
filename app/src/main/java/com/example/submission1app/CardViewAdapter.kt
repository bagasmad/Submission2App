package com.example.submission1app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter(private val listUserDatas: ArrayList<UserDatas>) : RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {
    private lateinit var context: Context
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setContext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_github_recycler,viewGroup,false)
        return CardViewViewHolder(view)

    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = listUserDatas[position]
        holder.imageDisplayPicture.setImageResource(context.resources.getIdentifier(user.avatar?.substring(1), null, context.getPackageName()));
        holder.textUsername.text = user.username
        holder.textReplaceableFollowers.text = user.follower.toString()
        holder.textReplaceableFollowing.text = user.following.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserDatas)
    }


    override fun getItemCount(): Int {
        return listUserDatas.size
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageDisplayPicture : ImageView = itemView.findViewById(R.id.image_display_picture)
        var textUsername: TextView = itemView.findViewById(R.id.text_username)
        var textReplaceableFollowers: TextView = itemView.findViewById(R.id.text_replaceable_followers)
        var textReplaceableFollowing : TextView = itemView.findViewById(R.id.text_replaceable_following)
    }
}