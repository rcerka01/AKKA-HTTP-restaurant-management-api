package com.restoran.stock.test.data

import com.restoran.stock.model.{Item, Stock}

/**
  * Created by raitis on 25/06/2016.
  */

object TestData {

  val testDataGetAll = Stock(
    Vector(
      Item("cheese", 3.40, "food", 3),
      Item("bred", 2.20, "food", 23),
      Item("apple", 4.55, "food", 5),
      Item("spoon", 0.70, "cutlery", 12),
      Item("knife", 0.60, "cutlery", 9)
    )
  )

  val testDataAddItem = Stock(
    Vector(
      Item("cheese", 3.40, "food", 3),
      Item("bred", 2.20, "food", 23),
      Item("apple", 4.55, "food", 5),
      Item("spoon", 0.70, "cutlery", 12),
      Item("knife", 0.60, "cutlery", 9),
      Item("testName", 1.11, "testCategory", 11)
    )
  )

  val testDataUpdateItem = Stock(
    Vector(
      Item("cheese", 3.40, "food", 3),
      Item("bred", 2.20, "food", 23),
      Item("apple", 4.55, "food", 5),
      Item("spoon", 0.70, "cutlery", 12),
      Item("knife", 0.60, "cutlery", 19)
    )
  )

  val testDataRemoveItem = Stock(
    Vector(
      Item("cheese", 3.40, "food", 3),
      Item("bred", 2.20, "food", 23),
      Item("apple", 4.55, "food", 5),
      Item("spoon", 0.70, "cutlery", 12)
    )
  )

  val testDataItemsRunningOutFood = Stock(
    Vector(
      Item("cheese", 3.40, "food", 3),
      Item("apple", 4.55, "food", 5)
    )
  )

}
