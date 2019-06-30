#!/bin/bash

CONTAINERS=$(docker ps | grep 6351 | awk '{print $1}')
BROKERS=$(for CONTAINER in ${CONTAINERS}; do docker port "$CONTAINER" 6351 | sed -e "s/0.0.0.0:/$HOST_IP:/g"; done)
echo "${BROKERS/$'\n'/,}"
