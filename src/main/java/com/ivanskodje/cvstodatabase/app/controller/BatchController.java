package com.ivanskodje.cvstodatabase.app.controller;


import com.ivanskodje.cvstodatabase.app.service.BatchRunnerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BatchController {

    private final BatchRunnerService batchRunnerService;

    @SneakyThrows
    @GetMapping("start")
    public ResponseEntity<Object> startJob() {
        return ResponseEntity.ok(batchRunnerService.startJob());
    }
}
