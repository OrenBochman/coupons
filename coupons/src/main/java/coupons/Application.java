package coupons;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import coupons.filters.JwtFilter;
//import coupons.filters.LoginFilter;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("1234");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
    
//	// uncomment this and comment the @Component in the filter class definition to register only for a url pattern
//	@Bean
//	public FilterRegistrationBean<LoginFilter> loggingFilter() {
//		FilterRegistrationBean<LoginFilter> registrationBean = 
//				new FilterRegistrationBean<>();
//		registrationBean.setFilter(new LoginFilter());
//		registrationBean.addUrlPatterns("/api/*","/personal/*","/admin/*");
//		registrationBean.setOrder(Integer.MAX_VALUE-1);
//		return registrationBean;
//	}
	
	// uncomment this and comment the @Component in the filter class definition to register only for a url pattern
	@Bean
	public FilterRegistrationBean<JwtFilter> JwtFilterRegistrationBean() {
		final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/secure/*");
		registrationBean.setOrder(Integer.MAX_VALUE);
		return registrationBean;
	}
}