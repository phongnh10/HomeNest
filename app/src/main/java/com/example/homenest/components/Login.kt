package com.example.homenest.components

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homenest.R
import com.example.homenest.models.LoginRequest
import com.example.homenest.models.User
import com.example.homenest.viewModelApp.ViewModelsApp
import androidx.compose.runtime.getValue


@Composable
fun Login(
    navControllerApp: NavController, viewModel: ViewModelsApp = viewModel()
) {
    val emailState = remember { mutableStateOf("phong@gmail.com") }
    val passwordState = remember { mutableStateOf("123456") }
    val message = remember { mutableStateOf("") }
    val statusEye = remember { mutableStateOf(true) }
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current
    var awdcsad = 1


    val loginResponse by viewModel.loginResponse

    LaunchedEffect(key1 = loginResponse) {
        if (loginResponse != null) {
            if (loginResponse?.status == true) {
                awdcsad = awdcsad+2

                message.value = "Đăng nhập thành công, chuyển đến màn hình Home"
                saveUser(
                    user = User(
                        _id = loginResponse?.id.toString(),
                        name = "",
                        email = emailState.value,
                        password = passwordState.value
                    ), context = context
                )
                navControllerApp.navigate("appBottomTab")
            }
        }
    }

    fun clickLogin() {
        message.value = ""
        if (emailState.value.isEmpty() || passwordState.value.isEmpty()) {
            message.value = "Vui lòng nhập đầy đủ thông tin"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()) {
            message.value = "Vui lòng nhập đúng định dạng email"
            return
        }
        if (passwordState.value.length < 6) {
            message.value = "Mật khẩu phải có ít nhất 6 ký tự"
            return
        }
        if (loginResponse == null) {
            message.value = "Đang Xử lý có thể sai tài khoản mật khẩu"
            val userLogin: LoginRequest = LoginRequest(emailState.value, passwordState.value)
            viewModel.fetchLogin(userLogin)
        }


    }


    fun clickEye() {
        statusEye.value = !statusEye.value
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(22.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(105.dp)
                    .background(color = Color.Gray)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_login),
                contentDescription = "iconBackround",
                modifier = Modifier
                    .width(63.dp)
                    .height(63.dp)

            )
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(105.dp)
                    .background(color = Color.Gray)
            )

        }

        Column(modifier = Modifier.padding(start = 30.dp)) {
            Text(
                text = "Hello !",
                fontSize = 30.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                text = "WELCOME BACK",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }


        Box(
            modifier = Modifier
                .padding(top = 100.dp)
                .padding(end = 30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            )

            {
                TextField(
                    value = emailState.value,
                    onValueChange = { newValue -> emailState.value = newValue },
                    label = {
                        Text(
                            "Email", modifier = Modifier.padding(bottom = 10.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 30.dp)
                        .height(75.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedIndicatorColor = Color.Gray


                    )

                )
                Box(
                    modifier = Modifier, contentAlignment = Alignment.CenterEnd
                ) {
                    TextField(
                        value = passwordState.value,
                        onValueChange = { newValue -> passwordState.value = newValue },
                        label = {
                            Text(
                                "Password", modifier = Modifier.padding(bottom = 10.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp, start = 30.dp)
                            .height(75.dp),
                        visualTransformation = if (statusEye.value) PasswordVisualTransformation()
                        else VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray

                        ),
                    )
                    Image(painter = painterResource(id = R.drawable.icon_eye_nornom),
                        contentDescription = "icon eye",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(end = 10.dp)
                            .zIndex(1f)
                            .clickable {
                                clickEye()
                            })


                }

                Text(
                    text = message.value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (message.value == "Đăng nhập thành công, chuyển đến màn hình Home") {
                        Color.Green
                    } else {
                        Color.Red
                    },
                )

                Text(
                    text = "Forgot Password",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 40.dp, top = 30.dp)

                )

                TextButton(
                    onClick = {
                        clickLogin()
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .width(285.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black)
                )

                {
                    Text(
                        "Login", fontSize = 18.sp, color = Color.White
                    )
                }

                Text(text = "SIGN UP",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 20.dp, top = 30.dp)
                        .clickable {
                            navControllerApp.navigate("register")
                        })
            }
        }
    }
}

fun saveUser(user: User, context: Context) {
    val sharedPreferences = context.getSharedPreferences("user_Save", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("id", user._id)
        putString("email", user.email)
        putString("pass", user.password)
        apply()
    }
}
