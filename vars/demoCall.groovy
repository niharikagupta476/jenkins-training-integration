def call(body) {
    def config = [:]
    config = body
    print('in demoCall')
    print(config.test_var)
}