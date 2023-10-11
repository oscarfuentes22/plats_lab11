package com.zezzi.eventzezziapp.ui.meals.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zezzi.eventzezziapp.navigation.AppBar
import com.zezzi.eventzezziapp.data.networking.response.MealResponse
import com.zezzi.eventzezziapp.navigation.Navigation
import com.zezzi.eventzezziapp.ui.theme.EventZezziAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsCategoriesScreen(
    navController: NavController,
    viewModel: MealsCategoriesViewModel = viewModel()
) {
    val rememberedMeals: MutableState<List<MealResponse>> =
        remember { mutableStateOf(emptyList<MealResponse>()) }

    viewModel.getMeals { response ->
        val mealsFromTheApi = response?.categories
        rememberedMeals.value = mealsFromTheApi.orEmpty()
    }

    BuildUi(navController, rememberedMeals.value)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuildUi(
    navController: NavController,
    list: List<MealResponse>
)
{
    Scaffold(
        topBar = {
            AppBar(title = "Categories", navController = navController)
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(list) { meal ->
                Text(text = meal.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsAppPreview() {
    EventZezziAppTheme {
        BuildUi(rememberNavController(), listOf(
            MealResponse(
                "id",
                "nombre",
                "description",
                "url"
            ),
            MealResponse(
                "id",
                "nombre",
                "description",
                "url"
            ),
            MealResponse(
                "id",
                "nombre",
                "description",
                "url"
            )
        ))
    }
}