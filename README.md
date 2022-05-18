# To rebuild after updating source code
docker compose build --no-cache

# To start server
docker compose up

# To test echo server
curl --get --data-urlencode "msg=echo message" 'localhost:8888/echo'
