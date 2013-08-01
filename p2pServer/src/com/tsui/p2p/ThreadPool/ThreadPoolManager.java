package com.tsui.p2p.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPoolManager {
	private static int DEFAULT_POOL_SIZE = 4;
	private List<WorkThread> threadPool;
	private Queue<Task> taskQueue;
	private int poolSize;
	
	public ThreadPoolManager()
	{
		this(DEFAULT_POOL_SIZE);
	}
	
	public ThreadPoolManager(int poolSize)
	{
		if(poolSize < 0)
			this.poolSize = DEFAULT_POOL_SIZE;
		else
			this.poolSize = poolSize;
		
		threadPool = new ArrayList<WorkThread>(this.poolSize); 
	    taskQueue = new ConcurrentLinkedQueue<Task>();
	    
	    startup();
	}
	
	/*
	 * Startup threadPool
	 */
	public void startup()
	{
		System.out.println("Startup ThreadPool ...");
		synchronized(taskQueue){
			for(int i = 0; i<this.poolSize; i++)
			{
				WorkThread wt = new WorkThread(taskQueue);
				threadPool.add(wt);
				wt.start();
			}
		}
	}
	
	/*
	 * ShutDown ThreadPool 
	 */
	public void shutdown()
	{
		System.out.println("Shutdown ThreadPool ...");
		//synchronized(taskQueue){
			for(int i = 0; i < DEFAULT_POOL_SIZE; i++){ 
	        	 threadPool.get(i).shutdown(); 
	         }
		//}
	}
	
	/*
	 * Add Task
	 */
	public void addTask(Task task)
	{
		synchronized(taskQueue){ 
			taskQueue.add(task); 
			taskQueue.notifyAll();    
		} 
	}
}	
