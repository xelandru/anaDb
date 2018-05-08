#!/usr/bin/env bash

docker build -t my_derby .
sleep 1
docker run --name my_derby -d -p 1527:1527 my_derby