package com.example.runner;

import java.util.List;

public class Notification {
private String from;
private int count;
private List<String> message;
public Notification(String from, int count, List<String> message) {
	super();
	this.from = from;
	this.count = count;
	this.message = message;
}
@Override
public String toString() {
	return "Notification [from=" + from + ", count=" + count + ", message=" + message + "]";
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public List<String> getMessage() {
	return message;
}
public void setMessage(List<String> message) {
	this.message = message;
}

}