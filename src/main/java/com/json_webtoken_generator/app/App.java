package com.json_webtoken_generator.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import org.springframework.boot.ApplicationArguments;
// import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

	// private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);		
	}

	// public static void restart() {
    //     ApplicationArguments args = context.getBean(ApplicationArguments.class);

    //     Thread thread = new Thread(() -> {
    //         context.close();
    //         context = SpringApplication.run(App.class, args.getSourceArgs());
    //     });

    //     thread.setDaemon(false);
    //     thread.start();
    // }

}
