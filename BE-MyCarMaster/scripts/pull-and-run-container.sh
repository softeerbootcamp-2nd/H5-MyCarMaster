#!/bin/bash

# 도커허브 이미지 pull
sudo docker pull softeerh5/my-car-master

# 도커 run
sudo docker run -d -p 8080:8080 -p 9292:9292 \
    --name my-car-master \
    -v /home/ubuntu/logs/:/logs/ \
    softeerh5/my-car-master:latest


sudo docker run -d -p 9080:9080 \
    --name promtail \
    -v /home/ubuntu/promtail-config.yml:/etc/promtail/promtail-config.yml \
    -v /home/ubuntu/logs/:/logs/ \
    grafana/promtail:latest -config.file=/etc/promtail/promtail-config.yml

# 사용하지 않는 불필요한 이미지 삭제 -> 현재 컨테이너가 물고 있는 이미지는 삭제 안됨
sudo docker rmi -f $(docker images -f "dangling=true" -q) || true
