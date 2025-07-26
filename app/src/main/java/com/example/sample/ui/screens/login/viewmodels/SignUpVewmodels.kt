package com.example.sample.ui.screens.login.viewmodels

 // Or your preferred package

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Simple data class to hold validation results
data class SignUpFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val registrationError: String? = null, // For general errors from sign up process
    val isLoading: Boolean = false,
    val isRegistrationSuccessful: Boolean = false
)

class SignUpViewModel : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onNameChange(name: String) {
        formState = formState.copy(name = name, nameError = null, registrationError = null)
    }

    fun onEmailChange(email: String) {
        formState = formState.copy(email = email, emailError = null, registrationError = null)
    }

    fun onPasswordChange(password: String) {
        formState = formState.copy(password = password, passwordError = null, registrationError = null)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        formState = formState.copy(confirmPassword = confirmPassword, confirmPasswordError = null, registrationError = null)
    }

    private fun validateForm(): Boolean {
        var isValid = true
        if (formState.name.isBlank()) {
            formState = formState.copy(nameError = "Name cannot be empty") // Placeholder string
            isValid = false
        }
        if (formState.email.isBlank()) { // Basic email validation
            formState = formState.copy(emailError = "Email cannot be empty") // Placeholder string
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(formState.email).matches()) {
            formState = formState.copy(emailError = "Invalid email format") // Placeholder string
            isValid = false
        }
        if (formState.password.isBlank()) {
            formState = formState.copy(passwordError = "Password cannot be empty") // Placeholder string
            isValid = false
        } else if (formState.password.length < 6) { // Example: min password length
            formState = formState.copy(passwordError = "Password must be at least 6 characters") // Placeholder
            isValid = false
        }
        if (formState.confirmPassword.isBlank()) {
            formState = formState.copy(confirmPasswordError = "Confirm password cannot be empty") // Placeholder
            isValid = false
        } else if (formState.password != formState.confirmPassword) {
            formState = formState.copy(confirmPasswordError = "Passwords do not match") // Placeholder string
            isValid = false
        }
        return isValid
    }

    fun attemptSignUp() {
        if (validateForm()) {
            formState = formState.copy(isLoading = true, registrationError = null)
            viewModelScope.launch {
                // --- !!! ---
                // TODO: Replace with actual sign-up API call (e.g., Firebase, your backend)
                // Simulate network call
                kotlinx.coroutines.delay(2000) // Simulate delay
                val success = true // Simulate success/failure
                // --- !!! ---

                if (success) {
                    formState = formState.copy(isLoading = false, isRegistrationSuccessful = true)
                    // TODO: Trigger navigation to login or home screen
                } else {
                    formState = formState.copy(
                        isLoading = false,
                        registrationError = "Sign up failed. Please try again." // Placeholder
                    )
                }
            }
        }
    }
}