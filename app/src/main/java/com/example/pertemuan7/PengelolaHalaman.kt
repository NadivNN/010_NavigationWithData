@file:OptIn(ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class)
package com.example.navigasi


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pertemuan7.HalamanDua
import com.example.pertemuan7.HalamanHome
import com.example.pertemuan7.HalamanSatu
import com.example.pertemuan7.OrderViewModel
import com.example.pertemuan7.R
import com.example.pertemuan7.data.SumberData.flavor

enum class PengelolaHalaman {
    Home,
    Rasa,
    Summary
}
@Composable
fun EsJumboAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier =Modifier
){
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack){
                IconButton(onClick = navigasiUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@Composable
fun EsJumboApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = {
            EsJumboAppBar(bisaNavigasiBack = false, navigasiUp = { /*TODO: implement back navigation*/ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(navController = navController,
            startDestination = PengelolaHalaman.Home.name ,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(
                    onNextButtonClicked = {
                        navController.navigate(PengelolaHalaman.Rasa.name)
                    })
            }
            composable(route = PengelolaHalaman.Rasa.name){
                val context = LocalContext.current
                HalamanSatu(
                    flavor.map{id -> context.resources.getString(id)},
                    {viewModel.setRasa(it)},
                    {viewModel.setJumlah(it)},
                    { navController.navigate(PengelolaHalaman.Summary.name)},
                    { cancelOrderAndNavigateToHome(viewModel,navController
                    )
                    }
                )
            }
            composable(route = PengelolaHalaman.Summary.name){
                HalamanDua(orderUIState = uiState,
                    onCancelButtonClicked = {cancelOrderAndNavigateToRasa(navController)},
                )
            }
        }
    }
}
private  fun cancelOrderAndNavigateToHome(viewModel: OrderViewModel,
                                          navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
}
private fun  cancelOrderAndNavigateToRasa(
    navController: NavHostController
){
    navController.popBackStack(PengelolaHalaman.Rasa.name, inclusive=false)
}