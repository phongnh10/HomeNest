package com.example.homenest.customsconponets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.homenest.R


@Composable
fun DisplayImageFromUrl(
    url: String,
    width: Dp,
    height: Dp,
    cornerRadius: CornerRadius
) {
    AsyncImage(
        model = url,
        contentDescription = "Hình ảnh từ URL",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(
                RoundedCornerShape(
                    topStart = cornerRadius.topStart,
                    topEnd = cornerRadius.topEnd,
                    bottomStart = cornerRadius.bottomStart,
                    bottomEnd = cornerRadius.bottomEnd
                )
            )
            .background(Color.LightGray)
    )
}

data class CornerRadius(
    val topStart: Dp = 0.dp,
    val topEnd: Dp = 0.dp,
    val bottomStart: Dp = 0.dp,
    val bottomEnd: Dp = 0.dp
)

@Preview(showBackground = true)
@Composable
fun OrderSuccess() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SUCCESS !",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_success),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Center)


            )
            Image(
                painter = painterResource(id = R.drawable.icon_chair),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)

            )
            Image(
                painter = painterResource(id = R.drawable.icon_tick),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.BottomCenter)
            )


        }

        Text(
            text = "Your order will be delivered soon.\n" +
                    "Thank you for choosing our app!",
            fontSize = 18.sp,
            modifier = Modifier.padding(46.dp),
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .width(300.dp)
                .height(60.dp)
        ) {
            Text(
                text = "Track your orders",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }


        Box(
            modifier = Modifier
                .width(300.dp)
                .height(30.dp)
        )

        Box(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .width(300.dp)
                .height(60.dp)
        ) {
            Text(
                text = "BACK TO HOME",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}