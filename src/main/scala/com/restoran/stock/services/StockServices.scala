package services

import com.restoran.stock.model.{Stock, Item, DaoStock}

/**
  * Created by raitis on 25/06/2016.
  */
sealed trait Services

object StockServices extends Services {

  def getAllItems: Stock = DaoStock.getAllStock

  def getItemsRunningOut(quantity: Int, category: String): Stock = Stock(DaoStock.getAllStock.items filter (item =>
    (item.quantity < quantity) && (item.category == category)))

  def addItem(itemName: String, itemPrice: Double, itemCategory: String, itemQuantity: Int): Boolean =
    DaoStock.addItem(Item(itemName, itemPrice, itemCategory, itemQuantity))

  def updateItemQuantity(itemName: String, itemQuantity: Int): Boolean = DaoStock.updateItem(itemName, itemQuantity)

  def removeItem(itemName: String): Boolean = DaoStock.removeItem(itemName)

}
