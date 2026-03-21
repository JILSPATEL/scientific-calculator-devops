**Scientific Calculator with DevOps Implementation**

Complete Project Analysis & Implementation Guide

CS 816 - Software Production Engineering

Student: Patel Jils Vinaykumar (MT2025086)

*International Institute of Information Technology Bangalore*

Table of Contents

1\. Project Overview

2\. Technologies Used

3\. Git and GitHub

4\. Jenkins CI/CD Pipeline

5\. Docker Containerization

6\. Ansible Deployment

7\. Ngrok Integration

8\. Complete Execution Flow

9\. How Components Connect

10\. Conclusion

1\. Project Overview

This project implements a Scientific Calculator application with a
complete DevOps CI/CD (Continuous Integration/Continuous Deployment)
pipeline. The application performs mathematical operations including
square root, factorial, natural logarithm, and power functions.

1.1 Purpose

The primary purpose is to demonstrate enterprise-level DevOps practices
by automating the entire software development lifecycle - from code
commit to production deployment. This ensures:

-   Automated testing and quality assurance

-   Consistent and repeatable deployments

-   Reduced manual intervention and human errors

-   Faster time-to-market for new features

-   Infrastructure as Code (IaC) principles

1.2 Application Features

The Scientific Calculator provides the following mathematical
operations:

-   **Square Root:** Calculates the square root of a given number

-   **Factorial:** Computes factorial of non-negative integers

-   **Natural Logarithm:** Calculates ln(x) for positive numbers

-   **Power Function:** Computes x\^y (x raised to power y)

2\. Technologies Used

This project leverages multiple modern DevOps tools and technologies,
each serving a specific purpose in the software delivery pipeline:

  ----------------- -----------------------------------------------------
  **Technology**    **Purpose & Description**

  **Git**           Version control system for tracking code changes,
                    managing branches, and collaboration

  **GitHub**        Remote repository hosting service, webhook trigger
                    source for automated builds

  **Jenkins**       CI/CD automation server orchestrating build, test,
                    containerization, and deployment

  **Maven**         Build automation and dependency management tool for
                    Java projects

  **JUnit**         Testing framework for writing and running unit tests
                    to ensure code quality

  **Docker**        Containerization platform packaging the application
                    with all dependencies for consistent deployment

  **Docker Hub**    Container registry for storing and distributing
                    Docker images

  **Ansible**       Configuration management and deployment automation
                    tool using YAML playbooks

  **Ngrok**         Tunneling service exposing local Jenkins to the
                    internet for GitHub webhook delivery
  ----------------- -----------------------------------------------------

3\. Git and GitHub

3.1 What is Git?

Git is a distributed version control system that tracks changes in
source code during software development. It enables multiple developers
to work together on the same project without overwriting each other\'s
work.

Key Features:

-   **Branching and Merging:** Create isolated development branches and
    merge them when ready

-   **Commit History:** Complete timeline of all changes with who made
    them and when

-   **Distributed Architecture:** Every developer has a full copy of the
    repository

-   **Collaboration:** Multiple people can work simultaneously on
    different features

3.2 What is GitHub?

GitHub is a cloud-based hosting service for Git repositories. It
provides a web interface for Git repositories and adds collaboration
features like pull requests, issues, and project management tools.

Why Use GitHub in This Project?

-   **Remote Repository:** Centralized storage accessible from anywhere

-   **Webhook Integration:** Automatically triggers Jenkins when code is
    pushed

-   **Backup and Recovery:** Code is safely stored in the cloud

-   **Collaboration Platform:** Easy code review and team collaboration

3.3 GitHub Repository Structure

The project repository (scientific-calculator-devops) contains:

-   **src/:** Java source code for the calculator application

-   **ansible/:** Deployment automation playbooks and inventory files

-   **Dockerfile:** Instructions for building the Docker container image

-   **Jenkinsfile:** Declarative pipeline definition for CI/CD workflow

-   **pom.xml:** Maven configuration with dependencies and build
    settings

3.4 GitHub Webhooks

A webhook is an automated HTTP callback that GitHub sends to a specified
URL when certain events occur in the repository.

How Webhooks Work:

1.  Developer pushes code changes to GitHub repository

2.  GitHub detects the push event

3.  GitHub sends a POST request to the configured webhook URL (Jenkins)

4.  Jenkins receives the webhook and automatically triggers the pipeline

**Webhook Configuration URL:** *https://\[ngrok-url\]/github-webhook/*

