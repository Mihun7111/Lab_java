package tw.brad.websocket;

import java.util.HashSet;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/mycenter")
public class MyCenter {
	private static HashSet<Session> sessions;
	private static boolean isExistTeacher = false;
	private static Session teacherSession;
	
	public MyCenter() {
		if (sessions == null) {
			sessions = new HashSet<>();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		if (sessions.add(session)) {
			//System.out.println("New Session..");
		}
	}
	
	@OnMessage
	public void onMessage(String mesg, Session session) {
		// 判斷是否 Teacher => mesg
		if (!isExistTeacher && mesg.contains("isTeacher")) {
			isExistTeacher = true;
			teacherSession = session;
		}else if (session == teacherSession) {
			
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		//System.out.println("onClose()");
		sessions.remove(session);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("onError()");		
	}
	
}