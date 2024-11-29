package com.example.homenest.viewModelApp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homenest.Retrofits.RetrofitClient
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
import kotlinx.coroutines.launch


class ViewModelsApp : ViewModel() {

    private val _registerResponse = mutableStateOf<RegisterResponse?>(null)
    val registerResponse: State<RegisterResponse?> = _registerResponse

    private val _loginResponse = mutableStateOf<LoginResponse?>(null)
    val loginResponse: State<LoginResponse?> = _loginResponse

    private val _categoryReponse = mutableStateOf<CategoryResponse?>(null)
    val categoryResponse: State<CategoryResponse?> = _categoryReponse

    private val _productsResponse = mutableStateOf<ProductsResponse?>(null)
    val productsResponse: State<ProductsResponse?> = _productsResponse

    private val _productResponse = mutableStateOf<ProductResponse?>(null)
    val productResponse: State<ProductResponse?> = _productResponse

    private val _favoritesResponse = mutableStateOf<FavoritesResponse?>(null)
    val favoritesResponse: State<FavoritesResponse?> = _favoritesResponse

    private val _addFavoriteResponse = mutableStateOf<addFavoriteResponse?>(null)
    val addFavoriteResponse: State<addFavoriteResponse?> = _addFavoriteResponse

    private val _deleteFavoriteResponse = mutableStateOf<deleteFavoriteRespone?>(null)
    val deleteFavoriteRespone: State<deleteFavoriteRespone?> = _deleteFavoriteResponse
    

    fun fetchRegister(userRegister: RegisterRequest) {
        viewModelScope.launch {
            try {
                _registerResponse.value = RetrofitClient.api.register(userRegister)
                Log.d("call api Register", _registerResponse.value.toString());
            } catch (e: Exception) {
            }
        }
    }

    fun fetchLogin(userLogin: LoginRequest) {
        viewModelScope.launch {
            try {
                _loginResponse.value = RetrofitClient.api.login(userLogin)
                Log.d("call api Login", _loginResponse.value.toString());
            } catch (e: Exception) {
            }
        }
    }

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                _categoryReponse.value = RetrofitClient.api.getCategories()
                Log.d("call api category", _categoryReponse.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchProducts(categoryID: String) {
        viewModelScope.launch {
            try {
                _productsResponse.value = RetrofitClient.api.getProducts(categoryID)
                Log.d("call api products", _productsResponse.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchProduct(idProduct: String) {
        viewModelScope.launch {
            try {
                _productResponse.value = RetrofitClient.api.getProduct(idProduct)
                Log.d("call api product", _productResponse.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun fetchFavorites(idUser: String) {
        viewModelScope.launch {
            try {
                _favoritesResponse.value = RetrofitClient.api.getFavorites(idUser)
                Log.d("call api favorites", _favoritesResponse.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()

            }

        }
    }

    fun fetchAddFavorite(addFavorite: addFavoriteRequest) {
        viewModelScope.launch {
            try {
                _addFavoriteResponse.value = RetrofitClient.api.addFavorite(addFavorite)
                Log.d("call api addFavorite", _addFavoriteResponse.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun fetchDeleteFavorite(idFavorite: String, idUser: String) {
        viewModelScope.launch {
            try {
                _deleteFavoriteResponse.value = RetrofitClient.api.deleteFavorite(idFavorite)
                fetchFavorites(idUser)
                Log.d("call api deletefavorite", _deleteFavoriteResponse.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

