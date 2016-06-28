package com.restoran.stock.routers

/**
  * Created by raitis on 25/06/2016.
  */

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.restoran.stock.model.{Item, Stock}
import com.typesafe.config.ConfigFactory
import services.StockServices

import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

/**
  * curl -X GET http://localhost:8080/all-items
  * curl -X GET http://localhost:8080/running-out-of-stock/food
  * curl -X POST localhost:8080/add-item?name=newitem\&price=1.3\&category=food\&quantity=10
  * curl -X PUT localhost:8080/update-item-quantity?name=cheese\&quantity=1007
  * curl -X DELETE localhost:8080/remove-item?name=spoon
  */

sealed trait MainRouter

object StockRouter extends MainRouter {

  // marshalling / un-marshalling with spray json
  implicit val itemFormat = jsonFormat4(Item)
  implicit val stockFormat = jsonFormat1(Stock)

  // from config
  val config = ConfigFactory.load()
  val limiter = config.getInt("app-data.limit-of-running-out-items")

  val LIMIT_OF_RUNNING_OUT_ITEMS: Int = limiter

  val route =
    path("all-items") {
      get {
        val stock: Stock = StockServices.getAllItems
        complete(stock)
      }
    } ~
      path("running-out-of-stock" / Segment) { category: String =>
        get {
          val stock: Stock = StockServices.getItemsRunningOut(LIMIT_OF_RUNNING_OUT_ITEMS, category)
          complete(stock)
        }
      } ~
      path("add-item") {
        post {
          parameter("name", "price".as[Double], "category", "quantity".as[Int]) { (name, price, category, quantity) =>
            val added: Boolean = StockServices.addItem(name, price, category, quantity)
            if (added)
              complete(StatusCodes.Created)
            else
              complete(StatusCodes.Conflict)
          }
        }
      } ~
      path("update-item-quantity") {
        put {
          parameter("name", "quantity".as[Int]) { (name, quantity) =>
            val updated: Boolean = StockServices.updateItemQuantity(name, quantity)
            if (updated)
              complete(StatusCodes.OK)
            else
              complete(StatusCodes.Conflict)
          }
        }
      } ~
      path("remove-item") {
        delete {
          parameter("name") { (name) =>
            val updated: Boolean = StockServices.removeItem(name)
            if (updated)
              complete(StatusCodes.OK)
            else
              complete(StatusCodes.Conflict)
          }
        }
      }

}
