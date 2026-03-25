package com.example.apptagdog.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptagdog.CadastroScreen
import com.example.apptagdog.HomeFuncionarioScreen
import com.example.apptagdog.RastreamentoScreen
import com.example.apptagdog.TagdogDemandasScreen
import com.example.apptagdog.TagdogProfileScreen
import com.example.apptagdog.TagdooLoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // O NavHost gerencia quem está na tela. O app vai começar pela tela de "login"
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            TagdooLoginScreen(
                onLoginClick = {
                    // Após logar, vai direto para a rota "home" (sem MainAppScreen!)
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onCadastroClick = {
                    navController.navigate("cadastro")
                }
            )
        }

        composable("cadastro") {
            CadastroScreen(navController = navController)
        }

        // ==========================================
        // SUAS TELAS COM A BARRA INFERIOR EMBUTIDA
        // ==========================================

        composable("home") {
            // Passe o navController para a sua tela!
            // (Substitua HomeFuncionarioScreen pelo nome real da sua função)
            HomeFuncionarioScreen(navController = navController)
        }

        composable("mapa") {
            RastreamentoScreen(navController = navController)
        }

        composable("demandas") {
            TagdogDemandasScreen(navController = navController)
        }

        composable("pesquisar") {
            // A tela do Banco de Tutores
            TagdogProfileScreen(navController = navController)
        }
    }
}