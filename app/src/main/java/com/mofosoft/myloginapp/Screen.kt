package com.mofosoft.myloginapp

sealed class Screen(val route: String){
    object login : Screen("login")
    object register : Screen("register")
    object home : Screen("home")
}