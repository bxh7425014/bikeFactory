package com.test.bean;

import com.test.behavior.IBikeBehavior;

/**
 * bike基类，所有的单车类型都继承此类型。
 * 关于实现IBikeBehavior接口，其实其中的动作也可以使用抽象方法也可以完成本题，但是用接口的话，有几方面的好处，比如：
 * 1、扩展性更强，比如后续可以动态设定具体类型bike的行为，子类的Behavior可以通过set来改变。
 * 2、子类不必强制实现抽象父类的方法。
 * @author bianxh
 *
 */
public abstract class bike implements IBikeBehavior { 
	public enum bikeType{  
	    ELETRIC_BICYCLE,MOTOR_CAR,BICYCLE
	}
	
	protected String color;
	protected int height;
	protected String name;
	
	public bike() {
		name = "bike";
		color = "default_color";
		height = 0;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void trumpet() {
		// TODO Auto-generated method stub

	}
}
