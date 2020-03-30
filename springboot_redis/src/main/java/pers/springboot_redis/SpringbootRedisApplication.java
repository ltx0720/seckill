package pers.springboot_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="pers.springboot_redis.*")
public class SpringbootRedisApplication {

//	@Autowired
//	Service service;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisApplication.class, args);

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
//
//		Service service =(Service) context.getBean("service");
//		AnnotaionAspect annotaionAspect = context.getBean(AnnotaionAspect.class);
//		System.out.println(annotaionAspect);

//		System.out.println(service);
//
//		service.test();
	}

}
