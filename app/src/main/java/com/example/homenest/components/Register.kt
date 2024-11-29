package com.example.homenest.components

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
import com.example.homenest.models.RegisterRequest
import com.example.homenest.viewModelApp.ViewModelsApp
import androidx.compose.runtime.getValue


@Composable
fun Register(
    navControllerApp: NavController,
    viewModel: ViewModelsApp = viewModel()
) {
    val nameStable = remember { mutableStateOf("Phong Nguyen") }
    val emailState = remember { mutableStateOf("phong@gmail.com") }
    val passwordState = remember { mutableStateOf("123456") }
    val confirmPasswordState = remember { mutableStateOf("123456") }
    val message = remember { mutableStateOf("") }
    val statusEye = remember { mutableStateOf(false) }

    val registerResponse by viewModel.registerResponse

    LaunchedEffect(key1 = registerResponse) {
        if (registerResponse?.status == true) {
            message.value = "Đăng ký thành công"
            navControllerApp.popBackStack()
        }
    }


    fun clickRegister() {
        message.value = ""
        if (nameStable.value.isEmpty() || emailState.value.isEmpty() || passwordState.value.isEmpty() || confirmPasswordState.value.isEmpty()) {
            message.value = "Vui lòng nhập đầy đủ thông tin"
            return
        }
        if (nameStable.value.length < 6) {
            message.value = "Tên phải có ít nhất 6 ký tự"
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
        if (passwordState.value != confirmPasswordState.value) {
            message.value = "Mật khẩu không khớp"
            return
        }
        if (registerResponse == null ) {
            val userRegister: RegisterRequest =
                RegisterRequest(nameStable.value, emailState.value, passwordState.value)
            viewModel.fetchRegister(userRegister)
            message.value = " Đăng ký thất bại, vui lòng thử lại"
        }

    }

    fun clickEye() {
        statusEye.value = !statusEye.value
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
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
        Text(
            text = "WELCOME",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 30.dp)
        )
    }


    Box(
        modifier = Modifier
            .padding(top = 200.dp)
            .padding(end = 30.dp)

    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        )

        {
            TextField(
                value = nameStable.value,
                onValueChange = { newValue -> nameStable.value = newValue },
                label = {
                    Text(
                        "Name",
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 30.dp)
                    .height(75.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedIndicatorColor = Color.Gray
                ),
            )

            TextField(
                value = emailState.value,
                onValueChange = { newValue -> emailState.value = newValue },
                label = {
                    Text(
                        "Email",
                        modifier = Modifier
                            .padding(bottom = 10.dp)
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
                modifier = Modifier,
                contentAlignment = Alignment.CenterEnd
            ) {
                TextField(
                    value = passwordState.value,
                    onValueChange = { newValue -> passwordState.value = newValue },
                    label = {
                        Text(
                            "Password",
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, start = 30.dp)
                        .height(75.dp),
                    visualTransformation =
                    if (statusEye.value) PasswordVisualTransformation()
                    else (VisualTransformation.None),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Gray, unfocusedIndicatorColor = Color.Gray

                    ),
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_eye_nornom),
                    contentDescription = "icon eye",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .padding(end = 10.dp)
                        .zIndex(1f)
                        .clickable { clickEye() }
                )
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 50.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextField(
                    value = confirmPasswordState.value,
                    onValueChange = { newValue -> confirmPasswordState.value = newValue },
                    label = {
                        Text(
                            "Confirm Password",
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp, start = 30.dp)
                        .height(75.dp),
                    visualTransformation =
                    if (statusEye.value) PasswordVisualTransformation()
                    else (VisualTransformation.None),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Gray, unfocusedIndicatorColor = Color.Gray
                    ),

                    )
                Image(
                    painter = painterResource(id = R.drawable.icon_eye_nornom),
                    contentDescription = "icon eye",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .padding(end = 10.dp)
                        .zIndex(1f)
                )


            }
            Text(
                message.value,
                fontSize = 10.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            TextButton(
                onClick = {
                    clickRegister()
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(285.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
            )


            {
                Text(
                    "SIGN UP",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }



            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {

                Text(
                    text = "Already have account ",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(bottom = 20.dp, top = 30.dp)
                )
                Text(
                    text = " SIGN IN",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 20.dp, top = 30.dp)
                        .clickable {
                            navControllerApp.popBackStack()
                        }
                )
            }
        }
    }


}
