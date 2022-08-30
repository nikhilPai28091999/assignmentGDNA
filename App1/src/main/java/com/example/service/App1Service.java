package com.example.service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.example.model.IFSCObject;

public interface App1Service {

	public IFSCObject getDataByIfsc(String ifsc) throws IOException;

	public Map<String, Integer> getLeaderBoard(String sortOrder, Integer fetchCount) throws IOException;

	public Object getStats(String sortOrder, TreeMap<String, Date> statisticsMap);
	
}
