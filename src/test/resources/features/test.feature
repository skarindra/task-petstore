@petstore
Feature: Petstore

  @get-pet-status
  Scenario Outline: GET pets by status
    Given user sends GET request with param "/pet/findByStatus?status=<status>"
    Then list of "<status>" pets are displayed

    Examples:
    | status    |
    | available |
    | pending   |
    | sold      |

  @post-pet
  Scenario: POST new pet
    Given user sends POST request "/pet" with body:
    """
    {
      "id": 1111,
      "category": {
        "id": 121,
        "name": "animal"
      },
      "name": "brown rabbit",
      "photoUrls": [
        "https://www.crushpixel.com/stock-photo/pretty-brown-rabbit-seen-from-1950386.html"
      ],
      "tags": [
        {
          "id": 232,
          "name": "rabbit"
        }
      ],
      "status": "available"
    }
    """
    Then new pet is added with "id" equals to 1111
    And new pet is added with "name" equals to "brown rabbit"
    And new pet is added with "category.id" equals to 121
    And new pet is added with "category.name" equals to "animal"
    And new pet is added with "status" equals to "available"
    And new pet is added with "tags.id" equals to 232
    And new pet is added with "tags.name" equals to "rabbit"
    And new pet is added with "photoUrls" equals to "https://www.crushpixel.com/stock-photo/pretty-brown-rabbit-seen-from-1950386.html"

  @upload-image
  Scenario: Upload image to existing pet
    Given user sends POST request "/pet" with body:
    """
    {
      "id": 2222,
      "category": {
        "id": 212,
        "name": "animal"
      },
      "name": "cute brown rabbit",
      "tags": [
        {
          "id": 323,
          "name": "rabbit"
        }
      ],
      "status": "available"
    }
    """
    And new pet is added with "id" equals to 2222
    When user sends POST request to upload image with id 2222 and image "brown-rabbit.jpg"
    Then image "brown-rabbit.jpg" is uploaded

  @delete-pet
  Scenario: Delete an existing pet
    Given user sends POST request "/pet" with body:
    """
    {
      "id": 3333,
      "category": {
        "id": 212,
        "name": "animal"
      },
      "name": "cute black rabbit",
      "tags": [
        {
          "id": 323,
          "name": "rabbit"
        }
      ],
      "status": "available"
    }
    """
    When user sends DELETE request "/pet/3333"
    Then pet with id "3333" is deleted