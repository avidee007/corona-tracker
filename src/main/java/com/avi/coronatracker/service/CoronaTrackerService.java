package com.avi.coronatracker.service;

import com.avi.coronatracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaTrackerService {

    private static final String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @Autowired
    private RestTemplate restTemplate;

    private List<LocationStats> stats = new ArrayList<>();

    @PostConstruct
    public void fetchCaseData() throws IOException {
        List<LocationStats> locationStats = new ArrayList<>();
        String data = restTemplate.getForObject(DATA_URL, String.class);
        assert data != null;
        StringReader in = new StringReader(data);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            LocationStats stats = new LocationStats();
            stats.setCountry(record.get("Country/Region"));
            stats.setState(record.get("Province/State"));
            stats.setTotalCase(Integer.parseInt(record.get(record.size() - 1)));
            locationStats.add(stats);
        }
        this.stats = locationStats;
    }

    public List<LocationStats> getStats() {
        return stats;
    }
}
