package com.realmdb.realmgym

import android.app.Application
import com.realmdb.realmgym.models.GYM
import com.realmdb.realmgym.models.Trainer
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApplication : Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Trainer::class,
                    GYM::class,
                )
            )
        )
    }
}