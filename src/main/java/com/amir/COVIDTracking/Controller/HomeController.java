package com.amir.COVIDTracking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.amir.COVIDTracking.service.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping("/")
	public String home(Model model) {
		return homeService.pullData(model);
	}
	
	@GetMapping("/aboutus")
	public String aboutUs(Model model) {
		return homeService.aboutUs(model);
	}

}
