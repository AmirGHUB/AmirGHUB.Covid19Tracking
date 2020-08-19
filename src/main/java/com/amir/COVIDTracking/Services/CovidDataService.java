package com.amir.COVIDTracking.Services;


import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amir.COVIDTracking.models.LocationStats;

@Service("DataService")
public class CovidDataService {
	
	private List<LocationStats> allstats = new ArrayList<LocationStats>();
	
	public List<LocationStats> getAllstats() {
		return allstats;
	}

	public void setAllstats(List<LocationStats> allstats) {
		 this.allstats = allstats;
	}

	private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats> newStat = new ArrayList<LocationStats>();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(COVID_DATA_URL))
				.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvReader = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (CSVRecord record : records) {
		  LocationStats locationStats = new LocationStats();
		  locationStats.setState(record.get("Province/State"));
		  locationStats.setCountry(record.get("Country/Region"));
		   int latestCases = Integer.parseInt(record.get(record.size()-1));
		   int previousDayCases = Integer.parseInt(record.get(record.size()-2));
		  locationStats.setNewTotalCases(latestCases);
		  locationStats.setDifferenceFromPreviousDay(latestCases - previousDayCases);
		 // System.out.println(locationStats);
		  newStat.add(locationStats);
		}
		this.allstats = newStat;
  	}
}
