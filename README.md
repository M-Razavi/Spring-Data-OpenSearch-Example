# Spring Data OpenSearch Example with Spring Boot 3 and OpenSearch 1.1

## Introduction

This example demonstrates how to use Spring Data OpenSearch to do simple CRUD operations.

This example inspired be
the [Spring-Data-ElasticSearch-Example](https://github.com/kasramp/Spring-Data-ElasticSearch-Example/tree/master)

You can find the tutorial about this example at this
link: [Getting started with Spring Data OpenSearch](https://medium.com/@m-razavi/getting-started-with-spring-boot-data-opensearch-3acde70492a8)

For this example, we created a Book controller that allows doing the following operations with OpenSearch:

- Get the list of all books
- Create a book
- Update a book by Id
- Delete a book by Id
- Search for a book by ISBN
- Fuzzy search for books by author and title

## How to run

The first thing to do is to start OpenSearch. For that, you can use the `docker-compose` file in the develop-scripts
directory and run it like this:

```bash
$ cd develop-scripts && docker-compose -f docker-compose up -d
``` 

It brings OpenSearch up with its dashboard.

Then you can run the application like below:

```bash
$ ./mvnw spring-boot:run
```

Once everything is up and running open the browser and go to [http://localhost:8080](http://localhost:8080). You should
see Swagger to interact with.

## Run Testcontainers tests

The integration tests are written relying on [Testcontainers](https://www.testcontainers.org/) to spin up OpenSearch on
the spot and run tests against it.

To run the integration test (using Testcontainers) just run the below command:

```bash
$ mvn clean verify
```

Make sure you have your docker running.