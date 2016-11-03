# Pokémongo REST API Documentation

## Pokémon Data

### Get all Pokémon

Get all static pokémon.

* URL  

   /PokeMongo/rest/pokemondata

* Method  

   `GET`
  
* Success Response  

   Code: 200 OK  
    Example content: `[
  {
    "id": 1,
    "pokedexNumber": 1,
    "name": "Bulbasaur",
    "restLinks": [
      {
        "uri": "http://localhost:8080/PokeMongo/rest/pokemondata/1",
        "rel": "Self"
      }
    ]
  },
  {
    "id": 2,
    "pokedexNumber": 2,
    "name": "Ivysaur",
    "restLinks": [
      {
        "uri": "http://localhost:8080/PokeMongo/rest/pokemondata/2",
        "rel": "Self"
      }
    ]
  }
]`  

* Error Response  

   Code: 204 NO CONTENT

### Get Pokémon by ID

Get specific static pokémon by pokedex number.

* URL  

   /PokeMongo/rest/pokemondata/:pokedexNumber

* Method  

   `GET`

*  URL Params

   Required: `pokedexNumber=[integer]`

* Success Response  

   Code: 200 OK  
   Example content: `{
  "id": 1,
  "pokedexNumber": 1,
  "name": "Bulbasaur",
  "restLinks": [
    {
      "uri": "http://localhost:8080/PokeMongo/rest/pokemondata/1",
      "rel": "Self"
    }
  ]
}`  

* Error Response  

   Code: 204 NO CONTENT

## User Added Pokémon

### Get all user Pokémon

Get all pokémon added by the users.

* URL  

   /PokeMongo/rest/pokemon

* Method  

   `GET`
  
* Success Response  

   Code: 200 OK  
   Example content: `[
  {
    "id": 38,
    "pokedexNumber": 2,
    "name": "Ivysaur",
    "combatPower": 55,
    "healthPoints": 68,
    "lng": "11.9744",
    "lat": "57.7088",
    "timeAdded": {
      "monthValue": 10,
      "nano": 0,
      "year": 2016,
      "month": "OCTOBER",
      "dayOfYear": 294,
      "dayOfWeek": "THURSDAY",
      "dayOfMonth": 20,
      "hour": 13,
      "minute": 28,
      "second": 32,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "ownerId": 35,
    "restLinks": []
  },
  {
    "id": 39,
    "pokedexNumber": 17,
    "name": "Pidgeotto",
    "combatPower": 62,
    "healthPoints": 54,
    "lng": "11.974",
    "lat": "57.7087",
    "timeAdded": {
      "monthValue": 10,
      "nano": 0,
      "year": 2016,
      "month": "OCTOBER",
      "dayOfYear": 294,
      "dayOfWeek": "THURSDAY",
      "dayOfMonth": 20,
      "hour": 13,
      "minute": 29,
      "second": 1,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "ownerId": 35,
    "restLinks": []
  }
]`  

* Error Response  

   Code: 204 NO CONTENT

### Get Pokémon by ID

Get specific user added pokémon by id number.

* URL  

   /PokeMongo/rest/pokemon/:id

* Method  

   `GET`

*  URL Params

   Required: `id=[Long]`

* Success Response  

   Code: 200 OK  
   Example content: `{
  "id": 38,
  "pokedexNumber": 2,
  "name": "Ivysaur",
  "combatPower": 55,
  "healthPoints": 68,
  "lng": "11.9744",
  "lat": "57.7088",
  "timeAdded": {
    "monthValue": 10,
    "nano": 0,
    "year": 2016,
    "month": "OCTOBER",
    "dayOfYear": 294,
    "dayOfWeek": "THURSDAY",
    "dayOfMonth": 20,
    "hour": 13,
    "minute": 28,
    "second": 32,
    "chronology": {
      "id": "ISO",
      "calendarType": "iso8601"
    }
  },
  "ownerId": 35,
  "restLinks": [
    {
      "uri": "http://localhost:8080/PokeMongo/rest/pokemon/38",
      "rel": "Self"
    },
    {
      "uri": "http://localhost:8080/PokeMongo/rest/user/35",
      "rel": "Owner"
    }
  ]
}`  

* Error Response  

   Code: 204 NO CONTENT

