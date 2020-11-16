package com.example.learndaggerhilt.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import coil.transform.CircleCropTransformation
import com.example.learndaggerhilt.databinding.ActivityMainBinding
import com.example.learndaggerhilt.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm : MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observeResult()
        vm.getProfileUser("riefist")
    }

    private fun observeResult(){
        vm.githubProfile.observe(this, Observer {
            when(it.status){
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    it.data?.let { profile ->
                        profile.apply {
                            binding.imgProfile.load(avatarUrl){
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }
                            binding.tvName.text = name

                            binding.tvRepos.text = "$publicRepos"
                            binding.tvFollowing.text = "$following"
                            binding.tvFollower.text = "$followers"
                        }

                    }
                }
                Status.ERROR -> {

                }
            }
        })
    }
}