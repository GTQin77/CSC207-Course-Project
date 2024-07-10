# Search Business using Keywords & Location

-----------------------------------
This page is a part of the documentation of the
Yelp Fusion API. On this page, you will be able to find **how to call the API to search business using keywords and user's location**.

Note that the information on this page comes from the official website of Yelp Fusion: https://docs.developer.yelp.com/docs/fusion-intro

The style of this page follows the grade.api documentation.
___________________________________

To search for restaurant with users' location, you will need the **delis** keyword, see below for an example from Yelp Fusion:

**URL** : `https://api.yelp.com/v3/businesses/search?term=delis&latitude=37.786882&longitude=-122.399972`

**Method** : `GET`

**Success Response** :

```json
{
  "total": 1316,
  "businesses": [
    {
      "rating": 4.5,
      "price": "$$",
      "phone": "+14154212337",
      "id": "molinari-delicatessen-san-francisco",
      "categories": [
        {
          "alias": "delis",
          "title": "Delis"
        }
      ],
      "review_count": 910,
      "name": "Molinari Delicatessen",
      "url": "https://www.yelp.com/biz/molinari-delicatessen-san-francisco",
      "coordinates": {
        "latitude": 37.7983818054199,
        "longitude": -122.407821655273
      },
      "image_url": "http://s3-media4.fl.yelpcdn.com/bphoto/6He-NlZrAv2mDV-yg6jW3g/o.jpg",
      "location": {
        "city": "San Francisco",
        "country": "US",
        "address2": "",
        "address3": "",
        "state": "CA",
        "address1": "373 Columbus Ave",
        "zip_code": "94133"
      }
    },
    "..."
  ]
}
```

