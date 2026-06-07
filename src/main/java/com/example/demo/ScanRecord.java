package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ScanRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String originalText;

    @Column(columnDefinition = "TEXT")
    private String maskedText;

    private int phoneCount;
    private int emailCount;
    private int aadhaarCount;
    private int panCount;

    @Column(columnDefinition = "TEXT")
    private String aiAnalysis;

    private LocalDateTime scannedAt;

    public ScanRecord() {}

    public ScanRecord(String originalText, String maskedText,
                      int phoneCount, int emailCount,
                      int aadhaarCount, int panCount,
                      String aiAnalysis) {
        this.originalText = originalText;
        this.maskedText   = maskedText;
        this.phoneCount   = phoneCount;
        this.emailCount   = emailCount;
        this.aadhaarCount = aadhaarCount;
        this.panCount     = panCount;
        this.aiAnalysis   = aiAnalysis;
        this.scannedAt    = LocalDateTime.now();
    }

    public Long getId()                  { return id; }
    public String getOriginalText()      { return originalText; }
    public String getMaskedText()        { return maskedText; }
    public int getPhoneCount()           { return phoneCount; }
    public int getEmailCount()           { return emailCount; }
    public int getAadhaarCount()         { return aadhaarCount; }
    public int getPanCount()             { return panCount; }
    public String getAiAnalysis()        { return aiAnalysis; }
    public LocalDateTime getScannedAt()  { return scannedAt; }
}