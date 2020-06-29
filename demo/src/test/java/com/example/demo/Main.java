package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository, AccountRepository accountRepository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			Account account1 = new Account("Jack", 230);
			Account account2 = new Account("Kim", 770);
			accountRepository.save(account1);
			accountRepository.save(account2);

			// fetch all accounts
			log.info("Accounts found with findAll():");
			log.info("-------------------------------");
			for (Account account : accountRepository.findAll()) {
				log.info(account.toString());
			}
			log.info("");

			// fetch accounts by balances
			log.info("Account(s) found with findByBalanceBetween(600,1000)");
			log.info("--------------------------------");
			for (Account account : accountRepository.findByBalanceBetween(600, 1000)) {
				log.info(account.toString());
			}
			log.info("");

			// fetch account by name
			log.info("Account found with findByNameStartingWith(\"Ki\")");
			log.info("--------------------------------------------");
			accountRepository.findByNameStartingWith("Ki").forEach(result -> {
				log.info(result.toString());
			});
			log.info("");

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}