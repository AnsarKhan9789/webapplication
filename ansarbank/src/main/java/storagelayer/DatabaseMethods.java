package storagelayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import userexception.UserException;
import utilpackage.HelperUtil;
import methods.AccountsPojo;
import methods.NonTransactionRequest;
import methods.PersonPojo;
import methods.TransactionPojo;
import methods.TransactionRequest;

public class DatabaseMethods implements StorageMethods {

	private Connection getConnection() throws UserException {
		String url="jdbc:mysql://localhost/BankingDB";
		String uName="root";
		String password="Root@123";
		Connection myConnection =null;
		try {
			myConnection = DriverManager.getConnection(url,uName,password); 
			return myConnection;
		}
		catch(SQLException sqlEx) {
			throw new UserException("There is a issue in connection ",sqlEx);
		}
	}
	private void close(ResultSet inputSet,Statement inputStatement,Connection myConnection) {
		try {
			if(inputSet!=null) {
				inputSet.close();
			}

		}
		catch(SQLException e) {}
		try {
			if(inputStatement!=null) {
				inputStatement.close();
			}

		}
		catch(SQLException e) {}
		try {
			if(myConnection!=null) {
				myConnection.close();
			}

		}
		catch(SQLException e) {}

	}
	private int getUserId(long accountNumber) throws UserException {
		String sqlSelect="select AccountDetails.UserId from AccountDetails where  AccountDetails.AccountNumber=? ";
		int userId =0;
		ResultSet resultSet=null;
		PreparedStatement myStatement = null;
		Connection inputConn =null;

		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sqlSelect);
			myStatement.setLong(1, accountNumber);
			resultSet = myStatement.executeQuery();
			if(resultSet.next()) {
				userId = resultSet.getInt("UserId");
			}
			return userId;

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}	

	}
	public long getBalance(long accountNumber) throws UserException {
		String sqlSelect="select AccountDetails.Balance from AccountDetails where  AccountDetails.AccountNumber=? ";
		long balance =-1;
		ResultSet resultSet=null;
		PreparedStatement myStatement = null;
		Connection inputConn =null;

		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sqlSelect);
			myStatement.setLong(1, accountNumber);
			resultSet = myStatement.executeQuery();
			if(resultSet.next()) {
				balance =(long) resultSet.getDouble("Balance");
			}
			return balance;

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}	

	}
	private void changeBalance(long balance,long accountNumber) throws UserException {
		String sql ="UPDATE AccountDetails SET Balance = ? WHERE AccountNumber = ?";

		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setLong(1, balance);
			myStatement.setLong(2, accountNumber);
			myStatement.executeUpdate();
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}



	}
	private void updateTheTransactionTable(TransactionPojo input) throws UserException {
		String sql="insert into TransactionTable(UserId,PrimaryAccount,SecondaryAccount,Type,Details,Amount,Status,TransactionTime,Balance,ReferenceId) values(?,?,?,?,'Transfer',?,'Success',?,?,?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setLong(1, input.getUserId());
			myStatement.setLong(2, input.getPrimaryAccount());
			myStatement.setLong(3, input.getSecondaryAccount());
			myStatement.setString(4, input.getType());
			myStatement.setLong(5, input.getAmounts());
			myStatement.setLong(6, input.getTransactionTime());
			myStatement.setLong(7, input.getBalance());
			myStatement.setString(8, input.getReferenceId());
			myStatement.executeUpdate();
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	private int insertThePersonTable(PersonPojo input) throws UserException {
		int userId=0;
		String sql="insert into PersonTable(Name,Email,Mobile,IdentityToVerify,Password) values(?,?,?,?,?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			myStatement.setString(1, input.getName());
			myStatement.setString(2, input.getEmailId());
			myStatement.setLong(3, input.getMobileNumber());
			myStatement.setString(4, input.getRole());
			myStatement.setString(5, input.getPassword());
			myStatement.executeUpdate();
			try(ResultSet transactionIdKeys=myStatement.getGeneratedKeys();){
				if(transactionIdKeys.next()) {
					userId=transactionIdKeys.getInt(1);
				}
			}
			input.setUserId(userId);
			insertTheCustomerTable(input);
			return userId;
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	private void insertTheCustomerTable(PersonPojo input) throws UserException {
		String sql="insert into CustomerTable(CustomerId,AadharNumber,PanCard,CustomerStatus) values(?,?,?,?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setInt(1, input.getUserId());
			myStatement.setLong(2, input.getAadharCard());
			myStatement.setString(3, input.getPanCard());
			myStatement.setString(4, input.getUserStatus());
			myStatement.executeUpdate();

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}

	private void insertTheTransactionRequest(TransactionRequest input) throws UserException {
		String sql="insert into TransactionRequest(AccountNumber,Status,Amount,TransactionId,UserId) Values(?,'Requested',?,?,?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setLong(1, input.getAccountNumber());
			myStatement.setLong(2, input.getAmount());
			myStatement.setInt(3, input.getTransactionId());
			myStatement.setInt(4, input.getUserId());
			myStatement.executeUpdate();
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	
	
	private void changeForWithdraw(TransactionPojo  input) throws UserException {
		String sql="update TransactionTable set Status=?,Balance=?,TransactionTime=? Where TransactionId=? ";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){

			myStatement.setString(1, input.getStatus());
			myStatement.setLong(2,input.getBalance());
			myStatement.setLong(3, System.currentTimeMillis());
			myStatement.setLong(4,input.getTransactionId());
			myStatement.executeUpdate();

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}

	private void updateForDeposit(TransactionPojo input) throws UserException {
	
		String sql="insert into TransactionTable(UserId,PrimaryAccount,SecondaryAccount,Type,Details,Amount,Status,TransactionTime,Balance,ReferenceId) values(?,?,Null,'Credit','Deposit',?,'Success',?,?,?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			long millis=System.currentTimeMillis();
			String refId="Ref"+String.valueOf(millis);
			myStatement.setLong(1, input.getUserId());
			myStatement.setLong(2, input.getPrimaryAccount());
			myStatement.setLong(3, input.getAmounts());
			myStatement.setLong(4, millis);
			myStatement.setLong(5, input.getBalance());
			myStatement.setString(6, refId);
			myStatement.execute();
			
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	@SuppressWarnings("null")
	private void updateForWithdraw(TransactionPojo input) throws UserException {
		int transactionId=0;
		String sql="insert into TransactionTable(UserId,PrimaryAccount,SecondaryAccount,Type,Details,Amount,Status,TransactionTime,Balance,ReferenceId) values(?,?,Null,'Debit','WithDraw',?,'Requested',?,?,?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			long millis=System.currentTimeMillis();
			String refId="Ref"+String.valueOf(millis);
			myStatement.setLong(1, input.getUserId());
			myStatement.setLong(2, input.getPrimaryAccount());
			myStatement.setLong(3, input.getAmounts());
			myStatement.setLong(4, millis);
			myStatement.setLong(5, input.getBalance());
			myStatement.setString(6, refId);
			myStatement.executeUpdate();
			try(ResultSet transactionIdKeys=myStatement.getGeneratedKeys();){
				if(transactionIdKeys.next()) {
					transactionId=transactionIdKeys.getInt(1);
				}
			}
			TransactionRequest requestPojo=new TransactionRequest();
			requestPojo.setAccountNumber(input.getPrimaryAccount());
			requestPojo.setAmount(input.getAmounts());
			requestPojo.setUserId(input.getUserId());
			requestPojo.setTransactionId(transactionId);
			insertTheTransactionRequest(requestPojo);
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
		
	}
	private void insertNonTransactionRequest(NonTransactionRequest input) throws UserException {
		String sql="insert into NonTransactionRequest(UserId,AccountNumber,Status,RequestStatus,Statement) values(?,?,?,'Requested',?)";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setInt(1, input.getUserId());
			myStatement.setLong(2, input.getAccountNumber());
			myStatement.setString(3, input.getStatus());
			myStatement.setString(4, input.getStatement());
			myStatement.executeUpdate();
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	private void changeForNonTransactionRequest(NonTransactionRequest  input,boolean flagForChange) throws UserException {
		String sql="update NonTransactionRequest set Status=?,RequestStatus=?,Statement=? Where RequestId=? ";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){

			myStatement.setString(1, input.getStatus());
			myStatement.setString(2,input.getRequestStatus());
			myStatement.setString(3,input.getStatement());
			myStatement.setInt(4,input.getRequestId());
			myStatement.executeUpdate();
			if(flagForChange==true) {
				changeForAccountDetails(input);
			}

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	private void changeForAccountDetails(NonTransactionRequest  input) throws UserException {
		String sql="update AccountDetails set AccountStatus=? Where AccountNumber=?";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){

			myStatement.setString(1, input.getStatus());
			myStatement.setLong(2, input.getAccountNumber());
			myStatement.executeUpdate();

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
		
	}
	
	private Map<Integer, TransactionPojo> convertToTransactionMap(ResultSet resultSet)throws UserException{

		Map<Integer,TransactionPojo> map =null;
		try {
			map = new HashMap<Integer,TransactionPojo>();
			while(resultSet.next()) {
				TransactionPojo transPojoObj=createTransactionPojo(resultSet);
				map.put(resultSet.getInt("TransactionId"),transPojoObj);	
			}
		}
		catch(SQLException sqlEx) {
			throw new UserException("The resultset is empty",sqlEx);
		}


		return map;
	}
	private Map<Integer,Map<Long,AccountsPojo>>  convertToAccountsMap(ResultSet resultSet)throws UserException{

		Map<Integer,Map<Long,AccountsPojo>> accountMap=null;
		try {
			accountMap=new HashMap<Integer,Map<Long,AccountsPojo>>();
			Map<Long,AccountsPojo> map=null;
			while(resultSet.next()) {
				AccountsPojo transPojoObj=createAccountsPojo(resultSet);
				if(accountMap.get(transPojoObj.getUserId())==null) {
					map=new HashMap<Long,AccountsPojo>();
				}
				map.put(transPojoObj.getAccountNumber(),transPojoObj);	
				accountMap.put(transPojoObj.getUserId(), map);
			}
			
		}
		catch(SQLException sqlEx) {
			throw new UserException("The resultset is empty",sqlEx);
		}


		return accountMap;
	}
	private Map<Integer, PersonPojo> convertToUserMap(ResultSet resultSet)throws UserException{

		Map<Integer,PersonPojo> map =null;
		try {
			map = new HashMap<Integer,PersonPojo>();
			while(resultSet.next()) {
				PersonPojo personPojo=createPersonPojo(resultSet);
				map.put(resultSet.getInt("UserId"),personPojo);	
			}
		}
		catch(SQLException sqlEx) {
			throw new UserException("The resultset is empty",sqlEx);
		}


		return map;
	}
	private Map<Integer, NonTransactionRequest> convertToNonTransactionRequest(ResultSet resultSet)throws UserException{

		Map<Integer,NonTransactionRequest> map =null;
		try {
			map = new HashMap<Integer,NonTransactionRequest>();
			while(resultSet.next()) {
				NonTransactionRequest personPojo=createNonTransactionRequest(resultSet);
				map.put(resultSet.getInt("RequestId"),personPojo);	
			}
		}
		catch(SQLException sqlEx) {
			throw new UserException("The resultset is empty",sqlEx);
		}


		return map;
	}
	private Map<Integer, TransactionRequest> convertToTransactionRequest(ResultSet resultSet)throws UserException{

		Map<Integer,TransactionRequest> map =null;
		try {
			map = new HashMap<Integer,TransactionRequest>();
			while(resultSet.next()) {
				TransactionRequest personPojo=createTransactionRequest(resultSet);
				map.put(resultSet.getInt("RequestId"),personPojo);	
			}
		}
		catch(SQLException sqlEx) {
			throw new UserException("The resultset is empty",sqlEx);
		}


		return map;
	}
	private PersonPojo createPersonPojo(ResultSet inputResultSet) throws SQLException {
		PersonPojo pojoObj=new PersonPojo();
			pojoObj.setUserId(inputResultSet.getInt("UserId"));
			pojoObj.setName(inputResultSet.getString("Name"));
			pojoObj.setEmailId(inputResultSet.getString("Email"));
			pojoObj.setMobileNumber(inputResultSet.getLong("Mobile"));
			pojoObj.setRole(inputResultSet.getString("Role"));
			pojoObj.setPassword(inputResultSet.getString("Password"));
	if(inputResultSet.getString("Role").equals("user")){
			pojoObj.setUserStatus(inputResultSet.getString("CustomerStatus"));
			pojoObj.setAadharCard(inputResultSet.getLong("AadharNumber"));
			pojoObj.setPanCard(inputResultSet.getString("PanCard"));
		}
		
		
		return pojoObj;
	}
	private TransactionPojo createTransactionPojo(ResultSet inputResultSet) throws SQLException {
		TransactionPojo pojoObj=new TransactionPojo();
		pojoObj.setTransactionId(inputResultSet.getInt("TransactionId"));
		pojoObj.setUserId(inputResultSet.getInt("UserId"));
		pojoObj.setPrimaryAccount(inputResultSet.getLong("PrimaryAccount"));
		pojoObj.setSecondaryAccount(inputResultSet.getLong("SecondaryAccount"));
		pojoObj.setType(inputResultSet.getString("Type"));
		pojoObj.setAmounts(inputResultSet.getLong("Amount"));
		pojoObj.setStatus(inputResultSet.getString("Status"));
		pojoObj.setDetails(inputResultSet.getString("Details"));
		pojoObj.setTransactionTime(inputResultSet.getLong("TransactionTime"));
		pojoObj.setBalance(inputResultSet.getLong("Balance"));
		return pojoObj;
	}
	private AccountsPojo createAccountsPojo(ResultSet inputResultSet) throws SQLException {
		AccountsPojo pojoObj=new AccountsPojo();
		pojoObj.setAccountNumber(inputResultSet.getLong("AccountNumber"));
		pojoObj.setBalance(inputResultSet.getLong("Balance"));
		pojoObj.setBranch(inputResultSet.getString("Branch"));
		pojoObj.setIfscCode(inputResultSet.getString("IFSC"));
		pojoObj.setUserId(inputResultSet.getInt("UserId"));
		pojoObj.setAccountStatus(inputResultSet.getString("AccountStatus"));

		return pojoObj;
	}
	private TransactionRequest createTransactionRequest(ResultSet inputResultSet) throws SQLException {
		TransactionRequest pojoObj=new TransactionRequest();
		pojoObj.setRequestId(inputResultSet.getInt("RequestId"));
		pojoObj.setAccountNumber(inputResultSet.getLong("AccountNumber"));
		pojoObj.setAmount(inputResultSet.getLong("Amount"));
		pojoObj.setStatus(inputResultSet.getString("Status"));
		pojoObj.setUserId(inputResultSet.getInt("UserId"));
		pojoObj.setTransactionId(inputResultSet.getInt("TransactionId"));

		return pojoObj;
	}
	private NonTransactionRequest createNonTransactionRequest(ResultSet inputResultSet) throws SQLException {
		NonTransactionRequest pojoObj=new NonTransactionRequest();
		pojoObj.setRequestId(inputResultSet.getInt("RequestId"));
		pojoObj.setAccountNumber(inputResultSet.getLong("AccountNumber"));
		pojoObj.setStatus(inputResultSet.getString("Status"));
		pojoObj.setUserId(inputResultSet.getInt("UserId"));
		pojoObj.setRequestStatus(inputResultSet.getString("RequestStatus"));
		pojoObj.setStatement(inputResultSet.getString("Statement"));

		return pojoObj;
	}
	
	public String getUserIdDetails(int userId,String password) throws UserException {
		String sqlSelect="select * from PersonTable where UserId = ? and Password= ?";
		ResultSet resultSet=null;
		PreparedStatement myStatement = null;
		Connection inputConn =null;
		String identityToVerify =null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sqlSelect);
			myStatement.setInt(1, userId);
			myStatement.setString(2, password);
			resultSet = myStatement.executeQuery();
			if(resultSet.next()) {
				identityToVerify =resultSet.getString("Role");
			}
			return identityToVerify;

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}	

	}


	@Override
	public void sendTheMoney(long senderAccountNumber,long recieverAccountNumber, int amount) throws UserException {


		long senderBalance=getBalance(senderAccountNumber); 
		if(senderBalance<amount) {
			throw new UserException("Your balance is lower than the sending amount ");
		}
		long recieverBalance =getBalance(recieverAccountNumber); 
		if(recieverBalance==-1) {
			throw new UserException("The Receiver Account number does not exist");
		}
		long millis= System.currentTimeMillis();
		String refId="Ref"+String.valueOf(millis);
		recieverBalance=recieverBalance+amount;
		senderBalance=senderBalance-amount;
		changeBalance(recieverBalance,recieverAccountNumber);
		changeBalance(senderBalance,senderAccountNumber);
		int sendUserId=getUserId(senderAccountNumber);
		int recieverUserId=getUserId(recieverAccountNumber);
		TransactionPojo sender =new TransactionPojo();
		sender.setPrimaryAccount(senderAccountNumber);
		sender.setSecondaryAccount(recieverAccountNumber);
		sender.setUserId(sendUserId);
		sender.setAmounts(amount);
		sender.setType("Debit");
		sender.setBalance(senderBalance);
		sender.setTransactionTime(millis);
		sender.setReferenceId(refId);
		updateTheTransactionTable(sender);
		
		TransactionPojo reciever =new TransactionPojo(recieverUserId,recieverAccountNumber,senderAccountNumber,"Credit",amount,recieverBalance);
		reciever.setTransactionTime(millis);
		reciever.setReferenceId(refId);
		updateTheTransactionTable(reciever);
	}

	@Override
	public void deposit(int userId, long accountNumber, int amount) throws UserException {
		long balance=getBalance(accountNumber);
		balance=balance+amount;
		changeBalance(balance,accountNumber);
		TransactionPojo input = new TransactionPojo();
		input.setBalance(balance);
		input.setUserId(userId);
		input.setPrimaryAccount(accountNumber);
		input.setAmounts(amount);
		updateForDeposit(input);



	}
	@Override
	public void withDraw(int userId, long accountNumber, int amount) throws UserException {
		long balance=getBalance(accountNumber);
		if(balance<amount) {
			throw new UserException("The balance is lesser than transaction amount");
		}
		TransactionPojo input = new TransactionPojo();
		input.setBalance(balance);
		input.setUserId(userId);
		input.setPrimaryAccount(accountNumber);
		input.setAmounts(amount);
		
		//changeBalance(balance,accountNumber);
		updateForWithdraw(input);

	}

	@Override
	public void changePassword(int userId, String inputPassword) throws UserException {
		String sql ="UPDATE PersonTable SET Password = ? WHERE UserId = ?";


		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){

			myStatement.setString(1, inputPassword);
			myStatement.setInt(2, userId);

			myStatement.executeUpdate();


		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}




	}



	@Override
	public void edit(PersonPojo inputPerson) throws UserException {
		// TODO Auto-generated method stub
		
		String sql="UPDATE PersonTable SET Name = ?,Mobile = ?,Email = ? WHERE UserId = ?";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement=inputConn.prepareStatement(sql);){
			
			myStatement.setString(1, inputPerson.getName());
			myStatement.setLong(2, inputPerson.getMobileNumber());
			myStatement.setString(3, inputPerson.getEmailId());
			myStatement.setInt(4, inputPerson.getUserId());
			myStatement.executeUpdate();

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}


	@Override
	public Map<Integer, PersonPojo> viewUserDetails(String role,int userId) throws UserException {
	
		String sql="select * from PersonTable inner join CustomerTable on UserId=CustomerId where UserId = ?";
		if(role.equals("admin")) {
			sql="select * from PersonTable where UserId = ?";
		}
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer, PersonPojo> person=new HashMap<Integer, PersonPojo>();
		
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
				PersonPojo person1=new PersonPojo();
				myStatement.setInt(1, userId);
				resultSet = myStatement.executeQuery();
				if(resultSet.next()) {
					person1=createPersonPojo(resultSet);
				}
				person.put(userId, person1);
			
			
			
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);


		}	
		return person;

	}
	@Override
	public Map<Integer,TransactionPojo> getTransactionDetails(long accountNumber) throws UserException {
		String sql="select * from TransactionTable where TransactionTable.PrimaryAccount= ? ";
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer,TransactionPojo>  transactionMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			myStatement.setLong(1, accountNumber);
			resultSet=myStatement.executeQuery();
			transactionMap=convertToTransactionMap(resultSet);

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}

		return transactionMap;
	}
	@Override
	public List<Long> getUserAccountList(String status,int... userId) throws UserException {
		String sql="select AccountDetails.AccountNumber from AccountDetails where AccountStatus=? AND UserId=?";
		if(userId.length==0) {
			 sql="select AccountDetails.AccountNumber from AccountDetails where AccountStatus=?";
		}
		
	
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		List<Long> accountList=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			myStatement.setString(1, status);
			if(userId.length>0) {
				myStatement.setInt(2, userId[0]);
			}
				
			
			
			resultSet = myStatement.executeQuery();
			accountList=new ArrayList<Long>();
			while(resultSet.next()) {
				accountList.add(resultSet.getLong("AccountNumber"));
			}
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);


		}	
		return accountList;



	}
	
	@Override
	public Map<Integer, TransactionPojo> getAllTransactionDetails() throws UserException {
		String sql="select * from TransactionTable  ";
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer,TransactionPojo>  transactionMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			resultSet=myStatement.executeQuery();
			transactionMap=convertToTransactionMap(resultSet);
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}

		return transactionMap;

	}
	@Override
	public Map<Integer,Map<Long,AccountsPojo>> viewAccountDetails( String status,int... userId) throws UserException {
		String sql="select * from AccountDetails where  AccountStatus=? and UserId=? " ;
		if(userId.length==0) {
			 sql="select * from AccountDetails where AccountStatus=?  " ;
		}
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer,Map<Long,AccountsPojo>>   accountsMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			myStatement.setString(1, status);
			if(userId.length>0) {
				myStatement.setInt(2, userId[0]);
			}
			
			resultSet=myStatement.executeQuery();
			accountsMap=convertToAccountsMap(resultSet);
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}
		return accountsMap;
	}
	@Override
	public Map<Integer,Map<Long,AccountsPojo>>  getAllAccountDetails() throws UserException {
		String sql="select * from AccountDetails " ;
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer,Map<Long,AccountsPojo>>  accountsMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			resultSet=myStatement.executeQuery();
			accountsMap=convertToAccountsMap(resultSet);
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}
		return accountsMap;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override

	public Map<Integer,PersonPojo> getAllUserDetails(String...specification)throws UserException{
		String sqlSelect="";
		if(specification.length==0) {
			sqlSelect="select * from PersonTable inner join CustomerTable on PersonTable.UserId=CustomerTable.CustomerId where PersonTable.Role='user'";
		}
		else {
			if(specification[0].equals("Inactive")) {
				 sqlSelect="select * from PersonTable inner join CustomerTable on PersonTable.UserId=CustomerTable.CustomerId where PersonTable.Role='user' AND CustomerTable.CustomerStatus='Inactive'";
			}
			else {
				sqlSelect="select * from PersonTable inner join CustomerTable on PersonTable.UserId=CustomerTable.CustomerId where PersonTable.Role='user' AND CustomerTable.CustomerStatus='Active'";
			}
		}
		
		
		
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer,PersonPojo>  userMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sqlSelect);
			resultSet=myStatement.executeQuery();
			userMap=convertToUserMap(resultSet);

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}
		return userMap;

	}
	@Override
	public Map<Integer, TransactionRequest>  getWithdrawlRequests() throws UserException {
		String sql="select * from TransactionRequest Where Status='Requested'";
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer, TransactionRequest> transactionMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			resultSet=myStatement.executeQuery();
			transactionMap=convertToTransactionRequest(resultSet);

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}

		return transactionMap;
	}
	@Override
	public void updateTheTransactionRequest(TransactionRequest input) throws UserException {
		String sql="update TransactionRequest set status=? where RequestId=? ";
		TransactionPojo transaction =new TransactionPojo();
		long balance=0;
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setString(1, input.getStatus());
			myStatement.setInt(2, input.getRequestId());
			myStatement.executeUpdate();
			if(input.getStatus().equals("Success")) {
				balance=getBalance(input.getAccountNumber());
				balance=balance-input.getAmount();
				transaction.setStatus("Success");
				transaction.setBalance(balance);
				transaction.setTransactionId(input.getTransactionId());
				changeBalance(balance,input.getAccountNumber());
				
			}
		
			else {
				balance=getBalance(input.getAccountNumber());
				transaction.setStatus("Rejected");
				transaction.setBalance(balance);
				transaction.setTransactionId(input.getTransactionId());
			}
			
			changeForWithdraw(transaction);
			
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	@Override
	public void requestForApproval(NonTransactionRequest input) throws UserException {
		insertNonTransactionRequest(input);
		
	}
	@Override
	public void updateTheRequest(NonTransactionRequest input, boolean flagForChange) throws UserException{
		changeForNonTransactionRequest(input,flagForChange);
	}
	@Override
	public Map<Integer, NonTransactionRequest>  getAllAccountRequests(int... userId) throws UserException {
		
		String sql="select * from NonTransactionRequest Where RequestStatus='Requested'";
		if(userId.length>0) {
			sql="select * from NonTransactionRequest Where RequestStatus='Requested' AND UserId=?";
		}
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		Map<Integer, NonTransactionRequest> transactionMap=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			if(userId.length>0) {
				myStatement.setInt(1,userId[0]);
			}
			resultSet=myStatement.executeQuery();
			transactionMap=convertToNonTransactionRequest(resultSet);

		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);

		}

		return transactionMap;
	}
	@Override
	public int insertUser(PersonPojo input) throws UserException {
		int userId=insertThePersonTable(input);
		return userId;
	}
	@Override
	public long insertNewAccount(AccountsPojo input) throws UserException {
		long accountNumber=0l;
		String sql="insert into AccountDetails (Balance,Branch,IFSC,UserId,AccountStatus) values(?,?,?,?,'Active')";
		
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
			myStatement.setLong(1, input.getBalance());
			myStatement.setString(2, input.getBranch());
			myStatement.setString(3, input.getIfscCode());
			myStatement.setInt(4, input.getUserId());
			myStatement.executeUpdate();
			try(ResultSet transactionIdKeys=myStatement.getGeneratedKeys();){
				if(transactionIdKeys.next()) {
					accountNumber=transactionIdKeys.getInt(1);
				}
			}
			
			return accountNumber;
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	@Override
	public String getThePassword(int userId) throws UserException {
		String sql="select PersonTable.Password from PersonTable Where UserId=?";
		String password="";
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			myStatement.setInt(1, userId);
			 resultSet=myStatement.executeQuery();
				if(resultSet.next()) {
					password=resultSet.getString("Password");
				}
		
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
		finally {
			close(resultSet,myStatement,inputConn);

		}
		return password;
	}
	@Override
	public void setThePassword(int userId,String password) throws UserException {
	 String sql="update PersonTable set Password=? where UserId=?";
	 Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			myStatement.setString(1, password);
			myStatement.setInt(2, userId);
			myStatement.executeUpdate();
			}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
		finally {
			close(resultSet,myStatement,inputConn);

		}
	}
	@Override
	public List<Integer> getUserIdList() throws UserException {
		String sql="Select PersonTable.UserId from PersonTable inner join CustomerTable on PersonTable.UserId=CustomerTable.CustomerId";
		Connection inputConn =null;
		PreparedStatement myStatement = null;
		ResultSet resultSet=null;
		List<Integer> userIdList=null;
		try {
			inputConn = getConnection();
			myStatement = inputConn.prepareStatement(sql);
			resultSet = myStatement.executeQuery();
			userIdList=new ArrayList<Integer>();
			while(resultSet.next()) {
				userIdList.add(resultSet.getInt("UserId"));
			}
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}

		finally {
			close(resultSet,myStatement,inputConn);


		}	
		return userIdList;

	}
	@Override
	public void changeTheAccountStatus(long account,String status) throws UserException {
		String sql="Update AccountDetails set AccountStatus=? where AccountNumber=?";
		try (Connection inputConn = getConnection();
				PreparedStatement myStatement = inputConn.prepareStatement(sql);){
			myStatement.setString(1, status);
			myStatement.setLong(2, account);
			
			myStatement.executeUpdate();
		}
		catch(SQLException sqlEx) {
			throw new UserException("You enter a wrong query",sqlEx);
		}
	}
	

}