4\. Jenkins CI/CD Pipeline

4.1 What is Jenkins?

Jenkins is an open-source automation server that enables developers to
build, test, and deploy their software reliably. It\'s the orchestrator
of the entire CI/CD pipeline in this project.

CI/CD Explained:

-   **Continuous Integration (CI):** Automatically building and testing
    code every time a developer commits changes

-   **Continuous Deployment (CD):** Automatically deploying tested code
    to production environment

4.2 Why Use Jenkins?

-   **Automation:** Eliminates manual build and deployment tasks

-   **Quality Assurance:** Runs tests automatically before deployment

-   **Faster Delivery:** Code changes reach production quickly

-   **Consistency:** Same process runs every time, reducing errors

-   **Extensibility:** Supports thousands of plugins for integration

4.3 Pipeline Stages Explained

The Jenkins pipeline consists of seven sequential stages. Each stage has
a specific responsibility:

Stage 1: Checkout

**Purpose:** Retrieve the latest source code from GitHub repository

**What It Does:**

-   Connects to the GitHub repository

-   Downloads the latest code from the main branch

-   Ensures Jenkins is working with the most recent version

Stage 2: Compile

**Purpose:** Compile Java source code into bytecode

**Command:** *mvn clean compile*

**What It Does:**

-   Cleans previous build artifacts to avoid conflicts

-   Compiles all Java source files in the src/ directory

-   Checks for syntax errors and dependency issues

-   If compilation fails, the pipeline stops immediately

Stage 3: Test

**Purpose:** Run JUnit test cases to validate functionality

**Command:** *mvn test*

**Tests Performed:**

-   Square Root calculation test

-   Factorial computation test

-   Natural logarithm calculation test

-   Power function test

**Quality Gate:** All tests must pass for the pipeline to continue. Any
test failure stops the deployment.

Stage 4: Package

**Purpose:** Create executable JAR (Java Archive) file

**Command:** *mvn package -DskipTests*

**What It Does:**

-   Packages compiled classes into a JAR file

-   Includes all dependencies required to run the application

-   Skips tests (they were already run in Stage 3)

-   Output: scientific-calculator-1.0-SNAPSHOT.jar

Stage 5: Build Docker Image

**Purpose:** Create a Docker container image containing the application

**Command:** *docker build -t jilspatel/scientific-calculator:latest .*

**What It Does:**

-   Reads instructions from the Dockerfile

-   Uses Java 17 base image

-   Copies the JAR file into the container

-   Configures the container to run the application on startup

Stage 6: Push Docker Image

**Purpose:** Upload the Docker image to Docker Hub registry

**What It Does:**

-   Authenticates with Docker Hub using stored credentials

-   Pushes the built image to the remote registry

-   Makes the image available for deployment on any server

-   Tags the image as \'latest\' for easy reference

Stage 7: Deploy using Ansible

**Purpose:** Automatically deploy the application container

**Command:** *ansible-playbook -i ansible/inventory
ansible/deploy_calculator.yml*

**What It Does:**

-   Executes the Ansible deployment playbook

-   Pulls the latest Docker image from Docker Hub

-   Stops and removes any existing container

-   Starts a new container with the updated image

5\. Docker Containerization

5.1 What is Docker?

Docker is a platform for developing, shipping, and running applications
inside containers. A container is a lightweight, standalone, executable
package that includes everything needed to run the application: code,
runtime, system tools, libraries, and settings.

Why Use Docker?

-   **Consistency:** \"Works on my machine\" problems are eliminated -
    the same container runs everywhere

-   **Isolation:** Application runs in its own environment, separate
    from the host system

-   **Portability:** Deploy the same container on local machine, cloud
    servers, or anywhere

-   **Efficiency:** Containers are lightweight compared to virtual
    machines

-   **Version Control:** Images can be versioned and rolled back if
    needed

5.2 Dockerfile Breakdown

The Dockerfile contains instructions for building the Docker image.
Let\'s examine each instruction:

**1. FROM eclipse-temurin:17-jdk-jammy**

-   **Purpose:** Specifies the base image to build upon

-   *eclipse-temurin:17-jdk-jammy* provides Java 17 runtime environment

-   Built on Ubuntu 22.04 (Jammy) for stability

**2. WORKDIR /app**

-   **Purpose:** Sets the working directory inside the container

-   All subsequent commands execute in /app directory

-   Creates /app directory if it doesn\'t exist

