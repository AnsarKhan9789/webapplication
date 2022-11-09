package storagelayer;


import java.util.List;
import java.util.Map;

import methods.AccountsPojo;
import methods.NonTransactionRequest;
import methods.PersonPojo;
import methods.TransactionPojo;
import methods.TransactionRequest;
import userexception.UserException;

public interface StorageMethods {
	String getUserIdDetails(int userId,String password) throws UserException;
	Map<Integer,PersonPojo> viewUserDetails(String role,int userId)throws UserException;
	Map<Integer,Map<Long,AccountsPojo>>  viewAccountDetails( String status,int... userId)throws UserException;
	void deposit(int userId,long accountNumber,int amount)throws UserException;
	void withDraw(int userId,long accountNumber,int amount)throws UserException;
	Map<Integer,TransactionPojo> getTransactionDetails(long accountNumber)throws UserException;
	void changePassword(int userId,String inputPassword) throws UserException;
	void sendTheMoney(long senderAccountNumber,long recieverAccountNumber,int amount) throws UserException;
	void edit(PersonPojo inputPerson) throws UserException;	  
	List<Long> getUserAccountList(String status,int... userId)throws UserException;	
	void requestForApproval(NonTransactionRequest input) throws UserException;
	String getThePassword(int userId)throws UserException;
	void setThePassword(int userId,String password)throws UserException;
	//admin
	Map<Integer,TransactionPojo> getAllTransactionDetails()throws UserException;
	Map<Integer,Map<Long,AccountsPojo>> getAllAccountDetails()throws UserException;
	Map<Integer,PersonPojo> getAllUserDetails(String...specification)throws UserException;
	long getBalance(long accountNumber)throws UserException;
	Map<Integer, TransactionRequest>  getWithdrawlRequests()throws UserException;
	void updateTheTransactionRequest(TransactionRequest input) throws UserException;
	int insertUser(PersonPojo input)throws UserException;
	void updateTheRequest(NonTransactionRequest input,boolean flagForChange) throws UserException;
	Map<Integer, NonTransactionRequest>  getAllAccountRequests(int...userId) throws UserException ;
	long insertNewAccount(AccountsPojo input) throws UserException;
	List<Integer>getUserIdList()throws UserException;
	void changeTheAccountStatus(long accountNumber,String status)throws UserException;

}
