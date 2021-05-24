def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    print('in demoCall')
    print("${env.BUILD_ID}")
    print("${config.test_var}")
}