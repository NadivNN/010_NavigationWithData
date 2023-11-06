@file:OptIn (ExperimentalMaterial3Api::class)
package com.example.pertemuan7
import androidx.compose.foundation.layout. Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose. foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose. foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3. Divider
import androidx.compose.material3. ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun HalamanSatu(
    pilihanRasa: List<String>,
    onSelectionChanged: (String) -> Unit,
    onConfirmButtonClicked: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
)
{
    var rasaYgDipilih by rememberSaveable { mutableStateOf("") }
    var textJmlBeli by remember { mutableStateOf( "") }

    Column (modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween){
        Column ( modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            pilihanRasa.forEach{ item ->
                Row (modifier = Modifier.selectable(
                    selected = rasaYgDipilih == item,
                    onClick = {
                        rasaYgDipilih = item
                        onSelectionChanged(item)
                    }
                ),
                    verticalAlignment =  Alignment.CenterVertically){
                    RadioButton(selected = rasaYgDipilih == item,
                        onClick = {
                            rasaYgDipilih = item
                            onSelectionChanged(item)}
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
                horizontalArrangement =
                Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),){
                OutlinedTextField(
                    value = textJmlBeli,
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier= Modifier.width(150.dp),
                    label = { Text(text = "Jumlah Order")},
                    onValueChange = {
                        textJmlBeli = it
                    }
                )
                Button(
                    modifier = Modifier.weight(1f),
                    enabled = textJmlBeli.isNotEmpty(),
                    onClick = { onConfirmButtonClicked(textJmlBeli.toInt())}
                ) {
                    Text(stringResource(R.string.confirm))
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .weight(1f, false),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.Bottom
            ){
                OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                    Text(stringResource(R.string.cancel))
                }
                Button(
                    modifier = Modifier.weight(1f),
                    enabled = textJmlBeli.isNotEmpty(),
                    onClick = onNextButtonClicked
                ) {
                    Text(stringResource(R.string.next))
                }
            }
            }
        }
}