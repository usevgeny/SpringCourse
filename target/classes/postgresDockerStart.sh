#!/bin/bash

docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=springLearning -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql postgres