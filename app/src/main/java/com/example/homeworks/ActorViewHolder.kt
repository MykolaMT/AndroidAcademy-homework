package com.example.homeworks

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homeworks.data.Actor

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView = itemView.findViewById<TextView>(R.id.actor_name)
    private val pictureView = itemView.findViewById<ImageView>(R.id.actor_picture)

    fun renderActor(actor: Actor) {
        nameView.text = actor.name
        pictureView.load(actor.picture)
    }
}