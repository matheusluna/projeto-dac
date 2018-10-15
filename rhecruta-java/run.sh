#!/bin/sh

mvn clean package
docker build -t dac/banco ./banco
docker build -t dac/app .
docker run -p 5433:5432 --name banco -d dac/banco
docker run -p 8081:8080 --name app --link banco:host-banco dac/app
