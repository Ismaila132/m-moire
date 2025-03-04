package sn.pad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BordereauServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BordereauServiceApplication.class, args);
	}

}
