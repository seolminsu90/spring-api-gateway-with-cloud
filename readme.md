## spring cloud gateway example

공통적으로 GW에서 유저에 대한 인증을 수행한다.   
GW에서의 인증 정보는 서비스 별 사용 권한 정도를 가진다.   
이후 GW를 거쳐 요청되는 각 서비스들은 각각의 책임 권한이 필요하게 된다.   

대략의 API 흐름은 아래와 같다.   

1. 로그인단계: GW를 통하여 (/auth/api/login 등) Authorization-server로부터의 인증 수행
2. 로그인응답: 로그인 성공 시 jwt 토큰을 발급받는다. (토큰 Clames에는 하위 서비스에 대한 권한 정보를 갖는다)
3. 서비스API호출: 예시 서비스인 user service로 (/user/api/test 등) 호출 시, GW는 user 서비스를 호출할 권한을 확인
4. 서비스응답: user-service는 인증된 유저에 대하여 /api/test 권한이 있는지를 확인
5. 응답: 권한 유무에 따른 응답

#### DO

- gateway (유저 인증 기본 권한 인가)
- config (cloud config)
- eureka (각 서비스간 destination)
- service[eureka-clients] (인증 유저에 대하여 서비스 별 세부 권한 부여)

### TODO 

- gw에 공용 인증/권한
- fallback 처리
- dynamic routing (refreshScope? javaconfig?)
- file 처리
- 각각 서비스 dockerfile 생성
- docker-compose

### ETC

Global Filter: 모든 라우터에 대한 필터로서, GlobalFilter가 가장 먼저 실행됩니다.
Pre Filters: GlobalFilter 이후에, 라우팅 전에 실행되는 필터들이 적용됩니다.
Route Matching: Request를 기반으로 어떤 라우터에 매칭되는지 결정하는 필터입니다.
Route Filters: 각 라우터에 대한 필터들이 실행됩니다.
Post Filters: 라우팅 후에 실행되는 필터들이 적용됩니다.

```java
@Component
public class GlobalJwtAuthenticationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        if (route != null) {
            String routeId = route.getId();
            // routeId를 통해 현재 라우터의 정보를 사용할 수 있음
            System.out.println("Current route ID: " + routeId);
        }

        // 다음 필터 또는 라우터 실행
        return chain.filter(exchange);
    }
}
```
