package ParentControlCode;

public interface ParentalControlService {
    boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception;
}
