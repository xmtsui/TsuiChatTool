package com.tsui.p2p.ThreadPool;

import java.util.Queue;

public class WorkThread extends Thread{
	private boolean shutdown;
	private Queue<Task> queue;
	
	public WorkThread(Queue<Task> queue)
	{
		this.queue = queue;
		
	}
	
	public void run()
	{
		while(!shutdown)
		{
			synchronized(queue){
				if(!queue.isEmpty())
				{
					System.out.println("TaskQueue: not empty!");
					Task task = queue.poll();
					task.execute();
				}
				else
				{
					System.out.println("TaskQueue: empty!");
					try{
						queue.wait();
					}catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}//end of sync
		}//end of while
	}
	
	public void shutdown()
	{
		shutdown = true;
	}
}
