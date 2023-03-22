package com.jihoon.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.jihoon.board.provider.SocketProvider;

@Configuration // Socket형태를 쓰기 위한 
@EnableWebSocket 
public class WebSocketConfig implements WebSocketConfigurer{ // 클래스명에 컴파일오류 뜨면 빠른수정해서 addunimplements method인가 그거 눌러주면 아래처럼 자동으로 메서드 완성됨 // 그리고 얘는 WebSocetConfigurer라는 인터페이스를 구현해야한다고함

    @Autowired private SocketProvider socketProvider;
  
    @Override // WebSocketConfigurer 안에 들어있는 메서드를 override한거임
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
       registry.addHandler(socketProvider, "/web-socket").setAllowedOrigins("*");  // 뒤에 매개변수(addHandler 메서드에서 두번째인자값을 말함) : 어떠한 연결통로로 연결시켜줄것이냐하하는 걸 의미해서 그 어떠한 경로를 적어주면 된다고 함
    }
    
}
