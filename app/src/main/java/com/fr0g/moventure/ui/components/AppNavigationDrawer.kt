package com.fr0g.moventure.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppNavigationDrawer(
    onNavigateToHome: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerContentColor = MaterialTheme.colorScheme.onSurface,
        modifier = modifier.width(300.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Moventure",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 4.sp
            )
        }

        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

        // Home
        NavigationDrawerItem(
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, null) },
            selected = false,
            onClick = {
                onNavigateToHome()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 32.dp))

        // Watchlist
        NavigationDrawerItem(
            label = { Text("Watchlist") },
            icon = { Icon(Icons.Default.Bookmark, null) },
            selected = false,
            onClick = {
                onNavigateToWatchlist()
            },
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}