package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun MyNavigation(navController: NavHostController, sharedPreferences: SharedPreferences, menuItems: List<MenuItemRoom>) {
    val isLogin  = sharedPreferences.getString("Email","")?.isEmpty();


    return NavHost(navController = navController, startDestination = Onboarding.route) {
        composable(Onboarding.route) {
            if(isLogin == false)
                navController.navigate(Home.route)
            OnboardingScreen(navController, sharedPreferences)
        }
        composable(Home.route) {
            HomeScreen(navController, menuItems)
        }
        composable(Profile.route) {
            ProfileScreen(navController, sharedPreferences)
        }
    }

}