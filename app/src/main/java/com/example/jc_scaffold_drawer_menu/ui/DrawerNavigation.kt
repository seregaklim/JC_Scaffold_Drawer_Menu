package com.example.jc_scaffold_drawer_menu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
   //контейнер
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(Color.Green),
       //расположение текста хидера
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Header",
            //размер текста
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun DrawerBody(){
   //по всей ширине
    LazyColumn(modifier = Modifier.fillMaxWidth()){
       //количество элементов
        items(5){
            Text(
                modifier = Modifier.fillMaxWidth().clickable {
                //отступ
                }.padding(10.dp),
                text = "Menu item $it"
            )
        }
    }
}