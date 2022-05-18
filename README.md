# To rebuild after updating source code
docker compose build --no-cache

# To start server
docker compose up

# To test echo server
## Test GET endpoint
curl --get --data-urlencode "msg=echo message" 'localhost:8888/api/echo'

## Test POST endpoint
curl -X POST localhost:8888/api/echo -d msg="testing a post message" 

## Test GET PATH endpoint
curl localhost:8888/api/echo/this_is_a_test