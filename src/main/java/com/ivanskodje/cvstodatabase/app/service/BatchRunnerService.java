package com.ivanskodje.cvstodatabase.app.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchRunnerService {

    private final JobLauncher jobLauncher;
    private final Job job;

    @SneakyThrows
    public BatchStatus startJob() {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        log.info("Job execution: " + jobExecution.getStatus());

        return jobExecution.getStatus();
    }
}
