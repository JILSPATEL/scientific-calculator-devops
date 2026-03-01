pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3'
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
