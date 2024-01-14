package com.itgorillaz.jmespathplayground;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JmespathPlaygroundApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(JmespathPlaygroundApplication.class)
            .web(WebApplicationType.NONE)
            .bannerMode(Banner.Mode.OFF)
            .headless(false)
            .build()
            .run(args);
    }

}
