pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                echo "hi"
            }
        }
        
        
        post {
        always {
            emailtext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
        }
    }
        
        
    }
}
