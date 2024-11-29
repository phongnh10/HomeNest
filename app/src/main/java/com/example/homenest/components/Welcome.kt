package com.example.homenest.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.homenest.R

@Composable
fun Welcome(navControllerApp: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_backround),
            contentDescription = "backround",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "MAKE YOUR",
                fontSize = 24.sp,
                color = Color(0xff606060),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 15.dp, top = 190.dp)
            )
            Text(
                text = "HOME BEAUTIFUL",
                fontSize = 30.sp,
                fontWeight = FontWeight(800),
                color = Color(0xff303030),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 35.dp)
            )
            Text(
                text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunito_san)),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 59.dp, bottom = 45.dp, end = 30.dp)
            )
            Column {
                Spacer(modifier = Modifier.padding(top = 154.dp))
                Box(
                    modifier = Modifier
                        .width(159.dp)
                        .height(54.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xff242424))
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                ) {
                    TextButton(
                        onClick = {
                            navControllerApp.navigate("login")
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Get Started",
                            fontSize = 18.sp,
                            color = Color(0xffffffff),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }

}