# MicroserviceSpringBoot

These Application is a Microservice Application 
Spring Boot Version 3.X
JVM 11

Features used is 

Eureka Naming Server
Feign 
Spring Cloud Load Balance
Spring Cloud Gateway
Zipkin
Resilience4j

Working URL


DOCKER:

IMAGES:

Product-Price-Service-V2  : tanay002/mv3-product-price-service-v2:0.0.1-SNAPSHOT

Named-Server : tanay002/mv3-naming-server-v2:0.0.1-SNAPSHOT

ProductPrice-Calculation-V2 : tanay002/mv3-product-price-calculation-service-v2:0.0.1-SNAPSHOT

API Gateway : tanay002/mv3-api-gateway-v2:0.0.1-SNAPSHOT


ZIPKIN 

http://localhost:9411/zipkin

ROUTER

http://localhost:8765/product-price/productCode/LAPH12/isAvailable/true

http://localhost:8765/productprice-calculation/productCode/LAPH12/isAvailable/true/quantity/3

http://localhost:8765/productprice-calculation-feign/productCode/LAPH12/isAvailable/true/quantity/3

http://localhost:8765/productprice-calculation-new/productCode/LAPH12/isAvailable/true/quantity/3

http://localhost:8765/get

API GATEWAY

http://localhost:8765/

http://localhost:8765/PRODUCT-PRICE/product-price/productCode/LAPH12/isAvailable/true

http://localhost:8765/PRODUCTPRICE-CALCULATION/productprice-calculation/productCode/LAPH12/isAvailable/true/quantity/3

http://localhost:8765/PRODUCTPRICE-CALCULATION/productprice-calculation-feign/productCode/LAPH12/isAvailable/true/quantity/3


Load Balancing

http://localhost:8100/productprice-calculation-feign/productCode/LAPH12/isAvailable/true/quantity/3

http://localhost:8100/productprice-calculation-feign/productCode/LAPH12/isAvailable/true/quantity/2

Eureka

http://localhost:8761/


http://localhost:8100/productprice-calculation/productCode/LAPH12/isAvailable/true/quantity/2

http://localhost:8000/product-price/productCode/LAPH12/isAvailable/true

http://localhost:8888/product-price-calculation/default

http://localhost:8888/product-price-calculation/qa

http://localhost:8888/product-price-calculation/dev


For Running all these Docker Container Image download yaml file 
and Execute Command were yaml file is located> docker-compose up 
