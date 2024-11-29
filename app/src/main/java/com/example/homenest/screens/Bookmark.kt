package com.example.homenest.screens

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homenest.R
import com.example.homenest.customsconponets.CornerRadius
import com.example.homenest.customsconponets.DisplayImageFromUrl
import com.example.homenest.models.Favorites
import com.example.homenest.viewModelApp.ViewModelsApp

@Composable
fun Top() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_search),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        Column {
            Text(text = "Favorites", fontSize = 24.sp)
        }
        Image(
            painter = painterResource(id = R.drawable.icon_cart),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
    }

}

@Composable
fun Bookmark(viewModel: ViewModelsApp = viewModel()) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_Save", Context.MODE_PRIVATE)
    val idUser = sharedPreferences.getString("id", null)

    val favoritesResponse by viewModel.favoritesResponse
    val favorites: List<Favorites> = favoritesResponse?.favorites.orEmpty()

    LaunchedEffect(key1 = idUser, favoritesResponse) {
        idUser?.let {
            viewModel.fetchFavorites(idUser.toString())
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Top()

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Chưa có sản phẩm yêu thích", fontSize = 18.sp, color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                items(favorites.size) { index ->
                    val favorite = favorites[index]
                    FavoriteItem(favorite = favorite)
                }
            }
        }
    }
}
@Composable
fun FavoriteItem(favorite: Favorites) {

    val product = favorite.product
    val viewModel: ViewModelsApp = viewModel()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_Save", Context.MODE_PRIVATE)
    val idUser = sharedPreferences.getString("id", null)


    

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(screenWidth.dp)
            .height((screenWidth / 3).dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        DisplayImageFromUrl(
            url = product.image.toString(),
            width = 100.dp,
            height = 100.dp,
            cornerRadius = CornerRadius(10.dp, 10.dp, 10.dp, 10.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 15.dp)
        ) {
            Text(
                text = product.name, fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
            Text(
                text = "$ ${product.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color.Gray
            )
        }
        Image(painter = painterResource(id = R.drawable.icon_minus),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    viewModel.fetchDeleteFavorite(favorite._id, idUser.toString())
                    val deleteFavoriteRespone = viewModel.deleteFavoriteRespone
                    if(deleteFavoriteRespone.value?.status == true){
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show()
                    }
                })
    }
}
