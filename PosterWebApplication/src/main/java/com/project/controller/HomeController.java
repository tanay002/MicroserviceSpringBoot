package com.project.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ContentMessage;

@RestController
public class HomeController 
{
  // http://localhost:8080/home
	private MessageSource messageSource;
	
	public HomeController(MessageSource messageSource)
	{
		this.messageSource=messageSource;
	}
	
	@RequestMapping(method =RequestMethod.GET,path = "/home")
	public String getHomePageContent()
	{
		return "Home Page";
	}

	@GetMapping(path="/homeWithCustomContent")
	public ContentMessage getHomePageCustomContent()
	{
		return new ContentMessage("Welcome to Home Page");
	}

	@GetMapping(path="/homeWithUser/{name}")
	public String getHomePageWithUser(@PathVariable String name)
	{
		return String.format("Welcome to Home Page %s", name);
	}
	
	@GetMapping(path="/welcomeMessageInternationalization")
	public String getWelcomeMessageUsingInternationization()
	{
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default Message", locale);
	}
}
