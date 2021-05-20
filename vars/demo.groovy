def call(body) {
    def config = [:]
    config = body
    print(config)
    node {
    def mvnHome = tool 'M3'

    stage('Checkout') {
        git 'https://github.com/cloudogu/jenkinsfiles'
    }

    stage('Build') {
        sh "${mvnHome}/bin/mvn -B package"
    }
}
}