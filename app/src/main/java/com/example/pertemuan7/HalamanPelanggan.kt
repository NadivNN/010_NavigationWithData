package com.example.pertemuan7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDetailsScreen(
    onConfirmButtonClicked: (String, String, String) -> Unit,
    onCancelButtonClicked: () -> Unit
){
    var namaPelanggan by remember { mutableStateOf("") }
    var nomorTelepon by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        OutlinedTextField(
            value = namaPelanggan,
            onValueChange = { namaPelanggan = it },
            label = { Text(text = "Nama Pelanggan") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )
    }

}

