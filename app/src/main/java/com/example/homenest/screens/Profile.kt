package com.example.homenest.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homenest.R

@Preview(showBackground = true)
@Composable
fun Profile(navControllerApp: NavController = rememberNavController()) {
    Column {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top=  20.dp, bottom = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_search),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                    Column {
                        Text(text = "Profile", fontSize = 24.sp)
                    }
                    Box(modifier = Modifier
                        .clickable {
                            navControllerApp.navigate("login")
                        }) {

                        Image(
                            painter = painterResource(id = R.drawable.icon_logout),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image2),
                        contentDescription = "Image description",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(100))
                            .background(color = Color.White)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp)
                    ) {
                        Text(text = "Nguyen Hong Phong", fontSize = 18.sp)
                        Text(
                            text = "nguyenhongphong@gmail.com", fontSize = 12.sp,
                            fontWeight = FontWeight(100),
                            modifier = Modifier
                                .padding(top = 5.dp)
                        )

                    }
                }

                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(60.dp)
                ) {

                }
                Item(tilet = "Address", note = "Change address")
                Item(tilet = "Shipping Addresses", note = "03 Addresses")
                Item(tilet = "Payment", note = "You have 2 cards")
                Item(tilet = "My reviews", note = "Reviews for 5 items")
                Item(tilet = "Setting", note = "Notification, Password, FAQ, Contact")

            }
        }
    }
}

@Composable
fun Item(tilet: String, note: String) {

    Box(
        modifier = Modifier
            .width(300.dp)
            .height(20.dp)
    ) {

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(330.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(10))
            .background(color = Color(0xffEAEAEA))
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(text = tilet, fontSize = 18.sp)
            Text(
                text = note, fontSize = 15.sp,
                fontWeight = FontWeight(200)
            )

        }
        Image(
            painter = painterResource(id = R.drawable.icon_back_right),
            contentDescription = "Image description",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 10.dp)
        )
    }
}