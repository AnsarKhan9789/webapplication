package methods;

import java.util.List;
import java.util.Map;

import userexception.UserException;
import utilpackage.HelperUtil;

public class BankingForAdmin extends BankingForUser{

public Map<Integer,PersonPojo> getAllUserDetails(String...speacification) throws UserException {
	HelperUtil.nullCheck(speacification);
	Map<Integer,PersonPojo> userMap =storeMethodObj.getAllUserDetails(speacification);
	return userMap;
	
}
public Map<Integer, Map<Long, AccountsPojo>> getAllAccountsDetails() throws UserException {
	Map<Integer, Map<Long, AccountsPojo>> accountMap =storeMethodObj.getAllAccountDetails();
	return accountMap;
	
}
public Map<Integer,TransactionPojo> getAllTransactionDetails() throws UserException {
	Map<Integer,TransactionPojo> transactionMap =storeMethodObj.getAllTransactionDetails();
	return transactionMap;
	
}

	
public Map<Integer, TransactionRequest>  getWithdrawlRequests()throws UserException{
	Map<Integer, TransactionRequest>  transactionMap =storeMethodObj.getWithdrawlRequests();
	return transactionMap;
}
public void updateTheTransactionRequest(TransactionRequest input) throws UserException
{
	storeMethodObj.updateTheTransactionRequest(input);

}
public void updateTheRequest(NonTransactionRequest input,boolean flag) throws UserException{
	HelperUtil.nullCheck(input);
	storeMethodObj.updateTheRequest(input,flag);
}
	
	
//get account requestDetails
public Map<Integer, NonTransactionRequest>  getAccountRequests()throws UserException{
	Map<Integer, NonTransactionRequest>  transactionMap = storeMethodObj.getAllAccountRequests();
	return transactionMap;
}
public List<Long> getAccountList(String status) throws UserException {
	List<Long> accountList = storeMethodObj.getUserAccountList(status);
	if(accountList==null) {
		throw new UserException("Please enter the correct User Id");
		
	}
	return accountList;
}
public int addTheUser(PersonPojo userDetails)throws UserException{
	HelperUtil.nullCheck(userDetails);
	int userId=storeMethodObj.insertUser(userDetails);
	return userId;
}
public long addTheAccount(AccountsPojo accountDetails)throws UserException{
	HelperUtil.nullCheck(accountDetails);
	long accountNumber=storeMethodObj.insertNewAccount(accountDetails);
	return accountNumber;
}

public List<Integer> getAllUserIdList()throws UserException{
	List<Integer> userIdList=storeMethodObj.getUserIdList();
	return userIdList;
}
public void changeTheAccountStatus(long accountNumber,String status) throws UserException {
	if(accountNumber<0) {
		throw new UserException("Please enter the Valid Account  Number ");
	}
	HelperUtil.nullCheck(status);
	storeMethodObj.changeTheAccountStatus(accountNumber, status);
}
}
