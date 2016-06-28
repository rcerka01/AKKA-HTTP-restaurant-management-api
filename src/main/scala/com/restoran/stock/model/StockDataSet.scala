package com.restoran.stock.model

/**
  * Created by raitis on 25/06/2016.
  */
object StockDataSet {
  var data = Stock(
    Vector(
      Item("cheese", 3.40, "food", 3),
      Item("bred", 2.20, "food", 23),
      Item("apple", 4.55, "food", 5),
      Item("spoon", 0.70, "cutlery", 12),
      Item("knife", 0.60, "cutlery", 9)
    )
  )
}
