package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PiiController {

    private final PiiMaskingService service;
    private final ScanRecordRepository scanRecordRepository;
    private final AiPiiService aiPiiService;

    public PiiController(PiiMaskingService service,
                         ScanRecordRepository scanRecordRepository,
                         AiPiiService aiPiiService) {
        this.service              = service;
        this.scanRecordRepository = scanRecordRepository;
        this.aiPiiService         = aiPiiService;
    }

    @PostMapping("/mask")
    public MaskResponse mask(@RequestBody MaskRequest request) {

        // 1. Get AI analysis first
        String aiAnalysis = aiPiiService.analyze(request.getText());

        // 2. Save to DB with AI analysis
        ScanRecord record = service.maskAndSave(request.getText(), aiAnalysis);

        // 3. Return full response
        return new MaskResponse(
            record.getOriginalText(),
            record.getMaskedText(),
            record.getPhoneCount(),
            record.getEmailCount(),
            record.getAadhaarCount(),
            record.getPanCount(),
            record.getAiAnalysis()
        );
    }

    @GetMapping("/history")
    public List<ScanRecord> history() {
        return scanRecordRepository.findAll();
    }
}