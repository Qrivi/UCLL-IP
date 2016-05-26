---
title: Networks API Reference

toc_footers:
  - <a href='http://193.191.187.14:10228'>Project homepage</a>

search: true
---

# Introduction

Welcome to the Networks API! You can use this API to access all available Networks endpoints, which can get information on various networks, their details, passwords list or comments from our database. You can view output examples in the dark area to the right.

# Networks

## Get All Networks

> HTTP 200 OK

```json
[
  {
    "id": 2,
    "ssid": "Free Wifi Hotspot",
    "timestamp": 1463586574000,
    "location": {
      "id": 2,
      "name": "",
      "lat": 50.8780723,
      "lon": 4.699557,
      "address": "Oude Markt",
      "crossStreet": "",
      "city": "Leuven",
      "zip": 3000,
      "country": "Belgium"
    },
    "comments": [
      {
        "id": 1,
        "comment": "Very fast wifi. I recommend.",
        "timestamp": 1463604678000
      },
      ...
    ],
    "type": "OPEN"
  },
  ...
]
```

This endpoint retrieves all networks.

### HTTP Request

`GET /api/networks`

## Get All Open Networks

> HTTP 200 OK

```json
[
  {
    "id": 2,
    "ssid": "Free Wifi Hotspot",
    "timestamp": 1463586574000,
    "location": {
      "id": 2,
      "name": "",
      "lat": 50.8780723,
      "lon": 4.699557,
      "address": "Oude Markt",
      "crossStreet": "",
      "city": "Leuven",
      "zip": 3000,
      "country": "Belgium"
    },
    "comments": [
      {
        "id": 1,
        "comment": "Very fast wifi. I recommend.",
        "timestamp": 1463604678000
      },
      ...
    ],
    "type": "OPEN"
  },
  ...
]
```

This endpoint retrieves all open networks.

### HTTP Request

`GET /api/networks/open`

## Get All Protected Networks

> HTTP 200 OK

```json
[
  {
    "id": 1,
    "ssid": "Secure CoffeeSpot",
    "timestamp": 1463453147000,
    "location": {
      "id": 1,
      "name": "Coffee Black",
      "lat": 50.7491296,
      "lon": 4.8636441,
      "address": "Sentier des Corées 6",
      "crossStreet": "",
      "city": "Saint-Remy-Geest",
      "zip": 1370,
      "country": "Belgium"
    },
    "comments": [],
    "passwords": [
      {
        "id": 1,
        "password": "coolpassord",
        "upvotes": 4,
        "downvotes": 0,
        "score": 4
      },
      ...
    ],
    "type": "PROTECTED",
    "topPassword": {
      "id": 1,
      "password": "Pass1234",
      "upvotes": 44,
      "downvotes": 10,
      "score": 34
    }
  }
]
```

This endpoint retrieves all protected networks.

### HTTP Request

`GET /api/networks/protected`

## Get Network by ID

> HTTP 200 OK

```json
{
  "id": 2,
  "ssid": "Free Wifi Hotspot",
  "timestamp": 1463586574000,
  "location": {
    "id": 2,
    "name": "",
    "lat": 50.8780723,
    "lon": 4.699557,
    "address": "Oude Markt",
    "crossStreet": "",
    "city": "Leuven",
    "zip": 3000,
    "country": "Belgium"
  },
  "comments": [
    {
      "id": 1,
      "comment": "Very fast wifi. I recommend.",
      "timestamp": 1463604678000
    }
  ],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.Network}"
    }
  }
}
```

This endpoint retrieves a specific network.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`GET /api/networks/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the network to retrieve

## Get Open Network by ID

> HTTP 200 OK

```json
{
  "id": 2,
  "ssid": "Free Wifi Hotspot",
  "timestamp": 1463586574000,
  "location": {
    "id": 2,
    "name": "",
    "lat": 50.8780723,
    "lon": 4.699557,
    "address": "Oude Markt",
    "crossStreet": "",
    "city": "Leuven",
    "zip": 3000,
    "country": "Belgium"
  },
  "comments": [
    {
      "id": 1,
      "comment": "Very fast wifi. I recommend.",
      "timestamp": 1463604678000
    }
  ],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.OpenNetwork}"
    }
  }
}
```

This endpoint retrieves a specific open network.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`GET /api/networks/open/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the open network to retrieve

## Get Protected Network by ID

> HTTP 200 OK

```json
{
  "id": 1,
  "ssid": "TestNetwork",
  "timestamp": 1463453147000,
  "location": {
    "id": 1,
    "name": "Hello",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "passwords": [
    {
      "id": 1,
      "password": "coolpassord",
      "upvotes": 4,
      "downvotes": 0,
      "score": 4
    }
  ],
  "type": "PROTECTED",
  "topPassword": {
    "id": 1,
    "password": "coolpassord",
    "upvotes": 4,
    "downvotes": 0,
    "score": 4
  }
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.ProtectedNetwork}"
    }
  }
}
```

