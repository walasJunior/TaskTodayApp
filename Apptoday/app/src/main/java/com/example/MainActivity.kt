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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
    var tabIndex = by remember{ mutableStateOf(0) }
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



                val calendar = Calendar.getInstance()





                val  tProvadeCalculo = Tarefa(
                    "Estudar Prova de Calculo",
                    "Cplto1 do livro xyz",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val  tProvadeKotlin= Tarefa(
                    "Estudar Prova de Kotlin",
                    "Cplto1 do livro xyz",
                    Date(),
                    Date(),
                    status = 0.0
                )

                var MinhaListaDeTarefas = listOf<Tarefa>(tProvadeCalculo, tProvadeKotlin)


                MyTaskWidgetList(MinhaListaDeTarefas)




            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text("asdf")}
            )
                

        },
        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            icon = {
                   Icon(imageVector = Icons.Default.AddCircle,
                       contentDescription ="Add Task")
            },
            text = { Text(text = "ADD") },
            onClick = { /*TODO*/ })

        }

    )

}
@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(
       action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), tarefasASerMostrada = it)})

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
             tarefasASerMostrada : Tarefa

            ){
    val dateFormatter = SimpleDateFormat("EEE,MMM,dd,yyyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Icons of a pedent task"
        )
        Text(text = dateFormatter.format(tarefasASerMostrada.pzoFinal),
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
            text = tarefasASerMostrada.nome,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

            Text(
                text = tarefasASerMostrada.detalhes.toString(),
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