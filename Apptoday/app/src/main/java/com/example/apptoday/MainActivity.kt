package com.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Tarefa.Tarefa
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContest(DrawerState(initialValue = DrawerValue.Closed))

        }
    }
}
@Composable
fun MainScreenContest (drawerState: DrawerState){
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    var scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TaskTodayAPP")},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                         }
                    ) {

                        Icon(

                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu")

                }
        }
            )

        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(Color.Magenta)
                    .height(16.dp)
            ) {
                Text(text = "Opções!!!")
                Text(text = "----------")
            }
            Column() {
                Text(text = "Opcao de menu 1")
                Text(text = "Opcao de menu 2")
                Text(text = "Opcao de menu 3")


            }

        },
        content = {
            paddingValues -> Log.i ("paddinValues","$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()

            ){
                MySearchField(modificador = Modifier.fillMaxWidth())

                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Preparar Aula LazyList/LazyColum",
                    taskDetails ="É bem melhor usar lazilist ao inves de colum",
                    deadEndDate = Date()
                )
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Prova Matematica",
                    taskDetails ="Estudar Calculo capitulo 1 e 2" ,
                    deadEndDate = Date() )


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
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(action = {Log.i("#####%%%%%#####","${it.nome}") })

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
fun MyTaskWidget
            (modificador: Modifier,
             taskName : String,
             taskDetails: String,
             deadEndDate: Date
            ){
    val dateFormatter = SimpleDateFormat("EEE,MMM,dd,yyyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Icons of a pedent task"
        )
        Text(text = dateFormatter.format(deadEndDate),
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp
        )
    }
    Column(
        modifier = modificador
            .border(width = 1.dp, color = Color.Black)
            .padding(3.dp)
    ) {
        Text(
            text = taskName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = taskDetails,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
    }
}
    Spacer(modifier = Modifier.height(16.dp))
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContest(DrawerState(initialValue = DrawerValue.Closed))
}