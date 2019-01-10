Feature: Search Repositories


    # If we are mocking the rest end point we can have a given step here to post the data to the mock server.
    # Query Parameter "q" :Required. The query contains one or more search keywords and qualifiers. Qualifiers allow you to limit your search to specific areas of GitHub.
    # Query parameter "sort" :Sorts the results of your query by number of stars, forks, or help-wanted-issues or how recently the items were updated. Default: best match
    #Query Parameter "order" :Determines whether the first search result returned is the highest number of matches (desc) or lowest number of matches (asc). This parameter is ignored unless you provide sort. Default: desc
  Scenario: Single key word search in Repositories with q=tetris, order stars and sort desc returns valid results and a response code of 200.
    When User makes a search call for repositories to github with the parameters below
      | q      | order | sort |
      | tetris | stars | desc |
    Then verify the item count,total count and incomplete result false in the response json

  Scenario: Multiple key word search in Repositories with q=tetris+language:assembly, order forks and sort asc returns valid results and a response code of 200.
    When User makes a search call for repositories to github with the parameters below
      | q                        | order | sort |
      | tetris+language:assembly | forks | asc  |
    Then verify the item count,total count and incomplete result false in the response json


  Scenario: Multiple key word search in Repositories with q=tetris+language:assembly, order= help-wanted-issues and sort asc returns valid results and a response code of 200.
    When User makes a search call for repositories to github with the parameters below
      | q                        | order              | sort |
      | tetris+language:assembly | help-wanted-issues | asc  |
    Then verify the item count,total count and incomplete result false in the response json


  Scenario: Multiple key word search in Repositories with q=tetris+language:assembly, order= help-wanted-issues and missing sort returns valid results and a response code of 200.
    When User makes a search call for repositories to github with the parameters below
      | q                        | order              | sort |
      | tetris+language:assembly | help-wanted-issues |      |
    Then verify the item count,total count and incomplete result false in the response json


  Scenario: Multiple key word search in Repositories with q=tetris+language:assembly, order= help-wanted-issues and  invalid sort returns valid results and a response code of 200.
    When User makes a search call for repositories to github with the parameters below
      | q                        | order              | sort    |
      | tetris+language:assembly | help-wanted-issues | invalid |
    Then verify the item count,total count and incomplete result false in the response json


  Scenario: Multiple key word search in Repositories with q=tetris+language:assembly, invalid order= help-wanted-issues-invalid and sort returns valid results and a response code of 200.
    When User makes a search call for repositories to github with the parameters below
      | q                        | order                      | sort |
      | tetris+language:assembly | help-wanted-issues-invalid | desc |
    Then verify the item count,total count and incomplete result false in the response json


  Scenario: Multiple key word search in Repositories with q=tetris+language:assembly, missing order and sort returns valid results and response code of 200
    When User makes a search call for repositories to github with the parameters below
      | q                        | order | sort |
      | tetris+language:assembly |       |      |
    Then verify the item count,total count and incomplete result false in the response json

  Scenario: Missing query parameters
    Then verify a response code of 422 is returned





