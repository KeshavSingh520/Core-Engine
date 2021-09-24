pipeline {
    agent any
    stages {
    
    stage("clean") {
            steps {
                bat "mvn clean"
            }
        }
        
        stage("test") {
            steps {
                bat "mvn test"
            }
        }
         
        
    }
    
    
     post { 
        always { 
            emailext body: 'Test', subject: 'Test', to: 'keshavsingh520@gmail.com', attachmentsPattern: "C:\\Users\\HP\\.jenkins\\workspace\\FirstPipeline\\target\\surefire-reports\\*.html"
        }
    }
}
