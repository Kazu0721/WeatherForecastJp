package com.example.weatherforecastjp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun mainScreen(viewModel:ForecastViewModel) {

    val city = remember { mutableStateOf("") }
    val weather = remember { mutableStateListOf<ForecastItems>() }
    val keyboardController = LocalSoftwareKeyboardController.current

   Box(modifier = Modifier.background(Color.LightGray)){
      Column {

         Row(modifier = Modifier
             .fillMaxWidth()
             .wrapContentHeight(),
             horizontalArrangement = Arrangement.SpaceEvenly,
             verticalAlignment = Alignment.CenterVertically
         ){
             var  name by remember { mutableStateOf("")}

                 OutlinedTextField(value = name, onValueChange = {name = it},
                     label = {Text(stringResource(id = R.string.city_name), color = Color.Blue)},
                     modifier = Modifier
                         .padding(10.dp),
                     colors = TextFieldDefaults
                         .outlinedTextFieldColors(textColor = Color.Blue,
                         focusedLabelColor = Color.Blue,
                             focusedBorderColor = Color.Blue)
                 )
                Column {

                    Spacer(Modifier.size(10.dp))

                    Button(modifier = Modifier,
                        onClick = {
                            runBlocking {
                                viewModel.getForecast(name, city, weather)
                            }
                            name = ""
                            keyboardController?.hide()
                        },
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.textButtonColors(
                            Color.Blue,
                            contentColor = Color.White,
                            disabledContentColor = Color.LightGray),
                    ){
                        Text("GO")

                    }
                }

        }

          Text(city.value,
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(start = 0.dp, top = 8.dp, end = 25.dp, bottom = 8.dp),
              textAlign = TextAlign.End,
              fontSize = 24.sp,
              color = Color.Blue
          )
          
          LazyColumn(modifier = Modifier
              .fillMaxSize()
              .padding(start = 25.dp, top = 5.dp, end = 25.dp, bottom = 5.dp)){
              items(weather){weather -> WeatherTtem(weather)}
          }
      }
     }
  }

@Composable
fun WeatherTtem(weather: ForecastItems) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
           ){
            Column {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                    ) {
                    Text(text = weather.date, modifier = Modifier, textAlign = TextAlign.Start, fontSize = 20.sp, color = Color.White)
                    Text(text = weather.description, modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 15.dp, bottom = 0.dp)
                        ,textAlign = TextAlign.End ,fontSize = 20.sp, color = Color.White)
                }
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Row(modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){

                            Text(text =stringResource(id = R.string.temp),
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp, color = Color.White)

                            Text(text = weather.temp.toString(),
                                modifier = Modifier
                                    .padding(start = 0.dp, top = 5.dp, end = 5.dp, bottom = 5.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp, color = Color.White)

                            Text(text = stringResource(id = R.string.temp_unit),
                                modifier = Modifier
                                    .padding(start = 5.dp, top = 8.dp, end = 0.dp, bottom = 5.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp, color = Color.White)
                        }
                        Row(modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {

                            Text(text =stringResource(id = R.string.humidity),
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp, color = Color.White)

                            Text(text = weather.humidity.toString(),
                                modifier = Modifier
                                    .padding(start = 0.dp, top = 5.dp, end = 5.dp, bottom = 5.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp, color = Color.White)

                            Text(text = stringResource(id = R.string.humidity_unit),
                                modifier = Modifier
                                    .padding(start = 5.dp, top = 8.dp, end = 0.dp, bottom = 5.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp, color = Color.White)
                        }

                        Row(modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {

                            Text(text =stringResource(id = R.string.pressure),
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp, color = Color.White)

                            Text(text = weather.pressure.toString(),modifier = Modifier
                                .padding(start = 0.dp, top = 5.dp, end = 5.dp, bottom = 5.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp, color = Color.White)

                            Text(text = stringResource(id = R.string.pressure_unit),
                                modifier = Modifier
                                    .padding(start = 5.dp, top = 8.dp, end = 0.dp, bottom = 5.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp, color = Color.White)
                        }

                    }
                    val imageUrl = weather.icon
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(start = 0.dp, top = 0.dp, end = 20.dp, bottom = 0.dp)
                    )

                }
            }
        }
        Spacer(Modifier.size(20.dp))
    }
}




