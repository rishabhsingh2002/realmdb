package com.realmdb.realmgym.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId


class GYM : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var address: String = ""
    var enrolledTrainers: RealmList<Trainer> = realmListOf()
}