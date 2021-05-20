node ('worker_node1') {
     stage('Source') {
         // Get some code from our Git repository
         git 'http://github.com/brentlaster/roarv2'
      }
}

// Scripted Pipeline
node ('worker_node1') {
  // get code from our Git repository
  git 'http://github.com/brentlaster/roarv2'
   // get Gradle HOME value
  def gradleHome = tool 'gradle4'
   // run Gradle to execute compile and unit testing
  sh "'${gradleHome}/bin/gradle' clean compileJava test"
}

node {
    def mvnHome = tool 'M3'

    stage('Checkout') {
        git 'https://github.com/cloudogu/jenkinsfiles'
    }

    stage('Build') {
        sh "${mvnHome}/bin/mvn -B package"
    }
}
node {
    checkout scm
    /*
     * In order to communicate with the MySQL server, this Pipeline explicitly
     * maps the port (`3306`) to a known port on the host machine.
     */
    docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw" -p 3306:3306') { c ->
        /* Wait until mysql service is up */
        sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
        /* Run some tests which require MySQL */
        sh 'make check'
    }
}
//With Docker pipelines
node {
    checkout scm
    def dockerfile = 'Dockerfile.test'
    def customImage = docker.build("my-image:${env.BUILD_ID}", "-f ${dockerfile} ./dockerfiles") 
}
node {
    checkout scm

    docker.withRegistry('https://registry.example.com') {

        docker.image('my-custom-image').inside {
            sh 'make test'
        }
    }
}
node {
    checkout scm

    docker.withRegistry('https://registry.example.com', 'credentials-id') {

        def customImage = docker.build("my-image:${env.BUILD_ID}")

        /* Push the container to the custom Registry */
        customImage.push()
    }
}
//Extending with Shared Libraries 