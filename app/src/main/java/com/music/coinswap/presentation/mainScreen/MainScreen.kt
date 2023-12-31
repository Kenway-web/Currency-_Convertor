package com.music.coinswap.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.music.coinswap.R


@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
)
{

    val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "C")

       Column(
           modifier= Modifier
               .fillMaxSize()
               .padding(horizontal = 16.dp, vertical = 8.dp),
           verticalArrangement = Arrangement.SpaceAround
       ) {
           Text(
               modifier = Modifier.fillMaxWidth(),
               text = "CoinSwap",
               fontFamily = FontFamily.Cursive,
               fontSize = 40.sp,
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.Bold
           )

           Box(
               contentAlignment = Alignment.CenterStart
           ) {

               Column {
                   Card(
                       modifier = Modifier.fillMaxWidth()
                   ) {
                       Column(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(horizontal = 12.dp, vertical = 4.dp),
                           horizontalAlignment = Alignment.End
                       )
                       {

                           CurrencyRow(
                               modifier = Modifier.fillMaxWidth(),
                               currencyCode = state.fromCurrencyCode,
                               currencyName = state.currencyRates[state.toCurrencyCode]?.name?:"",
                               onDropDownIconClicked = {}
                           )

                           Text(
                               text = state.fromCurrencyValue,
                               fontSize = 40.sp,
                               modifier = Modifier.clickable {
                                   onEvent(MainScreenEvent.FromCurrencySelect)
                               }
                           )
                       }
                   }

                   Spacer(modifier = Modifier.height(12.dp))
                   Card(
                       modifier = Modifier.fillMaxWidth()
                   ) {
                       Column(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(horizontal = 12.dp, vertical = 4.dp),
                           horizontalAlignment = Alignment.End
                       )
                       {
                           Text(
                               text = state.toCurrencyValue,
                               fontSize = 40.sp,
                               modifier = Modifier.clickable {
                                   onEvent(MainScreenEvent.ToCurrencySelect)
                               }
                           )

                           CurrencyRow(
                               modifier = Modifier.fillMaxWidth(),
                               currencyCode = state.fromCurrencyCode,
                               currencyName = state.currencyRates[state.fromCurrencyCode]?.name?:"",
                               onDropDownIconClicked = {

                               }
                           )


                       }
                   }

               }

               Box(
                   modifier = Modifier
                       .padding(start = 40.dp)
                       .clip(CircleShape)
                       .clickable { onEvent(MainScreenEvent.SwapIconClicked)}
                       .background(color = MaterialTheme.colorScheme.background)
               ) {
                   Icon(
                       painter = painterResource(R.drawable.icon_sync_24),
                       contentDescription = "",
                       modifier = Modifier
                           .padding(8.dp)
                           .size(25.dp),
                       tint = MaterialTheme.colorScheme.onSurface
                   )
               }
           }


           LazyVerticalGrid(
               modifier = Modifier.padding(horizontal = 35.dp),
               columns = GridCells.Fixed(3)
           ) {
               items(keys) { key ->
                   KeyboardButton(
                       modifier = Modifier.aspectRatio(1f),
                       key = key,
                       backgroundColor = if (key == "C") MaterialTheme.colorScheme.primary
                       else MaterialTheme.colorScheme.surfaceVariant,
                       onClick = {
                           onEvent(MainScreenEvent.NumberButtonClicked(key))
                       }
                   )
               }
           }

       }
}




@Composable
fun CurrencyRow(
    modifier:Modifier=Modifier,
    currencyCode:String,
    currencyName:String,
    onDropDownIconClicked:() -> Unit
){

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
          Text(text = currencyCode, fontSize = 14.sp , fontWeight = FontWeight.Bold)
          IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Open Bottom Sheet"
                )
          }
          Spacer(modifier = Modifier.weight(1f))
          Text(text = currencyName, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }

}



@Composable
fun KeyboardButton(
    modifier: Modifier = Modifier,
    key: String,
    backgroundColor: Color,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable { onClick(key) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = key, fontSize = 32.sp)
    }
}