package com.restoran.stock.test

import com.restoran.stock.model.{Item, Stock, StockDataSet}
import com.restoran.stock.test.data.TestData._
import services.StockServices

/**
  * Created by raitis on 25/06/2016.
  */
class StockServicesTest extends UnitSpec {

  "getAllItems" should "return all items" in {
    StockDataSet.data = testDataGetAll // normalize data
    val expectedResult: Stock = testDataGetAll
    val result: Stock = StockServices.getAllItems
    assert(result === expectedResult)
  }

  "getItemsRunningOut" should "return all items" in {
    StockDataSet.data = testDataGetAll // normalize data
    val expectedResult: Stock = testDataItemsRunningOutFood
    val result: Stock = StockServices.getItemsRunningOut(10, "food")
    assert(result === expectedResult)
  }

  it should "return all food data-set if boundary is 100" in {
    val expectedResult: Int= 3
    val result: Int = StockServices.getItemsRunningOut(100, "food").items.length
    assert(result === expectedResult)
  }

  it should "return empty data-set if boundary is -1" in {
    val expectedResult: Stock = Stock(Vector())
    val result: Stock = StockServices.getItemsRunningOut(-1, "food")
    assert(result === expectedResult)
  }


  "addItem" should "add to data-set new item and return boolean value" in {
    StockDataSet.data = testDataGetAll // normalize data

    val expectedResult1: Boolean = true
    val result1: Boolean = StockServices.addItem("testName", 1.11, "testCategory", 11)

    val expectedResult2: Stock = testDataAddItem
    val result2: Stock = StockDataSet.data

    assert(result1 === expectedResult1)
    assert(result2 === expectedResult2)
  }

  it should "return false, if element with a such name already exist" in {
    val expectedResult: Boolean = false
    val result: Boolean = StockServices.addItem("cheese", 4.05, "food", 2)
    assert(result === expectedResult)
  }

  it should "return 6 elements from data-set" in {
    val expectedResult: Int = 6
    val result: Int = StockDataSet.data.items.length
    assert(result === expectedResult)
  }


  "UpdateItemQuantity" should "update item within data-set to set quantity for 'knife' 19" in {
    StockDataSet.data = testDataGetAll // normalize data

    val expectedResult1: Boolean = true
    val result1: Boolean = StockServices.updateItemQuantity("knife", 19)

    val expectedResult2: Stock = testDataUpdateItem
    val result2: Stock = StockDataSet.data

    val expectedResult3: Int = 19
    val result3: Option[Item] = StockDataSet.data.items find (_.name == "knife")

    assert(result1 === expectedResult1)
    assert(result2 === expectedResult2)
    assert(result3.get.quantity === expectedResult3)
  }

  it should "return false, if element with a such name not exist" in {
    val expectedResult: Boolean = false
    val result: Boolean = StockServices.updateItemQuantity("testName", 19)
    assert(result === expectedResult)
  }

  it should "return 5 elements from data-set" in {
    val expectedResult: Int = 5
    val result: Int = StockDataSet.data.items.length
    assert(result === expectedResult)
  }


  "removeItem" should "change all data-set minus removed item and return boolean value if item exist" in {
    StockDataSet.data = testDataGetAll // normalize data

    val expectedResult1: Boolean = true
    val result1: Boolean = StockServices.removeItem("knife")

    val expectedResult2: Stock = testDataRemoveItem
    val result2: Stock = StockDataSet.data

    assert(result1 === expectedResult1)
    assert(result2 === expectedResult2)
  }

  it should "return false, if element with a such name not exist" in {
    val expectedResult: Boolean = false
    val result: Boolean = StockServices.removeItem("testName")
    assert(result === expectedResult)
  }

  it should "return 4 elements from data-set" in {
    val expectedResult: Int = 4
    val result: Int = StockDataSet.data.items.length
    assert(result === expectedResult)
  }

}
