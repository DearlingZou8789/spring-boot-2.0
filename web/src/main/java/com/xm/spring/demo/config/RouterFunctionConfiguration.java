package com.xm.spring.demo.config;

import com.xm.spring.demo.domain.User;
import com.xm.spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * 路由器函数配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口：ServletRequest或者HttpServletRequest
     * 响应接口：ServletResponse或者HttpServletResponse
     * Spring 5.0 重新定义了服务请求和相应接口
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 即可支持Servlet规范，也可以支持自定义，比如Netty(Web Server)
     * 以本例：
     * 定义：GET请求，并且返回所有的用户对象URI:/person/find/all
     * Flux 是 0 - N 个兑现国际和
     * Mono 是 0 - 1 个对象集合
     * Reactive 中的Flux或者Mono它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux 或者 Mono 都是Publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository) {
        return  RouterFunctions.route(RequestPredicates.GET("/person/find/all"), request -> {
            //  返回所有的用户对象
            Collection<User> users = userRepository.findAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux, User.class);
        });
    }
}
