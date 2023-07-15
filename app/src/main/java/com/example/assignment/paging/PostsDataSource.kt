package com.example.assignment.paging

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment.MockPost
import com.example.assignment.R
import java.util.UUID
import kotlin.random.Random

class PostsDataSource:PagingSource<Int,MockPost>(){

    val page : Int =0

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
    /*val mockStories = List(20) {
        val uid = UUID.randomUUID().toString()
        val image = animalsList[Random.nextInt(animalsList.size)]
        MockStory(uid, image)
    }*/

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





    private fun getPosts(page:Int): List<MockPost>? {
        val posts : MutableList<MockPost> = mutableListOf<MockPost>()

        when(page){
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
            else -> return null

        }
        return posts
    }




    override fun getRefreshKey(state: PagingState<Int, MockPost>): Int? {
        return state.anchorPosition?.let {position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MockPost> {
        return try {
            val page = params.key ?: 1
            val posts = getPosts(page) ?: emptyList<MockPost>()
            LoadResult.Page(
                data = posts,
                prevKey = null,
                nextKey = if(posts.isNotEmpty()) page+1 else 0
            )

        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}