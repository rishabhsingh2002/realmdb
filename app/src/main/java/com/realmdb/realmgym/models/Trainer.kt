package com.realmdb.realmgym.models

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId


class Trainer: RealmObject {
    var name: String = ""
    var experience: Int = 0
    val enrolledGYMs: RealmResults<GYM> by backlinks(GYM::enrolledTrainers)
}