package com.yourcompany.smartfarmmarketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sample.R
import com.example.sample.ui.navigation.Destinations


@Composable
fun OnboardingScreen(
    navController:NavController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id =R.drawable.background),
            contentDescription = "Farmers Market Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .alpha(0.5f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 64.dp), // Adjust padding as needed
//                .background(Color.Black.copy(alpha = 0f)), // Semi-transparent overlay for text readability
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WELCOME\nTO",
                fontSize = 52.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "SMART FARM\nMARKET PLACE",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 45.sp, // Adjust line height for better spacing
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Text(
                text = "CONNECTING FARMERS WITH\nTHE MARKET",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 64.dp)
            )

            Button(
                onClick = {

                    navController.navigate(Destinations.Login)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)), // A vibrant green color
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Make button take 80% of width
                    .height(60.dp) // Set a fixed height for the button
            ) {
                Text(
                    text = "GET STARTED",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

