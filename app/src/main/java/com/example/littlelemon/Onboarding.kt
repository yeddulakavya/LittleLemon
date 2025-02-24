package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun OnboardingScreen(navController: NavHostController, sharedPreferences: SharedPreferences) {
    val context = LocalContext.current;
    return Column(
        modifier = Modifier
            .fillMaxWidth().fillMaxHeight()
            .padding(start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        var firstName by remember {
            mutableStateOf("")
        }
        var lastName by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        Column() {
            Image(
                modifier = Modifier
                    .height(75.dp).fillMaxWidth().padding(15.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillHeight,
            )
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(1f)
                    .background(color = Color(0xFF495E57))
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Text(text = "Let's get to know you", color = Color(0xFFFFFFFF), fontSize = 24.sp)
            }
            Text(
                text = "Personal Information:",
                modifier = Modifier.padding(vertical = 30.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "FirstName:",
                modifier = Modifier.padding(vertical = 5.dp),

                )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.White),
                value = firstName,
                textStyle = TextStyle(fontSize = 16.sp),
                onValueChange = { firstName = it },
            )
            Text(
                text = "LastName:",
                modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.White),
                value = lastName,
                textStyle = TextStyle(fontSize = 16.sp),
                onValueChange = { lastName = it },
            )

            Text(
                text = "Email:",
                modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.White),
                value = email,
                textStyle = TextStyle(fontSize = 16.sp),
                onValueChange = { email = it },
            )
        }

            Button(
                modifier = Modifier.fillMaxWidth().padding(bottom=30.dp),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful please enter all data",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        sharedPreferences.edit().putString("FirstName", firstName).apply()
                        sharedPreferences.edit().putString("LastName", lastName).apply()
                        sharedPreferences.edit().putString("Email", email).apply()
                        Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(Home.route)
                    }
                }, colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14))
            ) {
                Text(text = "Register", color = Color(0xFF000000))
            }

    };
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    LittleLemonTheme {
        OnboardingScreen(
            rememberNavController(),
            LocalContext.current.getSharedPreferences("LittleLemon", MODE_PRIVATE)
        )
    }
}