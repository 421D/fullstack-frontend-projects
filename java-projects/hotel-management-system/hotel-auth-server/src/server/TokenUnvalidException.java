package server;

public class TokenUnvalidException extends Exception{ //令牌认证异常累
	private static final long serialVersionUID = 1L;
	public String toString() {
		return "TokenUnvalid";
	}
}
