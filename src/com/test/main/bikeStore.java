package com.test.main;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.test.bean.bike;
import com.test.bean.bike.bikeType;
import com.test.utils.DirectLinkedList;
import com.test.utils.Util;
import com.test.utils.DirectLinkedList.Entry;

public class bikeStore {
	private String name;
	bikeFactory factory;
	public DirectLinkedList<bike> mBikeList = new DirectLinkedList<>();
	private static final int MAX_ORDER_NUM = 5; //商店一次性向工厂请求单车的最大数目
	private static final int MAX_SELL_TIME = 10; //商店随机售卖的最大时间因子
	public bikeStore(String name, bikeFactory factory) {
		this.name = name;
		this.factory = factory;
	}
	
	public void orderBikeTask(){
		orderBikeTask(null);
	}
	/**
	 * 向工厂申请单车任务/线程
	 * @param type
	 */
	public void orderBikeTask(bikeType type) {
		TimerTask task = new TimerTask() {  
            @Override  
            public void run() {
            	orderBike(type);
            }  
        };  
        Timer timer = new Timer();  
        long delay = 0; 
        long intevalPeriod = 20 * 1000;  // 商店每隔20秒请求5辆单车
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
		
//		bike bike = factory.createBike(type);
//		if (bike != null) { // 对bike进行行为测试
//			bike.start();
//			bike.trumpet();
//			bike.forward();
//		}
	}
	
	private void orderBike() {
		orderBike(null);
	}
	
	private void orderBike(bikeType type) { // 为拓展性预留，暂时未实现。商店可以在此要求工厂生产什么类型的单车。
		for(int index = 0; index < MAX_ORDER_NUM; index ++) {
			Entry<bike> entry = new Entry<bike>(factory.getBike());
			synchronized (mBikeList) { // 售卖和订货都会写商店库存，加锁
				mBikeList.add(entry);
			}
			System.out.println(Util.getCurrentTime() + name + " store one bike sucess. Current store inventory size = " 
            		+ ((mBikeList == null) ? 0 : mBikeList.size()));  
			if(factory.isBikeEmpty()) { // 如果工厂库存为零，则跳出请求货源循环
				break;
			}
		}
	}
	
	/**
	 * 单车销售任务/线程
	 */
	public void sellBikeTask() {
		while(true) {
	        long intevalPeriod = new Random().nextInt(MAX_SELL_TIME) * 1000;  // 商店每10秒内卖出1辆单车
	        try {
				Thread.sleep(intevalPeriod);
				
				if(mBikeList.isEmpty()) { // 商店库存为0时停止销售单车;并向工厂申请单车直到申请成功库存>0时再开始销售.
	        		orderBike();
	        	} else {
	        		Entry<bike> entry = mBikeList.getHead();
	        		synchronized (mBikeList) {	// 售卖和订货都会写商店库存，加锁
	        			mBikeList.remove(entry); // 卖出一辆单车并从库存中清出。
	        		}
	        		System.out.println(Util.getCurrentTime() + name + " sell one bike sucess. Current store inventory size = " 
	                		+ ((mBikeList == null) ? 0 : mBikeList.size())); 
	        	}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
