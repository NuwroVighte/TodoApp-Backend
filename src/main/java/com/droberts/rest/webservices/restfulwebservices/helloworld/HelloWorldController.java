package com.droberts.rest.webservices.restfulwebservices.helloworld;
//tell spring this is a controller

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//tells spring this handles requests
//crossorigin prevents CORS errors from blocking when a certain address requests info
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HelloWorldController {
	
	//GET
	//URI - /hello-world
	
	//method - "hello world"
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "hello world :)";
	}
	
	//hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello world");
	}
	
	//hello-world/path-variable/droberts
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		//throw new RuntimeException("something went wrong");
		return new HelloWorldBean(String.format("hello, %s", name));
	}
	
}
