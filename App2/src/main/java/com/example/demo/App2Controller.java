package com.example.demo;

import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.App1Service;

@RestController
public class App2Controller {

	private static final Logger LOGGER = LogManager.getLogger(App2Controller.class);

	
	
	@Autowired
	private App1Service app1Service;
	
	HashMap<String, Object> cacheMap = new HashMap<String, Object>();

	int hitCount = 0;


	@GetMapping("/App2/{ifsc}")
	public Object callApp1IfscSearch(@PathVariable String ifsc) throws URISyntaxException {
		
		if (cacheMap.get(ifsc) != null) {
			Object cachedData = null;
			try {
				cachedData = app1Service.callCache(cacheMap, ifsc);
			} catch (Exception e) {
				LOGGER.info("Cached Data not found");
			}
			return cachedData;
		} else {
			ResponseEntity<Object> App1Data = null;
			try {
				App1Data = app1Service.callApp1(cacheMap, ifsc);
			} catch (Exception e) {
				LOGGER.error("App 1 data not found. Please check the services in App1");
			}
			return App1Data;

		}
	}
}