This endpoint retrieves a specific protected network.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`GET /api/networks/protected/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the protected network to retrieve

## Get Networks from City

> HTTP 200 OK

```json
[
  {
    "id": 2,
    "ssid": "Free Wifi Hotspot",
    "timestamp": 1463586574000,
    "location": {
      "id": 2,
      "name": "",
      "lat": 50.8780723,
      "lon": 4.699557,
      "address": "Oude Markt",
      "crossStreet": "",
      "city": "Leuven",
      "zip": 3000,
      "country": "Belgium"
    },
    "comments": [
      {
        "id": 1,
        "comment": "Very fast wifi. I recommend.",
        "timestamp": 1463604678000
      },
      ...
    ],
    "type": "OPEN"
  },
  ...
]
```

This endpoint retrieves networks from a specific city.

### HTTP Request

`GET /api/networks/city/{city}`

### Parameters

Parameter | Description
--------- | -----------
city | The name of the city of which to retrieve networks

<aside class="notice">
Parameter <code>city</code> is case insensitive.
</aside>

## Add Open Network

> Request  (application/json)

```json
{
    "ssid": "FieldAP",
    "location": {
        "name": "",
        "address": "Sentier des Corées 6",
        "crossStreet": "",
        "city": "Saint-Remy-Geest",
        "zip": 1370,
        "country": "Belgium",
        "lat": 50.7491296,
        "lon": 4.8636441
    }
}
```

> HTTP 200 OK

```json
{
  "id": 2,
  "ssid": "Free Wifi Hotspot",
  "timestamp": 1463586574000,
  "location": {
    "id": 2,
    "name": "",
    "lat": 50.8780723,
    "lon": 4.699557,
    "address": "Oude Markt",
    "crossStreet": "",
    "city": "Leuven",
    "zip": 3000,
    "country": "Belgium"
  },
  "comments": [
    {
      "id": 1,
      "comment": "Very fast wifi. I recommend.",
      "timestamp": 1463604678000
    }
  ],
  "type": "OPEN"
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "location": {
      "address": "An address is required",
      "city": "A city is required",
      "lon": "A longitude is required",
      "lat": "A lattitude is required",
      ... : ...
    },
    "network": {
      "ssid": "An SSID is required"
    }
  }
}
```

This endpoint adds a new open network to the database and retrieves the newly added network.

Throws an error when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/networks/open`

### Parameters

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

## Add Protected Network

> Request  (application/json)

```json
{
    "ssid": "FieldAP",
    "location": {
        "name": "",
        "address": "Sentier des Corées 6",
        "crossStreet": "",
        "city": "Saint-Remy-Geest",
        "zip": 1370,
        "country": "Belgium",
        "lat": 50.7491296,
        "lon": 4.8636441
    },
    "password": "Secret"
}
```

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP",
  "timestamp": 1463613748970,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "passwords": [
    {
      "id": 2,
      "password": "Secret",
      "upvotes": 0,
      "downvotes": 0,
      "score": 0
    }
  ],
  "type": "PROTECTED",
  "topPassword": {
    "id": 2,
    "password": "Secret",
    "upvotes": 0,
    "downvotes": 0,
    "score": 0
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "location": {
      "address": "An address is required",
      "city": "A city is required",
      "lon": "A longitude is required",
      "lat": "A lattitude is required",
      ... : ...
    },
    "network": {
      "password": "A password is required",
      "ssid": "An SSID is required"
    }
  }
}
```

This endpoint adds a new protected network to the database and retrieves the newly added network.

Throws an error when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/networks/protected`

### Parameters

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

## Add Protected Network Password

> Request  (application/json)

```json
{
    "password": "Pass2016"
}
```

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP",
  "timestamp": 1463614004335,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "passwords": [
    {
      "id": 2,
      "password": "Secret",
      "upvotes": 420,
      "downvotes": 69,
      "score": 351
    },
    {
      "id": null,
      "password": "Pass2016",
      "upvotes": 0,
      "downvotes": 0,
      "score": 0
    }
  ],
  "type": "PROTECTED",
  "topPassword": {
    "id": 2,
    "password": "Secret",
    "upvotes": 420,
    "downvotes": 69,
    "score": 351
  }
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.Network}"
    }
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "network": {
      "password": "A password is required"
    }
  }
}
```

This endpoint adds a new password to a protected network in the database and retrieves that network's details.

Throws an error when the requested network does not exist in the database or when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/networks/protected/{id}/passwords`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the protected network to add a password to

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

<aside class="warning">
The returned JSON will not list the newly added password's ID
</aside>

