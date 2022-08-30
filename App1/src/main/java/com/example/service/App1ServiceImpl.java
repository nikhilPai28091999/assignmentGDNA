package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.IFSCObject;

@Service
public class App1ServiceImpl implements App1Service {

	private static final Logger LOGGER = LogManager.getLogger(App1ServiceImpl.class);

	@Autowired
	LoadMaps loadMaps;

	@Override
	public IFSCObject getDataByIfsc(String ifsc) throws IOException {
		Map<String, IFSCObject> data;

		try {
			data = loadMaps.populateMapWithAllExcelData();
		} catch (Exception e) {
			LOGGER.error("Data is empty");
			return null;
		}
		return data.get(ifsc);
	}

	@Override
	public Map<String, Integer> getLeaderBoard(String sortOrder, Integer fetchCount) throws IOException {
		Map<String, Integer> countMap = null;
		Map<String, Integer> filteredCountMap = null;
		ArrayList<Integer> list = new ArrayList<>();
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		
		try {
			countMap = loadMaps.populateMapWithCount();
			filteredCountMap = countMap.entrySet().stream()
				    .limit(fetchCount)
				    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
			
		} catch (Exception e) {
			LOGGER.error("Count Map Data not populated.");
			return null;
		}
		

		
		for (Entry<String, Integer> entry : filteredCountMap.entrySet()) {
			list.add(entry.getValue());
		}

		if (sortOrder.equals("ASC")) {
			Collections.sort(list, new Comparator<Integer>() {

				public int compare(Integer o1, Integer o2) {
					return (o1).compareTo(o2);
				}
			});

		} else {
			Collections.sort(list, Collections.reverseOrder());
		}
		for (Integer a : list) {
			for (Entry<String, Integer> entry : filteredCountMap.entrySet()) {
				if (entry.getValue().equals(a)) {
					sortedMap.put(entry.getKey(), a);
				}
			}
		}

		return sortedMap;
	}

	@Override
	public Object getStats(String sortOrder, TreeMap<String, Date> statisticsMap) {
		Map<String, Date> newStatisticsMap = null;

		// Sort done based on IFSC code
		if (sortOrder.equals("ASC")) {
			return statisticsMap;
		} else {
			try {
				newStatisticsMap = statisticsMap.descendingMap();
			} catch (Exception e) {
				LOGGER.error("Statistics Map Data not found");
				return null;
			}
			return newStatisticsMap;

		}
	}

}
