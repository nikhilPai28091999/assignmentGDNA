package com.example.demo;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.IFSCObject;
import com.example.service.App1ServiceImpl;

@RestController
@RequestMapping("/app1")
public class App1Controller {
	
	@Autowired
	App1ServiceImpl ifscServiceImpl;
	
	TreeMap<String, Date> statisticsMap = new TreeMap<String, Date>();

	
	@GetMapping("/searchByIFSC/{ifsc}")
	public ResponseEntity<IFSCObject> ifscSearch(@PathVariable String ifsc) throws IOException {
		IFSCObject ifscData = ifscServiceImpl.getDataByIfsc(ifsc)  ;
		if(ifscData == null) {
			throw new ResourceNotFoundException("Data is not available for the given ifsc code");
		}else {
			statisticsMap.put(ifsc, new Date());
			return ResponseEntity.ok(ifscData);
			
		}
		
	}
	
	
	@GetMapping(value = {"/bankLeaderBoard/","/bankLeaderBoard/{sortOrder}/{fetchCount}"})
	public Map<String, Integer> bankLeaderBoard(@PathVariable(required = false) String sortOrder, @PathVariable(required = false) Integer fetchCount) throws IOException {
		if(sortOrder == null && fetchCount == null) {
			 sortOrder = "DESC";
			 fetchCount = 10;
		}

		Map<String, Integer> leaderboardData = ifscServiceImpl.getLeaderBoard(sortOrder,fetchCount);
		if(leaderboardData == null) {
			throw new ResourceNotFoundException("Leader Board Data not found.");
		}
		return leaderboardData;
	}
	
	
	@GetMapping(value= {"/stats/","/stats/{sortOrder}"})
	public Object getStatistics(@PathVariable(required = false) String sortOrder) {
		if(sortOrder == null) {
			 sortOrder = "ASC";
		}
		
			Object statsData = ifscServiceImpl.getStats(sortOrder, statisticsMap);
		
		 
		 if(statsData == null) {
			 throw new ResourceNotFoundException("Statistics Data not found");
		 }
		 return statsData;
	}
	
	
}
