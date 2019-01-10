pipeline {
  agent any
  stages {
    stage('') {
      steps {
        sh '''chmod +x ./gradlew
./gradlew assembleRelease'''
      }
    }
  }
  environment {
    prod = 'true'
  }
}