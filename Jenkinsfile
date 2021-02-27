pipeline {
    agent none

    stages {
        stage('Build Java 8') {
            agent {
                docker {
                    image 'maven:3-openjdk-8'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building..'
                sh 'mvn clean package -Djar.finalName=MinecraftServer-${GIT_BRANCH#*/}-#${BUILD_NUMBER}'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
                }
            }
        }

        stage('Build Java 11') {
            agent {
                docker {
                    image 'maven:3-openjdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building..'
                sh 'mvn clean verify'
            }
        }
    }
}
