pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                bat "mvn test"
            }
        }
         
        
    }
    
    
     post { 
        always { 
            emailext body: 'Test', subject: 'Test', to: 'keshavsingh520@gmail.com', attachmentsPattern: '.html'
        }
    }
}
