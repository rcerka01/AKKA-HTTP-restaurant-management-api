package com.restoran.stock.test

import com.restoran.stock.model.{StockDataSet, DaoStock, Item, Stock}
import data.TestData._

/**
  * Created by raitis on 25/06/2016.
  */
class DaoStockTest extends UnitSpec {

  "getAllStock" should "return all data-set" in {
    StockDataSet.data = testDataGetAll // normalize data
    val expectedResult: Stock = testDataGetAll
    val result: Stock = DaoStock.getAllStock
    assert(result === expectedResult)
  }


  "itemExist" should "return true for existing item" in {
    StockDataSet.data = testDataGetAll // normalize data
    val item = Item("cheese", 3.40, "food", 3)
    val expectedResult: Boolean = true
    val result: Boolean = DaoStock.itemExist(item.name)
    assert(result === expectedResult)
  }

  it should "return false for not existing item" in {
    val item = Item("testName", 1.11, "testCategory", 11)
    val expectedResult: Boolean = false
    val result: Boolean = DaoStock.itemExist(item.name)
    assert(result === expectedResult)
  }


  "addItem" should "add to data-set new item and return boolean value" in {
    StockDataSet.data = testDataGetAll // normalize data

    val item = Item("testName", 1.11, "testCategory", 11)

    val expectedResult1: Boolean = true
    val result1: Boolean = DaoStock.addItem(item)

    val expectedResult2: Stock = testDataAddItem
    val result2: Stock = StockDataSet.data

    assert(result1 === expectedResult1)
    assert(result2 === expectedResult2)
  }

  it should "return false, if element with a such name already exist" in {
    val item = Item("cheese", 4.05, "food", 2)
    val expectedResult: Boolean = false
    val result: Boolean = DaoStock.addItem(item)
    assert(result === expectedResult)
  }

  it should "return 6 elements from data-set" in {
    val expectedResult: Int = 6
    val result: Int = StockDataSet.data.items.length
    assert(result === expectedResult)
  }


  "UpdateItem" should "update item within data-set" in {
    StockDataSet.data = testDataGetAll // normalize data

    val item = Item("knife", 0.60, "cutlery", 19)

    val expectedResult1: Boolean = true
    val result1: Boolean = DaoStock.updateItem(item.name, item.quantity)

    val expectedResult2: Stock = testDataUpdateItem
    val result2: Stock = StockDataSet.data

    val expectedResult3: Int = 19
    val result3: Option[Item] = StockDataSet.data.items find (_.name == "knife")

    assert(result1 === expectedResult1)
    assert(result2 === expectedResult2)
    assert(result3.get.quantity === expectedResult3)
  }

  it should "return false, if element with a such name not exist" in {
    val item = Item("testName", 1.11, "testCategory", 22)
    val expectedResult: Boolean = false
    val result: Boolean = DaoStock.updateItem(item.name, item.quantity)
    assert(result === expectedResult)
  }

  it should "return 5 elements from data-set" in {
    val expectedResult: Int = 5
    val result: Int = StockDataSet.data.items.length
    assert(result === expectedResult)
  }


  "removeItem" should "change all data-set minus removed item and return boolean value if item exist" in {
    StockDataSet.data = testDataGetAll // normalize data

    val item = Item("knife", 0.60, "cutlery", 9)

    val expectedResult1: Boolean = true
    val result1: Boolean = DaoStock.removeItem(item.name)

    val expectedResult2: Stock = testDataRemoveItem
    val result2: Stock = StockDataSet.data

    assert(result1 === expectedResult1)
    assert(result2 === expectedResult2)
  }

  it should "return false, if element with a such name not exist" in {
    val item = Item("testName", 1.11, "testCategory", 11)
    val expectedResult: Boolean = false
    val result: Boolean = DaoStock.removeItem(item.name)
    assert(result === expectedResult)
  }

  it should "return 4 elements from data-set" in {
    val expectedResult: Int = 4
    val result: Int = StockDataSet.data.items.length
    assert(result === expectedResult)
  }

}