## Update Open Network by ID

> Request  (application/json)

```json
{
    "ssid": "FieldAP 2",
    "location": {
        "name": "",
        "address": "Sentier des Corées 6B",
        "crossStreet": "",
        "city": "Saint-Remy-Geest",
        "zip": 1370,
        "country": "Belgium",
        "lat": 50.7491296,
        "lon": 4.8636441
    }
}
```

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP 2",
  "timestamp": 1463613748970,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6B",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: null"
    }
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "location": {
      "address": "An address is required",
      "city": "A city is required",
      "lon": "A longitude is required",
      "lat": "A lattitude is required",
      ... : ...
    },
    "network": {
      "ssid": "An SSID is required"
    }
  }
}
```

This endpoint updates an existing open network in the database and retrieves the newly updated network.

Throws an error when the requested network does not exist in the database or when the JSON body does not meet the requirements.

### HTTP Request

`PUT /api/networks/open/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the open network to update

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

## Update Protected Network by ID

> Request  (application/json)

```json
{
    "ssid": "FieldAP 2",
    "location": {
        "name": "",
        "address": "Sentier des Corées 6B",
        "crossStreet": "",
        "city": "Saint-Remy-Geest",
        "zip": 1370,
        "country": "Belgium",
        "lat": 50.7491296,
        "lon": 4.8636441
    },
    "password": "Secret"
}
```

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP 2",
  "timestamp": 1463613748970,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6B",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "passwords": [
    {
      "id": 2,
      "password": "Secret",
      "upvotes": 420,
      "downvotes": 69,
      "score": 351
    }
  ],
  "type": "PROTECTED",
  "topPassword": {
    "id": 2,
    "password": "Secret",
    "upvotes": 420,
    "downvotes": 69,
    "score": 351
    }
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: null"
    }
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "location": {
      "address": "An address is required",
      "city": "A city is required",
      "lon": "A longitude is required",
      "lat": "A lattitude is required",
      ... : ...
    },
    "network": {
      "password": "A password is required",
      "ssid": "An SSID is required"
    }
  }
}
```

This endpoint updates an existing protected network in the database and retrieves the newly updated network.

Throws an error when the requested network does not exist in the database or when the JSON body does not meet the requirements.

### HTTP Request

`PUT /api/networks/protected/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the protected network to update

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

## Remove Network by ID

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP",
  "timestamp": 1463613748970,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.Network}"
    }
  }
}
```

This endpoint removes an existing network from the database and returns the removed network's details.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`DELETE /api/networks/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the network to remove

## Remove Open Network by ID

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP",
  "timestamp": 1463613748970,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.OpenNetwork}"
    }
  }
}
```

This endpoint removes an existing open network from the database and returns the removed network's details.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`DELETE /api/networks/open/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the open network to remove


## Remove Protected Network by ID

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP",
  "timestamp": 1463613748970,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [],
  "passwords": [
    {
      "id": 2,
      "password": "Secret",
      "upvotes": 420,
      "downvotes": 69,
      "score": 351
    }
  ],
  "type": "PROTECTED",
  "topPassword": {
    "id": 2,
    "password": "Secret",
    "upvotes": 420,
    "downvotes": 69,
    "score": 351
    }
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.ProtectedNetwork}"
    }
  }
}
```

This endpoint removes an existing protected network from the database and returns the removed network's details.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`DELETE /api/networks/protected/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the protected network to remove

# Votes

## Add Vote to Password

> HTTP 200 OK

```json
{
  "id": 1,
  "password": "P4s5W0rD",
  "upvotes": 3,
  "downvotes": 1,
  "score": 2
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.Password}"
    }
  }
}
```

This endpoint adds a vote to an existing password in the database and retrieves the updated password's details.

Throws an error when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/passwords/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the password to update
action | The action to perform on the password

<aside class="notice">
Parameter <code>action</code> is case insensitive.
</aside>

<aside class="success">
The only actions that will affect the password are <code>upvote</code> and <code>downvote</code>.
</aside>

# Comments

## Get Comments by Network ID

> HTTP 200 OK

```json
[
  {
    "id": 1,
    "comment": "Very fast wifi. I recommend.",
    "timestamp": 1463604678000
  },
  ...
]
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.Network}"
    }
  }
}
```

This endpoint retrieves the comments for a specific network.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`GET /api/comments/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the network of which to retrieve the comments

## Get Comments by Open Network ID

> HTTP 200 OK

```json
[
  {
    "id": 1,
    "comment": "Very fast wifi. I recommend.",
    "timestamp": 1463604678000
  },
  ...
]
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.OpenNetwork}"
    }
  }
}
```

This endpoint retrieves the comments for a specific open network.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`GET /api/comments/open/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the open network of which to retrieve the comments

