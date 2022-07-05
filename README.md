# movie-service

## About App

* This is a "Movie service" RESTful Web Service with 3 end points, using Spring Boot/Hibernate H2 in memory database. 
* There is a large CSV file containing = data from the oscars between 1927 - 2010, which is queried for results. 
* When a user inputs a movie title into this service, be it to rate a movie or check against the CSV for oscars, an external OMDB API is first queried to see if the movie even exists. If the movie doesn't, then the services's result will let you know.
* This OMDB API also gives us the movie's Box office total, this will be returned when rating a movie, and also when requesting the top 10 rated movies it order them by so.


## How to run

#### Check if a movie won 'Best picture' at the oscars 1927 - 2010. 

------

* **Request** 
 
```
GET - http://localhost:8080/title/{movie title}
```

* **Response**
```
{movie title} did win best picture
 ```
#### Rate any movie from 0 - 100. 

* **Request** 
 
```
POST - http://localhost:8080/rating
```
```JSON
{
  "title": "toy story",
  "rating": 10
}
```
* **Response**
```
{
    "title": "toy story",
    "boxOffice": 223225679,
    "rating": 10
}
```

#### Get a list of 10 top-rated movies ordered by box office value.

* **Request** 
 
```
GET - http://localhost:8080/rating
```

* **Response**
```
[
    {
        "title": "toy story 3",
        "boxOffice": 415004880.00,
        "rating": 5
    },
    {
        "title": "toy story 2",
        "boxOffice": 245852179.00,
        "rating": 30
    },
    {
        "title": "toy story",
        "boxOffice": 223225679.00,
        "rating": 10
    }
]
```


### Spec -
------
* Accepts JSON 
* Response in JSON 
* Java 11
* Build with Maven
* Data storage: (in memory) database
* Lombok has been used to reduce boilerplate code

### How to test
Run as a Spring Boot App, you can send requests via Postman to API outlined in the 'How to run' section

### To do's
* more coverage of unit tests (at the moment everything is covered, but some coverage is only done by integration tests, finer grain tests may come in handy)


### Scale





