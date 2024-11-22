package com.rakib.datalakeorchestrator.service;

import com.azure.analytics.synapse.artifacts.ArtifactsClientBuilder;
import com.azure.analytics.synapse.artifacts.models.CreateRunResponse;
import com.azure.core.credential.TokenCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PipelineService {

    @Value("${azure.synapse.workspace-url}")
    private String synapseWorkspaceUrl;

    public String triggerPipeline(String pipelineName) {
        // Authenticate with Azure
        TokenCredential credential = new DefaultAzureCredentialBuilder().build();

        // Create Artifacts Client
        var artifactsClient = new ArtifactsClientBuilder()
                .credential(credential)
                .endpoint(synapseWorkspaceUrl)
                .buildPipelineClient();

        // Trigger the pipeline run
        try {
            CreateRunResponse runResponse = artifactsClient.createPipelineRun(pipelineName);
            return "Pipeline triggered successfully. Run ID: " + runResponse.getRunId();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to trigger pipeline: " + e.getMessage();
        }
    }
}
