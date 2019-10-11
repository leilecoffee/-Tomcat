package ex02;

import java.io.IOException;

public class StaticResourceProcessor {

	public void processor(Request request,Response response) {
		try {
			response.sendStaticResouce();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
