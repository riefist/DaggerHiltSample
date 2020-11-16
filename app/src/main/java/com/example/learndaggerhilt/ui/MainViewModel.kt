package com.example.learndaggerhilt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learndaggerhilt.data.remote.response.ProfileResponse
import com.example.learndaggerhilt.data.repository.GithubRepository
import com.example.learndaggerhilt.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val githubRepository: GithubRepository
) : ViewModel(){

    val githubProfile by lazy {
        MutableLiveData<Resource<ProfileResponse>>()
    }

    fun getProfileUser(username: String){
        viewModelScope.launch {
            githubProfile.postValue(Resource.loading(null))
            try {
                val profileResponse = githubRepository.getProfile(username)
                githubProfile.postValue(Resource.success(profileResponse))
            } catch (e: Exception){
                githubProfile.postValue(Resource.error(e.localizedMessage, null))
            }
        }
    }

}