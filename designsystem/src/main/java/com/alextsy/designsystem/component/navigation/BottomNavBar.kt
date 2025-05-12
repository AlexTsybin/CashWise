package com.alextsy.designsystem.component.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalShapes

data class BottomNavItemData(
    val index: Int,
    val title: String,
    val icon: ImageVector,
)

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
) {
    val items = remember {
        listOf(
            BottomNavItemData(0, "Home", Icons.Default.Home),
            BottomNavItemData(1, "Records", Icons.Default.Receipt),
            BottomNavItemData(2, "Add Record", Icons.Default.Receipt),
            BottomNavItemData(3, "Analysis", Icons.Default.PieChart),
            BottomNavItemData(4, "Menu", Icons.Default.Menu),
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            val selected = selectedIndex == index
            if (index == 2) {
                FloatingActionButton(
                    onClick = {
                        onItemSelected(item.index)
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    shape = LocalShapes.current.dots,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            } else {
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onItemSelected(item.index)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(LocalDimensions.current.dimensions16),
                            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    label = {
                        CwText(
                            text = item.title,
                            textType = TextType.LABEL_SMALL,
                        )
                    },
                )
            }
        }
    }
}