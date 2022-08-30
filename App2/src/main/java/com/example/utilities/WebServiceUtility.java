package com.example.utilities;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebServiceUtility {

	public ResponseEntity<Object> callApp1WebService(URI url, HttpMethod method, String ifsc) {

		ResponseEntity<Object> response = null;
		
		try {
			RestTemplate restTemplate = new RestTemplate();

			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.set("Cookie", null);
			headers.set("cache-control", "no-cache");

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			response = restTemplate.exchange(url, method, entity, Object.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
