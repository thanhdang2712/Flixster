package com.example.flixster

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "Movie Adapter"
class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //Expensive operation: create a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    //Cheap operation: simply bind data to an existing viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder $position")
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val tvPoster = itemView.findViewById<ImageView>(R.id.tvPoster)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            val imageUrl: String = if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                movie.backdropImageUrl
            } else {
                movie.posterImageUrl
            }
            Glide.with(context).load(imageUrl).into(tvPoster)

        }
    }
}
