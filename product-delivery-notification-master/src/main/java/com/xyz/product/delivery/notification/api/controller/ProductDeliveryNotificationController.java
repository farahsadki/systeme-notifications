package com.xyz.product.delivery.notification.api.controller;

import com.xyz.product.delivery.notification.api.model.NotificationRequest;
import com.xyz.product.delivery.notification.api.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDeliveryNotificationController {
	@Autowired
	private EmailService emailService;
	@PostMapping("/notify")
	public void sendNotification(@RequestBody NotificationRequest notificationRequest){
		emailService.notifyCustomer(notificationRequest);
	}
}
