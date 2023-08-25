package com.project.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController 
{
    @GetMapping("/filtering")
	public Person filtering()
	{
		return new Person("Tanay", "Saxena", "Tanay002", "Tanay@123", "tanay@yopmail.com", 8888999789L);
	}
    
    @GetMapping("/filtering-all")
   	public List<Person> filteringAll()
   	{
   		return Arrays.asList(
   				new Person("Tanay", "Saxena", "Tanay002", "Tanay@123", "tanay@yopmail.com", 8888999789L),
   				new Person("Kunal", "Sharma", "Kunal002", "Kunal@986", "kunal@yopmail.com", 8785657876L),
   				new Person("Shubham", "Gupta", "Shubham002", "Shubham@@23", "shubham@yopmail.com", 4599897876L));
   	}
    
    @GetMapping("/dynamicFiltering")
    public MappingJacksonValue dynamicFiltering()
    { 
    	Employee emp=new Employee("Tanay", "Saxena", "Tanay002", "Tanay@123", "tanay@yopmail.com", 8888999789L);;
    	MappingJacksonValue jacksonValue=new MappingJacksonValue(emp);

    	SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
    			.filterOutAllExcept("firstName","lastName","mobNo","email");

    	SimpleFilterProvider filterProvider= new SimpleFilterProvider().addFilter("EmployeeFilter",filter);
    	jacksonValue.setFilters(filterProvider);
    	return jacksonValue;

    }
    
    @GetMapping("/dynamicFiltering-allData")
    public MappingJacksonValue dynamicFilteringAll()
    { 
    	List<Employee> empList=Arrays.asList(
   				new Employee("Tanay", "Saxena", "Tanay002", "Tanay@123", "tanay@yopmail.com", 8888999789L),
   				new Employee("Kunal", "Sharma", "Kunal002", "Kunal@986", "kunal@yopmail.com", 8785657876L),
   				new Employee("Shubham", "Gupta", "Shubham002", "Shubham@@23", "shubham@yopmail.com", 4599897876L));
    	
    	MappingJacksonValue jacksonValue=new MappingJacksonValue(empList);

    	SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
    			.filterOutAllExcept("firstName","lastName","mobNo","email","username");
    	SimpleFilterProvider filterProvider= new SimpleFilterProvider().addFilter("EmployeeFilter",filter);
    	jacksonValue.setFilters(filterProvider);
    	return jacksonValue;

    }
}
