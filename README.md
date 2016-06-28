AKKA-HTTP-restaurant-management-api
=

### Task

Implement a fully functional HTTP API for a restaurant stock management system.

Each item restaurant stocks has a name, price and category.
We also  keep track of the items quantity as soon as they get used into the restaurant, so we know in advance when we're running out of one of them.

Implement the following endpoints:

- list of all available items of a given category
- list of all the items running out of stock (assume 10 items as a threshold)
- add a new item
- update the item quantity (example: when the restaurant uses an item, we want to reduce its stock availability counter)
- remove an item (meaning that the item will not be bought again, so we need to take it out of our inventory)

The database can be mocked in memory as preferred, so there's not need to deal with drivers, connections and so on.

The application should be production grade (mocked database apart of course) and it should be possible to run it from command line. It’s fine to assume that we already have building tools installed (java, maven, sbt…).

It would be much appreciated if you use Scala but there are no language/tool restrictions, use whatever you find more appropriate.

### Installation

```sh
$ git clone https://github.com/rcerka01/AKKA-HTTP-restaurant-management-api
$ cd AKKA-HTTP-restaurant-management-api
$ sbt run
```
### Test
 ```sh
$ sbt test
```
### Curl commands
 ```sh
  $ curl -X GET http://localhost:8080/all-items
  $ curl -X GET http://localhost:8080/running-out-of-stock/food
  $ curl -X POST localhost:8080/add-item?name=newitem\&price=1.3\&category=food\&quantity=10
  $ curl -X PUT localhost:8080/update-item-quantity?name=cheese\&quantity=1007
  $ curl -X DELETE localhost:8080/remove-item?name=spoon
```
