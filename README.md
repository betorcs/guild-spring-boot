### What's it?

A sample application in [kotlin](http://kotlinlang.org/), [spring-boot](http://spring.io/) using [h2](http://www.h2database.com/html/main.html) database. It consist in a [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) of entity `Product {id, name, sku}`

### What do you need?

You need in your machine [JDK 1.8+](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) and [Git](https://git-scm.com/downloads).

### How do you get the code?

Clone project 
```bash 
git clone git@github.com:betorcs/guild-spring-boot.git
```

### Launching application

```bash
cd guild-spring-boot/
set GRADLE_OPTS=-Xms64m -Xmx128m
./gradlew bootRun
```

### Access database

Using browser access [http://localhost:8080/h2](http://localhost:8080/h2)


### Operations

* Fetch products

```bash
curl -XGET http://localhost:8080/product

[
  {
    "name": "Mouse",
    "sku": "M001",
    "id": 1
  },
  {
    "name": "Monitor",
    "sku": "M002",
    "id": 2
  }
]
```

* Add product

```bash
curl -XPOST http://localhost:8080/product -H "Content-Type: application/json" -d '
{
    "name": "Trackpad",
    "sku": "T234"
}'

{
    "name": "Trackpad",
    "sku": "T234",
    "id": 3
}
```

* Get product by sku

```bash
curl -XGET http://localhost:8080/product/M002

{
  "name": "Monitor",
  "sku": "M002",
  "id": 2
}
```

* Delete product by product ID

```bash
curl -XDELETE http://localhost:8080/product/2
```
