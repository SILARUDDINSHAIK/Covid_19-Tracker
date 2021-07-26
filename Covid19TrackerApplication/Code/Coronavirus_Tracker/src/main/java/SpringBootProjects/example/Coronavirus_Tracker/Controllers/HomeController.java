package SpringBootProjects.example.Coronavirus_Tracker.Controllers;

import SpringBootProjects.example.Coronavirus_Tracker.models.locationstats;
import SpringBootProjects.example.Coronavirus_Tracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.StringReader;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronavirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<locationstats> allStats = coronaVirusDataService.getAllstats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getNewlyRecordedCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
