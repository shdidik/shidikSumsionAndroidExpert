package com.app.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.favorite.di.favoriteModule
import com.dicoding.tugas.core.ui.TourismAdapter
import com.dicoding.tugas.databinding.FragmentFavoriteBinding
import com.dicoding.tugas.detail.DetailTugasActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteDynamic : AppCompatActivity() {


    private val favoriteViewModelDynamic: FavoriteViewModelDynamic by viewModel()
    private  var binding: FragmentFavoriteBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        loadKoinModules(favoriteModule)
        supportActionBar?.title = "Favorite"



        callF()

    }

    private fun callF() {


            val tourismAdapter = TourismAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val intent = Intent(this, DetailTugasActivity::class.java)
                intent.putExtra(DetailTugasActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }


            favoriteViewModelDynamic.favoriteTourism.observe(this, { dataTourism ->
                tourismAdapter.setData(dataTourism)
                binding!!.viewEmpty.root.visibility = if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding!!.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tourismAdapter
            }

        binding!!.fabDinamic.setOnClickListener {
            Log.d("sidik","tester")
            try {
//                moveToChatActivity()
            } catch (e: Exception){
                Log.d("sidik", "Module not found")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}