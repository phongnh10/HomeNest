package com.example.homenest.Retrofits

import com.example.homenest.models.CategoryResponse
import com.example.homenest.models.FavoritesResponse
import com.example.homenest.models.LoginRequest
import com.example.homenest.models.LoginResponse
import com.example.homenest.models.ProductResponse
import com.example.homenest.models.ProductsResponse
import com.example.homenest.models.RegisterRequest
import com.example.homenest.models.RegisterResponse
import com.example.homenest.models.addFavoriteRequest
import com.example.homenest.models.addFavoriteResponse
import com.example.homenest.models.deleteFavoriteRespone
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {
    @POST("user/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse?

    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse?

    @GET("category")
    suspend fun getCategories(): CategoryResponse?

    @GET("product/bycategory/")
    suspend fun getProducts(@Query("categoryId") categoryID: String): ProductsResponse?

    @GET("product/getproduct/")
    suspend fun getProduct(@Query("idProduct") idProduct: String): ProductResponse?

    @GET("favorite/getFavoritesByIdUser/")
    suspend fun getFavorites(@Query("idUser") idUser: String): FavoritesResponse?

    @POST("favorite/addFavorite")
    suspend fun addFavorite(@Body addFavorite: addFavoriteRequest): addFavoriteResponse?

    @DELETE("favorite/deleteFavorite/{idFavorite}")
    suspend fun deleteFavorite(@Path("idFavorite") idFavorite: String): deleteFavoriteRespone?
}

object RetrofitClient {
    private const val BASE_URL = "https://api-coffeeshop-wkmh.onrender.com/"
    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}

