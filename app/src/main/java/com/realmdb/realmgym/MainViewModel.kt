package com.realmdb.realmgym

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.realmdb.realmgym.models.GYM
import com.realmdb.realmgym.models.Trainer
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class MainViewModel : ViewModel() {

    private val realm = MyApplication.realm


    init {
        // Add dummy data in the init block
        viewModelScope.launch {
            realm.write {
                // Dummy Trainer 1
                val trainer1 = Trainer()
                trainer1.name = "John Doe"
                trainer1.experience = 5
////                copyToRealm(trainer1)
//
//                // Dummy Trainer 2
                val trainer2 = Trainer()
                trainer2.name = "Jane Smith"
                trainer2.experience = 8
//                copyToRealm(trainer2)

                // Dummy GYM 1 with enrolled trainers
                val gym1 = GYM()
                gym1.name = "Fitness Center A"
                gym1.address = "123 Main Street, City"
                gym1.enrolledTrainers.add(trainer1)
                gym1._id = ObjectId() // Generate a unique primary key
//                copyToRealm(gym1)

                // Dummy GYM 2 with no enrolled trainers
                val gym2 = GYM()
                gym2.name = "Gym X"
                gym2.address = "456 Park Avenue, Town"
                gym2.enrolledTrainers.add(trainer2)
                gym2._id = ObjectId() // Generate a unique primary key
//                copyToRealm(gym2)
                addGym(gym1,null)
                addGym(gym2,null)
            }
        }
    }


    val gyms = realm
        .query<GYM>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    var gymDetails: GYM? by mutableStateOf(null)
        private set


    fun showGYMDetails(gym: GYM) {
        gymDetails = gym
    }

    fun addGym(gym: GYM, trainer: Trainer?) {
        viewModelScope.launch {
            realm.write {
                trainer?.let {gym.enrolledTrainers.add(trainer)}
                copyToRealm(gym, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }


    fun deleteCourse() {
        viewModelScope.launch {
            realm.write {
                val course = gymDetails ?: return@write
                val latestCourse = findLatest(course) ?: return@write
                delete(latestCourse)

                gymDetails = null
            }
        }
    }
}