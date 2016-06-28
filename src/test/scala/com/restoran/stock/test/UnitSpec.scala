package com.restoran.stock.test

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest._

/**
  * Created by raitis on 25/06/2016.
  */
abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors

abstract class RouterSpec extends WordSpec with Matchers with ScalatestRouteTest
