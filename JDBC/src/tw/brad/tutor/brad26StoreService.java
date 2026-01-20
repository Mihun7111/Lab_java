package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class brad26StoreService {
	private final String url, user, passwd;
	
	public brad26StoreService(String url, String user, String passwd) {
		this.url = url; this.user = user; this.passwd = passwd;
	}
	
	public void restock(int productId, int qty)  throws Exception{
		withRetry(() -> {
			try(Connection conn = DriverManager.getConnection(url, user, passwd)){
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				
				int stock = selectStockForUpdate(conn, productId);
				updateStock(conn, productId, stock + qty);
				// TODO log
				insertLog(conn, "IN", productId, "IN:" + qty);
				
				conn.commit();
			}
			return null;
		});
	}
	public void purchase(int productId, int qty) throws Exception{
		withRetry(() -> {
			try(Connection conn = DriverManager.getConnection(url, user, passwd)){
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				
				int stock = selectStockForUpdate(conn, productId);
				System.out.println(stock + ":" + qty);
				if (stock < qty) {
					conn.rollback();
					
					throw new brad26NotEnoughException("庫存量不足");
				}
				updateStock(conn, productId, stock - qty);
				insertLog(conn, "OUT", productId, "OUT:" + qty);				
				conn.commit();
			}
			return null;
		});		
	}
	
	private int selectStockForUpdate(Connection conn, int productId) throws SQLException{
		String sql = """
				SELECT stock
				FROM mytest
				WHERE id = ?
				FOR UPDATE
				""";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, productId);
			try(ResultSet rs = pstmt.executeQuery()){
				if (!rs.next()) throw new SQLException();
				return rs.getInt(1);
			}
		}
	}

	private void updateStock(Connection conn, int productId, int newstock) throws Exception{
		String sql = """
				UPDATE mytest
				SET stock = ?
				WHERE id = ?
				""";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, newstock);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		}		
	}
	
	
	private void insertLog(Connection conn, String type, int productId, String note) 
				throws Exception{
		String sql = """
				INSERT INTO log
					(type, pid, note)
				VALUES
					(?,?,?)
				""";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			pstmt.setInt(2, productId);
			pstmt.setString(3, note);
			pstmt.executeUpdate();
		}
		
	}
	
	// retry 框架
	private <T> T withRetry(Callable<T> action) throws Exception {
		int max = 3;
		int backoff = 20;
		for (int i = 0; i<=max; i++) {
			try {
				return action.call();
			}catch(Exception e) {
				// Deadlock, Lock
				if (i==max) throw e;
				Thread.sleep(backoff);
				backoff *= 2;
			}
		}
		
		throw new IllegalStateException("ERROR(1)");
	} 
}