**3. COPY target/scientific-calculator-1.0-SNAPSHOT.jar app.jar**

-   **Purpose:** Copies the compiled JAR file into the container

-   Source: JAR file from Maven target directory

-   Destination: Renamed to app.jar in /app directory

**4. ENTRYPOINT \[\"java\", \"-jar\", \"app.jar\"\]**

-   **Purpose:** Defines the command to run when container starts

-   Executes: java -jar app.jar

-   This starts the Scientific Calculator application

5.3 Docker Hub

Docker Hub is a cloud-based registry service for storing and
distributing Docker images.

Why Use Docker Hub?

-   **Central Repository:** Images are stored in one place, accessible
    from anywhere

-   **Version Management:** Different versions can be tagged (latest,
    v1.0, v2.0)

-   **Easy Deployment:** Servers can pull images directly from Docker
    Hub

-   **Automated Builds:** Integration with CI/CD pipelines

**Repository:** *jilspatel/scientific-calculator*

6\. Ansible Deployment

6.1 What is Ansible?

Ansible is an open-source automation tool for configuration management,
application deployment, and task automation. It uses simple YAML files
called \'playbooks\' to describe automation jobs.

Why Use Ansible?

-   **Agentless:** No software needs to be installed on target machines

-   **Simple Syntax:** YAML is easy to read and write

-   **Idempotent:** Running the same playbook multiple times produces
    the same result

-   **Declarative:** Describe the desired state, Ansible figures out how
    to achieve it

-   **Reusable:** Playbooks can be version-controlled and shared

6.2 Inventory File

The inventory file defines the target hosts where deployment will occur:

**\[local\]**

*localhost ansible_connection=local*

**Explanation:**

-   **\[local\]:** Group name for organizing hosts

-   **localhost:** Target host (local machine in this case)

-   **ansible_connection=local:** Execute commands locally instead of
    via SSH

6.3 Deployment Playbook

The deployment playbook (deploy_calculator.yml) performs the following
tasks:

Task 1: Pull Docker Image

-   Downloads the latest image from Docker Hub

-   Module: docker_image

-   Image: jilspatel/scientific-calculator:latest

-   Source: pull (from Docker Hub)

Task 2: Remove Old Container

-   Stops and removes any existing container

-   Module: docker_container

-   Name: calculator_container

-   State: absent (removes if exists)

-   Prevents conflicts from running containers

Task 3: Run New Container

-   Starts a new container with the latest image

-   Module: docker_container

-   Name: calculator_container

-   Image: jilspatel/scientific-calculator:latest

-   State: started

-   Restart policy: always (auto-restart if container stops)

-   Ports: Maps container port 8081 to host port 8080

7\. Ngrok Integration

7.1 What is Ngrok?

Ngrok is a tunneling service that exposes local servers to the public
internet through secure tunnels. It creates a public URL that forwards
requests to your local machine.

7.2 Why Do We Need Ngrok?

**The Problem:**

-   Jenkins runs on localhost:8080 (local machine only)

-   GitHub is on the internet

-   GitHub webhooks need a public URL to send notifications

-   Local Jenkins is not accessible from the internet

**The Solution:**

-   Ngrok creates a public tunnel to local Jenkins

-   GitHub can send webhooks to the ngrok URL

-   Ngrok forwards requests to localhost:8080

-   Jenkins receives the webhook and triggers the pipeline

7.3 How Ngrok Works

5.  **Start Ngrok:** *ngrok http 8080*

6.  Ngrok generates a public URL (e.g., https://abc123.ngrok.io)

7.  Configure this URL in GitHub webhook settings

8.  When code is pushed, GitHub sends POST request to ngrok URL

9.  Ngrok forwards the request to localhost:8080

10. Jenkins receives the webhook and starts the pipeline

**Example Ngrok URL:** *https://uncharted-stomachy-lynne.ngrok-free.dev*

**GitHub Webhook URL:**
*https://uncharted-stomachy-lynne.ngrok-free.dev/github-webhook/*

8\. Complete Execution Flow

This section provides a step-by-step walkthrough of the entire CI/CD
pipeline from code push to deployment.

8.1 Initial Setup Phase

Step 1: Developer Prepares Code

-   Developer writes Java code for the calculator

-   Creates JUnit test cases

-   Writes Dockerfile for containerization

-   Creates Jenkinsfile defining the pipeline

-   Writes Ansible playbooks for deployment

Step 2: Push Code to GitHub

-   **Command:** *git add .*

-   **Command:** *git commit -m \"message\"*

-   **Command:** *git push origin main*

-   Code is uploaded to GitHub repository

8.2 Automated Pipeline Execution

Step 3: GitHub Webhook Triggers

-   GitHub detects the push event

-   GitHub sends POST request to configured webhook URL

-   Webhook URL points to Ngrok tunnel

-   Ngrok forwards the request to Jenkins

Step 4: Jenkins Pipeline Starts

-   Jenkins receives the webhook notification

-   Jenkins reads the Jenkinsfile from the repository

-   Pipeline execution begins automatically

Step 5: Stage 1 - Checkout

-   **Action:** Jenkins clones the GitHub repository

-   **Result:** Latest code is available in Jenkins workspace

-   **Duration:** \~5-10 seconds

Step 6: Stage 2 - Compile

-   **Action:** Maven compiles Java source code

-   **Command:** *mvn clean compile*

-   **Result:** .class files generated in target/ directory

-   **Failure Condition:** Syntax errors or missing dependencies

Step 7: Stage 3 - Test

-   **Action:** JUnit runs all test cases

-   **Command:** *mvn test*

-   **Tests:** Square root, factorial, logarithm, power

-   **Result:** All tests pass ✓

-   **Failure Condition:** Any test fails - pipeline stops

Step 8: Stage 4 - Package

-   **Action:** Maven creates executable JAR file

-   **Command:** *mvn package -DskipTests*

-   **Output:** target/scientific-calculator-1.0-SNAPSHOT.jar

-   **Result:** Standalone executable ready

Step 9: Stage 5 - Build Docker Image

-   **Action:** Docker builds container image

-   **Command:** *docker build -t jilspatel/scientific-calculator:latest
    .*

-   **Process:** Reads Dockerfile, downloads Java base image, copies JAR

-   **Result:** Docker image created locally

Step 10: Stage 6 - Push Docker Image

-   **Action:** Upload image to Docker Hub

-   **Authentication:** Jenkins uses stored Docker Hub credentials

-   **Command:** *docker push jilspatel/scientific-calculator:latest*

-   **Result:** Image available on Docker Hub registry

Step 11: Stage 7 - Deploy with Ansible

-   **Action:** Ansible deploys the application

-   **Command:** *ansible-playbook -i ansible/inventory
    ansible/deploy_calculator.yml*

-   **Task 1:** Pull latest image from Docker Hub

-   **Task 2:** Remove old container if exists

-   **Task 3:** Start new container

-   **Result:** Application running in Docker container

Step 12: Pipeline Complete

-   All stages executed successfully ✓

-   Application is deployed and running

-   Users can access the calculator

-   Total time: Typically 3-5 minutes

9\. How Components Connect

Understanding how all the tools work together is crucial. This section
explains the connections and data flow between components.

9.1 Git → GitHub Connection

-   **Protocol:** HTTPS or SSH

-   **Authentication:** Personal Access Token or SSH key

-   **Data Flow:** Local Git repository → GitHub remote repository

-   **Commands:** git push, git pull, git clone

9.2 GitHub → Ngrok → Jenkins Connection

-   **Trigger:** Git push event

-   **GitHub Action:** Sends HTTP POST to webhook URL

-   **Ngrok Role:** Public tunnel → localhost:8080

-   **Jenkins Endpoint:** /github-webhook/

-   **Result:** Jenkins pipeline triggered automatically

9.3 Jenkins → GitHub Connection

-   **Purpose:** Clone repository, fetch code

-   **Authentication:** GitHub credentials stored in Jenkins

-   **Jenkins Plugin:** GitHub Integration Plugin

-   **Repository URL:**
    https://github.com/JILSPATEL/scientific-calculator-devops

9.4 Jenkins → Maven Connection

-   **Purpose:** Build automation, dependency management

-   **Configuration:** pom.xml defines project structure

-   **Jenkins Execution:** Runs mvn commands via shell

-   **Output:** Compiled classes, test results, JAR file

9.5 Jenkins → Docker Connection

-   **Purpose:** Build and manage container images

-   **Prerequisite:** Docker installed on Jenkins server

-   **Jenkins Execution:** Runs docker build, docker push commands

-   **Dockerfile Location:** Repository root directory

9.6 Jenkins → Docker Hub Connection

-   **Purpose:** Upload container images to registry

-   **Authentication:** Docker Hub credentials stored in Jenkins

-   **Credential ID:** dockerhub-cred

-   **Repository:** jilspatel/scientific-calculator

9.7 Jenkins → Ansible Connection

-   **Purpose:** Execute deployment automation

-   **Prerequisite:** Ansible installed on Jenkins server

-   **Jenkins Execution:** Runs ansible-playbook command

-   **Playbook Location:** ansible/deploy_calculator.yml

9.8 Ansible → Docker Hub Connection

-   **Purpose:** Pull container images for deployment

-   **Ansible Module:** docker_image

-   **Action:** pull image from Docker Hub

-   **Image Name:** jilspatel/scientific-calculator:latest

9.9 Ansible → Docker (Local) Connection

-   **Purpose:** Manage container lifecycle

-   **Ansible Module:** docker_container

-   **Actions:** stop, remove, start containers

-   **Target:** Local Docker daemon

10\. Conclusion

10.1 Project Summary

This project successfully demonstrates a complete DevOps CI/CD pipeline
for a Scientific Calculator application. The implementation showcases
industry-standard practices for automated software delivery, from code
commit to production deployment.

10.2 Key Achievements

-   **Full Automation:** Zero manual intervention from code push to
    deployment

-   **Quality Assurance:** Automated testing ensures code quality before
    deployment

-   **Containerization:** Application runs consistently across all
    environments

-   **Infrastructure as Code:** Deployment configuration is
    version-controlled and repeatable

-   **Fast Delivery:** Complete pipeline executes in 3-5 minutes

10.3 Technologies Mastered

-   **Version Control:** Git and GitHub for source code management

-   **CI/CD:** Jenkins pipeline automation

-   **Build Tools:** Maven for Java project management

-   **Testing:** JUnit for automated unit testing

-   **Containerization:** Docker for application packaging

-   **Registry:** Docker Hub for image distribution

-   **Configuration Management:** Ansible for deployment automation

-   **Networking:** Ngrok for webhook tunneling

10.4 Real-World Applications

The skills and practices demonstrated in this project are directly
applicable to enterprise software development:

-   Microservices architecture deployment

-   Continuous delivery of web applications

-   Cloud-native application development

-   DevOps team collaboration workflows

-   Infrastructure automation at scale

10.5 Benefits of This Approach

-   **Reduced Errors:** Automation eliminates human mistakes in
    deployment

-   **Faster Time-to-Market:** Features reach production in minutes, not
    days

-   **Improved Quality:** Automated testing catches bugs before
    deployment

-   **Better Collaboration:** Teams can work simultaneously without
    conflicts

-   **Easier Rollbacks:** Previous versions can be quickly restored if
    needed

-   **Scalability:** Same process works for small projects and large
    applications

10.6 Future Enhancements

This project can be extended with additional DevOps practices:

-   Add Kubernetes for container orchestration

-   Implement monitoring with Prometheus and Grafana

-   Add log aggregation with ELK stack

-   Integrate security scanning (SAST/DAST)

-   Deploy to cloud platforms (AWS, Azure, GCP)

-   Implement blue-green or canary deployments

-   Add performance testing automation

*This project serves as a comprehensive foundation for understanding
modern DevOps practices and can be adapted for any software development
project.*

Appendix: Quick Reference Commands

Git Commands

***git init*** - Initialize a new Git repository

***git clone \<url\>*** - Clone a remote repository

***git add .*** - Stage all changes

***git commit -m \"message\"*** - Commit staged changes

***git push origin main*** - Push commits to remote

Maven Commands

***mvn clean*** - Remove previous build artifacts

***mvn compile*** - Compile source code

***mvn test*** - Run test cases

***mvn package*** - Create JAR file

Docker Commands

***docker build -t \<name\>:\<tag\> .*** - Build Docker image

***docker images*** - List all Docker images

***docker push \<image\>:\<tag\>*** - Push image to registry

***docker run -it \<image\>:\<tag\>*** - Run container interactively

***docker ps*** - List running containers

***docker stop \<container\>*** - Stop a running container

Ansible Commands

***ansible-playbook playbook.yml*** - Execute playbook

***ansible-playbook -i inventory playbook.yml*** - Execute with specific
inventory

***ansible all -m ping*** - Test connectivity to hosts

Ngrok Commands

***ngrok http 8080*** - Create tunnel to localhost:8080

***ngrok http 8080 \--region=us*** - Create tunnel in specific region

**\-\-- End of Document \-\--**
