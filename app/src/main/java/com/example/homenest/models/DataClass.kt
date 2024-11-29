package com.example.homenest.models


data class User(
    val _id: String?,
    val name: String?,
    val email: String?,
    val password: String?,
)

data class Category(
    val _id: String,
    val name: String,
)

data class Product(
    val _id: String,
    val name: String,
    val image: String,
    val sort_description: String,
    val description: String,
    val rating: Double,
    val rating_quantity: Int,
    val price: Double,
    val category: List<String>,
)

data class Favorites (
    val _id: String,
    val product: Product,
    val user: String,

)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
)

data class RegisterResponse(
    val status: Boolean = false,
    val message: String? = "Có lỗi",
)
data class  LoginRequest(
    val email: String,
    val password: String,
)
data class LoginResponse(
    val status: Boolean = false,
    val message: String? = null,
    val id: String? = null,
)

data class CategoryResponse(
    val status: Boolean = false,
    val categories: List<Category> = emptyList()
)
 data class ProductsResponse(
     val status: Boolean = false,
     val products: List<Product> = emptyList()
 )

data class ProductResponse(
    val status: Boolean = false,
    val product: Product
)

//goi
data class FavoritesResponse(
    val status: Boolean = false,
    val favorites: List<Favorites> = emptyList()

)

// them xoa
data class  addFavoriteRequest(
    val idUser: String,
    val idProduct: String,
)

data class addFavoriteResponse(
    val status: Boolean = false,
    val message: String? = null,
)


data class deleteFavoriteRespone(
    val status: Boolean = false,
    val message: String? = null,
)

