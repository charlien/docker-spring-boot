# Description

Simple SpringBoot application wrapped in Docker with compose to manage its lifecycle. Each restart will recompile and deploy the new package.

## To start server
```
docker compose up
```

## To rebuild after code change
```
docker compose restart
```

## To stop serveers
```
docker compose stop
```

## To cleanup
```
docker compose down -v --rmi all
```

## To test echo server
### Test GET endpoint
```
curl --get --data-urlencode "msg=echo message" 'localhost:8888/api/echo'
```

### Test POST endpoint
```
curl -X POST localhost:8888/api/echo -d msg="testing a post message" 
```

### Test GET PATH endpoint
```
curl localhost:8888/api/echo/this_is_a_test
```
