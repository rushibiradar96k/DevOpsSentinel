
package com.devopssentinel;

import java.util.ArrayList;
import java.util.HashMap;

public class IncidentDetector {

    public ArrayList<Incident> detectIncidents(HashMap<String, Integer> errorFrequency) 
    {

    	IncidentDetector detector = new IncidentDetector();

    	ArrayList<Incident> incidents = detector.detectIncidents(errorFrequency);	

        for (String error : errorFrequency.keySet()) {

            int count = errorFrequency.get(error);

            if (count >= 2) {

                Incident incident = new Incident();

                incident.title = error + " Incident";

                if (count >= 3) {
                    incident.severity = "HIGH";
                } else {
                    incident.severity = "MEDIUM";
                }

                incident.errorMessage = error;
                incident.occurrenceCount = count;
                incident.status = "OPEN";

                incidents.add(incident);
            }
        }

        return incidents;
    }
}
