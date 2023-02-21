package com.techvanka.galleryapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techvanka.galleryapp.databinding.ImageviewerBinding
import com.techvanka.galleryapp.databinding.VideoLayoutBinding


class Adapter(var context:Context, var list: ArrayList<String>):RecyclerView.Adapter<Adapter.ViewHolder>() {
     val IMAGE_CODE = 0
  val VIDEO_CODE = 1

    class ViewHolder(var binding: ImageviewerBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view = ImageviewerBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     //   holder.binding.button.visibility = View.GONE
      Glide.with(context).load(list[position]).into(holder.binding.imageView)
        if (!list[position].contains(".mp4")){
            holder.binding.button.visibility = View.GONE
        }


    }

    override fun getItemCount(): Int {
      return  list.size
    }



}