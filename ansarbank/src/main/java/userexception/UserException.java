package userexception;

public class UserException extends Exception {
	public UserException (String message)    
	    {    
	        // calling parent Exception class constructor    
	        super(message);    
	    }    
	    public UserException (String message,Exception e)    
	    {    
	        // calling parent Exception class constructor    
	        super(message,e);    
	    }  
}
