package com.tao2882038.servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import sun.print.resources.serviceui;

public class ServerThread extends Thread{
	
	private Socket s;
	private String name;
	
	public ServerThread(Socket s , String name){
		this.s = s;
		this.name = name;
	}
	

	public void run(){
		boolean done = true;
		BufferedReader br = null;
			 try {
				 while(done){
						 br = new BufferedReader(new InputStreamReader(s.getInputStream()));
						 String content1 = br.readLine();
						 if(content1 == null){
							 done = false;
							 Server.remove(name);
							 System.out.println(name+"连接断开");
						 }else{
							 String content = new String(content1.getBytes(), "UTF-8"); 
							 String logString = name+":"+content;
							 System.out.println("[日志]："+logString);
							 Server.dataLog.add(logString);
							 if(Server.dataLog.size() > 50){
								 for (int i = 0; i < 10; i++) {
									 Server.dataLog.remove(MIN_PRIORITY);
								}
							 }
							 for(String object_name:Server.names){
//							 Server.sendata(name,object_name, content);
							 }
						 }
						 
//					 if(!Server.isClosed(s)){
//						 
//					 }else{
//						 done = false;
//						 Server.remove(name);
//						 System.out.println(name+"连接断开");
//					 }
				 }
				 
				 try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 
			} catch (IOException e) {
				e.printStackTrace();
//				System.out.println("输入流错误");
				done = false;
				Server.remove(name);
				System.out.println(name+"异常   连接断开");
			}
			
	}
}

