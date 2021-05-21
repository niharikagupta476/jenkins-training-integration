def call(body=[:]) {
    def config = [:]
    config = body
    print('in demoCall')
    print("${env.BUILD_ID}")
}