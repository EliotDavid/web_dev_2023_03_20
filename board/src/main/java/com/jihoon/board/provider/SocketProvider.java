package com.jihoon.board.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class SocketGroup {
    private String room;
    private WebSocketSession webSocketSession;


}


// ** 아래는 일반적으로 연결할 때의 순서대로 적어봤다고함
@Component
public class SocketProvider extends TextWebSocketHandler{ // SocketProvider를 정확히는 Handler라고 부르기도 한다고 함 // 그리고 이 provider를 extends로 특정한 클래스를 상속받게 해야함(TextWebSocketHandler)
    
   private List<SocketGroup> sessionList = new ArrayList<>();


    // 2. 또 afterConnectionClosed 메서드도 써줌
    // 왜 2번이 1번보다 위에 있냐고? 강사도 그렇게 씀
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession){ // Established : 뭔가를 시행하다 출판하다 의미 // 연결됬을 적에 하는 작업
        System.out.println("Socket Connected!!");
        //System.out.println(webSocketSession.toString()); // 일단 toString으로 찍어줌
        //System.out.println(webSocketSession.getHandshakeHeaders().toString());
        System.out.println(webSocketSession.getHandshakeHeaders().getFirst("room"));
     
        //sessionList.add(webSocketSession);  // 로그인한 당사자의 정보를 session에 저장해주고
        String room = webSocketSession.getHandshakeHeaders().getFirst("room"); 
        sessionList.add(new SocketGroup(room, webSocketSession));
    }

    // 1. 여기에 handleTextMessage라는 메서드를 오버라이드해서 작성을 해줄거임
    @Override // 클라이언트가 서버로 연결을 해놓은상태에서 텍스트형 메세지를 보내면 텍스트메세지 객체형태로 받은 다음에 메세지 페이로드를 통해서 텍스트 객체를 문자화해서 뭐가 들었는지 볼 수 있다고함 
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws Exception{ //WebSocketSession 타입에 sebSocketSession이라는 이름의 매개변수랑 TextMessage타입의 textMessage이름의 매개변수를 받아와줌
        String messagePayload = textMessage.getPayload(); // textMessage에 있는 Payload()메서드를 불러옴
        System.out.println(messagePayload.toString()); // 출력을 한 번 찍어봄

        String room = webSocketSession.getHandshakeHeaders().getFirst("room");
        for(SocketGroup socketGroup : sessionList) {
            if(socketGroup.getRoom().equals(room)) 
            socketGroup.getWebSocketSession().sendMessage(textMessage);
        }

        //for(WebSocketSession session : sessionList) {
            //session.sendMessage(textMessage); // 빨간줄이 뜨는건 IoException인데 IOException은 필수로 받와야함 
        //}
    }

    // Connection이 끊어졌을 떄 어떤작업을 해줄건지 그거를 여기에 작성한거라고 함 (무슨말이지?)
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus){ // closed : 닫다  // 추가적으로 CloseStatus라는게 있는게 이 타입에 closeStatus라는 이름의 매겨변수도 같이 받아옴
        // 그 다음에 여기서 아래처럼 적어줌
        System.out.println("Soccket Closed!!");
        System.out.println(webSocketSession.toString()); // webSocketSession에 뭐가 들었는지 확인 
        System.out.println(closeStatus.toString()); 
        //sessionList.remove(webSocketSession);  // 로그인한 당사자의 정보를 session에서 제거를 함 (그 저장은 서버상에 또는 메모리상에 저장시킴)

        for (SocketGroup socketGroup : sessionList) { 
            if(socketGroup.getWebSocketSession().equals(webSocketSession))
               sessionList.remove(socketGroup);
        }

    }
    
}
