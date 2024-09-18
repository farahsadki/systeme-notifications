package com.xyz.product.delivery.notification.api.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.xyz.product.delivery.notification.api.model.NotificationRequest;
import com.xyz.product.delivery.notification.api.model.common.Email;
import com.xyz.product.delivery.notification.api.model.customer.CustomerDetails;
import com.xyz.product.delivery.notification.api.model.order.OrderDetail;
import com.xyz.product.delivery.notification.api.repository.CustomerRepository;
import com.xyz.product.delivery.notification.api.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {
	@Value("${conf.twillio.account.sid}")
	public String accountSID;
	@Value("${conf.twillio.auth.token}")
	public String authToken;
	@Value("${conf.twillio.mobile}")
	private String fromMobile;
	@Value("${spring.mail.username}")
	private String emailFrom;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	public void notifyCustomer(NotificationRequest notificationRequest) {
		
		// 1: Get customer number from notificationRequest
		// 2: Get customer details from Backend
		// 3: Send email or mobile notification as per customer preference
		CustomerDetails customerDetails = customerRepository
				.findById(notificationRequest.getOrderDetail().getCustomerId()).get();
		OrderDetail orderDetail = notificationRequest.getOrderDetail();
		this.sendSMS(notificationRequest, orderDetail, customerDetails);
		if(Optional.ofNullable(customerDetails.getEmailAddress()).isPresent()) {
			this.sendMail(notificationRequest, orderDetail, customerDetails);
		} else {
			this.sendSMS(notificationRequest, orderDetail, customerDetails);
		}
	}

	private void sendSMS(final NotificationRequest notificationRequest,
						  final OrderDetail orderDetail,
						  final CustomerDetails customerDetails){
		Twilio.init(accountSID, authToken);
		com.xyz.product.delivery.notification.api.model.common.PhoneNumber toPhoneNumberObject
				= phoneRepository.findById(notificationRequest.getOrderDetail().getCustomerId()).get();
		Message message = Message
				.creator(new PhoneNumber(toPhoneNumberObject.getCountryCode() + toPhoneNumberObject.getPhoneNumber()), // to
						new PhoneNumber(fromMobile), // from
						this.prepareMessageText(notificationRequest, orderDetail, customerDetails))
				.create();
		System.out.println(message.getSid());
		System.out.println(message.getStatus());
	}

	private void sendMail(final NotificationRequest notificationRequest,
						  final OrderDetail orderDetail,
						  final CustomerDetails customerDetails){
		Email email = new Email();
		email.setFrom(emailFrom);
		email.setTo(customerDetails.getEmailAddress());
		email.setSubject("Order status - " + orderDetail.getOrderNumber());
		email.setMessageText(this.prepareMessageText(notificationRequest, orderDetail, customerDetails));
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setSubject(email.getSubject());
		simpleMailMessage.setFrom(email.getFrom());
		simpleMailMessage.setTo(email.getTo());
		simpleMailMessage.setText(email.getMessageText());
		javaMailSender.send(simpleMailMessage);
	}

	private String prepareMessageText(final NotificationRequest notificationRequest,
									  final OrderDetail orderDetail,
									  final CustomerDetails customerDetails){
		return "Hi " + customerDetails.getName() + ".\n\n"
				+ "Thank you for your order with oder id " + notificationRequest.getOrderDetail().getOrderNumber()
				+ ". Please find below details of your order: " + "\n\n"
				+ "Order Number: " + orderDetail.getOrderNumber() + "\n"
				+ "Order Status: " + notificationRequest.getOrderStatus();
	}
}
