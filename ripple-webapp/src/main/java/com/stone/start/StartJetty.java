package com.stone.start;

import java.io.IOException;

import javax.naming.NamingException;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartJetty {

	public static void main(String[] args) throws IOException, NamingException {
		startJetty();
	}

	public static void startJetty() throws NamingException {
		Server server = new Server();
		// create pool
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMinThreads(10);
		threadPool.setMaxThreads(200);
		server.setThreadPool(threadPool);
		// get port
		int port = Integer.valueOf(12000);
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setMaxIdleTime(3000);
		connector.setAcceptors(Runtime.getRuntime().availableProcessors() + 1);
		connector.setStatsOn(false);
		connector.setLowResourcesConnections(32000);
		connector.setLowResourcesMaxIdleTime(60000 * 10);
		server.setConnectors(new Connector[] { connector });
		// init web cotext
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		// webAppContext.setDescriptor("/webdefault.xml");
		webAppContext.setResourceBase(System.getProperty("user.dir") + "/src/main/webapp");
		webAppContext.setContextPath("/");
		webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "true");
		server.setHandler(webAppContext);
		server.setStopAtShutdown(true);
		server.setSendServerVersion(false);
		server.setSendDateHeader(false);
		server.setGracefulShutdown(1000);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
