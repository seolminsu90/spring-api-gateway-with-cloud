# Eureka server

이 프로젝트와 별개로 옵션 메모용

```java
@EnableEurekaServer // 이거면 끝난다.
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
```

기타 기본 설정

```yml
spring:
  profiles:
    active: default
  application:
    name: eureka-server

server:
  port: 8761

eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true # host기반 -> ip기반
  client:
    healthcheck:
      enabled: true # client 헬스체크
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/

```
[참조][ref]
[ref]: https://cloud.spring.io/spring-cloud-netflix/reference/html/#spring-cloud-eureka-server
