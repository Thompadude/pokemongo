# Pokémongo REST API Documentation

## Pokemon Data

### Get all Pokémon

* URL  

   /PokeMongo/rest/pokemondata

* Method  

   `GET`
  
* Success Response  

   Code: 200 OK  
    Content: `[
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


* URL  

   /PokeMongo/rest/pokemondata/:pokedexNumber

* Method  

   `GET`

*  URL Params

   Required: `pokedexNumber=[integer]`

* Success Response  

   Code: 200 OK  
   Content: `{
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