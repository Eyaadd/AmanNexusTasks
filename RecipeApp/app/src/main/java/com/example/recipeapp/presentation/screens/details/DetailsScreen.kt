package com.example.recipeapp.presentation.screens.details

import com.example.recipeapp.data.model.DetailsTab


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.data.model.DirectionUiModel
import com.example.recipeapp.data.model.RecipeDetailsUiModel
import com.example.recipeapp.presentation.screens.details.components.DetailsImageHeader
import com.example.recipeapp.presentation.screens.details.components.DetailsTabs
import com.example.recipeapp.presentation.screens.details.components.DirectionsContent
import com.example.recipeapp.presentation.screens.details.components.IngredientsContent
import com.example.recipeapp.presentation.screens.details.components.RecipeDetailsHeader
import com.example.recipeapp.presentation.screens.details.components.RecipeSourceRow
import com.example.recipeapp.presentation.screens.details.components.WatchVideoButton
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(
    recipeId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel(
        parameters = {
            parametersOf(recipeId)
        }
    )
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                DetailsContract.DetailsEffect.NavigateBack -> {
                    onNavigateBack()
                }


                is DetailsContract.DetailsEffect.ShowMessage -> {
                    // Snackbar later.
                }
            }
        }
    }

    DetailsScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun DetailsScreenContent(
    state: DetailsContract.DetailsState,
    onIntent: (DetailsContract.DetailsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val recipe = state.recipe

    if (state.isLoading || recipe == null) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            CircularProgressIndicator()
            Spacer(modifier = Modifier.weight(1f))
        }

        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DetailsImageHeader(
            imageUrl = recipe.imageUrl,
            recipeName = recipe.title,
            durationMinutes = recipe.readyInMinutes,
            isFavorite = state.isFavorite,
            onBackClick = {
                onIntent(
                    DetailsContract.DetailsIntent.OnBackClicked
                )
            },
            onFavoriteClick = {
                onIntent(
                    DetailsContract.DetailsIntent.OnFavoriteClicked
                )
            }
        )

        Surface(
            modifier = Modifier.offset(y = (-18).dp),
            shape = RoundedCornerShape(
                topStart = 28.dp,
                topEnd = 28.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 28.dp
                )
            ) {
                RecipeDetailsHeader(
                    title = recipe.title,
                    description = recipe.description,
                    rating = recipe.rating
                )

                Spacer(modifier = Modifier.height(18.dp))

                RecipeSourceRow(
                    sourceName = recipe.sourceName
                )

                Spacer(modifier = Modifier.height(22.dp))

                DetailsTabs(
                    selectedTab = state.selectedTab,
                    onTabSelected = { tab ->
                        onIntent(
                            DetailsContract.DetailsIntent
                                .OnTabSelected(tab)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                when (state.selectedTab) {
                    DetailsTab.INGREDIENTS -> {
                        IngredientsContent(
                            servings = recipe.servings,
                            ingredients = recipe.ingredients
                        )
                    }

                    DetailsTab.DIRECTIONS -> {
                        DirectionsContent(
                            directions = recipe.directions
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                WatchVideoButton(
                    onClick = {}
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 430,
    heightDp = 900
)
@Composable
fun DetailsScreenContentPreview() {
    DetailsScreenContent(
        state = DetailsContract.DetailsState(
            isLoading = false,
            isFavorite = true,
            selectedTab = DetailsTab.INGREDIENTS,
            recipe = RecipeDetailsUiModel(
                id = 1,
                imageUrl = null,
                title = "Seblak Bandung",
                description =
                    "A flavorful traditional recipe prepared with fresh ingredients.",
                rating = 4.8,
                readyInMinutes = 20,
                servings = 4,
                sourceName = "Wade Warren",
                ingredients = listOf(
                    "300 g chicken feet",
                    "2 pcs beef sausage",
                    "5 pcs beef meatballs",
                    "1 egg beaten"
                ),
                directions = listOf(
                    DirectionUiModel(
                        number = 1,
                        step = "Prepare the ingredients."
                    ),
                    DirectionUiModel(
                        number = 2,
                        step = "Cook over medium heat."
                    )
                ),
                sourceUrl = null
            )
        ),
        onIntent = {}
    )
}