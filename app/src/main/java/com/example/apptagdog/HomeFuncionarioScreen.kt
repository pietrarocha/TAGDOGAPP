package com.example.apptagdog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeFuncionarioScreen(navController: NavController) {
    // Cores baseadas no design
    val primaryOrange = Color(0xFFD37D41)
    val darkBlue = Color(0xFF1B243B)
    val backgroundGray = Color(0xFFF5F6F8)
    val lightOrangeBg = Color(0xFFFDF0E7)
    val darkText = Color(0xFF1B243B)

    Scaffold(
        containerColor = backgroundGray,
        floatingActionButton = {
            // Botão Flutuante
            Button(
                onClick = { navController.navigate("cadastro") }, // <-- Agora leva para o cadastro
                colors = ButtonDefaults.buttonColors(containerColor = primaryOrange),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "Novo Cadastro",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        bottomBar = {
            // AQUI ESTÁ A MÁGICA: A barra oficial conectada ao NavController
            TagdogBottomNav(navController = navController)
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White) // Fundo branco no topo para o cabeçalho
        ) {

            // 1. Topo com Voltar, Serviços e Perfil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            // Substitua "rota_de_login" pelo nome exato da sua rota no NavHost
                            navController.navigate("login") {
                                // Essa parte garante que a pilha de telas seja limpa,
                                // evitando que o usuário aperte "voltar" no celular e volte para cá
                                popUpTo(0) { inclusive = true }
                            }
                        }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Voltar",
                            tint = darkText,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Título Centralizado
                Text(
                    text = "Serviços",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )

                // Ícone de Perfil (Alinhado à direita)
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFFE5E5E5), CircleShape)
                        .align(Alignment.CenterEnd)
                        .clickable { /* Ação do Perfil */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            // 2. Área Azul e Cartões Sobrepostos
            Box(modifier = Modifier.fillMaxSize()) {

                // Fundo Azul Escuro
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(darkBlue)
                )

                // Recipiente Cinza Claro com Cantos Arredondados (Sobreposto ao Azul)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp) // Curva sobreposta ao azul
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(backgroundGray)
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 70.dp), // Compensa o espaço do Botão Flutuante
                    verticalArrangement = Arrangement.Center, // CENTRALIZA OS CARTÕES
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Cartão 1: Mapa e Rastreamento
                    FuncionarioServiceCardItem(
                        title = "Rastreamento GPS",
                        description = "Acompanhe a rota atual do PET.",
                        icon = Icons.Default.LocationOn,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText,
                        onClick = { navController.navigate("mapa") } // <-- Navegação!
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Cartão 2: Demandas
                    FuncionarioServiceCardItem(
                        title = "Demandas",
                        description = "Acesse as suas demandas atribuídas.",
                        icon = Icons.AutoMirrored.Filled.Assignment,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText,
                        onClick = { navController.navigate("demandas") } // <-- Navegação!
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Cartão 3: Banco de Tutores
                    FuncionarioServiceCardItem(
                        title = "Banco de Tutores",
                        description = "Consulte a lista de tutores.",
                        icon = Icons.Default.Search,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText,
                        onClick = { navController.navigate("pesquisar") } // <-- Navegação!
                    )
                }
            }
        }
    }
}

// --- Componente: Cartão de Serviço Atualizado (Com onClick) ---
@Composable
fun FuncionarioServiceCardItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconColor: Color,
    darkText: Color,
    onClick: () -> Unit // <-- Parâmetro obrigatório agora
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // <-- O clique acontece aqui
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(iconBgColor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Ir",
                tint = iconColor,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

// --- Componentes da Barra de Navegação (Definitivos) ---
@Composable
fun TagdogBottomNav(navController: NavController) {

    val primaryOrange = Color(0xFFD37D41)
    val grayText = Color(0xFF888888)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(65.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = currentRoute == "home",
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

            BottomNavItem(
                icon = Icons.Default.PersonSearch,
                label = "Tutores",
                isSelected = currentRoute == "pesquisar",
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
            .fillMaxHeight()
            .clickable { onClick() }
    ){
        // Linha indicadora no topo
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(3.dp)
                .background(if (isSelected) activeColor else Color.Transparent)
        )

        Spacer(modifier = Modifier.weight(1f)) // Empurra o ícone pro meio

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

        Spacer(modifier = Modifier.weight(1f)) // Empurra o texto pra cima
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeFuncionarioScreen() {
    MaterialTheme {
        HomeFuncionarioScreen(navController = rememberNavController())
    }
}
