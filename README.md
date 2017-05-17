#bikeFactory
题目要求：
1、使用面向对象思想，编写程序描述摩托车、电动车、单车的属性和行为。 
说明：属性有颜色、高度、名称；行为有启动、前进、响喇叭
2、基于上一题，编写程序描述单车工厂、单车售卖商店。
条件:
(1)实现1个单车工厂类,1个单车售卖商店类(一个类new 两个实例),1个单车类
(2)各个商店每20秒向工厂申请5辆单车（工厂库存不足5辆就给出剩余的单车，不阻塞线程） ，商店有自己的库存(即装单车的集合)。
(3)商店随机时间（1秒到10秒）会卖出一辆单车，当商店单车库存为零时工厂会马上工厂申请5辆单车。
（提示：工厂库存不足5辆就给出剩余的单车，此时工厂如果库存为0则会阻塞线程等待工厂生产单车，商店类中有2个线程,线程1用于向工厂申请单车,线程2用于单车销售。）
(4)工厂会每2秒生产一辆单车，有自己的库存(即装单车的集合)。
补充：工厂线程先运行8秒钟后再运行商店线程，商店库存为0时停止销售单车;并向工厂申请单车直到申请成功库存>0时再开始销售
要求：实现时用好面向对象思想，代码要整洁可读性良好，结构要考虑可扩展性。

反思代码的不足之处：
数据结构不应该用链表，应该用普通的数组加锁，wait notify来控制；或者用java的阻塞队列，现在的代码没有阻塞等待生产的能力；如果商店没有单车卖会一直死循环，这样比较浪费资源。