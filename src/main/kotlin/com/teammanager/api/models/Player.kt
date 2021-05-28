package com.teammanager.api.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//https://www.section.io/engineering-education/introduction-spring-data/

enum class Position { GOALKEEPER, DEFENDER, MIDFIELDER, STRIKER }

@Entity
class Player(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var teamId: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var position: Position? = null,
    var age: Int? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var skills: Int? = null
)