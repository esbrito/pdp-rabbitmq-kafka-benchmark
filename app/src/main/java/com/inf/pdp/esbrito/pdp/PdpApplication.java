package com.inf.pdp.esbrito.pdp;

import com.inf.pdp.esbrito.pdp.producer.KafkaProducer;
import com.inf.pdp.esbrito.pdp.producer.Producer;
import com.inf.pdp.esbrito.pdp.producer.RabbitMQProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PdpApplication {


	public static void main(String[] args) {
		SpringApplication.run(PdpApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ApplicationContext ctx) {
		return args -> {
			Producer producer = null;

			String broker = args != null && args.length > 0 ? args[0] : "kafka";
			int byteSize = args != null && args.length > 1 ? Integer.valueOf(args[1]) : 1024;
			int totalMessages = args != null && args.length > 2 ? Integer.valueOf(args[2]) : 100000;
			System.out.println(broker);
			if (broker.equalsIgnoreCase("rabbit")) {
				producer = ctx.getBean(RabbitMQProducer.class);
			} else if (broker.equalsIgnoreCase("kafka")) {
				producer = ctx.getBean(KafkaProducer.class);
			} else {
				System.out.println("Invalid broker! Use `rabbit` or `kafka`");
				System.exit(1);
			}
			int messages = 0;
			while (messages < totalMessages) {
				Thread.sleep(10);
				producer.produce(byteSize);
				messages++;
			}
		};
	}

}
