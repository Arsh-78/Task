package com.example.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.assignment.paging.PostsDataSource
import java.util.UUID
import kotlin.random.Random

class MainActivityViewModel : ViewModel(){

/*    val livePosts = MutableLiveData<List<MockPost>>()
    private var nextPage = 0

    val animalsList = listOf(
        R.drawable.ape, R.drawable.bear, R.drawable.bird, R.drawable.bison, R.drawable.cat,
        R.drawable.chicken, R.drawable.cow, R.drawable.deer, R.drawable.dog, R.drawable.dolphin,
        R.drawable.duck, R.drawable.eagle, R.drawable.fish, R.drawable.horse, R.drawable.lion,
        R.drawable.lobster, R.drawable.monkey, R.drawable.pig, R.drawable.pony, R.drawable.rabbit,
        R.drawable.shark, R.drawable.snake, R.drawable.spider, R.drawable.turkey, R.drawable.wolf
    )

    val commonNames = listOf(
        "Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "Elijah",
        "Charlotte", "William", "Sophia", "James", "Amelia", "Benjamin", "Isabella", "Lucas",
        "Mia", "Henry", "Evelyn", "Alexander", "Harper"
    )
    *//*val mockStories = List(20) {
        val uid = UUID.randomUUID().toString()
        val image = animalsList[Random.nextInt(animalsList.size)]
        MockStory(uid, image)
    }*//*

    val mockPosts = List(100) {
        val uid = UUID.randomUUID().toString()
        val userImage = R.drawable.guy
        val username = commonNames[Random.nextInt(commonNames.size)]
        val image = animalsList[Random.nextInt(animalsList.size)]
        val description = "This is a random description ${Random.nextFloat()}"
        val likes = Random.nextInt(1000)
        val comments = Random.nextInt(100)
        MockPost(uid, userImage, username, image, description, likes, comments)
    }





    fun getPosts(page:Int){
        val posts = mutableListOf<MockPost>()

        when(nextPage){
            0-> posts.addAll(
                List(15) {
                val uid = UUID.randomUUID().toString()
                val userImage = R.drawable.guy
                val username = commonNames[Random.nextInt(commonNames.size)]
                val image = animalsList[Random.nextInt(animalsList.size)]
                val description = "This is a random description ${Random.nextFloat()}"
                val likes = Random.nextInt(1000)
                val comments = Random.nextInt(100)
                MockPost(uid, userImage, username, image, description, likes, comments)
            })
            1-> posts.addAll(
                List(15) {
                    val uid = UUID.randomUUID().toString()
                    val userImage = R.drawable.guy
                    val username = commonNames[Random.nextInt(commonNames.size)]
                    val image = animalsList[Random.nextInt(animalsList.size)]
                    val description = "This is a random description ${Random.nextFloat()}"
                    val likes = Random.nextInt(1000)
                    val comments = Random.nextInt(100)
                    MockPost(uid, userImage, username, image, description, likes, comments)
                })
            2-> posts.addAll(
                List(15) {
                    val uid = UUID.randomUUID().toString()
                    val userImage = R.drawable.guy
                    val username = commonNames[Random.nextInt(commonNames.size)]
                    val image = animalsList[Random.nextInt(animalsList.size)]
                    val description = "This is a random description ${Random.nextFloat()}"
                    val likes = Random.nextInt(1000)
                    val comments = Random.nextInt(100)
                    MockPost(uid, userImage, username, image, description, likes, comments)
                })
        }
        livePosts.value = posts
        nextPage++
    }*/

    val userPager = Pager(
        PagingConfig(pageSize =10)
    ){
       PostsDataSource()
    }.flow.cachedIn(viewModelScope)
}