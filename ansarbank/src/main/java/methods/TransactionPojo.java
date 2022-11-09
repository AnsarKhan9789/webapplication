package methods;

import utilpackage.HelperUtil;

public class TransactionPojo  {
	private int transactionId;
	private int userId ;
	private long primaryAccount;
	private long secondaryAccount;
	private String type;
	private String details;
	private String status;
	private long amounts;
	private long transactionTime;
	private String referenceId;
	private long balance;


	public TransactionPojo() {
		
	}
      public TransactionPojo(int userId,long primary,long secondary,String type,long amount,long balance) {
		this.userId=userId;
		this.primaryAccount=primary;
		this.secondaryAccount=secondary;
		this.type=type;
		this.amounts=amount;
		this.balance=balance;
		
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getPrimaryAccount() {
		return primaryAccount;
	}
	public void setPrimaryAccount(long primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
	public long getSecondaryAccount() {
		return secondaryAccount;
	}
	public void setSecondaryAccount(long secondaryAccount) {
		this.secondaryAccount = secondaryAccount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAmounts() {
		return amounts;
	}
	public void setAmounts(long amounts) {
		this.amounts = amounts;
	}
	public long getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}



}
