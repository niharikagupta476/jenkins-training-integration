import src.org.utils
def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def test_var = 'test_var_value'
    def mvn_build = new src.org.utils.maven.maven_build()
    print("body")
    print("${config.dummy_var}")
    print("config")
    print(config)
    node {
        
        stage('Checkout') {
            git 'https://github.com/cloudogu/jenkinsfiles'
        }

        stage('Build') {
           mvn_build
           print('Maven build')
           
           demoCall{
               test_var = 'test_var_value'
           }
        }
    }
}