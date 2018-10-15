#!/bin/sh

mvn clean
docker stop app
docker rm app
docker rmi dac/app
docker stop banco
docker rm banco
docker rmi dac/banco
