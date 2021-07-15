package com.example.runner;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

@GetMapping("/notification")
public List<Notification> getNotification() throws InterruptedException{
	List<Notification> nt=Gmail.getNotification();
		return nt;  
	

}

}
