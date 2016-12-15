package com.tao2882038.servers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Server extends Thread{


	public static Map<String, Socket> socketMap = new HashMap<String, Socket>();
	public static List<String> names = new ArrayList<String>();
	public static List<String> dataLog = new ArrayList<String>();
	public void run() {
		
		ServerSocket server;
		try {
			System.out.println("开启服务");
			server = new ServerSocket(1995);
//			int count = 0;
			while(true){
				Socket s = server.accept();
				String name = s.getInetAddress().toString()+":"+s.getPort();
				names.add(name);
				socketMap.put(name, s);
				System.out.println("连接成功  当前在线"+socketMap.size());
				for (String disname : names) {
					System.out.println(disname);
				}
				ServerThread serverThread = new ServerThread(s,name);
				serverThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
//	public static boolean isClosed(Socket socket){
//		try {
//			socket.sendUrgentData(0);
//		} catch (IOException e) {
//			return true;
//		}
//		return false;
//	}
	
	public static void remove(String name){
		socketMap.remove(name);
		for (int j = 0; j < names.size(); j++) {
			if(names.get(j).equals(name)){
				names.remove(j);
			}
		}
	}
	
	public static List<String> getlist(){
		return names;
	}
	
	public static List<String> getlist_dataLog(){
		return dataLog;
	}
	
	public static void clear_dataLog(){
		dataLog.clear();
	}
	
	public static String getLog(){
		String log = "";
		for(String string:dataLog){
			log += string;
			log += "\n";
		}
		return log;
	}
	
	public static int getlistsize(){
		return names.size();
	}
	
	public static void sendata(String source_name, String object_name,String data){
		Socket socket = socketMap.get(object_name);
		OutputStreamWriter osw;
//		if(socket == null || isClosed(socket)){
		if(socket == null){
			remove(object_name);
			System.out.println("发送时检测  "+object_name+"：已断开连接");
		}else {
			try {
				osw = new OutputStreamWriter(socket.getOutputStream());
			    BufferedWriter bosw =new BufferedWriter(osw);
				bosw.write(source_name+":"+data+"\n");
				bosw.flush();
				dataLog.add("web:"+data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}






