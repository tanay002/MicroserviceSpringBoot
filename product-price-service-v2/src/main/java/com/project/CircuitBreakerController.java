package com.project;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class CircuitBreakerController {

	private Logger logger = 
			LoggerFactory.getLogger(CircuitBreakerController.class);

	// If we want to check the magic of Circuit breaker then we want fire a lot of request to specific api
	//use in cmd -> watch curl http://localhost:8000/sample-api
	//watch -n 0.1 curl http://localhost:8000/sample-api   10 req per second
	
	/* 
	 
	 the circuit breaker sample API is being called up to a certain extent we
	  see that there are no method calls in here.
	  The circuit breaker is returning the response back without even calling
	   this method
	   It'll break this circuit and it'll directly return a response back.
	   
	   State of circuit breaker -> closed, open, and half open.
	   
	   Closed is when I am calling the dependent microservice continuously.So in a 
	   closed state I'll always be calling the dependent microservice. In a open state, the circuit breaker will not
       call the dependent microservice. It'll directly return the fallback response.
       And in a half open state a circuit secure breaker would be sending a percentage
        of requests to the dependent microservice, and for rest of the requests,it 
        would return the hard coded response or the fallback response back.
	     */
	
	@GetMapping("/sample-api")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	//@RateLimiter(name="default")
	/*
	 * rate limiting is all about saying in 10 seconds I want to only allow 10,000 calls
	 * to the sample API.
	 */
	
	// We can also configure how many concurrent calls are allowed.That's called bulkhead.
	
	@Bulkhead(name="sample-api")
	//10s => 10000 calls to the sample api
	public String sampleApi() {
		logger.info("Sample api call received");
	return "sample-api";
	}

	@GetMapping("/sample-api2")
//	@Retry(name="default")  // 3times retry 
	
	//for custome retry i used below name and added attempt in properties file
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String sampleApi2() {
		logger.info("Sample api call received");
			ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", 
						String.class);
			return forEntity.getBody();
	}
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}