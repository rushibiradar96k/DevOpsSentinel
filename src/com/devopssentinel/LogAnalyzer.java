package com.devopssentinel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer {

    int infoCount = 0;
    int warningCount = 0;
    int errorCount = 0;

    public void readLog() {

        try {

            FileReader fileReader = new FileReader("logs/application.log");
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            ArrayList<Log> logs = new ArrayList<>();

            // Read log file line by line
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" ", 2);

                Log log = new Log();

                log.level = parts[0];
                log.message = parts[1];

                logs.add(log);

                System.out.println("Level : " + log.level);
                System.out.println("Message : " + log.message);
                System.out.println("---------------------");
            }

            System.out.println("Log Size : " + logs.size());

            // Store error frequency
            HashMap<String, Integer> errorFrequency = new HashMap<>();

            // Analyze logs
            for (Log log : logs) {

                if (log.level.equals("INFO")) {

                    infoCount++;

                }
                else if (log.level.equals("WARNING")) {

                    warningCount++;

                }
                else if (log.level.equals("ERROR")) {

                    errorCount++;

                    String errorMessage = log.message;

                    if (errorFrequency.containsKey(errorMessage)) {

                        errorFrequency.put(
                            errorMessage,
                            errorFrequency.get(errorMessage) + 1
                        );

                    }
                    else {

                        errorFrequency.put(errorMessage, 1);

                    }

                }
                else {

                    System.out.println("Level Can't match");

                }
            }

            // Display log analysis
            System.out.println();
            System.out.println("====== LOG ANALYSIS ======");

            System.out.println("INFO : " + infoCount);
            System.out.println("WARNING : " + warningCount);
            System.out.println("ERROR : " + errorCount);

            int total = infoCount + warningCount + errorCount;

            System.out.println("TOTAL : " + total);

            // Display error frequency
            System.out.println();
            System.out.println("====== ERROR FREQUENCY ======");

            for (String error : errorFrequency.keySet()) {

                int count = errorFrequency.get(error);

                System.out.println(error + " : " + count);
                
                if (count >= 2) {

                    System.out.println("INCIDENT DETECTED");
                    System.out.println("Error : " + error);
                    System.out.println("Count : " + count);
                    System.out.println("---------------------");

                }

            }
            
            ArrayList<Incident> incidents = new ArrayList<>();
            
            for(String error : errorFrequency.keySet())
            {
            	int count =  errorFrequency.get(error);
            	
            	if(count >= 2)
            	{
            		Incident incidents1 = new Incident();
            		
            		incidents1.title = error + "Incident";
            		if (count >= 3) {
            		    incidents1.severity = "HIGH";
            		}
            		else {
            		    incidents1.severity = "MEDIUM";
            		}
            		incidents1.errorMessage = error;
            		incidents1.occurrenceCount = count;
            		incidents1.status = "OPEN";
            		
            		
            		incidents.add(incidents1);
            		
            	}
            }
            
            System.out.println();
            System.out.println("====== INCIDENT REPORT ======");

            for (Incident incident : incidents) {

                System.out.println("Title : " + incident.title);
                System.out.println("Severity : " + incident.severity);
                System.out.println("Error : " + incident.errorMessage);
                System.out.println("Occurrences : " + incident.occurrenceCount);
                System.out.println("Status : " + incident.status);
                System.out.println("---------------------");

            }
            
            
            

            reader.close();

        }
        catch (Exception e) {

            System.out.println("File not found. Please check file location.");

            e.printStackTrace();

        }
    }
}