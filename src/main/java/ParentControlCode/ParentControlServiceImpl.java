package ParentControlCode;

import java.util.HashMap;
import java.util.Map;

public class ParentControlServiceImpl implements ParentalControlService{

    private MovieServiceImpl movieService;

    @Override
    public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws TitleNotFoundException, TechnicalFailureException {

        if(customerParentalControlLevel.isEmpty() || movieId.isEmpty()){
            throw new TechnicalFailureException("Null inputs");
        }

        movieService = new MovieServiceImpl();

        String movieParentControlLevel = movieService.getParentalControlLevel(movieId);
        if(compareParentControlLevelToInt(movieParentControlLevel) <= compareParentControlLevelToInt(customerParentalControlLevel)){
            return true;
        }
        return false;
    }

    public int compareParentControlLevelToInt(String controlLevel) throws TechnicalFailureException{
        Map<String,Integer> map = new HashMap<>();
        map.put("U", 1);
        map.put("PG", 2);
        map.put("12", 3);
        map.put("15", 4);
        map.put("18", 5);

        if(map.containsKey(controlLevel)){
            return map.get(controlLevel);
        }else{
            throw new TechnicalFailureException("Control Level not found: "+ controlLevel);
        }
    }
}
