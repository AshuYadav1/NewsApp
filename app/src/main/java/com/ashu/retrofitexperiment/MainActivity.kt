package com.ashu.retrofitexperiment

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Url

class MainActivity : AppCompatActivity() {

    lateinit var adapter: Newsadapter
    private var articles = mutableListOf<Article>()
    var pagenumber =1
    var totalresult =-1
    val TAG = "Mainactivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recycle = findViewById<RecyclerView>(R.id.newslist) as RecyclerView
        var container = findViewById<ConstraintLayout>(R.id.containerbg)
        adapter = Newsadapter(this@MainActivity,articles)
        recycle.adapter = adapter // assign our adapter
       // recycle.layoutManager = LinearLayoutManager(this@MainActivity)

        val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        manager.setItemChangedListener(object :StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) { // whenever we swipe it create a event
                container.setBackgroundColor(Color.parseColor(colorpicker.getcolor()))
                Log.d(TAG,"First visible item -${manager.getFirstVisibleItemPosition()}")
                Log.d(TAG,"Item count -${manager.itemCount}")


                if (totalresult > manager.itemCount && manager.getFirstVisibleItemPosition()>= manager.itemCount - 5)
                {
                    pagenumber++

                    getNews()
                }

            }

        })
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)
        recycle.layoutManager = manager

        getNews()


    }

    private fun getNews() {

        Log.d(TAG,"second request call $pagenumber")

        val news = NewsService.newsInstance.getheadlines("in", pagenumber)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("vegiwell", news.toString())


                    totalresult = news.totalResults

                    // when wee receive data we add here

                    articles.addAll(news.articles)
                    // will tell our adpter our data is changed Re - render it
                    adapter.notifyDataSetChanged()


                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("hello", "Error in fetching data", t)

            }

        })

    }
}