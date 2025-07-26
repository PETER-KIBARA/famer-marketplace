package com.example.sample.ui.screens // Adjust package as needed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.* // Still Material 2 based on your original code
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.password
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sample.ui.screens.login.viewmodels.SignUpViewModel

// Define your color palette (adjust these to match your exact shades)
val Green500 = Color(0xFF4CAF50)
val LightGreenBackground = Color(0xFFD9F1D9)
val DarkGrayTextField = Color(0xFF616161)
val WhiteText = Color(0xFFFFFFFF)
val ErrorColor = Color.Red // For error text

// Placeholder String Resource function (in a real app, use stringResource(R.string.your_id))
@Composable
fun placeholderString(key: String, default: String): String {
    // In a real app, you'd use stringResource(id = ...)
    // For this example, we'll just return the default.
    // Consider creating a strings.xml for these.
    return when (key) {
        "back_to_login" -> "BACK TO LOGIN"
        "sign_up_title" -> "SIGN UP"
        "name_label" -> "Name"
        "email_label" -> "Email"
        "password_label" -> "Password"
        "confirm_password_label" -> "Confirm Password"
        "sign_up_button" -> "SIGN UP"
        else -> default
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(), // Inject ViewModel
    onNavigateBack: () -> Unit, // Callback for back navigation
    onNavigateToLogin: () -> Unit // Callback for successful registration (example)
) {
    val formState = signUpViewModel.formState
    val context = LocalContext.current // For potential Toasts or other context needs

    // Effect for successful registration navigation
    LaunchedEffect(formState.isRegistrationSuccessful) {
        if (formState.isRegistrationSuccessful) {
            // TODO: Show a success message (e.g., Toast) if desired before navigating
            // Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
            onNavigateToLogin() // Navigate to login or home
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            // TODO: Implement actual back navigation
                            onNavigateBack()
                        }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = placeholderString(
                                    "back_to_login_cd",
                                    "Back to Login"
                                ), // Placeholder
                                tint = WhiteText
                            )
                        }
                        Text(
                            text = placeholderString("back_to_login", "BACK TO LOGIN"),
                            color = WhiteText,
                            fontSize = 16.sp
                        )
                    }
                }

            )
        },
        backgroundColor = LightGreenBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .verticalScroll(rememberScrollState()), // Make content scrollable if it overflows
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.spacedBy(10.dp) // Adjusted spacing
        ) {
            Text(
                text = placeholderString("sign_up_title", "SIGN UP"),
                color = Green500,
                fontSize = 28.sp,
                style = MaterialTheme.typography.h4, // Example usage of MaterialTheme typography
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Name TextField
            CustomTextField(
                value = formState.name,
                onValueChange = { signUpViewModel.onNameChange(it) },
                label = placeholderString("name_label", "Name"),
                errorMessage = formState.nameError,
                enabled = !formState.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Email TextField
            CustomTextField(
                value = formState.email,
                onValueChange = { signUpViewModel.onEmailChange(it) },
                label = placeholderString("email_label", "Email"),
                keyboardType = KeyboardType.Email,
                errorMessage = formState.emailError,
                enabled = !formState.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            CustomTextField(
                value = formState.password,
                onValueChange = { signUpViewModel.onPasswordChange(it) },
                label = placeholderString("password_label", "Password"),
                isPassword = true,
                errorMessage = formState.passwordError,
                enabled = !formState.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password TextField
            CustomTextField(
                value = formState.confirmPassword,
                onValueChange = { signUpViewModel.onConfirmPasswordChange(it) },
                label = placeholderString("confirm_password_label", "Confirm Password"),
                isPassword = true,
                errorMessage = formState.confirmPasswordError,
                enabled = !formState.isLoading
            )

            if (formState.registrationError != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = formState.registrationError,
                    color = ErrorColor,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    signUpViewModel.attemptSignUp()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Green500),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !formState.isLoading // Disable button when loading
            ) {
                if (formState.isLoading) {
                    CircularProgressIndicator(
                        color = WhiteText,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = placeholderString("sign_up_button", "SIGN UP"),
                        color = WhiteText,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

// Reusable TextField with error display
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String? = null,
    enabled: Boolean = true
) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, color = WhiteText) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = DarkGrayTextField,
                cursorColor = WhiteText,
                focusedIndicatorColor = Color.Transparent, // No bottom line when focused
                unfocusedIndicatorColor = Color.Transparent, // No bottom line when unfocused
                textColor = WhiteText,
                disabledTextColor = Color.LightGray,
                disabledLabelColor = Color.Gray,
                errorLabelColor = ErrorColor, // Color for label when isError is true
                errorCursorColor = ErrorColor,
                // errorIndicatorColor = ErrorColor // if you want an error line
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else PasswordVisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            isError = errorMessage != null, // Set isError based on errorMessage
            enabled = enabled
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = ErrorColor,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    // Provide dummy callbacks for preview
    SignUpScreen(onNavigateBack = {}, onNavigateToLogin = {})
}