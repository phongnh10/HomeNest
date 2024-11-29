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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homenest.R
import com.example.homenest.viewModelApp.ViewModelsApp
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import com.example.homenest.models.Category
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.homenest.customsconponets.CornerRadius
import com.example.homenest.customsconponets.DisplayImageFromUrl
import com.example.homenest.models.Product


@Composable
fun Home(
    navControllerApp: NavController,
    viewModel: ViewModelsApp = viewModel()
) {
    val selectedCategory = remember { mutableStateOf("") }
    val products = remember { mutableStateOf(listOf<Product>()) }
    val isCategoryLoading = remember { mutableStateOf(true) }
    val isProductLoading = remember { mutableStateOf(false) }

    val categoryReponse by viewModel.categoryResponse
    val productsResponse by viewModel.productsResponse

    LaunchedEffect(key1 = categoryReponse, key2 = selectedCategory) {
        isCategoryLoading.value = true
        viewModel.fetchCategories()
        if (categoryReponse != null) {
            selectedCategory.value = categoryReponse!!.categories[0]._id
            isCategoryLoading.value = false
        }

        if (selectedCategory.value != "") {
            isProductLoading.value = true
            viewModel.fetchProducts(selectedCategory.value)
        }
    }

    productsResponse?.let { response ->
        products.value = response.products.orEmpty()
        isProductLoading.value = false
    }


    // Hiển thị giao diện chính
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_search),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Column {
                    Text(text = "Make home", fontSize = 24.sp)
                    Text(text = "BEAUTIFUL", fontSize = 24.sp)
                }
                Image(
                    painter = painterResource(id = R.drawable.icon_cart),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
            if (isCategoryLoading.value || isProductLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(color = Color.White),
                    contentAlignment = Alignment.Center,


                ) {
                    Text(
                        text = "Đang tải...",
                        fontSize = 18.sp,
                        color = Color.Gray,
                    )
                }
            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    items(
                        items = categoryReponse?.categories.orEmpty(),
                        itemContent = { item ->
                            Box(
                                modifier = Modifier.clickable {
                                    selectedCategory.value = item._id
                                    isProductLoading.value = true
                                    viewModel.fetchProducts(selectedCategory.value)
                                }
                            ) {
                                CategoryRender(item, selectedCategory.value)
                            }
                        }
                    )
                }
                ProductGrid(navControllerApp, products = products.value)
            }
        }
    }
}

@Composable
fun CategoryRender(item: Category, selectedCategory: String) {
    Box(
        modifier = Modifier.background(
            color = if (selectedCategory == item._id) Color.Black else Color.White,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = if (selectedCategory == item._id) Color.White else Color.Black,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun ProductGrid(navControllerApp: NavController, products: List<Product>) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp


    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        items(products.size) { index ->
            val item = products[index]
            Column(modifier = Modifier
                .padding(8.dp)
                .clickable {
                    val productId = item._id
                    navControllerApp.navigate("productDetail/$productId")
                }) {
                Box(
                    modifier = Modifier
                        .width(screenWidth.dp)
                        .height((screenWidth / 2).dp)
                )
                {
                    Box {
                        DisplayImageFromUrl(
                            url = item.image,
                            width = screenWidth.dp,
                            height = (screenWidth / 2).dp,
                            cornerRadius = CornerRadius(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 10.dp, bottom = 10.dp)
                    ) {
                        Box(

                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = Color(0xFF999999), shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_favorite),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        text = "$ " + item.price.toString(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
    }
}
