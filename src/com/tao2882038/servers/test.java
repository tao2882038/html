package com.tao2882038.servers;

import java.util.ArrayList;
import java.util.List;





public class test {

	private static List<String> disnames = new ArrayList<String>();
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		while(true){
			try {
				Thread.sleep(5000);
				disnames = Server.getlist();
				System.out.println(Server.getlistsize());
				for (String name:disnames) {
					System.out.println(name);
				}
			} catch (InterruptedException e) {}
		}
	}
}
