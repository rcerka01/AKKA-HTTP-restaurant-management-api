package com.restoran.stock.test

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import com.restoran.stock.model.{Item, Stock, StockDataSet}
import com.restoran.stock.routers.StockRouter
import com.restoran.stock.test.data.TestData
import com.restoran.stock.test.data.TestData._
import spray.json.DefaultJsonProtocol._

/**
  * Created by raitis on 27/06/2016.
  */

class RouterTest extends RouterSpec {

  // marshalling / un-marshalling with spray json
  implicit val itemFormat = jsonFormat4(Item)
  implicit val stockFormat = jsonFormat1(Stock)

  "The all-items service" should {

    "return all data-set from GET request to the /all-items path" in {
      StockDataSet.data = testDataGetAll // normalize data

      Get("/all-items") ~> StockRouter.route ~> check {
        responseAs[Stock] shouldEqual TestData.testDataGetAll
      }
    }

    "leave GET requests to undefined paths unhandled" in {
      StockDataSet.data = testDataGetAll // normalize data

      Get("/undefined") ~> StockRouter.route ~> check {
        handled shouldBe false
      }
    }

  }


  "The running-out-of-stock service" should {

    "from GET request return data-set for given category, where quantity is less than 10" in {
      StockDataSet.data = testDataGetAll // normalize data

      Get("/running-out-of-stock/food") ~> StockRouter.route ~> check {
        responseAs[Stock] shouldEqual TestData.testDataItemsRunningOutFood
      }
    }

    "return empty stock for undefined category" in {

      Get("/running-out-of-stock/undefined") ~> StockRouter.route ~> check {
        responseAs[Stock] shouldEqual Stock(Vector())
      }
    }

  }


  "The add-item service" should {

    "from POST request add new item" in {
      StockDataSet.data = testDataGetAll // normalize data

      Post("/add-item?name=testName&price=1.11&category=testCategory&quantity=11") ~> StockRouter.route ~> check {
        StockDataSet.data === TestData.testDataAddItem
        responseAs[String] shouldEqual "The request has been fulfilled and resulted in a new resource being created."
      }
    }

    "reject request if item already exist" in {
      Post("/add-item?name=testName&price=1.11&category=testCategory&quantity=11") ~> StockRouter.route ~> check {
        responseAs[String] shouldEqual "The request could not be processed because of conflict in the request, such as an edit conflict."
      }
    }

    "reject other requests than Post" in {
      Get("/add-item") ~> Route.seal(StockRouter.route) ~> check {
       status === StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: POST"
      }
    }
  }


  "The update-item-quantity service" should {

    "from PUT request update item quantity" in {
      StockDataSet.data = testDataGetAll // normalize data

      Put("/update-item-quantity?name=knife&quantity=19") ~> StockRouter.route ~> check {
        StockDataSet.data === TestData.testDataUpdateItem
        responseAs[String] shouldEqual "OK"
      }
    }

    "reject request for not existing item" in {
      Put("/update-item-quantity?name=undefined&quantity=19") ~> StockRouter.route ~> check {
        responseAs[String] shouldEqual "The request could not be processed because of conflict in the request, such as an edit conflict."
      }
    }

    "reject other requests than Post" in {
      Get("/update-item-quantity") ~> Route.seal(StockRouter.route) ~> check {

        status === StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: PUT"
      }
    }
  }


  "The remove-item service" should {

    "from DELETE request remove item by name" in {
      StockDataSet.data = testDataGetAll // normalize data

      Delete("/remove-item?name=knife") ~> StockRouter.route ~> check {
        StockDataSet.data === TestData.testDataRemoveItem
        responseAs[String] shouldEqual "OK"
      }
    }

    "reject request for not existing item" in {
      Delete("/remove-item?name=knife") ~> StockRouter.route ~> check {
        responseAs[String] shouldEqual "The request could not be processed because of conflict in the request, such as an edit conflict."
      }
    }

    "reject other requests than Delete" in {
      Get("/remove-item") ~> Route.seal(StockRouter.route) ~> check {

        status === StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: DELETE"
      }
    }
  }

}
