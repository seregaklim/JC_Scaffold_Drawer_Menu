package com.example.jc_scaffold_drawer_menu

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jc_scaffold_drawer_menu.ui.DrawerBody
import com.example.jc_scaffold_drawer_menu.ui.DrawerHeader
import com.example.jc_scaffold_drawer_menu.ui.theme.JC_Scaffold_Drawer_MenuTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_Scaffold_Drawer_MenuTheme {

                MainScreen(applicationContext)

            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context) {
//при вызове SnackBar,блокируется основной поток
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { host ->
            SnackbarHost(hostState = host){ data ->
                Snackbar(
                    backgroundColor = Color.White,
                    snackbarData = data,
                    shape = RoundedCornerShape(20.dp),
                    contentColor = Color.Green,
                    modifier = Modifier.padding(bottom = 100.dp)
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Menu")
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(
                     ////////////////////////
                        //вызываем drawerState
                        onClick = {
                            coroutineScope.launch {
                                //открыть
                                scaffoldState.drawerState.open()
                            }
                        }
                   ///////////////
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch{
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Item deleted!",
                                    actionLabel = "Undone"
                                )
                                if(result == SnackbarResult.ActionPerformed){
                                    Toast.makeText(context, "Item recovered", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete")
                    }
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share")
                    }
                }
            )
        },
///////////////////
        drawerContent = {
            DrawerHeader()
            DrawerBody()
        }
   ///////////
    ) {

    }
}