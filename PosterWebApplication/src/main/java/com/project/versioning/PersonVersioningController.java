package com.project.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController 
{

	@GetMapping("/v1/person")
	public PersonV1 getPersonV1Name()
	{
		return new PersonV1("Tanay Saxena");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2Name()
	{
		return new PersonV2(new Name("Tanay", "Saxena"));
	}
	
	// Request Parameter
	@GetMapping(path="/person",params="version=v1")
	public PersonV1 getPersonV1NameRequestParameter()
	{
		return new PersonV1("Tanay Saxena");
	}
	
	@GetMapping(path="/person",params="version=v2")
	public PersonV2 getPersonV2NameRequestParameter()
	{
		return new PersonV2(new Name("Tanay", "Saxena"));
	}
	
	// Header Request
	@GetMapping(path="/person/header",headers="X-API-VERSION=1")
	public PersonV1 getPersonV1NameHeaderRequest()
	{
		return new PersonV1("Tanay Saxena");
	}
	
	@GetMapping(path="/person/header",headers="X-API-VERSION=2")
	public PersonV2 getPersonV2NameHeaderRequest()
	{
		return new PersonV2(new Name("Tanay", "Saxena"));
	}
	
	//Accept Header
	
	@GetMapping(path="/person/accept",produces ="application/vnd.company.app-v1+json")
	public PersonV1 getPersonFirstVersionAcceptHeader()
	{
		return new PersonV1("Tanay Saxena");
	}
	
	@GetMapping(path="/person/accept",produces ="application/vnd.company.app-v2+json")
	public PersonV2 getPersonSecondVersionAcceptHeader()
	{
		return new PersonV2(new Name("Tanay", "Saxena"));
	}
	
}
