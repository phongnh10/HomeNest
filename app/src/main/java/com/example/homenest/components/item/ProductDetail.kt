package com.example.homenest.components.item

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homenest.viewModelApp.ViewModelsApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homenest.R
import com.example.homenest.customsconponets.CornerRadius
import com.example.homenest.customsconponets.DisplayImageFromUrl
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.homenest.models.addFavoriteRequest


@Composable
fun ProductDetail(
    navControllerApp: NavController, productId: String?, viewModel: ViewModelsApp = viewModel()
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val isAddProduct = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_Save", Context.MODE_PRIVATE)
    val idUser = sharedPreferences.getString("id", null)
    val productResponse by viewModel.productResponse

    val price = remember { mutableStateOf(0f) }
    val quantity = remember { mutableStateOf(1) }

    LaunchedEffect(productId, productResponse) {
        if (!productId.isNullOrEmpty()) {
            viewModel.fetchProduct(productId)

        }
    }
    price.value = (productResponse?.product?.price?.toFloat() ?: 0f) * quantity.value

    fun clickPlus() {
        if (quantity.value < 100 && productResponse?.product?.price != null) {
            quantity.value++
            price.value = (productResponse?.product?.price?.toFloat() ?: 0f) * quantity.value
        }
    }

    fun clickMinus() {
        if (quantity.value > 1) {
            quantity.value--
            price.value = (productResponse?.product?.price?.toFloat() ?: 0f) * quantity.value
        }
    }

    fun clickFavorite() {
        val addFavorite = addFavoriteRequest(idUser.toString(), productId.toString())

        viewModel.fetchAddFavorite(addFavorite)
        val addfavoriteResponse = viewModel.addFavoriteResponse.value
        if (addfavoriteResponse?.status == true) {
            isAddProduct.value = true
        }
    }
    if (productResponse == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight / 10 * 6).dp)
            ) {
                if (isAddProduct.value) {
                    Toast.makeText(context, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show()
                    isAddProduct.value = false
                }

                Box(
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    DisplayImageFromUrl(
                        url = productResponse?.product?.image.toString(),
                        width = (screenWidth / 10 * 8.5).dp,
                        height = ((screenHeight / 10 * 6).dp),
                        cornerRadius = CornerRadius(
                            topStart = 0.dp,
                            topEnd = 40.dp,
                            bottomStart = 40.dp,
                            bottomEnd = 0.dp
                        )
                    )
                }
                Box(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.item_color_detail),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width((screenWidth / 10 * 4).dp)
                            .height((screenWidth / 10 * 7).dp)

                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 43.dp, top = 50.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .size(40.dp)

                ) {
                    Image(painter = painterResource(id = R.drawable.icon_back),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .clickable { navControllerApp.popBackStack() }
                            .align(Alignment.Center)

                    )
                }
            }
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
            ) {
                Text(
                    text = productResponse?.product?.name.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "$ " + price.value.toString(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = R.drawable.icon_minus),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { clickMinus() })
                        Text(
                            text = quantity.value.toString(),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Image(painter = painterResource(id = R.drawable.icon_push),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { clickPlus() })
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_star),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = productResponse?.product?.rating.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = "(" + productResponse?.product?.rating_quantity.toString() + " reviews)",
                        fontSize = 16.sp,
                        color = Color((0xff606060)),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                Text(
                    text = productResponse?.product?.description.toString(),
                    fontSize = 16.sp,
                    maxLines = 4,
                    color = Color((0xff606060)),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.size(10.dp)
                    )
                    Image(painter = painterResource(id = R.drawable.icon_marker),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                clickFavorite()
                                Toast
                                    .makeText(context, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT)
                                    .show()

                            }
                    )
                    Box(
                        modifier = Modifier
                            .width((screenWidth / 4 * 2.5).dp)
                            .height((screenHeight / 10 * 0.6).dp)
                            .background(color = Color.Black, shape = RoundedCornerShape(10.dp))

                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