### Get newest user Pokémon

Get all pokémon added by the users the last 30 minutes.

* URL  

   /PokeMongo/rest/pokemon/newest

* Method  

   `GET`
  
* Success Response  

   Code: 200 OK  
   Example content: `[
  {
    "id": 49,
    "pokedexNumber": 1,
    "name": "Bulbasaur",
    "combatPower": 23,
    "healthPoints": 23,
    "lng": "11.9744",
    "lat": "57.7089",
    "timeAdded": {
      "monthValue": 11,
      "nano": 0,
      "year": 2016,
      "month": "NOVEMBER",
      "dayOfYear": 308,
      "dayOfWeek": "THURSDAY",
      "dayOfMonth": 3,
      "hour": 13,
      "minute": 41,
      "second": 46,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "ownerId": 36,
    "restLinks": []
  },
  {
    "id": 50,
    "pokedexNumber": 12,
    "name": "Butterfree",
    "combatPower": 23,
    "healthPoints": 23,
    "lng": "11.9746",
    "lat": "57.7088",
    "timeAdded": {
      "monthValue": 11,
      "nano": 0,
      "year": 2016,
      "month": "NOVEMBER",
      "dayOfYear": 308,
      "dayOfWeek": "THURSDAY",
      "dayOfMonth": 3,
      "hour": 13,
      "minute": 44,
      "second": 6,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "ownerId": 36,
    "restLinks": []
  }
]`  

* Error Response  

   Code: 204 NO CONTENT

## Posts

### Get all posts

Get all posts.

* URL  

   /PokeMongo/rest/post

* Method  

   `GET`
  
* Success Response  

   Code: 200 OK  
    Example content: `[
  {
    "id": 122,
    "title": "EN TILL",
    "content": "caps",
    "postTime": {
      "monthValue": 10,
      "nano": 0,
      "year": 2016,
      "month": "OCTOBER",
      "dayOfYear": 292,
      "dayOfWeek": "TUESDAY",
      "dayOfMonth": 18,
      "hour": 14,
      "minute": 51,
      "second": 12,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "childPosts": [
      {
        "id": 123,
        "title": null,
        "content": "Och här har vi kommentaren...",
        "postTime": {
          "monthValue": 10,
          "nano": 0,
          "year": 2016,
          "month": "OCTOBER",
          "dayOfYear": 292,
          "dayOfWeek": "TUESDAY",
          "dayOfMonth": 18,
          "hour": 14,
          "minute": 52,
          "second": 12,
          "chronology": {
            "id": "ISO",
            "calendarType": "iso8601"
          }
        },
        "childPosts": [],
        "restLinks": [
          {
            "uri": "http://localhost:8080/PokeMongo/rest/post/123",
            "rel": "Self"
          },
          {
            "uri": "http://localhost:8080/PokeMongo/rest/user/35",
            "rel": "Author"
          }
        ],
        "authorId": 35,
        "authorImageURL": "/images/35.jpg",
        "postTimeAsString": "2016-10-18 14:52"
      }
    ],
    "restLinks": [
      {
        "uri": "http://localhost:8080/PokeMongo/rest/post/122",
        "rel": "Self"
      },
      {
        "uri": "http://localhost:8080/PokeMongo/rest/user/35",
        "rel": "Author"
      }
    ],
    "authorId": 35,
    "authorImageURL": "/images/35.jpg",
    "postTimeAsString": "2016-10-18 14:51"
  },
  {
    "id": 121,
    "title": "YOTYOYYO",
    "content": "MANOGGER",
    "postTime": {
      "monthValue": 10,
      "nano": 0,
      "year": 2016,
      "month": "OCTOBER",
      "dayOfYear": 292,
      "dayOfWeek": "TUESDAY",
      "dayOfMonth": 18,
      "hour": 14,
      "minute": 42,
      "second": 42,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "childPosts": [],
    "restLinks": [
      {
        "uri": "http://localhost:8080/PokeMongo/rest/post/121",
        "rel": "Self"
      },
      {
        "uri": "http://localhost:8080/PokeMongo/rest/user/35",
        "rel": "Author"
      }
    ],
    "authorId": 35,
    "authorImageURL": "/images/35.jpg",
    "postTimeAsString": "2016-10-18 14:42"
  }
]`  

