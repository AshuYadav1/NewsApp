package com.ashu.retrofitexperiment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Newsadapter ( val context: Context, val article: List<Article>) : RecyclerView.Adapter<Newsadapter.ArticleViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.iteamview,parent,false)
        return ArticleViewholder(view)
    }



    override fun onBindViewHolder(holder: ArticleViewholder, position: Int) {
        val article = article[position]
        holder.title.text = article.title
        holder.description.text = article.description

        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener{
            Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()

            val intent = Intent(context,Webviewactivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)

        }



    }

    override fun getItemCount(): Int {
        return  article.size
    }

    class ArticleViewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsimage)
        var title = itemView.findViewById<TextView>(R.id.Title)
        var description = itemView.findViewById<TextView>(R.id.Description)
    }


}