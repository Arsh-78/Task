package com.example.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID
import kotlin.random.Random





val mockComments = List(100) {
    val uid = UUID.randomUUID().toString()
    val userImage = R.drawable.guy
    val username = "Liam"
    val comment = "A yellow face with a frown and closed,downcast eyes as if pain"
    MockComment(uid, userImage, username,comment )
}


@Composable
fun CommentScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)){
        /*Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(80.dp)){

        }*/
        PostItem(
            post = MockPost(UUID.randomUUID().toString(),R.drawable.guy,"Liam",R.drawable.ape,"This is a random description ${Random.nextFloat()}",Random.nextInt(1000),Random.nextInt(100)),
            nav = null

        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 1.dp, vertical = 1.dp)
        ) {
            items(
                items = mockComments,
                itemContent = {
                    PostComment(comment = it)
                })
        }
        }

    /*LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = mockComments,
            itemContent = {
                PostComment(comment = it)
            })
    }*/

        //LazyColumn()


}



@Composable
fun PostComment(comment: MockComment) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .background(Color.White)){
        Row(verticalAlignment = Alignment.CenterVertically){
            RoundImageCard(image = comment.userImage,
                Modifier
                    .size(48.dp)
                    .padding(4.dp))
            Column(modifier = Modifier.padding(2.dp)) {
                Text(text = comment.userName, fontWeight = FontWeight.Bold)
                Text(text = "Public", fontSize = 8.sp,fontWeight = FontWeight.ExtraLight)
                
            }

        }

        Text(text = comment.comment, modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
            )
            Text(text = " like", modifier = Modifier.padding(start = 8.dp))
        }

    }
}


@Preview
@Composable
fun CommentPreview()
{
    CommentScreen()
}