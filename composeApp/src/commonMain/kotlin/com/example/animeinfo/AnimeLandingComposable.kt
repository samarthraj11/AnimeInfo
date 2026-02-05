package com.example.animeinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

// --- Constants & Colors based on Tailwind Config ---
val ColorBackgroundDark = Color(0xFF1C1022)
val ColorBackgroundCard = Color(0xFF231C27)
val ColorPrimary = Color(0xFFA413EC)
val ColorTextSecondary = Color(0xFFB09DB9)
val ColorSearchBg = Color(0xFF332839)
val ColorGlass = Color(0xCC1C1022) // ~80% opacity for glass effect

@Composable
fun AnimeDiscoveryScreen() {
    Scaffold(
        containerColor = ColorBackgroundDark,
        bottomBar = { AnimeBottomNavigation() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { TopHeaderSection() }
            item { TrendingHeroSection() }
            item { SeasonalHitsSection() }
            item { GenreExplorerSection() }
            item { MustWatchClassicsSection() }
        }
    }
}

// --- Top Header ---
@Composable
fun TopHeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorGlass)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp)
            )
            .padding(bottom = 12.dp)
    ) {
        // Avatar and Title Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Avatar
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCVIGN0JEIKe7hIEqQyxjGbERRq5s90bzIXRyBld4MgzNrYHlSkpX2FJyopl37sCoxWTAtFNMuN-QMaeBapuGcyvt9-M2XenhMaIDqZZtqb79EGP0TRE0Lk7Zgl5EXBdspR1wmZxI7qt--MOA41J-FpEXtf_T9DBJpYNsXIR3jrKs0kRGwrAv_egcAun4TSwveKcA3ownoPPmAcalNpY5yqr1ijHXsMFlU-8iQlA0N0ztRNG7wAfd8M857_S3gJpS6mXss2_GE0dUI",
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, ColorPrimary.copy(alpha = 0.3f), CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Discovery",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            // Notification Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.05f)),
                contentAlignment = Alignment.Center
            ) {
//                Icon(
//                    imageVector = Icon,
//                    contentDescription = "Notifications",
//                    tint = Color.White
//                )
            }
        }

        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(ColorSearchBg)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Icon(
//                    imageVector = Icons.Rounded.Search,
//                    contentDescription = "Search",
//                    tint = ColorTextSecondary,
//                    modifier = Modifier.padding(start = 16.dp, end = 8.dp)
//                )
                Text(
                    text = "Search for anime, studios...",
                    color = ColorTextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// --- Trending Hero Section ---
@Composable
fun TrendingHeroSection() {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Trending Now",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = ColorPrimary
                ),
                modifier = Modifier.clickable { }
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(trendingData) { item ->
                TrendingCard(item)
            }
        }
    }
}

@Composable
fun TrendingCard(item: TrendingAnime) {
    Card(
        modifier = Modifier
            .width(320.dp) // ~85% width style
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = ColorBackgroundCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(180.dp)) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    ColorBackgroundCard,
                                    Color.Transparent,
                                    Color.Transparent
                                ),
                                startY = Float.POSITIVE_INFINITY,
                                endY = 0f
                            )
                        )
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                // Badge
                Surface(
                    color = ColorPrimary,
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = item.badgeText,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        color = Color.White
                    )
                }

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "${item.studio} • ${item.genre}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = ColorTextSecondary
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = ColorPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.PlayArrow,
//                        contentDescription = null,
//                        modifier = Modifier.size(18.dp)
//                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Watch Now", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// --- Seasonal Hits Section ---
@Composable
fun SeasonalHitsSection() {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Seasonal Hits",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(seasonalData) { item ->
                SeasonalCard(item)
            }
        }
    }
}