* Error Response  

   Code: 204 NO CONTENT

### Get post by ID

Get specific post by id.

* URL  

   /PokeMongo/rest/post/:id

* Method  

   `GET`

*  URL Params

   Required: `id=[Long]`

* Success Response  

   Code: 200 OK  
   Example content: `{
  "id": 121,
  "title": "YOTYOYYO",
  "content": "MANOGGER",
  "postTime": {
    "monthValue": 10,
    "nano": 0,
    "year": 2016,
    "month": "OCTOBER",
    "dayOfYear": 292,
    "dayOfWeek": "TUESDAY",
    "dayOfMonth": 18,
    "hour": 14,
    "minute": 42,
    "second": 42,
    "chronology": {
      "id": "ISO",
      "calendarType": "iso8601"
    }
  },
  "childPosts": [],
  "restLinks": [
    {
      "uri": "http://localhost:8080/PokeMongo/rest/post/121",
      "rel": "Self"
    },
    {
      "uri": "http://localhost:8080/PokeMongo/rest/user/35",
      "rel": "Author"
    }
  ],
  "authorId": 35,
  "authorImageURL": "/images/35.jpg",
  "postTimeAsString": "2016-10-18 14:42"
}`  

* Error Response  

   Code: 204 NO CONTENT

### Get post by keyword

Get post containing provided keyword in title or content.

* URL  

   /PokeMongo/rest/post/search/:keyword

* Method  

   `GET`

*  URL Params

   Required: `keyword=[String]`

* Success Response  

   Code: 200 OK  
   Example content: `[
  {
    "id": 122,
    "title": "EN TILL",
    "content": "caps",
    "postTime": {
      "monthValue": 10,
      "nano": 0,
      "year": 2016,
      "month": "OCTOBER",
      "dayOfYear": 292,
      "dayOfWeek": "TUESDAY",
      "dayOfMonth": 18,
      "hour": 14,
      "minute": 51,
      "second": 12,
      "chronology": {
        "id": "ISO",
        "calendarType": "iso8601"
      }
    },
    "childPosts": [
      {
        "id": 123,
        "title": null,
        "content": "Och här har vi kommentaren...",
        "postTime": {
          "monthValue": 10,
          "nano": 0,
          "year": 2016,
          "month": "OCTOBER",
          "dayOfYear": 292,
          "dayOfWeek": "TUESDAY",
          "dayOfMonth": 18,
          "hour": 14,
          "minute": 52,
          "second": 12,
          "chronology": {
            "id": "ISO",
            "calendarType": "iso8601"
          }
        },
        "childPosts": [],
        "restLinks": [
          {
            "uri": "http://localhost:8080/PokeMongo/rest/post/123",
            "rel": "Self"
          },
          {
            "uri": "http://localhost:8080/PokeMongo/rest/user/35",
            "rel": "Author"
          }
        ],
        "authorId": 35,
        "authorImageURL": "/images/35.jpg",
        "postTimeAsString": "2016-10-18 14:52"
      }
    ],
    "restLinks": [
      {
        "uri": "http://localhost:8080/PokeMongo/rest/post/122",
        "rel": "Self"
      },
      {
        "uri": "http://localhost:8080/PokeMongo/rest/user/35",
        "rel": "Author"
      }
    ],
    "authorId": 35,
    "authorImageURL": "/images/35.jpg",
    "postTimeAsString": "2016-10-18 14:51"
  }
]`  

* Error Response  

   Code: 204 NO CONTENT

## User

### Get all pokémon owned by specific user

Get all pokémon owned by specific user.

* URL  

   /PokeMongo/rest/user/:id/pokemon

* Method  

   `GET`

*  URL Params

   Param is the user id.  
   Required: `id=[int]`
  
