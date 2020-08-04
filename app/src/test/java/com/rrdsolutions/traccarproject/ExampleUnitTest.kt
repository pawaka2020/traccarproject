package com.rrdsolutions.traccarproject

import com.rrdsolutions.traccarproject.models.DogImage
import com.rrdsolutions.traccarproject.models.RetrofitModel
import com.rrdsolutions.traccarproject.models.RetrofitRepo
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import kotlinx.coroutines.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getNotificationType(){

    }

    @Test
    fun getDogImage(){
        val dogurl = "https://dog.ceo/api/breeds/image/random/"
        var result = "result"
        runBlocking {
            println("running")
            RetrofitRepo(dogurl).getDogImage {
                println("running finished")
                result = it.message
            }
            println(result)
        }
        //println(result)

    }

    @Test
    fun dog(){
        val dogurl = "https://dog.ceo/api/breeds/image/random/"
        val model = RetrofitModel(dogurl)
        val test = Repo(model).getDog()
        println(test)
    }

}