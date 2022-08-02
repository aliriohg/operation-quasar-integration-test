Feature: top secret service receives messages sent to satellites
  and the distance to respond with the message and the location of the source


  Scenario: the user send request to topsecret service with messages and distances sent for the source
    When the user send request to topsecret
      | name      | distance | message            |
      | kenobi    | 100.0    | este,_,_,mensaje,_ |
      | skywalker | 115.5    | _,es,_,_,secreto   |
      | sato      | 142.7    | este,_,un,_,_      |
    Then should response with message "este es un mensaje secreto" and position
      | x      | y      |
      | -57.44 | -16.16 |


  Scenario: the user send request with not correct satellite name
    When the user send request to topsecret
      | name      | distance | message            |
      | badName   | 100.0    | este,_,_,mensaje,_ |
      | skywalker | 115.5    | _,es,_,_,secreto   |
      | sato      | 142.7    | este,_,un,_,_      |
    Then should response with error 400 and messageError
      | code      | message     | description                        |
      | QUASAR_05 | Bad Request | Error satellite name not supported |

  Scenario: the user send request with sato message corrupt
    When the user send request to topsecret
      | name      | distance | message            |
      | kenobi    | 100.0    | este,_,_,mensaje,_ |
      | skywalker | 115.5    | _,es,_,_,secreto   |
      | sato      | 142.7    | este,_             |
    Then should response with error 404 and messageError
      | code      | message   | description              |
      | QUASAR_02 | Not Found | Error in Message process |

