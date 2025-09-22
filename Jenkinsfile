pipeline {
    agent any
    options { timestamps() }
    environment {
        JAVA_HOME = tool(name: 'jdk17', type: 'jdk')
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Wrapper') {
            steps { sh './gradlew wrapper' }
        }
        stage('Build') {
            steps { sh './gradlew clean build -x test' }
        }
        stage('Test') {
            steps {
                sh './gradlew :tests:test --info -Dheadless=true -DTEST_PARALLEL_FORKS=4'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'tests/build/reports/**, tests/build/logs/**', allowEmptyArchive: true
                }
            }
        }
    }
}