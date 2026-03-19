package com.example.apptagdog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TagdogBottomNav(navController: NavController) {

    // Coloque as cores aqui dentro para não precisar pedir de fora:
    val primaryOrange = Color(0xFFD37D41)
    val grayText = Color(0xFF888888)
    // Descobre qual tela está aberta agora
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = true, // Você precisará lógica para isso depois
                activeColor = primaryOrange,
                inactiveColor = grayText,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            // 2. MAPA / RASTREAMENTO
            BottomNavItem(
                icon = Icons.Default.LocationOn,
                label = "Mapa",
                isSelected = currentRoute == "mapa",
                activeColor = primaryOrange,
                inactiveColor = grayText,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("mapa") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            // 3. DEMANDAS
            BottomNavItem(
                icon = Icons.AutoMirrored.Filled.Assignment,
                label = "Demandas",
                isSelected = currentRoute == "demandas",
                activeColor = primaryOrange,
                inactiveColor = grayText,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("demandas") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            // 4. PESQUISAR TUTOR
            BottomNavItem(
                icon = Icons.Default.PersonSearch,
                label = "Pesquisar tutor",
                isSelected = currentRoute == "pesquisar tutor", // Ajuste para a rota correta se for diferente
                activeColor = primaryOrange,
                inactiveColor = grayText,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("pesquisar") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { onClick() } // <-- ISSO AQUI FAZ O CLIQUE FUNCIONAR!
            .padding(bottom = 8.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(3.dp)
                .background(if (isSelected) activeColor else Color.Transparent)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) activeColor else inactiveColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) activeColor else inactiveColor
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}