package org.tmpk.scheduler2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Scheduler2024Application {

    public static void main(String[] args) {
        SpringApplication.run(Scheduler2024Application.class, args);
    }

}
