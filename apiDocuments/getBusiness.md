# Get business details and reviews

-----------------------------------
This page is a part of the documentation of the
Yelp Fusion API. On this page, you will be able to find **how to call the API to get business information and reviews**.

Note that the information on this page comes from the official website of Yelp Fusion: https://docs.developer.yelp.com/docs/fusion-intro

The style of this page follows the grade.api documentation.
___________________________________
In the following two sections, the **business_id** refers to the id of that business from the Yelp Fusion API. You can read more about this in the searchBusiness.md file.

### Get Business Detail
To get detail of a business, you can make the following call:

**URL** : `https://api.yelp.com/v3/businesses/business_id`

**Method** : `GET`

**Success Response** :

```json
{
  "rating": 4,
  "price": "$$",
  "hours": [
    {
      "hours_type": "REGULAR",
      "open": [
        {
          "is_overnight": false,
          "end": "2300",
          "day": 0,
          "start": "1000"
        },
        {
          "is_overnight": false,
          "end": "2300",
          "day": 1,
          "start": "1000"
        },
        {
          "is_overnight": false,
          "end": "2300",
          "day": 2,
          "start": "1000"
        },
        {
          "is_overnight": false,
          "end": "2300",
          "day": 3,
          "start": "1000"
        },
        {
          "is_overnight": false,
          "end": "0000",
          "day": 4,
          "start": "1000"
        },
        {
          "is_overnight": false,
          "end": "0000",
          "day": 5,
          "start": "1000"
        },
        {
          "is_overnight": false,
          "end": "2300",
          "day": 6,
          "start": "1000"
        }
      ],
      "is_open_now": true
    }
  ],
  "photos": [
    "http://s3-media4.fl.yelpcdn.com/bphoto/howYvOKNPXU9A5KUahEXLA/o.jpg",
    "http://s3-media3.fl.yelpcdn.com/bphoto/I-CX8nioj3_ybAAYmhZcYg/o.jpg",
    "http://s3-media2.fl.yelpcdn.com/bphoto/uaSNfzJUiFDzMeSCwTcs-A/o.jpg"
  ],
  "id": "north-india-restaurant-san-francisco",
  "categories": [
    {
      "alias": "indpak",
      "title": "Indian"
    }
  ],
  "review_count": 551,
  "name": "North India Restaurant",
  "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco",
  "coordinates": {
    "latitude": 37.787789124691,
    "longitude": -122.399305736113
  },
  "image_url": "https://s3-media1.fl.yelpcdn.com/bphoto/howYvOKNPXU9A5KUahEXLA/o.jpg",
  "location": {
    "city": "San Francisco",
    "country": "US",
    "address2": "",
    "address3": "",
    "state": "CA",
    "address1": "123 Second St",
    "zip_code": ""
  }
}
```

### Get Business Review
This allow you to get three reviews, you can also goes into the url to see the full reviews for that business.

**URL** : `https://api.yelp.com/v3/businesses/business_id/reviews`

**Method** : `GET`

**Success Response** :

```json
{
  "reviews": [
    {
      "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco?hrid=AeVAkQgueu6JtYtU4r3Jrg",
      "text": "This place is really pretty and I really love this place. My friends and me came here yesterday. The food is superb, the service is impeccable (mostly) and...",
      "user": {
        "image_url": "",
        "name": "Hoang V."
      },
      "rating": 5
    },
    {
      "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco?hrid=6tsz9tl7HAiOcYj_fGrsCg",
      "text": "Went there for the first time Saturday Evening,everything is great, the ambiance is outstanding for this location, tried the mulliatawny soup for starters...",
      "user": {
        "image_url": "http://s3-media2.fl.yelpcdn.com/photo/O1ZuPKBhwxHAT60XZksWHQ/o.jpg",
        "name": "Winston P."
      },
      "rating": 5
    },
    {
      "url": "https://www.yelp.com/biz/north-india-restaurant-san-francisco?hrid=3b3-zDKfomV-1qR3Z0jmQw",
      "text": "I came in here for the $9.95 lunch buffet the day after it opened.  It is the old Tara space and I like how it has been opened up to accommodate many more...",
      "user": {
        "image_url": "http://s3-media1.fl.yelpcdn.com/photo/bQRonQWaxInb7eKAtMjf3A/o.jpg",
        "name": "Ronita J."
      },
      "rating": 4
    }
  ],
  "total": 3
}
```