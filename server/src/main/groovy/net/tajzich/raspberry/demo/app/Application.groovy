package net.tajzich.raspberry.demo.app

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource

@CompileStatic
@Configuration
@EnableAutoConfiguration
@ImportResource('classpath:applicationContext.xml')
@ComponentScan(basePackages = ['net.tajzich.raspberry'])
class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
