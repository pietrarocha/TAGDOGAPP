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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeFuncionarioScreen(navController: NavController) {
    // Cores baseadas no design
    val primaryOrange = Color(0xFFD37D41)
    val darkBlue = Color(0xFF1B243B)
    val backgroundGray = Color(0xFFF5F6F8)
    val lightOrangeBg = Color(0xFFFDF0E7)
    val darkText = Color(0xFF1B243B)
    val grayText = Color(0xFF888888)

    Scaffold(
        containerColor = backgroundGray,
        floatingActionButton = {
            // Botão Flutuante
            Button(
                onClick = { /* Ação de Novo Cadastro/Check-in */ },
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
            // Barra de Navegação Inferior
            FuncionarioBottomNav(primaryOrange = primaryOrange, grayText = grayText)
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
                // Botão Voltar (Alinhado à esquerda)
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterStart)
                        .clickable { /* Ação de voltar */ }
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
                    verticalArrangement = Arrangement.Center, // <-- CENTRALIZA OS CARTÕES
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Cartão 1: Mapa e Rastreamento
                    FuncionarioServiceCardItem(
                        title = "Rastreamento GPS",
                        description = "Acompanhe a rota atual do PET.",
                        icon = Icons.Default.LocationOn,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText
                    )

                    Spacer(modifier = Modifier.height(24.dp)) // <-- ESPAÇAMENTO AUMENTADO

                    // Cartão 2: Demandas
                    FuncionarioServiceCardItem(
                        title = "Demandas",
                        description = "Acesse as suas demandas atribuídas.",
                        icon = Icons.AutoMirrored.Filled.Assignment,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText
                    )

                    Spacer(modifier = Modifier.height(24.dp)) // <-- ESPAÇAMENTO AUMENTADO

                    // Cartão 3: Banco de Tutores
                    FuncionarioServiceCardItem(
                        title = "Banco de Tutores",
                        description = "Consulte a lista de tutores.",
                        icon = Icons.Default.Search,
                        iconBgColor = lightOrangeBg,
                        iconColor = primaryOrange,
                        darkText = darkText
                    )
                }
            }
        }
    }
}

// --- Componentes Reutilizáveis ---

@Composable
fun FuncionarioServiceCardItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconColor: Color,
    darkText: Color
) {
    Card(
        shape = RoundedCornerShape(20.dp), // Cantos um pouco mais redondos
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp), // Leve aumento na sombra
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Ação do cartão */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp), // <-- PADDING INTERNO AUMENTADO
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp) // <-- CAIXA DO ÍCONE MAIOR
                    .background(iconBgColor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(32.dp) // <-- ÍCONE MAIOR
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp, // <-- TÍTULO MAIOR
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = description,
                    fontSize = 14.sp, // <-- DESCRIÇÃO MAIOR
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
                modifier = Modifier.size(28.dp) // <-- SETINHA MAIOR
            )
        }
    }
}

@Composable
fun FuncionarioBottomNav(primaryOrange: Color, grayText: Color) {
    Surface(
        color = Color.White,
        shadowElevation = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FuncionarioBottomNavItem(icon = Icons.Default.Home, label = "Inicio", isSelected = true, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
            FuncionarioBottomNavItem(icon = Icons.Default.LocationOn, label = "Mapa", isSelected = false, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
            FuncionarioBottomNavItem(icon = Icons.AutoMirrored.Filled.Assignment, label = "Demandas", isSelected = false, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
            // Alterado aqui: de Person para PersonSearch e texto para Tutores
            FuncionarioBottomNavItem(icon = Icons.Default.PersonSearch, label = "Banco de Tutores", isSelected = false, activeColor = primaryOrange, inactiveColor = grayText, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun FuncionarioBottomNavItem(icon: ImageVector, label: String, isSelected: Boolean, activeColor: Color, inactiveColor: Color, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable { /* Navegação */ }
    ) {
        // Indicador de aba selecionada colado no topo
        if (isSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(0.5f)
                    .height(3.dp)
                    .background(activeColor)
            )
        }

        // Ícone e Texto
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeFuncionarioScreen() {
    MaterialTheme {
        HomeFuncionarioScreen(navController = rememberNavController())
    }
}