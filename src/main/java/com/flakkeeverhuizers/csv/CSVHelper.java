package com.flakkeeverhuizers.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Map<String, String>> convertCSVToList(InputStream is, List<String> columns) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            List<Map<String, String>> csvRows = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Map<String, String> csvRow = new HashMap<>();
                List<String> values = getValues(csvRecord);

                if (values.size() == columns.size()) {
                    for (int i = 0; i < values.size(); i++) {
                        csvRow.put(columns.get(i), values.get(i));
                    }
                    csvRows.add(csvRow);
                } else {
                    throw new RuntimeException("The number of values is not compatible with the number of columns");
                }
            }
            return csvRows;
        } catch (Exception e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

    private static List<String> getValues(CSVRecord csvRecord) {
        String allValuesAsString = "";

        if (csvRecord.size() == 1) {
            char lastChar = csvRecord.get(0).charAt(csvRecord.get(0).length() - 1);
            allValuesAsString = lastChar != ';' ? csvRecord.get(0) + ";" : csvRecord.get(0);
        } else {
            for (int i = 0; i < csvRecord.size(); i++) {
                allValuesAsString += csvRecord.get(i);
                allValuesAsString += ".";
            }
        }

        List<String> values = new ArrayList<>();
        StringBuilder value = new StringBuilder();

        char lastChar = 0;
        for (char c : allValuesAsString.toCharArray()) {
            if (c != ';') {
                value.append(c);
            } else {
                if (lastChar == ';') {
                    values.add(null);
                } else {
                    values.add(value.toString());
                    value = new StringBuilder();
                }
            }
            lastChar = c;
        }
        return values;
    }
}
