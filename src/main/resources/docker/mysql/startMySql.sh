#!/usr/bin/env bash

docker build -t my_sql .
sleep 1
docker run --name=my_sql -p 3306:3306 my_sql