@Composable
fun SeasonalCard(item: AnimeItem) {
    Column(modifier = Modifier.width(140.dp)) {
        Box(
            modifier = Modifier
                .aspectRatio(3f / 4f)
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Rating Badge
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
//                    Icon(
//                        imageVector = Icons.Rounded.Star,
//                        contentDescription = null,
//                        tint = Color(0xFFFFD700), // Gold
//                        modifier = Modifier.size(12.dp)
//                    )
                    Text(
                        text = item.rating,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = ColorTextSecondary
            ),
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

// --- Genre Explorer ---
@Composable
fun GenreExplorerSection() {
    Column(modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp)) {
        Text(
            text = "Top Genres",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//                GenreCard(
//                    title = "Action",
//                    icon = Icons.Default.Swords, // Requires material-icons-extended, using generic fallback if needed
//                    gradientColors = listOf(ColorPrimary, Color(0xFF6B21A8)), // Purple
//                    modifier = Modifier.weight(1f)
//                )
//                GenreCard(
//                    title = "Sci-Fi",
//                    icon = Icons.Default.RocketLaunch,
//                    gradientColors = listOf(Color(0xFF2563EB), Color(0xFF1E3A8A)), // Blue
//                    modifier = Modifier.weight(1f)
//                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//                GenreCard(
//                    title = "Romance",
//                    icon = Icons.Default.Favorite,
//                    gradientColors = listOf(Color(0xFFEC4899), Color(0xFF9D174D)), // Pink
//                    modifier = Modifier.weight(1f)
//                )
//                GenreCard(
//                    title = "Magic",
//                    icon = Icons.Default.AutoFixHigh,
//                    gradientColors = listOf(Color(0xFFF59E0B), Color(0xFF92400E)), // Amber
//                    modifier = Modifier.weight(1f)
//                )
            }
        }
    }
}

@Composable
fun GenreCard(
    title: String,
    icon: ImageVector,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Brush.linearGradient(gradientColors))
    ) {
        // Rotated Icon Background
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.2f),
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 8.dp, y = 8.dp)
                .rotate(12f)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// --- Must Watch Classics ---
@Composable
fun MustWatchClassicsSection() {
    Column(modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp)) {
        Text(
            text = "Must Watch Classics",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            classicsData.forEach { item ->
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(3f / 4f)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        AsyncImage(
                            model = item.imageUrl,
                            contentDescription = item.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Rating: ${item.rating}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = ColorTextSecondary
                        )
                    )
                }
            }
        }
    }
}

