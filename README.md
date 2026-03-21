# Scientific Calculator with DevOps Implementation

**CS 816 - Software Production Engineering**  
**Student:** Patel Jils Vinaykumar (MT2025086) — IIIT Bangalore

---

## 1. Project Overview

A Scientific Calculator app with a full CI/CD pipeline, demonstrating automated build, test, containerization, and deployment. Operations supported: square root, factorial, natural logarithm, and power.

**Goals:** Automated testing, consistent deployments, reduced manual intervention, faster delivery, and Infrastructure as Code.

---

## 2. Technologies

| Tool | Purpose |
|---|---|
| Git / GitHub | Version control & webhook trigger |
| Jenkins | CI/CD orchestration |
| Maven | Build & dependency management |
| JUnit | Unit testing |
| Docker / Docker Hub | Containerization & image registry |
| Ansible | Deployment automation |
| Ngrok | Expose local Jenkins to GitHub webhooks |

---

## 3. Git & GitHub

- **Git** — distributed VCS tracking all code changes with full history.
- **GitHub** — remote repo hosting with webhook support that auto-triggers Jenkins on push.

**Repo structure:**
- `src/` — Java source code
- `ansible/` — deployment playbooks & inventory
- `Dockerfile`, `Jenkinsfile`, `pom.xml`

**Webhook flow:** Push → GitHub detects event → POST to `https://[ngrok-url]/github-webhook/` → Jenkins triggered.

---

## 4. Jenkins CI/CD Pipeline

Jenkins automates the full build-test-deploy cycle. The pipeline has 7 stages:

| # | Stage | Command | Purpose |
|---|---|---|---|
| 1 | Checkout | — | Pull latest code from GitHub |
| 2 | Compile | `mvn clean compile` | Compile Java source, catch syntax errors |
| 3 | Test | `mvn test` | Run all JUnit tests (quality gate) |
| 4 | Package | `mvn package -DskipTests` | Build JAR file |
| 5 | Build Image | `docker build -t jilspatel/scientific-calculator:latest .` | Create Docker image |
| 6 | Push Image | `docker push ...` | Upload image to Docker Hub |
| 7 | Deploy | `ansible-playbook -i ansible/inventory ansible/deploy_calculator.yml` | Deploy container via Ansible |

Any stage failure stops the pipeline immediately.

---

## 5. Docker

Containers package the app with all dependencies for consistent, portable deployment.

**Dockerfile:**
```dockerfile
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/scientific-calculator-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Images are stored on **Docker Hub** at `jilspatel/scientific-calculator`.

---

## 6. Ansible Deployment

Ansible automates deployment using YAML playbooks — no agent required on target machines.

**Inventory:**
```ini
[local]
localhost ansible_connection=local
```

**Playbook tasks (`deploy_calculator.yml`):**
1. Pull latest image from Docker Hub
2. Stop & remove existing container
3. Start new container (port 8081 → 8080, restart policy: always)

---

## 7. Ngrok

Bridges local Jenkins (`localhost:8080`) to the public internet so GitHub webhooks can reach it.

```bash
ngrok http 8080
# Generates: https://uncharted-stomachy-lynne.ngrok-free.dev
# Webhook URL: https://uncharted-stomachy-lynne.ngrok-free.dev/github-webhook/
```

---

## 8. Complete Flow

```
Developer pushes code
    → GitHub detects push → sends webhook to Ngrok URL
    → Ngrok forwards to Jenkins (localhost:8080)
    → Jenkins runs pipeline:
        Checkout → Compile → Test → Package
        → Build Docker Image → Push to Docker Hub
        → Ansible pulls image & deploys container
    → App live in ~3–5 minutes ✓
```

---

## 9. Conclusion

**Achieved:** Full automation from commit to deployment, automated quality gates, consistent containerized deployments, IaC via Ansible, ~3–5 min pipeline execution.

**Future enhancements:** Kubernetes orchestration, Prometheus/Grafana monitoring, ELK logging, SAST/DAST security scanning, cloud deployment (AWS/Azure/GCP), blue-green deployments.

---

## Appendix: Quick Reference

**Git**
```bash
git add . && git commit -m "msg" && git push origin main
```

**Maven**
```bash
mvn clean compile   # compile
mvn test            # run tests
mvn package         # build JAR
```

**Docker**
```bash
docker build -t <name>:<tag> .
docker push <image>:<tag>
docker ps / docker stop <container>
```

**Ansible**
```bash
ansible-playbook -i inventory playbook.yml
ansible all -m ping
```

**Ngrok**
```bash
ngrok http 8080
```
