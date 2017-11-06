#!/bin/sh
MODULE_NAME=houhan-library && \
VERSION=${BUILD_NUMBER} && \
PRODUCT_NAME=${MODULE_NAME}${VERSION} && \
cd ${WORKSPACE} && \
mv target/*.jar target/app.jar && \
docker stop  ${MODULE_NAME} || true;
docker rm  ${MODULE_NAME} || true;
docker build -t  ${PRODUCT_NAME} .;
docker run --name ${MODULE_NAME} -p 8080:8765 -d ${PRODUCT_NAME};