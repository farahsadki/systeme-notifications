package com.xyz.product.delivery.notification.api.model;

import com.xyz.product.delivery.notification.api.model.order.OrderDetail;
import com.xyz.product.delivery.notification.api.model.order.OrderStatus;

public class NotificationRequest {

	private OrderDetail orderDetail;
	
	private OrderStatus orderStatus;

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
