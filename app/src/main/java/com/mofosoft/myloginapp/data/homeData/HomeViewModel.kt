package com.mofosoft.myloginapp.data.homeData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel(){

    val isUserLogedIn : MutableLiveData<Boolean> = MutableLiveData()
    fun checkForActiveSession(){
        if(FirebaseAuth.getInstance().currentUser != null){
            isUserLogedIn.value = true
        }
        else{
            isUserLogedIn.value = false
        }
    }
}