// --- Bottom Navigation ---
@Composable
fun AnimeBottomNavigation() {
    NavigationBar(
        containerColor = ColorGlass,
        contentColor = ColorTextSecondary,
        modifier = Modifier.border(
            width = 1.dp,
            color = Color.White.copy(alpha = 0.1f),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
        )
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {  },
            label = { Text("HOME", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ColorPrimary,
                selectedTextColor = ColorPrimary,
                unselectedIconColor = ColorTextSecondary,
                unselectedTextColor = ColorTextSecondary,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {  },
            label = { Text("BROWSE", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = ColorTextSecondary,
                unselectedTextColor = ColorTextSecondary,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {  },
            label = { Text("MY LIST", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = ColorTextSecondary,
                unselectedTextColor = ColorTextSecondary,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {  },
            label = { Text("PROFILE", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = ColorTextSecondary,
                unselectedTextColor = ColorTextSecondary,
                indicatorColor = Color.Transparent
            )
        )
    }
}

// --- Data Models & Mock Data ---

data class TrendingAnime(
    val title: String,
    val studio: String,
    val genre: String,
    val imageUrl: String,
    val badgeText: String
)

data class AnimeItem(
    val title: String,
    val subtitle: String, // Studio or extra info
    val rating: String,
    val imageUrl: String
)

val trendingData = listOf(
    TrendingAnime(
        "Demon Slayer: Kimetsu no Yaiba",
        "Ufotable", "Action, Fantasy",
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDdidMPr53uvKJ_MJvGcx_JboWbXmoH5wZimKjqawV6xUwdCZj26u_sI3hw9eB7C5kHwjf9Y-ayt0Kv3lvFRCCm_YRZ_y8Xs00BVuJxNSjQSyT5OoruIYfkheU56HAUD6A36cCfgJDem0r9293Jgf7U3uCfKNGv4HSnLg2NddVq2avaZy5VmZl0EMMJU9WCzuZo8CaaVkNHy2dVTO8Q4oswGow7KIsmQx1YRQyEC81CfxzWTfWnQ31dDyO4TuxL2A2gdBJ5IGaQGS0",
        "POPULAR"
    ),
    TrendingAnime(
        "Jujutsu Kaisen",
        "MAPPA", "Supernatural, Action",
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDTOz-Pvr6SMgVt92fTIyk1MKed2Camx1QH1ma87pWHtNQjHg9fYspHgnjlQMAqlow6yIIIq_WHUSImmbJKkmeYlt4SkEquLLSnaQeZmxnnpnzTXDgxgvjafl_27p52_Jj1R1aovd-N_GiiC-cJ3W6AiKwWpC7PzS1XjMtE7v0N9cj-q2teeHZ1u4BZtpPvKZxxeF9gE4-IiRAEOf5vX2oNhc5-9hKzr-8H0HnhbhjOn-Le2z1rza5qo2sQau1e4l75ieL-UIUsZnA",
        "NEW SEASON"
    )
)

val seasonalData = listOf(
    AnimeItem("Oshi no Ko", "Doga Kobo", "4.9", "https://lh3.googleusercontent.com/aida-public/AB6AXuCYBk7cJbbMa_ATfyXpmzpF449IEu_ta_gM1WIzsGgmfaz9jVj-N5SOeKEvM_M-WQQXwCxWGbJqYdhDaXPb75TJlRebT87ZtG-s_ShE71MxSPJQbRlXxw1izFg9q85pftUfe2zo57trTm4nr33lS1Rkg_QAVAHu39GE9OjfdZCEE5Eb7cXA8Zh31DbQVr7xuk5dSphbCwexa9M4uWVXIgPBT1psVhh7Z8fW1PrhqiqsKZXfbisi33x001Av1ttB5IrserIOJ3yRJF0"),
    AnimeItem("Hell's Paradise", "MAPPA", "4.7", "https://lh3.googleusercontent.com/aida-public/AB6AXuB4fQOfAAZl4rPt6oL5Ut3xM9aFTgomiOHeeRN_3sbPGtzE_zGpD9-F2fngkiG0fqlb2c0av7Xk6pQep1fyXqNF5C_wBTo2Qkj3Jy0nSBKJhe9KGmy4WnWMCtb3Nn6PuzWViN-Pg3hc08WEubOizqGP4S5SNsA9cAoSLk_eNqS4IWnK-Z9pDus837Aa5qyJfpQbbXCPTlcOdL526vHVADskcPFEqAU6HfizHrfPJEEV-z1H5ijUeUv-BbQ8H8jNl--48qU-PQa8OGI"),
    AnimeItem("Mashle", "A-1 Pictures", "4.5", "https://lh3.googleusercontent.com/aida-public/AB6AXuDVH2WQU7ACBrnhk7t8IMtk4gMKvuef_3Qg6ZL1trR-hSORq9w6jDxuwKH7PUFcwdqlcDjplAxYRlHKS6BAABJN8e7bjvn5kOSITTuFOlN8z9eU8jBs7D4QgT4a4WYAM1JBbLudlXYItzziCEFtf3AcG9GP3FE6OAsO9dgyWCxu9qfhCBuUg9O6TXBQMAROUwmdDZE3S9n3ONX-e4AyH76VlDmKynC5J7C4gJCrsXebTzKwkbrIKgChWvIl9ldB-BC3JrkxBoiWxQg")
)

val classicsData = listOf(
    AnimeItem("Fullmetal Alchemist", "", "9.1", "https://lh3.googleusercontent.com/aida-public/AB6AXuBZqqJeKI_DQG6uW4rThLp2tui9-7UTOZ5mpOIXLvS4lOxiLvz4EYDRoi-ZO2F4eW1Qov76W-WnzYkTjKdGEjNY8OvwvCsrDOIn0b-e6dLrcPQe3j86u5p_YKBzdYXCeakdA2vdMvelJsnLFNzbNCg7iuu1eTkXXCM2xhM0MP1CRn62JNjgePfzTtq5Zr0pvZarsTLW4No_pN-4ZqtpvHzb5F0ZD8ZgdG_DIAGX6yjve1NZPDS8zhEURazYww-vTNn1W0vrKvZpD5Y"),
    AnimeItem("Steins;Gate", "", "9.0", "https://lh3.googleusercontent.com/aida-public/AB6AXuBDvKgd_Ux5Yf-t6WWDPA7nY1efzM-wE4V7tvu3Ak1hqctuNrI2UZV59YovOZ8N4zppCW8UUos_SMJDKwvthcxi-htRDtEGiRK1L9P5bECOC7RDGci0bKfutqC8mH9QBIu6SKTo9T1B9q2NJ-O7x36dQjN9D-pjjB_4u77C11N5YVffeqPcL8Hwg7qViWVoahPT17i9ZT0pYxb940fYCzV_36pnRryttiUzsjZuiv6eRuSPu2pM4o7aXkQBAtS4a81Pp_eWwkoDjk0")
)