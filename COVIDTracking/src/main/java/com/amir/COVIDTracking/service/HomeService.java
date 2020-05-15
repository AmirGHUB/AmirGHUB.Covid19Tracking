package com.amir.COVIDTracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.amir.COVIDTracking.Services.CovidDataService;
import com.amir.COVIDTracking.models.LocationStats;

@Service
public class HomeService {
	
	@Autowired
	private CovidDataService covidService;
	
	public String pullData(Model model) {
		List<LocationStats> allStats = covidService.getAllstats();
		Double totalCases = allStats.stream().mapToDouble(stat -> stat.getNewTotalCases()).sum();
		Double totalEthiopiaCases = allStats.stream().filter(stat -> stat.getCountry().contains("Ethiopia")).mapToDouble(stat -> stat.getNewTotalCases()).sum();
		model.addAttribute("LocationStats", allStats);
		model.addAttribute("TotalReportedCases", totalCases);
		model.addAttribute("TotalCasesInEthiopia", totalEthiopiaCases);
		return "Home";
	}
	
	public String aboutUs(Model model) {
		return "AboutUs";
	}
	
	

}
