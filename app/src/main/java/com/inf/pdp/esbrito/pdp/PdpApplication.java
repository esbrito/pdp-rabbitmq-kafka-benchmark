package com.inf.pdp.esbrito.pdp;

import com.inf.pdp.esbrito.pdp.configuration.AllConsumedLatch;
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
			int waitTime = args != null && args.length > 3 ? Integer.valueOf(args[3]) : 10;

			System.out.println(broker);
			if (broker.equalsIgnoreCase("rabbit")) {
				producer = ctx.getBean(RabbitMQProducer.class);
			} else if (broker.equalsIgnoreCase("kafka")) {
				producer = ctx.getBean(KafkaProducer.class);
			} else {
				System.out.println("Invalid broker! Use `rabbit` or `kafka`");
				System.exit(1);
			}

			AllConsumedLatch latch = ctx.getBean(AllConsumedLatch.class);
			// Initializes latch with the number of total messages to be consumed
			latch.init(totalMessages);
			int messages = 0;
			// Initializes time counter to see total time from producing to consume all messages
			Long startTime = System.nanoTime();
			while (messages < totalMessages) {
				Thread.sleep(waitTime);
				producer.produce(byteSize);
				messages++;
			}
			Long endTimeForMessageProduction = System.nanoTime();


			// Awaits every message to be consumed
			latch.await();
			Long endTime = System.nanoTime();
			System.out.println("Total time " + (endTime - startTime)/1000000.0 + " ms");
			System.out.println("Total time for message production " +
					(endTimeForMessageProduction - startTime)/1000000.0 + " ms");
			System.out.println("Total time for message consumption " +
					(endTime - latch.firstConsumedMessageTime())/1000000.0 + " ms");
			System.out.println("Production rate " +
					totalMessages/((endTimeForMessageProduction - startTime)/1000000000.0) + " messages/second");
			System.out.println("Consumption rate " +
					totalMessages/((endTime - latch.firstConsumedMessageTime())/1000000000.0) + " messages/second");
			System.exit(0);
		};
	}

}
