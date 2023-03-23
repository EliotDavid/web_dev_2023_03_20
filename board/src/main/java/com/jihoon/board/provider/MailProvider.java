package com.jihoon.board.provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component // 얘는 왜 Component로 걸어준거지? // Provider는 다 Component로 걸어주는건가?
public class MailProvider {
    
    @Autowired private JavaMailSender javaMailSender;


    public boolean sendMail(){
    

        try { 
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("1223020@donga.ac.kr"); 
            simpleMailMessage.setTo("optimistprimer@naver.com");
            //simpleMailMessage.setFrom("보내는 사람의 메일"); // setFrom() : 누구로부터 보내겠다 // 보내는 사람의 메일을 적어준다
            //simpleMailMessage.setTo("받는 사람의 메일주소"); // setTo() :받는 사람의 메일주소   
            //simpleMailMessage.setSubject("메일 제목"); // setSubject() : 메일 제목
            simpleMailMessage.setSubject("메일 제목");
            simpleMailMessage.setText("<p style='color: red;'>html 형식</p>");
            //simpleMailMessage.setText("html형식의 내용"); // setText() : 메일 내용이 html형식으로 나타남
            javaMailSender.send(simpleMailMessage);
    
           
        } catch(Exception exception) {
            exception.printStackTrace();    
            return false;

        }

        return true;
    }
}
