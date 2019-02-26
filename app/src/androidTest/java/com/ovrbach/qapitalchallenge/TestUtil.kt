package com.ovrbach.qapitalchallenge

import com.ovrbach.qapitalchallenge.common.entity.Feed
import com.ovrbach.qapitalchallenge.common.entity.Goal
import com.ovrbach.qapitalchallenge.common.logic.formatToServer
import java.util.*

object TestUtil {

    fun goalJpgImageHalfCompleteActiveArmor1() = Goal(
        goalImageURL = "https://www.image.com/image.jpg",
        userId = 1,
        targetAmount = 200F,
        currentBalance = 100F,
        status = "active",
        name = "Adamantium armor",
        id = 1
    )

    fun goalJpgImageHalfCompleteActiveBrewery2() = Goal(
        goalImageURL = "https://www.image.com/image.jpg",
        userId = 1,
        targetAmount = 20F,
        currentBalance = 10F,
        status = "active",
        name = "Brewery",
        id = 2
    )

    fun goalJpgImageHalfCompleteActiveCar1() = Goal(
        goalImageURL = "https://www.image.com/image.png",
        userId = 1,
        targetAmount = 2F,
        currentBalance = 1F,
        status = "active",
        name = "World's slowest car",
        id = 1
    )

    fun feedSale1() = Feed(
        id = "1",
        type = "saving",
        timestamp = "2015-03-10T14:55:16.025Z",
        message = "Resisted steam sale",
        amount = 20F,
        userId = 1
    )

    fun feedDinner1() = Feed(
        id = "1",
        type = "saving",
        timestamp = "2015-03-10T14:55:16.025Z",
        message = "Cooked the dinner",
        amount = 40F,
        userId = 1
    )

    fun feedWalk2() = Feed(
        id = "2",
        type = "saving",
        timestamp = "2015-03-10T14:55:16.025Z",
        message = "Walked home",
        amount = 5F,
        userId = 1
    )

    fun feedWalkHostelRecent() = Feed(
        id = "4",
        type = "saving",
        timestamp = Date(System.currentTimeMillis()).formatToServer(),
        message = "Slept at hostel",
        amount = 3F,
        userId = 1
    )

    fun feedDicount2Recent() = Feed(
        id = "5",
        type = "saving",
        timestamp = Date(System.currentTimeMillis()).formatToServer(),
        message = "Discount food",
        amount = 6F,
        userId = 1
    )
}