package com.example.gapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.zip.Inflater

class ImageAdapter(private val context: Context,private val list: ArrayList<Image>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = list[position]
        Glide.with(context).load(currentImage.imagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder!!.image)
        holder.image.setOnClickListener {
            val intent = Intent(context,ImageFullActivity::class.java)
            intent.putExtra("path",currentImage.imagePath)
            intent.putExtra("name",currentImage.imageName)
            context.startActivity(intent)
        }



    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.row_image)

    }

}
