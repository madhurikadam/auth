//package com.unyx.auth;
//
//import com.unyx.auth.domain.Domain;
//import com.unyx.auth.domain.DomainRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//@SpringBootApplication
//public class demo {
//
//    public static void main(String[] args) {
//        SpringApplication.run(AuthApplication.class, args);
//    }
//
//    @Bean
//    CommandLineRunner init(DomainRepository domainRepository) {
//
//        return args -> {
//
////			Domain obj = domainRepository.findOne(7L);
////			System.out.println(obj);
//
//            Domain obj2 = domainRepository.findFirstByDomain("mkyong.com");
//            System.out.println(obj2);
//
//            long n = domainRepository.updateDomain("mkyong.com", true);
//            System.out.println("Number of records updated : " + n);
//
//            long n1 = domainRepository.updateDomain("mkyong.com", true);
//            System.out.println("Number of records updated : " + n1);
//
//        };
//
//    }
//
//}