
def call(body) {
    def config = [:]
    config = body
    print(config)
    def mvn_build = new src.org.utils.maven.maven_build()
    node {
        
        stage('Checkout') {
            git 'https://github.com/cloudogu/jenkinsfiles'
        }

        stage('Build') {
            mvn_build
        }
    }
}