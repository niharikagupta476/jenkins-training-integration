
def call(body = [:]) {
    def config = [:]
    config = body

    def test_var = 'test_var_value'
    //def mvn_build = new src.org.utils.maven()
    print("body",body)
    print("config",config)
    node {
        
        stage('Checkout') {
            git 'https://github.com/cloudogu/jenkinsfiles'
        }

        stage('Build') {
           //mvn_build.maven_build()
           print('Maven build')
           
           demoCall{
               test_var = 'test_var_value'
           }
        }
    }
}