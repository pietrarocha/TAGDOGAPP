package com.example.apptagdog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Lembre-se de importar o seu R aqui, ex: import com.seuapp.R

@Composable
fun TagdogProfileScreen() {
    // Cores e estilos baseados nos seus designs
    val primaryOrange = Color(0xFFD37D41)
    val inactiveGray = Color(0xFF888888)
    val darkText = Color(0xFF1B243B)

    // Lista mock de itens do pet
    val petInfoList = listOf(
        PetInfoItem("Matrícula", "MT-269842"),
        PetInfoItem("Nascimento", "02/01/2011")
    )

    Scaffold(
        topBar = {
            ProfileTopBar()
        },
        bottomBar = {
            ProfileBottomNav(primaryOrange = primaryOrange, inactiveGray = inactiveGray)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // 1. Card de Informações do Usuário
            item {
                UserProfileCard()
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            // 2. Seção "Minhas Informações"
            item {
                ProfileSectionSubtitle(text = "Minhas Informações")
            }
            item {
                UserInfoListItems()
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            // 3. Seção "Informações do Pet"
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(8.dp))
                    ProfileSectionSubtitle(text = "Informações do Pet")
                }
            }
            item {
                PetCard()
            }
            items(petInfoList) { item ->
                PetInfoListItem(item)
            }
            item {
                PetDataBoxes()
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            // 4. Seção "Comportamento & Saúde"
            item {
                ProfileSectionSubtitle(text = "Comportamento & Saúde")
            }
            item {
                HealthObservationBox()
            }

            // Espaço extra no fundo para não cobrir o rodapé
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

// --- Componentes Reutilizáveis ---

@Composable
fun ProfileTopBar() {
    Surface(
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão Voltar (Estilo da image_15 header)
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(0xFFE0E0E0), CircleShape)
                    .clickable { /* Ação de voltar */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Título
            Text(
                text = "Perfil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B243B)
            )

        }
    }
}

@Composable
fun UserProfileCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto e Badge (image_25 mockup)
            Box {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Troque pelo seu R.drawable.foto_maria
                    contentDescription = "Maria and Dog",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )
                // Green Badge
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFF5CB85C), CircleShape)
                        .align(Alignment.BottomEnd)
                        .shadow(4.dp, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textos info
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Maria Cristiane de Souza", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B243B))
                Text(text = "maria@gmail.com", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                // Employee badge (como na image_25)
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFFDF0E7), // Laranja clarinho
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "Tutor",
                        color = Color(0xFFD37D41),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileSectionSubtitle(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1B243B),
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )
}

@Composable
fun UserInfoListItems() {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        UserInfoListItem(icon = Icons.Default.Badge, title = "CPF", value = "547230943-65")
        UserInfoListItem(icon = Icons.Default.Phone, title = "Telefone", value = "(31) 98812-3456")
    }
}

@Composable
fun UserInfoListItem(icon: ImageVector, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone customizado
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFFDF0E7), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = Color(0xFFD37D41), modifier = Modifier.size(20.dp))
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Textos
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1B243B), modifier = Modifier.weight(1f))
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1B243B))

        Spacer(modifier = Modifier.width(12.dp))

        // Chevron
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Ir", tint = Color.LightGray, modifier = Modifier.size(20.dp))
    }
}

data class PetInfoItem(val title: String, val value: String)

@Composable
fun PetInfoListItem(item: PetInfoItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Textos
        Text(text = item.title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1B243B), modifier = Modifier.weight(1f))
        Text(text = item.value, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1B243B))

        Spacer(modifier = Modifier.width(12.dp))

        // Chevron
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Ir", tint = Color.LightGray, modifier = Modifier.size(20.dp))
    }
}

@Composable
fun PetCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto Pet (image_25 mockup)
            Image(
                painter = painterResource(R.drawable.labrador), // Troque pelo seu R.drawable.foto_corgi
                contentDescription = "Pet",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Textos info
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Bob", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B243B))
                    Spacer(modifier = Modifier.width(8.dp))
                    // Active badge (como na image_25)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF5CB85C), // Verde
                    ) {
                        Text(
                            text = "Ativo",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
                Text(text = "TAG-B232", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun PetDataBoxes() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PetDataBox(icon = Icons.Default.Male, label = "Sexo", value = "Male")
        PetDataBox(icon = Icons.Default.LineWeight, label = "Porte", value = "Médio") // Troque pelo ícone de coleira custom
    }
}

@Composable
fun PetDataBox(icon: ImageVector, label: String, value: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.width(160.dp) // Largura fixa mock
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = Color(0xFFD37D41), modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = label, fontSize = 10.sp, color = Color.Gray)
                Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B243B))
            }
        }
    }
}

@Composable
fun HealthObservationBox() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Alérgico a grama alta. Muito dócil com outros cães, excelente em atividades em grupo.",
            fontSize = 12.sp,
            color = Color.Gray,
            lineHeight = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ProfileBottomNav(primaryOrange: Color, inactiveGray: Color) {
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
            // Inicio (Inactive, style from image_18 footer structure)
            ProfileBottomNavItem(icon = Icons.Default.Home, label = "Inicio", isSelected = false, activeColor = primaryOrange, inactiveColor = inactiveGray, modifier = Modifier.weight(1f))
            // Mapa (Inactive)
            ProfileBottomNavItem(icon = Icons.Default.LocationOn, label = "Mapa", isSelected = false, activeColor = primaryOrange, inactiveColor = inactiveGray, modifier = Modifier.weight(1f))
            // Demandas (Inactive)
            ProfileBottomNavItem(icon = Icons.Default.Assignment, label = "Demandas", isSelected = false, activeColor = primaryOrange, inactiveColor = inactiveGray, modifier = Modifier.weight(1f))

            // Perfil (**Active**, label and icon requested)
            ProfileBottomNavItem(icon = Icons.Default.Person, label = "Perfil", isSelected = true, activeColor = primaryOrange, inactiveColor = inactiveGray, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ProfileBottomNavItem(icon: ImageVector, label: String, isSelected: Boolean, activeColor: Color, inactiveColor: Color, modifier: Modifier) {
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
fun PreviewTagdogProfileScreen() {
    MaterialTheme {
        TagdogProfileScreen()
    }
}