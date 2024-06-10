package com.example.domesticserviceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.FirebaseApp

class Homepage : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    private val delayMillis = 1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        FirebaseApp.initializeApp(this)

        viewPager = findViewById(R.id.viewPager)
                adapter = ImageSliderAdapter()
                viewPager.adapter = adapter

                // Optionally, set up indicator or any other customization
                handler = Handler(Looper.getMainLooper())
                runnable = object : Runnable {
                    override fun run() {
                        // Move to the next image
                        viewPager.setCurrentItem((viewPager.currentItem + 1) % adapter.itemCount, true)
                        handler.postDelayed(this, delayMillis)
                    }
                }

                // Start automatic sliding
                startAutoSlide()
            }

            private fun startAutoSlide() {
                handler.postDelayed(runnable, delayMillis)
            }

            override fun onDestroy() {
                super.onDestroy()
                // Stop automatic sliding when activity is destroyed to prevent memory leaks
                handler.removeCallbacks(runnable)
            }
        }

        class ImageSliderAdapter : RecyclerView.Adapter<ImageViewHolder>() {
            // Replace this array with your list of image URLs or resource IDs
            private val images = arrayOf(R.drawable.img1, R.drawable.img2, R.drawable.logo)

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.image_slider_item, parent, false)
                return ImageViewHolder(view)
            }

            override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
                holder.bind(images[position])
            }

            override fun getItemCount(): Int {
                return images.size
            }
        }

        class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val imageView: ImageView = itemView.findViewById(R.id.imageView)

            fun bind(imageResId: Int) {
                imageView.setImageResource(imageResId)
            }
        }
