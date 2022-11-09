package methods;

import java.util.List;
import java.util.Map;

import storagelayer.DatabaseMethods;
import storagelayer.StorageMethods;
import userexception.UserException;
import utilpackage.HelperUtil;

public class BankingForUser {
	
	public StorageMethods storeMethodObj =new DatabaseMethods();


	// if the userId and password is correct it will return their pojo class if it fails it return empty strin
	// it will give -1 if the userid and account number is wrong;
	
	public long getBalance(int userId,long accountNumber) throws UserException {
		long balance =storeMethodObj.getBalance( accountNumber);
		if(balance==-1) {
			throw new UserException("Please enter the valid user Id");
		}
		return balance;
	}
	
	public void sendMoney(long accountNumber,long recieverAccoundNumber,int amount) throws UserException {
		if(recieverAccoundNumber==accountNumber) {
			throw new UserException("You should cant transfer in same accout");
		}
			try {
				long balance=storeMethodObj.getBalance(accountNumber);
				long recieverBalance =storeMethodObj.getBalance(recieverAccoundNumber); 
				if(recieverBalance==-1) {
					throw new UserException("The Receiver Account number does not exist");
				}
				if(balance==-1) {
					throw new UserException("Please enter valid user account number");
				}
				if(balance<amount) {
					throw new UserException("The balance is less than the Amount");
				}	
				storeMethodObj.sendTheMoney(accountNumber,recieverAccoundNumber,amount);
			}
			catch(UserException e) {
				throw new UserException("Please enter valid reciever account number");
			}
			
			
		
		
	}

	
	public void changePassword(int userId,String newPassword) throws UserException {
		HelperUtil.isCorrectPassword(newPassword);
		storeMethodObj.changePassword(userId, newPassword);
	}
	public void editUser(PersonPojo inputPerson) throws UserException {
		HelperUtil.nullCheck(inputPerson);
		storeMethodObj.edit(inputPerson);
			
	}
	

	public Map viewUserDetails(int userId,String role) throws UserException {
		Map user =storeMethodObj.viewUserDetails(role,userId);
		return user;
	
	} 
	public List<Long> getAccountList(String status,int... userId) throws UserException {
		HelperUtil.nullCheck(status);
		List<Long> accountList = storeMethodObj.getUserAccountList(status,userId);
		if(accountList==null) {
			throw new UserException("Please enter the correct Account Number");
			
		}
		return accountList;
	}
	public void deposit(int userId,long accountNumber,int amount) throws UserException {
		if(amount<0) {
			throw new UserException("Please enter the Valid amount");
		}
		if(amount>1000000) {
			throw new UserException("Please enter the Valid amount");
		}
		storeMethodObj.deposit(userId, accountNumber, amount);
	}
	public void withDraw(int userId,long accountNumber,int amount) throws UserException{
		long balance=storeMethodObj.getBalance(accountNumber);
		if(balance<amount) {
			throw new UserException("The balance is lesser than transaction amount");
		}
		if(amount<=0) {
			throw new UserException("Please enter the valid amount");
		}
		storeMethodObj.withDraw(userId, accountNumber, amount);
	}
	public Map<Integer,TransactionPojo> getTransactionDetails(long accountNumber)throws UserException{
		Map<Integer,TransactionPojo> transactionMap =storeMethodObj.getTransactionDetails(accountNumber);
		if(transactionMap==null) {
			throw new UserException("Please enter correct Account Number");
		}
		return transactionMap;
	}
	public void requestForApproval(NonTransactionRequest input) throws UserException {
		HelperUtil.nullCheck(input);
		storeMethodObj.requestForApproval(input);
	}
	public Map<Integer,Map<Long,AccountsPojo>>  viewAccountDetails(String status,int... userId)throws UserException{
		HelperUtil.nullCheck(status);
		Map<Integer,Map<Long,AccountsPojo>>  map=storeMethodObj.viewAccountDetails(status,userId);
		if(map==null) {
			throw new UserException("please enter valid account");
		}
		return map;
	}
	public Map<Integer, NonTransactionRequest>  getAccountRequests(int...userid)throws UserException{
		Map<Integer, NonTransactionRequest>  transactionMap = storeMethodObj.getAllAccountRequests(userid);
		return transactionMap;
	}
	public String getPassword(int userId) throws UserException {
		String passcode=storeMethodObj.getThePassword(userId);
		if(passcode.isEmpty()) {
			throw new UserException("please enter valid UserId");
		}
		return passcode;
	}
	public boolean setPassword(int userId,String password) throws UserException{
		boolean result=true;
		if(userId<0) {
			throw new UserException("Please enter valid User Id");
		}
		HelperUtil.nullCheck(password);
		try {
			storeMethodObj.changePassword(userId, password);
		}
		catch(UserException e) {
			result=false;
		}
		return result;
	}
}
