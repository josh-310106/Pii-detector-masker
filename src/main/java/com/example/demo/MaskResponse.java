package com.example.demo;

public class MaskResponse {

    private String originalText;
    private String maskedText;
    private int phoneCount;
    private int emailCount;
    private int aadhaarCount;
    private int panCount;
    private String aiAnalysis;

    public MaskResponse(
            String originalText,
            String maskedText,
            int phoneCount,
            int emailCount,
            int aadhaarCount,
            int panCount,
            String aiAnalysis) {

        this.originalText = originalText;
        this.maskedText   = maskedText;
        this.phoneCount   = phoneCount;
        this.emailCount   = emailCount;
        this.aadhaarCount = aadhaarCount;
        this.panCount     = panCount;
        this.aiAnalysis   = aiAnalysis;
    }

    public String getOriginalText()  { return originalText; }
    public String getMaskedText()    { return maskedText; }
    public int getPhoneCount()       { return phoneCount; }
    public int getEmailCount()       { return emailCount; }
    public int getAadhaarCount()     { return aadhaarCount; }
    public int getPanCount()         { return panCount; }
    public String getAiAnalysis()    { return aiAnalysis; }
}