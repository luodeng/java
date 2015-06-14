

import org.apache.log4j.Logger;
public class Test {
	public static  Logger log = Logger.getLogger(Test.class);
		public static void main(String[] args) {
		 
		 log.debug("debug");
		 log.info("info");
		 log.warn("warn");
		 
		 log.error("error");
		 
	}

}
