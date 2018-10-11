package protocols;


import org.json.simple.JSONObject;

public class BO01 {
    public JSONObject parseFunction(String TESTSTRING){
        JSONObject opt = new JSONObject();

        String alarmCode = TESTSTRING.substring(17,Math.min(TESTSTRING.length(),18));
        // Switch case to get alarm type
        switch(Integer.parseInt(alarmCode)){

            case 0:
                System.out.println("Vehicle Power OFF");
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println("Vehicle robbery (SOS help)");
                break;
            case 3:
                System.out.println("Vehicle anti-theft and alarming");
                break;
            case 4:
                System.out.println("Lower speed alert");
                break;
            case 5:
                System.out.println("Over speed alert");
                break;
            case 6:
                System.out.println("Vehicle is out of GEO Fencing");
                break;


        }

        // code to get time
        String time = TESTSTRING.substring(18,Math.min(TESTSTRING.length(),24));
        // System.out.println("Time is "+time);
        String timeHR = time.substring(0,Math.min(time.length(),2));
        String timeMin = time.substring(2,Math.min(time.length(),4));
        String timeSec = time.substring(4,Math.min(time.length(),6));
        System.out.println("Time is "+timeHR+":"+timeMin+":"+timeSec);

        // to check if data is available
        String isDataAvailable = TESTSTRING.substring(24,Math.min(TESTSTRING.length(),25));
        System.out.println("isData available "+isDataAvailable);

        // North degree .. Took 2 bytes 25 to 27 because as per documents lat Degree will have 2 bytes of data only
        String northDeg = TESTSTRING.substring(25,Math.min(TESTSTRING.length(),27));
        System.out.println("north deg is "+northDeg);

        // get lat
        // to get index of N i.e north
        int nthPos = TESTSTRING.indexOf('N');
        // System.out.println("Nth position is "+nthPos);

        // to get latitude
        String latMin = TESTSTRING.substring(27,Math.min(TESTSTRING.length(),nthPos));
        System.out.println("Lat minutes is "+latMin);

        // get long degree i.e eastDegree adde 4 asin documents size of long degree is 3 bytes
        int startLongPosition = nthPos+1;
        int endLongposition = nthPos+4;
        String eastDeg = TESTSTRING.substring(startLongPosition,Math.min(TESTSTRING.length(),endLongposition));
        System.out.println("east Degree is "+eastDeg);

        // get index of E for east i.e longitude
        int ethPos = TESTSTRING.indexOf('E');

        String longMin = TESTSTRING.substring(endLongposition,Math.min(TESTSTRING.length(),ethPos));
        System.out.println("long min is "+longMin);

        // latitude - longitude calculation
        Double latitude = Double.parseDouble(northDeg) + (Double.parseDouble(latMin)/60);
        Double longitude = Double.parseDouble(eastDeg) + (Double.parseDouble(longMin)/60);

        System.out.println("Latitude is : "+latitude +" longitude is : "+longitude);


        // get Speed added 5 as speed will be of 5 bytes
        int startSpeedChar = ethPos + 1 ;
        int speedLength  = ethPos + 6 ;
        String speed = TESTSTRING.substring(startSpeedChar,Math.min(TESTSTRING.length(),speedLength));
        System.out.println("Speed is "+speed+" km/h");

        // speed HH
        int startSpeedHHChar = speedLength ;
        int endSpeedHHChar = startSpeedHHChar + 2 ;
        String speedHH = TESTSTRING.substring(startSpeedHHChar,Math.min(TESTSTRING.length(),endSpeedHHChar));
        System.out.println("Speed HH is "+speedHH);

        // speed MM
        int startSpeedMMChar = endSpeedHHChar;
        int endSpeedMMChar = startSpeedMMChar + 2 ;
        String speedMM = TESTSTRING.substring(startSpeedMMChar,Math.min(TESTSTRING.length(),endSpeedMMChar));
        System.out.println("Speed MM is "+speedMM);

        // speed SS
        int startSpeedSSChar = endSpeedMMChar;
        int endSpeedSSChar = startSpeedSSChar + 2 ;
        String speedSS = TESTSTRING.substring(startSpeedSSChar,Math.min(TESTSTRING.length(),endSpeedSSChar));
        System.out.println("Speed SS is "+speedSS);
        System.out.println("Speed HH:MM:SS : "+speedHH+" : "+speedMM+" : "+speedSS);

        int LthPos = TESTSTRING.indexOf('L');
        String orientation = TESTSTRING.substring(endSpeedSSChar,Math.min(TESTSTRING.length(),LthPos));
        System.out.println("Lth pos is "+LthPos +" and orientation is "+orientation);

        // adde 5 as size of speed signal is 5 byte
        int startDistChar = LthPos + 1;
        int endDistChar = startDistChar + 5 ;
        String distance = TESTSTRING.substring(startDistChar,Math.min(TESTSTRING.length(),endDistChar));
        System.out.println("distance travelled by veh is "+distance+" KM");




        return opt;
    }
}
