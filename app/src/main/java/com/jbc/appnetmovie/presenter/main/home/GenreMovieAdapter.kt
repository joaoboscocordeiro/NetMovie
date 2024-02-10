package com.jbc.appnetmovie.presenter.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbc.appnetmovie.databinding.ItemGenreBinding
import com.jbc.appnetmovie.presenter.model.GenrePresentation

/*
 * Created by Joao Bosco on 09/02/24.
 */
class GenreMovieAdapter :
    ListAdapter<GenrePresentation, GenreMovieAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenrePresentation>() {
            override fun areItemsTheSame(
                oldItem: GenrePresentation,
                newItem: GenrePresentation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GenrePresentation,
                newItem: GenrePresentation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val genre = getItem(position)

        holder.binding.textGenreName.text = genre.name

        val movieAdapter = MovieAdapter(holder.binding.root.context)
        val layoutManager =
            LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        holder.binding.rvGenre.layoutManager = layoutManager
        holder.binding.rvGenre.setHasFixedSize(true)
        holder.binding.rvGenre.adapter = movieAdapter
        movieAdapter.submitList(genre.movies)
    }

    inner class MyViewHolder(val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root)
}