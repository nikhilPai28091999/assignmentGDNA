package com.example.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.example.utilities.WebServiceUtility;

public class App1Service {
	
	private static final Logger LOGGER = LogManager.getLogger(App1Service.class);

	
	@Autowired
	private WebServiceUtility webServiceUtility;
	
	HashMap<String, Integer> countOfHitsToCache = new HashMap<String, Integer>();
	HashMap<String, Integer> countOfHitsToApp1 = new HashMap<String, Integer>();



	public Object callCache(HashMap<String, Object> cacheMap, String ifsc) {
		if(cacheMap.containsKey(ifsc)) {
			System.out.println("debug");
			countOfHitsToCache.merge(ifsc, 1, Integer::sum);
		}else {
			return null;
		}
		System.out.println(countOfHitsToCache);
		return cacheMap.get(ifsc);
	}


	public ResponseEntity<Object> callApp1(HashMap<String, Object> cacheMap, String ifsc) throws URISyntaxException {
		URI url = new URI("http://localhost:8090/ifsc/searchByIFSC/" + ifsc);
		ResponseEntity<Object> RESULT_JSON = null;
		try {
			RESULT_JSON = webServiceUtility.callApp1WebService(url, HttpMethod.GET, ifsc);
		} catch (Exception e) {
			LOGGER.error("Failure in calling web service");
		}
		cacheMap.put(ifsc, RESULT_JSON);
		countOfHitsToApp1.merge(url.toString(), 1, Integer::sum);
		System.out.println("2");
		System.out.println("2+++++++++++++++++"+cacheMap);
		System.out.println(countOfHitsToApp1);
		return RESULT_JSON;
	}

}
