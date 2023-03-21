package com.jihoon.board.service.mainservice;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    
    // 스케쥴 작업이 끝나는 시간 기준으로 실행
    //@Scheduled(fixedDelay = 1000) // 1000 밀리세컨드임
   // public void scheduleFixedDelay(){
       // System.out.println("고정 딜레이 작업 : " + System.currentTimeMillis() / 1000);
   // }

    // 두 개의 차이 : 
    // 여기 접하는 순간부터 밀리언세컨드 실행
    // 스케쥴 작업이 시작하는 시간 기준으로 실행 
    // 그래서 두 개 초 차이가 거의 없이 나오는건가?
    //@Scheduled(fixedRate = 1000)
    //public void scheduleFixedRate(){
       // System.out.println("시작 고정 딜레이 작업 : " + System.currentTimeMillis() / 1000);
   // }
    
    @Scheduled(cron="2 * * * * ?") // cron : 특정한 시간을 지정해서 하는 행위  (// 2초 뒤에 실행 됨)
    public void scheduleCronJob(){
        //System.out.println("Cron Job으로 시간 지정 작업 : " + System.currentTimeMillis() / 1000);
        try {
            crawlling();
        
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    
    }

    // 어떤 특정 스케쥴이 존재하고 실행중에 있다면 다른 스케쥴은 사용할 수 없음
    // 만약에 밑에처럼 2번 스케쥴이 있다면 이 두개가 동작을 안 한다고 했나 아니면 하나만 동작을 한다고 했나 그렇다고 함 
   
    //@Scheduled(cron="3 * * * * ?") // cron : 특정한 시간을 지정해서 하는 행위  (// 2초 뒤에 실행 됨)
    //public void scheduleCronJob2(){
        //System.out.println("Cron Job으로 시간 지정 작업 : " + System.currentTimeMillis() / 1000);
   // }

   public void crawlling() throws Exception {
        
    Document document = Jsoup.connect("http://naver.com").get();//Jsoup꺼 import해줘야함 
    System.out.println(document.html());    

    Elements elements = document.select("#NM_FAVORITE > div.group_nav > ul.list_nav.NM_FAVORITE_LIST");
    //Elements elements = document.select("#NM_NEWSSTAND_DEFAULT_THUMB > div._NM_UI_PAGE_CONTAINER > div:nth-child(4) > div > div.thumb_area"); // select할 때 cssqury라고 적혀져있는거 선택
    //document.selectXpath();
    System.out.println(elements.size());

    for(Element element : elements){
        System.out.println(element.absUrl("href"));
        //System.out.println(element.attr("data-pid"));      
        //Elements subElements = element.select("#NM_NEWSSTAND_DEFAULT_THUMB > div._NM_UI_PAGE_CONTAINER > div:nth-child(4) > div > div.thumb_area > div:nth-child(1) > a > img");
        //System.out.println(subElements.attr("src"));
    }
}

}
