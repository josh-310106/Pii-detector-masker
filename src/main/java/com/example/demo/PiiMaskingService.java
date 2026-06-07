package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.regex.*;

@Service
public class PiiMaskingService {

    private final ScanRecordRepository scanRecordRepository;

    public PiiMaskingService(ScanRecordRepository scanRecordRepository) {
        this.scanRecordRepository = scanRecordRepository;
    }

    public ScanRecord maskAndSave(String original, String aiAnalysis) {

        int phoneCount   = countMatches(original, "\\d{10}");
        int aadhaarCount = countMatches(original, "\\b\\d{4}\\s\\d{4}\\s\\d{4}\\b");
        int panCount     = countMatches(original, "\\b[A-Z]{5}[0-9]{4}[A-Z]\\b");
        int emailCount   = countMatches(original, "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");

        String masked = original;
        masked = masked.replaceAll("\\d{10}", "XXXXXXXXXX");
        masked = masked.replaceAll("\\b\\d{4}\\s\\d{4}\\s\\d{4}\\b", "XXXX XXXX XXXX");
        masked = masked.replaceAll("\\b[A-Z]{5}[0-9]{4}[A-Z]\\b", "XXXXXXXXXX");
        masked = masked.replaceAll("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+", "masked@email.com");

        ScanRecord record = new ScanRecord(
            original, masked,
            phoneCount, emailCount,
            aadhaarCount, panCount,
            aiAnalysis
        );

        return scanRecordRepository.save(record);
    }

    private int countMatches(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }
}