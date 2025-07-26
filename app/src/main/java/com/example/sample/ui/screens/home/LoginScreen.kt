package com.example.sample.ui.screens.home


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sample.R


// Define custom colors based on the image
val PrimaryGreen = Color(0xFF388E3C) // A vibrant green for the header and buttons
val LightGreen = Color(0xFFD0E0D0)   // A lighter green for the background of the login section



@OptIn(ExperimentalMaterial3Api::class) // For OutlinedTextField
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Overall background can be white or match the light green
    ) {
        // Top Green Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f) // Takes up about 35% of the screen height
                .background(PrimaryGreen)
                .padding(start = 32.dp, top = 64.dp), // Adjust padding as needed
            contentAlignment = Alignment.TopStart
        ) {
            Column {
                Text(
                    text = "HELLO !",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "WELCOME\nTO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "SMART FARM MARKET PLACE",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }

        // Bottom Login Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f) // Takes up about 65% of the screen height
                .background(LightGreen, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)) // Rounded top corners
                .padding(horizontal = 32.dp, vertical = 48.dp), // Adjust padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Align content to the top
        ) {
            Text(
                text = "LOGIN",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen,
                modifier = Modifier
                    .align(Alignment.Start) // Align "LOGIN" text to the start
                    .padding(bottom = 24.dp)
            )

            // Email Input Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("email", color = Color.Gray) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                shape = RoundedCornerShape(12.dp), // Rounded corners for input field
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(56.dp) // Fixed height for consistency
            )

            // Password Input Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("password", color = Color.Gray) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(), // Hides password characters
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                shape = RoundedCornerShape(12.dp), // Rounded corners for input field
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .height(56.dp) // Fixed height for consistency
            )

            // Forgot Password
            TextButton(
                onClick = { /* Handle forgot password click */ },
                modifier = Modifier
                    .align(Alignment.End) // Align to the end
                    .padding(bottom = 32.dp)
            ) {
                   Text (text = "forgot password",
                    color = PrimaryGreen,
                    fontSize = 14.sp
                    )
            }

            // Login Button
            Button(
                onClick = { /* Handle login click */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(12.dp), // Rounded corners for button
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp) // Fixed height for consistency
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // "or login with" separator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = " or login\nwith ",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            // Social Login Buttons (Facebook and Google)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Facebook Button
                Button(
                    onClick = { /* Handle Facebook login */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .size(64.dp) // Square button
                        .padding(end = 16.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)), // Add a subtle border
                    contentPadding = PaddingValues(0.dp) // Remove default padding
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook), // Replace with your Facebook icon
                        contentDescription = "Login with Facebook",
                        modifier = Modifier.size(40.dp)
                    )
                }

                // Google Button
                Button(
                    onClick = { /* Handle Google login */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .size(64.dp) // Square button
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)), // Add a subtle border
                    contentPadding = PaddingValues(0.dp) // Remove default padding
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google), // Replace with your Google icon
                        contentDescription = "Login with Google",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Pushes content to the top

            // Don't have an account? Sign up
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
                TextButton(onClick = { /* Handle sign up click */ }) {
                    Text(
                        text = "sign up",
                        color = PrimaryGreen,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
