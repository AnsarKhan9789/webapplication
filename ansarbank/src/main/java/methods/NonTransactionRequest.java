package methods;

public class NonTransactionRequest {
private int requestId;
private int userId;
private long accountNumber;
private String status;
private String requestStatus;
private String statement;
public int getRequestId() {
	return requestId;
}
public void setRequestId(int requestId) {
	this.requestId = requestId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public long getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getRequestStatus() {
	return requestStatus;
}
public void setRequestStatus(String requestStatus) {
	this.requestStatus = requestStatus;
}
public String getStatement() {
	return statement;
}
public void setStatement(String statement) {
	this.statement = statement;
}

}
