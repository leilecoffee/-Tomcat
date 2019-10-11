package ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	

	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		//监听的端口
		int port = 8080;
		try {
			//操作系统构建一个队列来存放请求的连接，先入先出
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (Exception e) {
			System.exit(1);
		}
		while (!shutdown) {
			Socket socket = null;
			InputStream in = null;
			OutputStream out = null;
			try {
				//从队列中获取连接,如果队列中没有连接则会阻塞
				socket = serverSocket.accept();
				in = socket.getInputStream();
				out = socket.getOutputStream();

				Request request = new Request(in);
				//解析请求的报文
				request.parse();
				Response response = new Response(out);
				response.setRequest(request);
				//判断是否是servlet请求
				if(request.getUri().startsWith("/servlet/")) {
					ServletProcessor1 processor = new ServletProcessor1();
					processor.processor(request, response);
				}else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.processor(request, response);
				}
				//关闭连接
				socket.close();
				if (request.getUri() != null)
					shutdown = request.getUri().equals(Constants.SHUTDOWN_COMMAND);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}

		}

	}
}
