package com.example.runner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail {

	public static List<Notification> getNotification() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Priyanshu\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(
				"https://login.yahoo.com/?.src=ym&pspid=1197806870&activity=header-signin&.lang=en-US&.intl=us&.done=https%3A%2F%2Fmail.yahoo.com%2Fd");

		// Send email address
		driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys("rohitraj_45@yahoo.co.in");
		// driver.findElement(By.id("identifierNext")).click();
		driver.findElement(By.xpath("//input[@id='login-signin']")).click();

		// send password
		WebElement passwordButton = driver.findElement(By.xpath("//input[@id ='login-passwd']"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(passwordButton));
		passwordButton.sendKeys("Marc.032020@puchhu");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@id='login-signin']")).click();
		// Click on unread button
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@title='Unread – click to see unread emails']")).click();
		Thread.sleep(500);
		FileWriter myWriter;
		List<Notification> notificationList=null;
		try {
			myWriter = new FileWriter("notification.txt");
		
		
		// Click on compose button
		// driver.findElement(By.xpath("//a[@data-test-id='compose-button']")).click();
		// read mail
		List<WebElement> elements = driver.findElements(By.xpath("//a[@role='article']"));
		Thread.sleep(500);
		//Notification  notification=new Notification();
		Map<String, List<String>> notificationMap=new HashMap<String, List<String>>();
		for (WebElement ele : elements) {
			
			System.out.println(ele.getAttribute("aria-label"));
			String element=ele.getAttribute("aria-label").toString();
			String from=element.substring(element.indexOf(":"),element.indexOf("."));
			if(notificationMap.containsKey(from)) {
				List<String> li=new ArrayList<>();
				li.addAll(notificationMap.get(from));
				li.add(element);
			notificationMap.put(from, li);
			}
			else {
				List<String> li=Arrays.asList(element);
				notificationMap.put(from, li);
			}
			myWriter.write("*******"+from+ele.getAttribute("aria-label"));
			//driver.findElement(By.xpath("//button[@title='Mark as read']")).click();
		}
		notificationMap=sortMap(notificationMap);
		notificationList=createNotifications(notificationMap);
		System.out.println(notificationList);
		myWriter.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificationList;

	}
	private static List<Notification> createNotifications(Map<String, List<String>> notificationMap) {
		Iterator<Map.Entry<String, List<String>>> itr=notificationMap.entrySet().iterator();
		List<Notification> notificationList=new ArrayList<>();
		while(itr.hasNext()) {
			
			Map.Entry<String, List<String>> ent=itr.next();
			Notification notification=new Notification(ent.getKey(), ent.getValue().size(), ent.getValue()) ;
			notificationList.add(notification);
		}
		return notificationList;
		
	}
	private static Map<String, List<String>> sortMap(Map<String, List<String>> map) {
		Map<String, List<String>> treeMap = new TreeMap<String, List<String>>(map);
        return treeMap;
	}
}