package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Gatewayservice;

@RestController
@RequestMapping
public class GatewayController {
	Gatewayservice GS;
	public GatewayController(Gatewayservice GS) {
		this.GS=GS;
	}
	@GetMapping("{service}/{endpoint}")
	public Object getService(@PathVariable String service,@PathVariable String endpoint) {
		return GS.invokeService(service, endpoint);
	}
	

}
