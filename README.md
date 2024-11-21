
# **DataLake Orchestrator**

## **Overview**
**DataLake Orchestrator** is a Spring Boot application designed to automate the ingestion and processing of data from REST APIs and admin-uploaded CSV files into Azure Data Lake. The application supports incremental data pipelines, processes large volumes of data (~5GB/day), and integrates with Azure Data Factory for data orchestration.

---

## **Features**
- Fetch data incrementally from REST APIs.
- Allow admin users to upload CSV files via an API.
- Store raw data in Azure Data Lake.
- Trigger Azure Data Factory pipelines for data processing.
- Monitor and log pipeline execution status.
