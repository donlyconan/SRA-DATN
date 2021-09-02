package com.donly.funny.data.repositories

import androidx.lifecycle.LiveData
import com.donly.funny.data.models.User

interface UserRepository {
    fun create(user: User): Boolean
    fun update(user: User): LiveData<User>
    fun getInfo(): LiveData<User>
}