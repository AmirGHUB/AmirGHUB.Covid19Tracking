package com.amir.COVIDTracking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.amir.COVIDTracking.Services.CovidDataService;
import com.amir.COVIDTracking.models.LocationStats;

@Controller
public class HomeController {
	
	@Autowired
	private CovidDataService covidService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = covidService.getAllstats();
		Double totalCases = allStats.stream().mapToDouble(stat -> stat.getNewTotalCases()).sum();
		Double totalEthiopiaCases = allStats.stream().filter(stat -> stat.getCountry().contains("Ethiopia")).mapToDouble(stat -> stat.getNewTotalCases()).sum();
		model.addAttribute("LocationStats", allStats);
		model.addAttribute("TotalReportedCases", totalCases);
		model.addAttribute("TotalCasesInEthiopia", totalEthiopiaCases);
		return "Home";
	}

}
