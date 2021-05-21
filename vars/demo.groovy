
def call(body) {
    def config = [:]
    config = body()
    print('config')
    print(config)
    //def mvn_build = new src.org.utils.maven()
    node {
        
        stage('Checkout') {
            git 'https://github.com/cloudogu/jenkinsfiles'
        }

        stage('Build') {
           //mvn_build.maven_build()
           print('Maven build')
        }
    }
}