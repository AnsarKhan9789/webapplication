package utilpackage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import userexception.UserException;

public class HelperUtil {
    public static void nullCheck(Object obj) throws UserException {
        if (Objects.isNull(obj)) {
            throw new UserException("Object should not be null");
        }

    }
    public static String getDateTime(long milliSecond) {
		LocalDateTime date=Instant.ofEpochMilli(milliSecond).atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return date.format(format);
	}
	public static long minusDates(int days) {
		Instant dates=Instant.ofEpochMilli(System.currentTimeMillis());
		Instant afterdate=dates.minus(Period.ofDays(days));
		return afterdate.toEpochMilli();
	}
    public static boolean isValidMobile(long input) throws UserException {
    	nullCheck(input);
    	String input1=""+input+"";
    	Pattern patternforMobile= Pattern.compile("^[6-9][0-9]{9}");
    	Matcher matcherForMobile = patternforMobile.matcher(input1);
    	return matcherForMobile.find();
    	
    }
    public static boolean isValidName(String input) throws UserException {
    	nullCheck(input);
    	
    	Pattern patternforName= Pattern.compile("[a-zA-Z]+");
    	Matcher matcherForName= patternforName.matcher(input);
    	
    	return matcherForName.find();
    }
    public static boolean isCorrectEmail(String input) throws UserException {
    	nullCheck(input);
    	String match="^[a-z0-9]+@[a-z]+\\.[a-z]{2,6}$";
    	Pattern patternForEmail = Pattern.compile(match);
    	 Matcher matcherForEmail=patternForEmail.matcher(input);
    	 return matcherForEmail.find();
    }
    public static boolean isCorrectPassword(String input) throws UserException {
    	nullCheck(input);
    	String match="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
    	Pattern patternForPassword = Pattern.compile(match);
    	 Matcher matcherForPassword=patternForPassword.matcher(input);
    	
    	 return matcherForPassword.find();
    }
	public static long convertIntoMillisecond(String date) {
		String myDate=date.concat(" 12:00:00");
		LocalDateTime localDateTime = LocalDateTime.parse(myDate,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
    
}