* Success Response  

   Code: 200 OK  
   Example content: `{
  "id": 36,
  "userName": "Thomas Lansing",
  "email": "thompa.lansing@gmail.com",
  "pokemons": [
    {
      "id": 49,
      "pokedexNumber": 1,
      "name": "Bulbasaur",
      "combatPower": 23,
      "healthPoints": 23,
      "lng": "11.9744",
      "lat": "57.7089",
      "timeAdded": {
        "monthValue": 11,
        "nano": 0,
        "year": 2016,
        "month": "NOVEMBER",
        "dayOfYear": 308,
        "dayOfWeek": "THURSDAY",
        "dayOfMonth": 3,
        "hour": 13,
        "minute": 41,
        "second": 46,
        "chronology": {
          "id": "ISO",
          "calendarType": "iso8601"
        }
      },
      "ownerId": 36,
      "restLinks": [
        {
          "uri": "http://localhost:8080/PokeMongo/rest/pokemon/49",
          "rel": "Self"
        }
      ]
    },
    {
      "id": 50,
      "pokedexNumber": 12,
      "name": "Butterfree",
      "combatPower": 23,
      "healthPoints": 23,
      "lng": "11.9746",
      "lat": "57.7088",
      "timeAdded": {
        "monthValue": 11,
        "nano": 0,
        "year": 2016,
        "month": "NOVEMBER",
        "dayOfYear": 308,
        "dayOfWeek": "THURSDAY",
        "dayOfMonth": 3,
        "hour": 13,
        "minute": 44,
        "second": 6,
        "chronology": {
          "id": "ISO",
          "calendarType": "iso8601"
        }
      },
      "ownerId": 36,
      "restLinks": [
        {
          "uri": "http://localhost:8080/PokeMongo/rest/pokemon/50",
          "rel": "Self"
        }
      ]
    }
  ],
  "userImageName": "default.png",
  "team": "none",
  "restLinks": [
    {
      "uri": "http://localhost:8080/PokeMongo/rest/user/36",
      "rel": "Self"
    },
    {
      "uri": "http://localhost:8080/PokeMongo/rest/user/36/pokemon",
      "rel": "Pokemon"
    }
  ]
}`  

* Error Response  

   Code: 204 NO CONTENT

### Get user

Get specific user.

* URL  

   /PokeMongo/rest/user/:id

* Method  

   `GET`

*  URL Params

   Required: `id=[int]`
  
* Success Response  

   Code: 200 OK  
   Example content: `{
  "id": 36,
  "userName": "Thomas Lansing",
  "email": "thompa.lansing@gmail.com",
  "pokemons": [
    {
      "id": 49,
      "pokedexNumber": 1,
      "name": "Bulbasaur",
      "combatPower": 23,
      "healthPoints": 23,
      "lng": "11.9744",
      "lat": "57.7089",
      "timeAdded": {
        "monthValue": 11,
        "nano": 0,
        "year": 2016,
        "month": "NOVEMBER",
        "dayOfYear": 308,
        "dayOfWeek": "THURSDAY",
        "dayOfMonth": 3,
        "hour": 13,
        "minute": 41,
        "second": 46,
        "chronology": {
          "id": "ISO",
          "calendarType": "iso8601"
        }
      },
      "ownerId": 36,
      "restLinks": [
        {
          "uri": "http://localhost:8080/PokeMongo/rest/pokemon/49",
          "rel": "Self"
        }
      ]
    },
    {
      "id": 50,
      "pokedexNumber": 12,
      "name": "Butterfree",
      "combatPower": 23,
      "healthPoints": 23,
      "lng": "11.9746",
      "lat": "57.7088",
      "timeAdded": {
        "monthValue": 11,
        "nano": 0,
        "year": 2016,
        "month": "NOVEMBER",
        "dayOfYear": 308,
        "dayOfWeek": "THURSDAY",
        "dayOfMonth": 3,
        "hour": 13,
        "minute": 44,
        "second": 6,
        "chronology": {
          "id": "ISO",
          "calendarType": "iso8601"
        }
      },
      "ownerId": 36,
      "restLinks": [
        {
          "uri": "http://localhost:8080/PokeMongo/rest/pokemon/50",
          "rel": "Self"
        }
      ]
    }
  ],
  "userImageName": "default.png",
  "team": "none",
  "restLinks": [
    {
      "uri": "http://localhost:8080/PokeMongo/rest/user/36",
      "rel": "Self"
    },
    {
      "uri": "http://localhost:8080/PokeMongo/rest/user/36/pokemon",
      "rel": "Pokemon"
    }
  ]
}`  

* Error Response  

   Code: 204 NO CONTENT