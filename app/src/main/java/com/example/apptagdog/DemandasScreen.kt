package com.example.apptagdog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TagdogDemandasScreen(navController: NavController) {
    val primaryOrange = Color(0xFFD37D41)
    val backgroundWhite = Color(0xFFFAFAFA)
    val darkText = Color(0xFF1B243B)
    val grayText = Color(0xFF888888)
    val lightGrayBg = Color(0xFFF0F0F0)
    val lightOrangeBg = Color(0xFFFDF0E7)
    val greenBadge = Color(0xFF5CB85C)

    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) } // 0 = Meus Cuidados, 1 = Todos no Hotel

    Scaffold(
        containerColor = backgroundWhite,
        bottomBar = {
            // Barra de Navegação Inferior (Atualizada com o novo design)
            TagdogBottomNav(activeColor = primaryOrange, inactiveColor = grayText)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // 1. Cabeçalho (Voltar e Título)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = darkText,
                    modifier = Modifier.clickable { /* Ação voltar */ }
                )
                Text(
                    text = "Demandas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(24.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Barra de Pesquisa
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Pesquisar por nome ou TAG...", color = grayText) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Pesquisar", tint = grayText)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = primaryOrange,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Abas (Tabs)
            Row(modifier = Modifier.fillMaxWidth()) {
                TabItem(
                    text = "Meus Cuidados",
                    isSelected = selectedTab == 0,
                    selectedColor = primaryOrange,
                    unselectedColor = grayText,
                    modifier = Modifier.weight(1f)
                ) { selectedTab = 0 }

                TabItem(
                    text = "Todos no Hotel",
                    isSelected = selectedTab == 1,
                    selectedColor = primaryOrange,
                    unselectedColor = grayText,
                    modifier = Modifier.weight(1f)
                ) { selectedTab = 1 }
            }
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Card do Cachorro (Thor)
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Foto, Nome e Badge
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.labrador),
                            contentDescription = "Foto do cão",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Bob", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = darkText)
                            Text(text = "Labrador", fontSize = 14.sp, color = grayText)
                        }

                        // Badge Verde
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = greenBadge,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "Sociável",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(16.dp))

                    // Ficha Técnica
                    Text(text = "FICHA TÉCNICA", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = grayText)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        // Matrícula
                        InfoBox(
                            icon = Icons.AutoMirrored.Filled.Assignment,
                            title = "MATRÍCULA",
                            value = "MT-101956",
                            iconBgColor = lightOrangeBg,
                            iconColor = primaryOrange,
                            modifier = Modifier.weight(1f)
                        )
                        // Nascimento
                        InfoBox(
                            icon = Icons.Default.DateRange,
                            title = "NASCIMENTO",
                            value = "02/01/2011",
                            iconBgColor = lightOrangeBg,
                            iconColor = primaryOrange,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Observações Comportamentais
                    Text(text = "OBSERVAÇÕES COMPORTAMENTAIS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = grayText)
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(lightGrayBg, RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Alérgico a grama alta. Manso, adora comer pães doces escondido, tem energia de sobra.",
                            fontSize = 14.sp,
                            color = darkText,
                            lineHeight = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botão Remover
                    OutlinedButton(
                        onClick = { /* Ação remover */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = primaryOrange),
                        border = androidx.compose.foundation.BorderStroke(1.dp, primaryOrange)
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Remover de meus cuidados", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}

// --- Componentes Reutilizáveis Customizados ---

@Composable
fun TabItem(text: String, isSelected: Boolean, selectedColor: Color, unselectedColor: Color, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) selectedColor else unselectedColor,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        // Linha laranja embaixo da aba selecionada
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(if (isSelected) selectedColor else Color.Transparent)
        )
    }
}

@Composable
fun InfoBox(icon: ImageVector, title: String, value: String, iconBgColor: Color, iconColor: Color, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconBgColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
            Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B243B))
        }
    }
}

@Composable
fun TagdogBottomNav(activeColor: Color, inactiveColor: Color) {
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
            // Inicio
            BottomNavItem(icon = Icons.Default.Home, label = "Home", isSelected = false, activeColor = activeColor, inactiveColor = inactiveColor, modifier = Modifier.weight(1f))
            // Mapa
            BottomNavItem(icon = Icons.Default.LocationOn, label = "Rastreamento", isSelected = false, activeColor = activeColor, inactiveColor = inactiveColor, modifier = Modifier.weight(1f))
            // Demandas (SELECIONADO)
            BottomNavItem(icon = Icons.AutoMirrored.Filled.Assignment, label = "Demandas", isSelected = true, activeColor = activeColor, inactiveColor = inactiveColor, modifier = Modifier.weight(1f))

            // CORRIGIDO AQUI: Trocado 'HomeBottomNavItem' para 'BottomNavItem'
            BottomNavItem(icon = Icons.Default.Person, label = "Perfil", isSelected = false, activeColor = activeColor, inactiveColor = inactiveColor, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean, activeColor: Color, inactiveColor: Color, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { /* Handle Nav */ }
    ) {
        // Linha indicadora no topo
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

@Preview(showBackground = true)
@Composable
fun PreviewTagdogDemandasScreen() {
    MaterialTheme {
        TagdogDemandasScreen(navController = rememberNavController())
    }
}