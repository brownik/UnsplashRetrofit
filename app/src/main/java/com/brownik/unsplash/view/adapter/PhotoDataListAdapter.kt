package com.brownik.unsplash.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brownik.unsplash.R
import com.brownik.unsplash.data.model.PhotoData
import com.brownik.unsplash.databinding.RecyclerviewPhotoBinding

class PhotoDataListAdapter :
    ListAdapter<PhotoData, PhotoDataListAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(
        private val binding: RecyclerviewPhotoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PhotoData) = with(binding) {
            photoDataBinding = data
        }
    }

    override fun submitList(list: MutableList<PhotoData>?) {
        super.submitList(list?.toList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            RecyclerviewPhotoBinding.bind(layoutInflater.inflate(
                R.layout.recyclerview_photo,
                parent,
                false)
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object DiffCallback : DiffUtil.ItemCallback<PhotoData>() {
        override fun areItemsTheSame(
            oldItem: PhotoData,
            newItem: PhotoData,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PhotoData,
            newItem: PhotoData,
        ): Boolean {
            return oldItem == newItem
        }
    }
}