package com.rakib.datalakeorchestrator.controller;

import com.rakib.datalakeorchestrator.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pipelines")
@RequiredArgsConstructor
public class PipelineController {


    private final PipelineService pipelineService;

    @PostMapping("/trigger")
    public String triggerPipeline(@RequestParam String pipelineName) {
        return pipelineService.triggerPipeline(pipelineName);
    }
}