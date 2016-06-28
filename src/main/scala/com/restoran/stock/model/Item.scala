package com.restoran.stock.model

/**
  * Created by raitis on 24/06/2016.
  */

case class Item(name: String, price: Double, category: String, quantity: Int)
case class Stock(items: Vector[Item])

trait DaoStockOps {
  def getAllStock: Stock
  def addItem(item: Item): Boolean
  def updateItem(name: String, quantity: Int): Boolean
  def removeItem(name: String): Boolean
  def itemExist(name: String): Boolean
}


object DaoStock extends DaoStockOps {

  def getAllStock: Stock = StockDataSet.data

  def addItem(item: Item): Boolean = {
    if (!itemExist(item.name)) {
      StockDataSet.data = Stock(StockDataSet.data.items :+ item)
      return itemExist(item.name)
    }
    false
  }

  def updateItem(name: String, quantity: Int): Boolean = {
    val item: Item = StockDataSet.data.items.find(_.name == name).getOrElse(return false)
    val updatedItem: Item = Item(item.name, item.price, item.category, quantity)
    removeItem(item.name)
    addItem(updatedItem)
  }

  def removeItem(name: String): Boolean = {
    if (!itemExist(name))
      return false
    StockDataSet.data = Stock(StockDataSet.data.items.filterNot(_.name == name))
    !itemExist(name)
  }

  def itemExist(name: String): Boolean = StockDataSet.data.items exists(_.name == name)

}