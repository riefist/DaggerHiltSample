package com.example.learndaggerhilt.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.learndaggerhilt.databinding.ActivityMainBinding
import com.example.learndaggerhilt.utils.SettingsManager
import com.example.learndaggerhilt.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm : MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        settingsManager = SettingsManager(this)
        observeResult()
        vm.getProfileUser("riefist")

        lifecycleScope.launchWhenStarted {
            if (settingsManager.isLogin == true){
                startActivity(Intent(this@MainActivity, CurrencyConverterActivity::class.java))
            }

        }
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

                        lifecycleScope.launch {
                            settingsManager.setLoginSession(true)
                        }

                    }
                }
                Status.ERROR -> {

                }
            }
        })
    }
}