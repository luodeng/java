package com.roden.java.io.nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchEventTest {
	public static void main(String[] args) throws IOException {

		try {
			Path myDir = Paths.get("C:/test");
			System.out.println(myDir.toString());
			WatchService watchService = myDir.getFileSystem().newWatchService();

			//java.nio.file.FileSystem fileSystem = java.nio.file.FileSystems.getDefault();
			//WatchService watch = fileSystem.newWatchService();
			//Path path = fileSystem.getPath("D:/data");

			myDir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			while (true) {
				WatchKey key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					
					System.out.println(event.context() + "发生了" + event.kind() + "事件");

					if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
						System.out.println("Modify: " + event.context().toString());
					}
				}
				if (!key.reset()) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}
}