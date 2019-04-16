package org.beny.ignite;

import org.apache.ignite.cache.websession.WebSessionFilter;
import org.apache.ignite.startup.servlet.ServletContextListenerStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextListener;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@SpringBootApplication
public class IgniteApplication {

    public static void main(String[] args) {
        SpringApplication.run(IgniteApplication.class, args);
    }

    @Bean
    public ServletContextListener igniteListener() {
        return new ServletContextListenerStartup();
    }

    @Bean
    public ServletContextInitializer igniteContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("IgniteConfigurationFilePath", "config-ignite.xml");
            servletContext.setInitParameter("IgniteWebSessionsCacheName", "session-cache");
        };
    }

    @Bean
    public FilterRegistrationBean igniteFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setOrder(HIGHEST_PRECEDENCE);
        registration.setName("IgniteWebSessionsFilter");
        registration.setFilter(new WebSessionFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}