## Get Comments by Protected Network ID

> HTTP 200 OK

```json
[
  {
    "id": 1,
    "comment": "Very fast wifi. I recommend.",
    "timestamp": 1463604678000
  },
  ...
]
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.ProtectedNetwork}"
    }
  }
}
```

This endpoint retrieves the comments for a specific protected network.

Throws an error when the requested network does not exist in the database.

### HTTP Request

`GET /api/comments/protected/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the protected network of which to retrieve the comments

## Add Comment to Network

> Request  (application/json)

```json
{
    "message": "Hello World"
}
```

> HTTP 200 OK

```json
{
  "id": 2,
  "ssid": "Free Wifi Hotspot",
  "timestamp": 1463586574000,
  "location": {
    "id": 2,
    "name": "",
    "lat": 50.8780723,
    "lon": 4.699557,
    "address": "Oude Markt",
    "crossStreet": "",
    "city": "Leuven",
    "zip": 3000,
    "country": "Belgium"
  },
  "comments": [
    {
      "id": null,
      "comment": "Hello World",
      "timestamp": 1463617106654
    },
    {
      "id": 1,
      "comment": "Very fast wifi. I recommend.",
      "timestamp": 1463604678000
    }
  ],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.Network}"
    }
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "network": {
      "message": "Your comment must contain at least 2 and at max 140 characters"
    }
  }
}
```

This endpoint adds a new comment to an existing network in the database and retrieves that network's details.

Throws an error when the requested network does not exist in the database or when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/comments/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the network to add a comment to

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

<aside class="warning">
The returned JSON will not list the newly added comment’s ID
</aside>

## Add Comment to Open Network

> Request  (application/json)

```json
{
    "message": "Hello World"
}
```

> HTTP 200 OK

```json
{
  "id": 2,
  "ssid": "Free Wifi Hotspot",
  "timestamp": 1463586574000,
  "location": {
    "id": 2,
    "name": "",
    "lat": 50.8780723,
    "lon": 4.699557,
    "address": "Oude Markt",
    "crossStreet": "",
    "city": "Leuven",
    "zip": 3000,
    "country": "Belgium"
  },
  "comments": [
    {
      "id": null,
      "comment": "Hello World",
      "timestamp": 1463617106654
    },
    {
      "id": 1,
      "comment": "Very fast wifi. I recommend.",
      "timestamp": 1463604678000
    }
  ],
  "type": "OPEN"
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.OpenNetwork}"
    }
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "network": {
      "message": "Your comment must contain at least 2 and at max 140 characters"
    }
  }
}
```

This endpoint adds a new comment to an existing open network in the database and retrieves that network's details.

Throws an error when the requested network does not exist in the database or when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/comments/open/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the open network to add a comment to

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

<aside class="warning">
The returned JSON will not list the newly added comment’s ID
</aside>

## Add Comment to Protected Network

> Request  (application/json)

```json
{
    "message": "Hello World"
}
```

> HTTP 200 OK

```json
{
  "id": 4,
  "ssid": "FieldAP",
  "timestamp": 1463614004000,
  "location": {
    "id": 4,
    "name": "",
    "lat": 50.7491296,
    "lon": 4.8636441,
    "address": "Sentier des Corées 6",
    "crossStreet": "",
    "city": "Saint-Remy-Geest",
    "zip": 1370,
    "country": "Belgium"
  },
  "comments": [
    {
      "id": null,
      "comment": "Hello World",
      "timestamp": 1463617678958
    },
    ...
  ],
  "passwords": [
    {
      "id": 3,
      "password": "Pass2016",
      "upvotes": 0,
      "downvotes": 0,
      "score": 0
    },
    ...
  ],
  "type": "PROTECTED",
  "topPassword": {
    "id": 3,
    "password": "Pass2016",
    "upvotes": 0,
    "downvotes": 0,
    "score": 0
  }
}
```

> HTTP 404 Not Found

```json
{
  "errors": {
    "database": {
      "404": "Not Found: {NullPointerException.ProtectedNetwork}"
    }
  }
}
```

> HTTP 400 Bad Request

```json
{
  "errors": {
    "network": {
      "message": "Your comment must contain at least 2 and at max 140 characters"
    }
  }
}
```

This endpoint adds a new comment to an existing protected network in the database and retrieves that network's details.

Throws an error when the requested network does not exist in the database or when the JSON body does not meet the requirements.

### HTTP Request

`POST /api/comments/protected/{id}`

### Parameters

Parameter | Description
--------- | -----------
id | The ID of the protected network to add a comment to

This endpoint expects a JSON request body.

<aside class="notice">
Unnecessary JSON fields are ignored.
</aside>

<aside class="warning">
The returned JSON will not list the newly added comment’s ID
</aside>
