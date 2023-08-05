#!/bin/bash

# 가동중인 도커 중단 및 삭제
sudo docker ps -a -q --filter "name=my-car-master" | grep -q . && docker stop my-car-master && docker rm my-car-master | true

# 기존 이미지 삭제
sudo docker rmi softeerh5/my-car-master || true
