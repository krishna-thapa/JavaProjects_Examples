package ParentControlCode;

import java.util.HashMap;
import java.util.Map;

public class MovieServiceImpl implements MovieService{

    //public static Map<String, String> map = new HashMap<>();

    @Override
    public String getParentalControlLevel(String titleId) throws TitleNotFoundException, TechnicalFailureException {
        Map<String, String> map = new HashMap<>();
        /*switch(titleId){
            case "1": return "U";
            case "2": return "PG";
            case "3": return "12";
            case "4": return "15";
            case "5": return "18";
            default:  throw new TitleNotFoundException("Input title is not found: " + titleId);
        }*/

        map.put("1", "U");
        map.put("2", "PG");
        map.put("3", "12");
        map.put("4", "15");
        map.put("5", "18");

        if(map.containsKey(titleId)){
            return map.get(titleId);
        }else{
            throw new TitleNotFoundException("Input title is not found: " + titleId);
        }
    }
}
