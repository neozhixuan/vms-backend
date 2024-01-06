pipeline {
    agent any

    tools {
        maven 'maven_3_8_4'
    }

    stages {
        stage('Build Maven') {
            steps {
                script {
                    def isWindows = isUnix() ? false : true

                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/neozhixuan/vms-backend']])

                    if (isWindows) {
                        bat 'mvn clean install'
                    } else {
                        sh 'mvn clean install'
                    }
                }
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    def isWindows = isUnix() ? false : true

                    if (isWindows) {
                        bat 'docker build -t neozhixuan/vms-backend .'
                    } else {
                        sh 'docker build -t neozhixuan/vms-backend .'
                    }
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    def isWindows = isUnix() ? false : true
        
                    if (isWindows) {
                        withCredentials([string(credentialsId: 'dockerhub-pwd2', variable: 'dockerhubpwd2')]) {
                            bat 'docker login --username neozhixuan --password "%dockerhubpwd2%"'
                            bat 'docker push neozhixuan/vms-backend'
                        }
                    } else {
                        withCredentials([string(credentialsId: 'dockerhub-pwd2', variable: 'dockerhubpwd2')]) {
                            sh 'docker login -u neozhixuan -p "$dockerhubpwd2"'
                            sh 'docker push neozhixuan/vms-backend'
                        }
                    }
                }
            }
        }
        stage('Configure AWS and Deploy to EKS') {
            steps {
                script{
    
                    def isWindows = isUnix() ? false : true
            
                    if (isWindows) {
                        withCredentials([aws(credentialsId: 'zhixuan-aws-creds', accessKeyVariable: 'AWS_ACCESS_KEY_ID', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                            bat """
                                aws eks update-kubeconfig --name ascode-cluster --region us-east-1
                                kubectl get ns
                                kubectl apply -f deployment.yaml
                            """
                        }
                    }else{
                        withCredentials([aws(credentialsId: 'zhixuan-aws-creds', accessKeyVariable: 'AWS_ACCESS_KEY_ID', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                            sh ('aws eks update-kubeconfig --name kubename --region us-east-1')
                            sh "kubectl get ns"
                            sh "kubectl apply -f deployment.yaml"
                        }
                    }
                }
            }
        }
    }
}
