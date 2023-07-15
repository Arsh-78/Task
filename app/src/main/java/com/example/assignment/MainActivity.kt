package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.assignment.ui.theme.AssignmentTheme

sealed class Destination(val route : String)
{
    object Home : Destination("home")
    object Comment : Destination("comment")
}



class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setContent {
            AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    /*FeedScreen(livePosts = viewModel.userPager.collectAsLazyPagingItems())*/
                    val navController = rememberNavController()
                    NavigationAppHost(navController= navController,viewModel = viewModel)
                }
            }
        }
    }
}


@Composable
fun NavigationAppHost(navController: NavHostController, viewModel: MainActivityViewModel)
{
    NavHost(navController = navController, startDestination = "home" ){
        composable(Destination.Home.route){
            FeedScreen(livePosts = viewModel.userPager.collectAsLazyPagingItems(), navController = navController)
        }
        composable(Destination.Comment.route){
            CommentScreen(
            )
        }
    }
}


@Composable
fun FeedScreen(livePosts: LazyPagingItems<MockPost>,navController: NavHostController){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)){
        /*Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(80.dp)){

        }*/

        LazyColumn{
            items(livePosts){
                item ->
               item?.let { PostItem(post = it, nav = navController) }
            }
        }
        
    }
}

@Composable
fun PostItem(post: MockPost, nav: NavHostController?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .background(Color.White)){
        Row(verticalAlignment = Alignment.CenterVertically){
            RoundImageCard(image = post.userImage,
                Modifier
                    .size(48.dp)
                    .padding(5.dp))
            Column {
                Text(
                    text = post.userName,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "2 hours ago",
                    fontWeight = FontWeight.Light,
                    fontSize =10.sp

                )
            }

            Box(
                Modifier
                    .background(color = Color(0X33ADD8E6))
                    .padding(2.dp))
            {
                Text(text = "Marketing", fontWeight = FontWeight.Normal,color =Color(0xFF0000FF) , fontSize = 10.sp)
            }
        }
        Image(painter = painterResource(id = post.image),
            contentDescription = null ,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(text = post.description, modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            /*Image(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(text = "${post.likes} likes", modifier = Modifier.padding(start = 8.dp))*/
            PostCardOptions(post = post, icon =R.drawable.ic_like,"likes", modifier = Modifier)
/*            Image(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(text = "${post.likes} comments", modifier = Modifier.padding(start = 8.dp))*/
            PostCardOptions(post = post, icon =R.drawable.ic_comment,"comments" , Modifier.clickable(onClick = {
                nav!!.navigate(Destination.Comment.route)
            }) )
            PostCardOptions(post = post, icon =R.drawable.ic_share , t ="Share", modifier = Modifier)
/*            Image(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(text = "Share", modifier = Modifier.padding(start = 8.dp))*/
        }

    }
}

@Composable
fun RoundImageCard(image : Int, modifier : Modifier= Modifier
    .padding(8.dp)
    .size(64.dp)) {
    Card(shape = CircleShape, modifier = modifier) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }

}

@Composable
fun PostCardOptions(post : MockPost , icon: Int,t:String,modifier: Modifier)
{
    Image(
        modifier = modifier,
        painter = painterResource(id = icon),
        contentDescription = null,
        colorFilter = ColorFilter.tint(Color.Gray)

    )
    Text(text = t)
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AssignmentTheme {
        FeedScreen(liv)
       }
}*/
