Feature: top_secret_split service receive message sent to each satellite
  and the distance for process when the user request the message and the location of the source


  Scenario: the user send satellite messages and request for the message and location
    Given the user send message to satellite "kenobi" with
      | distance | message            |
      | 100.0    | este,_,_,mensaje,_ |
    And the user send message to satellite "skywalker" with
      | distance | message            |
      | 115.5 | _,es,_,_,secreto |
    And the user send message to satellite "sato" with
      | distance | message            |
      | 142.7 | este,_,un,_,_ |
    When the user request the message and location
    Then should response with message "este es un mensaje secreto" and position
      | x      | y      |
      | -57.44 | -16.16 |


  Scenario: the user send message and distance for satellite with bad name
    When the user send message to satellite "badname" with
      | distance | message            |
      | 100.0    | este,_,_,mensaje,_ |
    Then should response with error 400 and messageError
      | code      | message     | description                        |
      | QUASAR_05 | Bad Request | Error satellite name not supported |


  Scenario: the user send satellite messages but not for all satellites and request for the message and location
    Given the user send message to satellite "skywalker" with
      | distance | message            |
      | 115.5 | _,es,_,_,secreto |
    And the user send message to satellite "sato" with
      | distance | message            |
      | 142.7 | este,_,un,_,_ |
    When the user request the message and location
    Then should response with error 404 and messageError
      | code      | message   | description              |
      | QUASAR_04 | Not Found | Error not enough messages |
