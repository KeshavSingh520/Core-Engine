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
           mail bcc: '', body: 'This is pipeline report.', cc: '', from: '', replyTo: '', subject: 'Test', to: 'keshavsingh520@gmail.com'
        }
    }
        
        
    }
}
