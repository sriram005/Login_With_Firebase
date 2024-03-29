package com.mofosoft.myloginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mofosoft.myloginapp.data.homeData.HomeViewModel
import com.mofosoft.myloginapp.ui.theme.MyLoginAppTheme
import com.mofosoft.myloginapp.user.HomeScreen
import com.mofosoft.myloginapp.user.LoginScreen
import com.mofosoft.myloginapp.user.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MyLoginAppTheme(
                darkTheme = false
            ) {
                val homeViewModel : HomeViewModel = viewModel()
                homeViewModel.checkForActiveSession()

                var startDestination : String
                if(homeViewModel.isUserLogedIn.value == true)
                        startDestination = Screen.home.route
                else
                        startDestination = Screen.login.route

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ){
                    composable(Screen.login.route){
                        LoginScreen(navController)
                    }

                    composable(Screen.register.route){
                        RegisterScreen(navController)
                    }

                    composable(Screen.home.route){
                        HomeScreen(navController)
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyLoginAppTheme {
        
    }
}