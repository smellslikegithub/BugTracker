package com.example.BugTracking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class BugTrackingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void run(){
		DateTimeFormatter format = DateTimeFormatter.ISO_DATE;
		String formattedDate = format.format(LocalDate.now());
		System.out.println(formattedDate);

		System.out.println("Epoch: "+ Instant.now().toEpochMilli());
	}

}
