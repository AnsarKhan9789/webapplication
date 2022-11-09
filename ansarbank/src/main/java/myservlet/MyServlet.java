package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import methods.AccountsPojo;
import methods.BankingForAdmin;
import methods.BankingForUser;
import methods.LoginPage;
import methods.NonTransactionRequest;
import methods.PersonPojo;
import methods.TransactionPojo;
import methods.TransactionRequest;
import userexception.UserException;
import utilpackage.HelperUtil;

/**
 * Servlet implementation class myservlet
 */
@WebServlet("/add")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("targetToMenu");
		HttpSession session = request.getSession(false);
		BankingForUser user= new BankingForUser();
		BankingForAdmin admin =new BankingForAdmin();


		if(!Objects.isNull(name)) {
			int userId=(int) session.getAttribute("userId");
			String role=(String) session.getAttribute("role");

			switch(name) {
			case "Home":
				String des="/JSP/Home.jsp";
				RequestDispatcher reqDis=request.getRequestDispatcher(des);
				reqDis.forward(request, response);
				break;
			case "Deposit":
				String des1="/JSP/Deposit.jsp";
				RequestDispatcher reqDis1=request.getRequestDispatcher(des1);
				reqDis1.forward(request, response);
				break;
			case "Withdraw":
				String des2="/JSP/Withdraw.jsp";
				RequestDispatcher reqDis2=request.getRequestDispatcher(des2);
				reqDis2.forward(request, response);
				break;
			case "Transaction":
				String des3="/JSP/Transaction.jsp";
				RequestDispatcher reqDis3=request.getRequestDispatcher(des3);
				reqDis3.forward(request, response);
				break;
			case "My details":
				try {
					Map<Integer,PersonPojo> userList=user.viewUserDetails(userId,role);
					PersonPojo userDetails=userList.get(userId);
					request.setAttribute("roles",role);
					request.setAttribute("userDetails",userDetails);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String des4="/JSP/MyDetails.jsp";
				RequestDispatcher reqDis4=request.getRequestDispatcher(des4);
				reqDis4.forward(request, response);
				break;
			case "Show Statement":
				List accountlist = (List) session.getAttribute("listCategory");
				long accountNumber3=(long) accountlist.get(0);
				try {
					Map<Integer,TransactionPojo> transactionMap=user.getTransactionDetails(accountNumber3);
					request.setAttribute("transactionMap", transactionMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String des5="/JSP/Statement.jsp";
				RequestDispatcher reqDis5=request.getRequestDispatcher(des5);
				reqDis5.forward(request, response);
				break;


			case "Account Details":
				try {
					Map<Integer,Map<Long,AccountsPojo>> acountList=user.viewAccountDetails("Active",userId);
					Map<Long,AccountsPojo> accountMap= acountList.get(userId);
					request.setAttribute("accountMap",accountMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String des7="/JSP/AccountDetails.jsp";
				RequestDispatcher reqDis7=request.getRequestDispatcher(des7);
				reqDis7.forward(request, response);
				break;
			case"requestForAccount":
				Map<Integer, NonTransactionRequest>  transactionMap=null;
				try {
					  transactionMap =user.getAccountRequests(userId);
				} catch (UserException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(transactionMap.isEmpty()) {
					try {
						List<Long> accountList=user.getAccountList("Inactive",userId);
						request.setAttribute("accountList", accountList);
						request.setAttribute("flag", 0);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					request.setAttribute("message", "Already request sent");
					request.setAttribute("transactionMap", transactionMap);
				}
				
				String destination1="/JSP/requestForAccount.jsp";
				RequestDispatcher requestDispatcher1=request.getRequestDispatcher(destination1);
				requestDispatcher1.forward(request, response);
				break;

			case "Change Password":
				
				String des8="/JSP/changepassword.jsp";
				RequestDispatcher reqDis8=request.getRequestDispatcher(des8);
				reqDis8.forward(request, response);
				break;
			case "Logout":
				String des9="JSP/login.jsp";
				session.removeAttribute("userId");
				session.invalidate();
				response.sendRedirect(des9);
				break;
				
			}
		}
		else {

			name=request.getParameter("admin");
			switch(name) {

			case "All User Details":

				try {
					Map<Integer,PersonPojo> allUserDetailsMap=admin.getAllUserDetails();
					request.setAttribute("allUserDetailsMap", allUserDetailsMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				String destination="/JSP/AllUserDetails.jsp";
				RequestDispatcher requsetDispatcher=request.getRequestDispatcher(destination);
				requsetDispatcher.forward(request, response);
				break;
			case "Non Transaction":
				Map<Integer,NonTransactionRequest> nonTransactionRequestMap=null;
				try {
					 nonTransactionRequestMap=admin.getAccountRequests();
					request.setAttribute("nonTransactionRequestMap", nonTransactionRequestMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(nonTransactionRequestMap.isEmpty()) {
					request.setAttribute("flag", 0);
					
				}
				String destination1="/JSP/NonTransaction.jsp";
				RequestDispatcher requsetDispatcher1=request.getRequestDispatcher(destination1);
				requsetDispatcher1.forward(request, response);
				break;
			case "Withdrawl Request":
				Map<Integer,TransactionRequest> withdrawlRequestMap=null;
				try {
					 withdrawlRequestMap=admin.getWithdrawlRequests();
					request.setAttribute("withdrawlRequestMap", withdrawlRequestMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(withdrawlRequestMap.isEmpty()) {
					request.setAttribute("flag", 0);
					
				}
				String destination2="/JSP/withdrawlRequest.jsp";
				RequestDispatcher requsetDispatcher2=request.getRequestDispatcher(destination2);
				requsetDispatcher2.forward(request, response);
				break;
			case "Statement":
				try {
					List<Long> accountList=admin.getAccountList("Active");
					request.setAttribute("listCategory", accountList);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Map<Integer,TransactionPojo> transactionMap=admin.getAllTransactionDetails();
					request.setAttribute("transactionMap", transactionMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String des5="/JSP/Statement.jsp";
				RequestDispatcher reqDis5=request.getRequestDispatcher(des5);
				reqDis5.forward(request, response);
				break;
			case "Account Details":
				try {
					List<Integer> userIdList=admin.getAllUserIdList();
					request.setAttribute("listCategory", userIdList);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Map<Integer,Map<Long,AccountsPojo>> accountMap=admin.getAllAccountsDetails();
					request.setAttribute("accountMap",accountMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String des7="/JSP/allAccountDetails.jsp";
				RequestDispatcher reqDis7=request.getRequestDispatcher(des7);
				reqDis7.forward(request, response);
				break;

			case "Add":
				String des8="/JSP/AddUser.jsp";
				RequestDispatcher reqDis8=request.getRequestDispatcher(des8);
				reqDis8.forward(request, response);
				break;
			}

		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name1=request.getParameter("login");
		BankingForUser user= new BankingForUser();
		BankingForAdmin admin =new BankingForAdmin();
		HttpSession session = request.getSession(true);
		String role=null;
		if(!Objects.isNull(name1)) {
			int userId=Integer.parseInt(request.getParameter("CustomerId"));
			String password=request.getParameter("password");

			

			LoginPage login = new LoginPage();
			try {
				role=login.getUserIdDetails(userId, password);
				session.setAttribute("role", role);
				session.setAttribute("userId", userId);
				if(role.equals("user")){
					
					try {
						List<Long> accountList=user.getAccountList("Active",userId);
						session.setAttribute("listCategory", accountList);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String des="/JSP/User.jsp";
					RequestDispatcher reqDis=request.getRequestDispatcher(des);
					reqDis.forward(request, response);

				}
				else {
					String des="/JSP/Admin.jsp";
					RequestDispatcher reqDis=request.getRequestDispatcher(des);
					reqDis.forward(request, response);
				}
			} catch (UserException e) {
				String message=e.getMessage();
				request.setAttribute("message", message);
				String des="/JSP/login.jsp";
				RequestDispatcher reqDis=request.getRequestDispatcher(des);
				reqDis.forward(request, response);

			}


		}
		name1=request.getParameter("admin");
		if(!Objects.isNull(name1)) {

			TransactionRequest transRequest = new TransactionRequest();
			NonTransactionRequest nonTransaction = new NonTransactionRequest(); 

			switch(name1) {
			case "Accept the withdrawl":
				int reqId = Integer.parseInt(request.getParameter("requestId"));
				long accountNumber =Long.parseLong(request.getParameter("accountNumber"));
				int transactionId =Integer.parseInt(request.getParameter("transactonId"));
				int amount =Integer.parseInt(request.getParameter("amount"));

				transRequest.setStatus("Success");
				transRequest.setRequestId(reqId);
				transRequest.setAccountNumber(accountNumber);
				transRequest.setTransactionId(transactionId);
				transRequest.setAmount(amount);
				try {
					admin.updateTheTransactionRequest(transRequest);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Map<Integer,TransactionRequest> withdrawlRequestMap=admin.getWithdrawlRequests();
					request.setAttribute("withdrawlRequestMap", withdrawlRequestMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String destination2="/JSP/withdrawlRequest.jsp";
				RequestDispatcher requsetDispatcher2=request.getRequestDispatcher(destination2);
				requsetDispatcher2.forward(request, response);

				break;
			case "Reject the withdrawl":
				int req1 = Integer.parseInt(request.getParameter("requestId"));
				long accountNumber1 =Long.parseLong(request.getParameter("accountNumber"));
				int transactionId1 =Integer.parseInt(request.getParameter("transactonId"));
				int amount1 =Integer.parseInt(request.getParameter("amount"));
				transRequest.setStatus("Rejected");
				transRequest.setRequestId(req1);
				transRequest.setAccountNumber(accountNumber1);
				transRequest.setTransactionId(transactionId1);
				transRequest.setAmount(amount1);
				try {
					admin.updateTheTransactionRequest(transRequest);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Map<Integer,TransactionRequest> withdrawlRequestMap=admin.getWithdrawlRequests();
					request.setAttribute("withdrawlRequestMap", withdrawlRequestMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String destination3="/JSP/withdrawlRequest.jsp";
				RequestDispatcher requsetDispatcher3=request.getRequestDispatcher(destination3);
				requsetDispatcher3.forward(request, response);
				break;
			case "acceptNonTrans":
			
				int req2 = Integer.parseInt(request.getParameter("requestId"));
				long accountNumber2 =Long.parseLong(request.getParameter("accountNumber"));
				String status=request.getParameter("Status");
				nonTransaction.setRequestId(req2);
				nonTransaction.setAccountNumber(accountNumber2);
				nonTransaction.setRequestStatus("Success");
				
				if(status.equals("Active")) {
					nonTransaction.setStatus("Inactive");
					try {
						admin.updateTheRequest(nonTransaction, true);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					nonTransaction.setStatus("Active");
					try {
						admin.updateTheRequest(nonTransaction, true);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				String destination11="/JSP/NonTransaction.jsp";
				RequestDispatcher reqDis11=request.getRequestDispatcher(destination11);
				request.setAttribute("message", "Success");
				reqDis11.forward(request, response);
				break;
			case "rejectNonTrans":
				int req3 = Integer.parseInt(request.getParameter("requestId"));
				long accountNumber3 =Long.parseLong(request.getParameter("accountNumber"));
				String status1=request.getParameter("Status");
				nonTransaction.setRequestId(req3);
				nonTransaction.setAccountNumber(accountNumber3);
				nonTransaction.setRequestStatus("Rejected");
				nonTransaction.setStatus(status1);
					try {
						admin.updateTheRequest(nonTransaction, false);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				String des1="/JSP/NonTransaction.jsp";
				RequestDispatcher reqDis1=request.getRequestDispatcher(des1);
				request.setAttribute("message", "Success");
				reqDis1.forward(request, response);
				break;
			case "Add":
				String userName=request.getParameter("name");
				long mobileNumber = Long.parseLong(request.getParameter("mobile"));
				String email =request.getParameter("email");
				long aadhar= Long.parseLong(request.getParameter("Aadhar"));
				String panCard=request.getParameter("PanCard");
				PersonPojo userDetails = new PersonPojo();
				userDetails.setName(userName);
				userDetails.setMobileNumber(mobileNumber);
				userDetails.setEmailId(email);
				userDetails.setAadharCard(aadhar);
				userDetails.setPanCard(panCard);
				userDetails.setUserStatus("Active");
				userDetails.setPassword("12345678");
				userDetails.setRole("user");
				
				try {
					boolean email1=HelperUtil.isCorrectEmail(email);
					boolean number1=HelperUtil.isValidMobile(mobileNumber);
					boolean name=HelperUtil.isValidName(userName);
					if(email1==false||number1==false||name==false) {
						request.setAttribute("userDetails",userDetails);
						request.setAttribute("editMessage", "Please Enter in valid format");
						String des44="/JSP/AddUser.jsp";
						RequestDispatcher reqDis44=request.getRequestDispatcher(des44);
						reqDis44.forward(request, response);
					}
				}
				catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int userId=0;
				try {
					 userId=admin.addTheUser(userDetails);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String editMessage ="our User Added sucessfully";
				request.setAttribute("editMessage", editMessage);
				request.setAttribute("userId", userId);
				String des5="/JSP/AddAccount.jsp";
				RequestDispatcher reqDis5=request.getRequestDispatcher(des5);
				reqDis5.forward(request, response);
				break;
			case"AddAccount":
				long newAccountNumber=0l;
				int userId10=Integer.parseInt(request.getParameter("userId"));
				String branch=request.getParameter("Branch");
				long balance=Long.parseLong(request.getParameter("Balance"));
				AccountsPojo accounts = new AccountsPojo();
				accounts.setUserId(userId10);		
				accounts.setBranch(branch);
				switch(branch) {
				case"Karaikudi":
					
					accounts.setIfscCode("kar123");
					break;
				case"Pudukkottai":
					accounts.setIfscCode("pud123");
					break;
				case"Chennai":
					accounts.setIfscCode("che123");
					break;
				}
				accounts.setBalance(balance);
				try {
					 newAccountNumber=admin.addTheAccount(accounts);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Map<Integer,PersonPojo> allUserDetailsMap=admin.getAllUserDetails();
					request.setAttribute("allUserDetailsMap", allUserDetailsMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				String editMessage1 =" User account Added sucessfully";
				request.setAttribute("editMessage1", editMessage1);
				String des6="/JSP/AllUserDetails.jsp";
				RequestDispatcher reqDis6=request.getRequestDispatcher(des6);
				reqDis6.forward(request, response);
				break;
			case"Edit the user":
				int userId12=Integer.parseInt(request.getParameter("userId"));
				
				String userName12=request.getParameter("name");
				long mobileNumber12 = Long.parseLong(request.getParameter("mobile"));
				String email12 =request.getParameter("email");
				String roleForUser=request.getParameter("roleForUser");
			
				PersonPojo userDetails1 = new PersonPojo();
				userDetails1.setName(userName12);
				userDetails1.setMobileNumber(mobileNumber12);
				userDetails1.setEmailId(email12);
				userDetails1.setUserId(userId12);
				userDetails1.setRole(roleForUser);
			
				try {
					Map<Integer,PersonPojo> userList=admin.viewUserDetails(userId12,roleForUser);
					PersonPojo userDetails11=userList.get(userId12);
					request.setAttribute("userDetails",userDetails11);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("roles", roleForUser);
				String des4="/JSP/MyDetails.jsp";
				RequestDispatcher reqDis4=request.getRequestDispatcher(des4);
				reqDis4.forward(request, response);
				break;
			case "All user details":
				String specification=request.getParameter("Specification");
				System.out.println(specification);
				switch(specification) {
				case "All":{
					try {
						Map<Integer,PersonPojo> allUserDetailsMap=admin.getAllUserDetails();
						request.setAttribute("allUserDetailsMap", allUserDetailsMap);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
				}
					
				case "Active":{
					try {
						Map<Integer,PersonPojo> allUserDetailsMap=admin.getAllUserDetails("Active");
						request.setAttribute("allUserDetailsMap", allUserDetailsMap);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
				}
					
				case "Inactive":{
					try {
						Map<Integer,PersonPojo> allUserDetailsMap=admin.getAllUserDetails("Inactive");
						if(allUserDetailsMap.isEmpty()) {
							request.setAttribute("flag", 0);
						}
						else {
							request.setAttribute("allUserDetailsMap", allUserDetailsMap);
						}
						
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
				}
					
				
				}
				String destination="/JSP/AllUserDetails.jsp";
				RequestDispatcher requsetDispatcher=request.getRequestDispatcher(destination);
				requsetDispatcher.forward(request, response);
				break;
			case"Account Details":
				int usersUserId12 = Integer.parseInt(request.getParameter("userId"));
				
				String status12=request.getParameter("Status");
			
				if(usersUserId12==0) {
					try {
						Map<Integer,Map<Long,AccountsPojo>> acountList=user.viewAccountDetails(status12);
						request.setAttribute("accountMap",acountList);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					try {
						Map<Integer,Map<Long,AccountsPojo>> acountList=user.viewAccountDetails(status12,usersUserId12);
						request.setAttribute("accountMap",acountList);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					List<Integer> userIdList=admin.getAllUserIdList();
					request.setAttribute("listCategory", userIdList);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String destination1="/JSP/allAccountDetails.jsp";
				RequestDispatcher requsetDispatcher1=request.getRequestDispatcher(destination1);
				requsetDispatcher1.forward(request, response);
				break;
			case "Change Status":
				String accountStatus=request.getParameter("StatusForAccount");
				long accountNumber12=Long.parseLong(request.getParameter("account"));
				if(accountStatus.equals("Active")) {
					try {
						admin.changeTheAccountStatus(accountNumber12, "Inactive");
					} catch (UserException e) {
						// TODO Auto-generated catch block
						System.out.print("true");
						e.printStackTrace();
					}
				}
				else {
					try {
						admin.changeTheAccountStatus(accountNumber12, "Active");
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					List<Integer> userIdList=admin.getAllUserIdList();
					request.setAttribute("listCategory", userIdList);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Map<Integer,Map<Long,AccountsPojo>> accountMap=admin.getAllAccountsDetails();
					request.setAttribute("accountMap",accountMap);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String des7="/JSP/allAccountDetails.jsp";
				RequestDispatcher reqDis7=request.getRequestDispatcher(des7);
				reqDis7.forward(request, response);
				break;
				
			}



		}
		name1=request.getParameter("user");	
		HttpSession session1 = request.getSession(false);
		if(session1.getAttribute("userId")==null) {
			String des9="JSP/login.jsp";
			request.setAttribute("message", "Please Re Login");
			session.removeAttribute("userId");
			session.invalidate();
			response.sendRedirect(des9);
		}
		int userId=(int) session1.getAttribute("userId");
		if(!Objects.isNull(name1)) {
			switch(name1) {
			case "MakeDeposit":
				long accountNumber =Long.parseLong(request.getParameter("account"));
				int amount=Integer.parseInt(request.getParameter("amount"));
				try {
					user.deposit(userId, accountNumber, amount);
					String message="Your Transaction Completed Successfully";
					request.setAttribute("message", message);
				} catch (UserException e) {
					String message=e.getMessage();
					request.setAttribute("emessage", message);
					e.printStackTrace();
				}
				String des1="/JSP/Deposit.jsp";
				RequestDispatcher reqDis1=request.getRequestDispatcher(des1);
				reqDis1.forward(request, response);
				break;
			case "MakeWithDraw":
				long accountNumber1 =Long.parseLong(request.getParameter("account"));
				int amount1=Integer.parseInt(request.getParameter("amount"));
				try {
					user.withDraw(userId, accountNumber1, amount1);
					String message="Your Request sent Successfully";
					request.setAttribute("message", message);

				} catch (UserException e) {
					String message=e.getMessage();
					request.setAttribute("emessage", message);
					e.printStackTrace();
				}
				String des2="/JSP/Withdraw.jsp";
				RequestDispatcher reqDis2=request.getRequestDispatcher(des2);
				reqDis2.forward(request, response);
				break;
			case "Transaction":
				long accountNumber2 =Long.parseLong(request.getParameter("account"));
				int amount2=Integer.parseInt(request.getParameter("amount"));
				long recieverAccountNumber =Long.parseLong(request.getParameter("recieverAccountNumber"));
				try {
					user.sendMoney(accountNumber2, recieverAccountNumber, amount2);
					String message="Your Transaction completed Successfully";
					request.setAttribute("message", message);

				} catch (UserException e) {
					String message=e.getMessage();
					request.setAttribute("emessage", message);
					e.printStackTrace();
				}
				String des3="/JSP/Transaction.jsp";
				RequestDispatcher reqDis3=request.getRequestDispatcher(des3);
				reqDis3.forward(request, response);
				System.out.println("im in");
				break;
			case "statement":
				long accountNumber3 =Long.parseLong(request.getParameter("account"));
				try {
					Map<Integer,TransactionPojo> transactionMap=user.getTransactionDetails(accountNumber3);
					request.setAttribute("transactionMap", transactionMap);
					if(transactionMap.isEmpty()) {
						request.setAttribute("flag", 0);
						
					}

				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String roles=(String) session.getAttribute("role");
				if(roles.equals("admin")) {
					try {
						List<Long> accountList=admin.getAccountList("Active");
						request.setAttribute("listCategory", accountList);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				String des4="/JSP/Statement.jsp";
				RequestDispatcher reqDis4=request.getRequestDispatcher(des4);
				reqDis4.forward(request, response);
				break;
			case "edit":
				int userId12=Integer.parseInt(request.getParameter("UserId"));
				String oldName=request.getParameter("oldname");
				long oldMobileNumber12 = Long.parseLong(request.getParameter("oldnumber"));
				String oldEmail12 =request.getParameter("oldemail");
				String userName=request.getParameter("name");
				long mobileNumber = Long.parseLong(request.getParameter("mobile"));
				String email =request.getParameter("email");
				String roleForUser=request.getParameter("role");
				
				
				PersonPojo userDetails = new PersonPojo();
				userDetails.setName(userName);
				userDetails.setMobileNumber(mobileNumber);
				userDetails.setEmailId(email);
				userDetails.setUserId(userId12);
				userDetails.setRole(roleForUser);
				if(roleForUser.equals("user")) {
					long aadhar =Long.parseLong(request.getParameter("aadhar"));
					String pan = request.getParameter("Pan");
					String status=request.getParameter("status");
					userDetails.setAadharCard(aadhar);
					userDetails.setPanCard(pan);
					userDetails.setUserStatus(status);
				}
				if(oldName.equals(userName)&&email.equals(oldEmail12)&&mobileNumber==oldMobileNumber12 ) {
					
						request.setAttribute("userDetails",userDetails);
					
					request.setAttribute("roles", roleForUser);
					request.setAttribute("editMessage", "Please Edit and save");
					String des44="/JSP/MyDetails.jsp";
					RequestDispatcher reqDis44=request.getRequestDispatcher(des44);
					reqDis44.forward(request, response);
				}
				try {
					boolean email1=HelperUtil.isCorrectEmail(email);
					boolean number1=HelperUtil.isValidMobile(mobileNumber);
					boolean name=HelperUtil.isValidName(userName);
					if(email1==false||number1==false||name==false) {
						request.setAttribute("userDetails",userDetails);
						
						request.setAttribute("roles", roleForUser);
						request.setAttribute("editMessage", "Please Enter valid format");
						String des44="/JSP/MyDetails.jsp";
						RequestDispatcher reqDis44=request.getRequestDispatcher(des44);
						reqDis44.forward(request, response);
					}
				}
				catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					user.editUser(userDetails);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String editMessage ="Your edit is sucessfully";
				try {
					Map<Integer,PersonPojo> userList=user.viewUserDetails(userId12,roleForUser);
					PersonPojo userDetails1=userList.get(userId12);
					request.setAttribute("userDetails",userDetails1);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("roles",roleForUser);
				request.setAttribute("editMessage", editMessage);
				String des5="/JSP/MyDetails.jsp";
				RequestDispatcher reqDis5=request.getRequestDispatcher(des5);
				reqDis5.forward(request, response);
				break;
				
			case "Cancel for edit":
				String oldName1=request.getParameter("oldname");
				long oldMobileNumber1 = Long.parseLong(request.getParameter("oldnumber"));
				String oldEmail1 =request.getParameter("oldemail");
				int userId121=Integer.parseInt(request.getParameter("UserId"));
				String userName1=request.getParameter("name");
				long mobileNumber1 = Long.parseLong(request.getParameter("mobile"));
				String email1 =request.getParameter("email");
				String roleForUser1=request.getParameter("role");
				
				PersonPojo userDetails11 = new PersonPojo();
				userDetails11.setName(oldName1);
				userDetails11.setMobileNumber(oldMobileNumber1);
				userDetails11.setEmailId(oldEmail1);
				userDetails11.setUserId(userId121);
				userDetails11.setRole(roleForUser1);
			
				if(roleForUser1.equals("user")) {
					long aadhar =Long.parseLong(request.getParameter("aadhar"));
					String pan = request.getParameter("Pan");
					String status=request.getParameter("status");
					userDetails11.setAadharCard(aadhar);
					userDetails11.setPanCard(pan);
					userDetails11.setUserStatus(status);
				}
				request.setAttribute("userDetails",userDetails11);
				request.setAttribute("roles",roleForUser1);
				request.setAttribute("editMessage", "You cancel it");
				String des55="/JSP/MyDetails.jsp";
				RequestDispatcher reqDis55=request.getRequestDispatcher(des55);
				reqDis55.forward(request, response);
				
				break;
			case "MakeRequest":
				NonTransactionRequest requestForAccount = new NonTransactionRequest();
				long accountNumber11=Long.parseLong(request.getParameter("account"));
				requestForAccount.setAccountNumber(accountNumber11);
				requestForAccount.setUserId(userId);
				requestForAccount.setStatus("Inactive");
				try {
					user.requestForApproval(requestForAccount);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String editMessage1 ="Your Request sent sucessfully";
				request.setAttribute("message", editMessage1);
				String des6="/JSP/requestForAccount.jsp";
				RequestDispatcher reqDis6=request.getRequestDispatcher(des6);
				reqDis6.forward(request, response);
				break;
			case "showUserDetails":
				int usersUserId=Integer.parseInt(request.getParameter("userId"));
				String editMessage2 ="Your Request sent sucessfully";
				request.setAttribute("message", usersUserId);
				try {
					Map<Integer,PersonPojo> userList=user.viewUserDetails(usersUserId,"user");
					PersonPojo userDetails1=userList.get(usersUserId);
					request.setAttribute("userDetails",userDetails1);
					request.setAttribute("roles", "user");
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String des8="/JSP/MyDetails.jsp";
				RequestDispatcher reqDis8=request.getRequestDispatcher(des8);
				reqDis8.forward(request, response);
				break;
			case "Change password":
				String passcode ="";
				System.out.print(userId);
				try {
					 passcode = user.getPassword(userId);
	
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String password= request.getParameter("oldPassword");
				String message="failed";
				if(passcode.equals(password)) {
					String newPasscode=request.getParameter("confirmPassword");
					try {
						boolean password2=HelperUtil.isCorrectPassword(newPasscode);
						if(password2==false) {
							request.setAttribute("message", "Your password is not valid");
							String des9="/JSP/changepassword.jsp";
							RequestDispatcher reqDis9=request.getRequestDispatcher(des9);
							reqDis9.forward(request, response);
							break;
							
						}
					} catch (UserException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 message="Success";
					try {
						boolean result=user.setPassword(userId, newPasscode);
					} catch (UserException e) {
						// TODO Auto-generated catch block
						message="Failed";
						e.printStackTrace();
					}
					
				}
				request.setAttribute("message", message);
				String des9="/JSP/changepassword.jsp";
				RequestDispatcher reqDis9=request.getRequestDispatcher(des9);
				reqDis9.forward(request, response);
				break;
				

			}
		}
	}
}





