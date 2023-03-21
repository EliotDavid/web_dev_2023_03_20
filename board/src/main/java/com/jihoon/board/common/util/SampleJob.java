package com.jihoon.board.common.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;

public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       System.out.println("SampleJob excute!!");;
    }


    @Bean
    public JobDetail jobDetail() { 
        return JobBuilder
        .newJob().ofType(SampleJob.class)
        .storeDurably()
        .withIdentity("Test Sample Job Detail")
        .withDescription("Sample Job Detail 테스트입니다")
        .build();
        //.withDescription(jobDescription : "Sample Job Detail 테스트입니다.") // 이건 필요없는거임
        
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) { 
        System.out.println(jobDetail.toString());
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("2 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail)
        .withIdentity("Test Sample Trigger")
        .withDescription("Sample Trigger 테스트입니다.")
        .withSchedule(cronSchedule)
        .build();
        //.withDescription(triggerDescription : "Sample Trigger 테스트입니다.")
    
    }

    
    
}
