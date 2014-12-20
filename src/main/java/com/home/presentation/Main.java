package com.home.presentation;

import com.home.application.ApplicationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ApplicationService service = context.getBean(ApplicationService.class);

        service.addSuffixToEntitiesBeginningWith(" _3", 'W');
        service.deleteEntitiesBeginningWith('B');
    }
}
