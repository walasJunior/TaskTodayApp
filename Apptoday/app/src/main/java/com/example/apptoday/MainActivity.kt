package com.example.apptoday

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptoday.ui.theme.ApptodayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContest()

        }
    }
}
@Composable
fun MainScreenContest (){
    Scaffold(
        topBar = {
            TopAppBar(
                content =  { Text(text = "taskAppBar")}
            )

        },
        content = {
            paddingValues -> Log.i ("paddinValues","$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()

            ){
                MySearchField(modificador = Modifier.fillMaxWidth())
                MyTaskWidget(modificador = Modifier.fillMaxWidth() )
                Text("Task3")
                Text("Task4")

            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text("asdf")}
            )
                

        }
    )


}

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "pesquisar tarefas") },
        leadingIcon = {
            Icon(
                imageVector =  Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        }

        )
}
@Composable
fun MyTaskWidget(modificador: Modifier){
    Column(modifier = modificador) {
        Text(text = "Tarefa X")

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
 MainScreenContest()
}