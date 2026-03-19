package com.example.apptagdog


import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    // O seu "controle remoto" das telas
    val navController = rememberNavController()

    // O NavHost gerencia quem está na tela. O app vai começar pela tela de "login"
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            TagdooLoginScreen(
                onLoginClick = {
                    // Esse é o comando que muda de tela!
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("Home") {
            TagdooHomeScreen(navController = navController)// HomeScreen()
        }
        composable("Rastreamento"){
            RastreamentoScreen(navController = navController)
        }

        composable("Demandas") {
            TagdogDemandasScreen(navController = navController)
        }

        composable("Pesquisar Tutor") {
            TagdogProfileScreen(navController = navController)
        }

        // Adicione outras telas aqui conforme for criando...
    }
}