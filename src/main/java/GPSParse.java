/*Class for parsing string
* @Author subodh3344
* */

import org.json.simple.JSONObject;
import protocols.BO01;

public class GPSParse {

    //TODO Remove these hardcoded values
    private static String TESTSTRING = "(013612345678BO012061830A2934.0133N10627.2544E040.0080331309.6200000000L000770AD）"; // for Alarm message
   // private static String TESTSTRING = "(013612345678BP05000013612345678080524A2232.9806N11404.9355E000.1101241323.8700000000L000450AC)"; // for login message


    public static void main(String[] args){
        System.out.println("In project main class "+TESTSTRING);

        // Beginign Identifier
        String begIdentifier = TESTSTRING.substring(0,Math.min(TESTSTRING.length(),1));
       // System.out.println("Substring is  :"+begIdentifier);

        //Running NO/Time
        String RunTime = TESTSTRING.substring(1,Math.min(TESTSTRING.length(),13));
        System.out.println("Run time is :"+RunTime);

        //Command word
        String commandWo = TESTSTRING.substring(13,Math.min(TESTSTRING.length(),17));
        System.out.println("command Word : "+commandWo);

        // Message body
        String messageBody = TESTSTRING.substring(17,Math.min(TESTSTRING.length(),TESTSTRING.length()));
        System.out.println("messageBody is "+messageBody);
        switch(commandWo){
            case "BP00":
                System.out.println("One time calling message");
                break;
            case "BP03":
                break;
            case "BP04":
                break;
            case "BP05":
                // Login Message
                String deviceId = messageBody.substring(0,Math.min(messageBody.length(),15));
                System.out.print("device ID is "+deviceId);

                break;
            case "BP07":
                break;
            case "BP11":
                break;
            case "BP12":
                break;
            case "BP15":
                break;
            case "BP17":
                break;
            case "BO01":

                BO01 loginProtocl = new BO01();
                //TODO : Add data in JSON object and return from BO01 protocol
                JSONObject optData = loginProtocl.parseFunction(TESTSTRING);


                break;
        }

        //String

    }
}
