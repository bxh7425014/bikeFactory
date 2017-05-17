package com.test.main;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import com.test.bean.bicycle;
import com.test.bean.bike;
import com.test.bean.bike.bikeType;
import com.test.bean.electricBicycle;
import com.test.bean.motorcycle;
import com.test.utils.DirectLinkedList;
import com.test.utils.Util;
import com.test.utils.DirectLinkedList.Entry;

public class bikeFactory {
	public DirectLinkedList<bike> mBikeList = new DirectLinkedList<>();
	public bike createBike(bikeType type){
		switch (type) {
		case BICYCLE:
			return new bicycle();
		case ELETRIC_BICYCLE:
			return new electricBicycle();
		case MOTOR_CAR:
			return new motorcycle();
		default:
			 break;
		}
		return null;
	}
	
	public void startManufacture() {
		 TimerTask task = new TimerTask() {  
	            @Override  
	            public void run() {
	            	// 随机产生一种bike类型
	            	bikeType enums[] = bikeType.values();
	                Random random = new Random();  
	                bikeType type = enums[random.nextInt(enums.length)]; 
	                bike currentBike = createBike(type);
	                synchronized (mBikeList){ // 工厂生产和出货都有可能写工厂库存，加锁
	                	mBikeList.add(new DirectLinkedList.Entry<bike>(currentBike));
	                }
	                System.out.println(Util.getCurrentTime() + "Manufacture one bike sucess. type = " + type + ", current factory inventory size = " 
	                		+ ((mBikeList == null) ? 0 : mBikeList.size()));  
	            }  
	        };  
	        Timer timer = new Timer();  
	        long delay = 0; 
	        long intevalPeriod = 2 * 1000;  
	        // schedules the task to be run in an interval  
	        timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
	}
	
	public boolean isBikeEmpty() {
		if(mBikeList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 重要! 多个Store可能同时从工厂移走库存，务必加锁。
	 * @return
	 */
	public synchronized bike getBike() { // 工厂生产和出货都有可能写工厂库存，对mBikeList加锁
		Entry<bike> entry = mBikeList.getHead();
		mBikeList.remove(entry);
		return entry.value;
	}
}
