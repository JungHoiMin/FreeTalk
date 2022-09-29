package com.jhm.freeTalkServer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
// 웹소켓 활성화
@EnableWebSocket
public class WSConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // handler와 webSocket 주소를 registry에 저장해서 해당 주소로 접근 시 웹 소켓 연결을 할 수 있도록 함
        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*");
    }
}
