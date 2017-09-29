package paasxpert.demo.cf.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableRedisHttpSession
@EnableAutoConfiguration
public class CloudApplication implements CommandLineRunner {

	@Autowired(required = false) 
	DataSource dataSource;
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println(ParseUtil.toString(dataSource));
	}

 
	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
	}
}
