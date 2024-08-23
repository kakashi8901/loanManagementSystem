package net.statestreet.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaMessageListener {

    RestTemplate restTemplate=new RestTemplate();

    @KafkaListener(topics = "loanTopic1", groupId = "loanConsumer1")
    public void consumeEvents(Long loanId){
        restTemplate.put("http://localhost:8080/api/loans/sanction/"+ loanId, Long.class);
    }




}
