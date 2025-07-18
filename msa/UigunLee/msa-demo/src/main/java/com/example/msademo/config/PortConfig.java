package com.example.msademo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

// implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
// 위 파트는 Spring Boot의 내장 웹 서버를 커스터마이징 할 때 사용합니다
// Spring Boot의 내장 웹 서버는 Tomcat(톰캣)을 사용합니다.
// 톰캣이 구돌될 때 설정하는 정보들이 있고 이 설정 정보들을 만드는 작업을 'Factory' 계열들이 담당합니다.
// 이 인터페이스를 구현하면 조정할 수 있는 요소들이 있는데 아래와 같습니다.
// Tomcat 서버의 port(포트), thread(스레드) 개수,
// connection timeout(요청 이후 일정 시간 이후 응답을 받지 못할 때) 등을 조정할 수 있습니다.

@Component
public class PortConfig implements WebServerFactoryCustomizer<TomcatReactiveWebServerFactory> {

    @Value("${server.port}")
    private String gatewayPort;

    @Override
    public void customize(TomcatReactiveWebServerFactory factory) {
        try {
            // application.yaml의 spring.server.port를 가져오는 것이 관건입니다.
            // server:
            //  port: 7890
            int port = Integer.parseInt(gatewayPort);
            factory.setPort(port);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 포트 번호입니다.");
        }
    }
}
