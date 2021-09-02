package com.donly.funny.app.sessions

import com.donly.funny.data.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(val sessionId: String) {
    var user: User? = null
    var token: String? = null

    constructor(session: UserSession) : this(session.sessionId) {
        user = session.user
        token = session.token
    }

    fun isLogged(): Boolean {
        return user != null && token != null
    }

    fun getCurrentUser(): User? {
        return user
    }
}