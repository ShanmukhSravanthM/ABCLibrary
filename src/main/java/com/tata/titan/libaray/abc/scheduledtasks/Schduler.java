package com.tata.titan.libaray.abc.scheduledtasks;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schduler {

	@Scheduled(cron = "*/10 * * * * *")
	public void print() {
		System.out.println("printing date:" + new Date());
	}

}
