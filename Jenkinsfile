pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                echo "hi"
            }
        }
        
        
        stage("email") {
        steps {
          emailext body: 'Test', subject: 'Test', to: 'keshavsingh520@gmail.com'
        }
    }
        
        
    }
}
