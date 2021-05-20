#!/usr/bin/groovy
package src.org.utils

@NonCPS
def maven_build(){
    def mvnHome = tool 'M3'
    sh "${mvnHome}/bin/mvn -B package"
}