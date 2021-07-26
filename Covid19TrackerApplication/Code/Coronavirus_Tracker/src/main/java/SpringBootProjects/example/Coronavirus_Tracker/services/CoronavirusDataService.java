package SpringBootProjects.example.Coronavirus_Tracker.services;

import SpringBootProjects.example.Coronavirus_Tracker.models.locationstats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {
    private List<locationstats> allstats = new ArrayList<>();
    private static String covid_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<locationstats> getAllstats() {
        return allstats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchvirus_data() throws IOException, InterruptedException {
        List<locationstats> newstats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(covid_data_url)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(httpResponse.body());
        StringReader csvbody = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbody);


        for (CSVRecord record : records) {
            locationstats stats = new locationstats();
            stats.setState(record.get("Province/State"));
            stats.setCountry(record.get("Country/Region"));
            int latestcasescount = Integer.parseInt(record.get(record.size() - 1));
            stats.setNewlyRecordedCases(Integer.parseInt(record.get(record.size() - 1)));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            stats.setDiffFromPrevDay(latestcasescount - prevDayCases);
            //System.out.println(stats);
            newstats.add(stats);
        }
        this.allstats = newstats;
    }
}
