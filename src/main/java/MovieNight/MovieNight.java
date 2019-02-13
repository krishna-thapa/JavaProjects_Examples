package MovieNight;

import java.util.*;
import java.text.SimpleDateFormat;

public class MovieNight {
    public static Boolean canViewAll(ArrayList<Movie> movies) {

        //throw new UnsupportedOperationException("Waiting to be implemented.");
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie journalA, Movie journalB) {
                return (journalA.getStart()).compareTo(journalB.getStart()); }
        });

        for(Movie movie : movies){
            System.out.println(movie.getStart());
        }

        for(int i = 0; i < movies.size(); i++){
            Date mov1 = movies.get(i).getEnd();
            Date mov2 = movies.get(i+1).getStart();
            Date mov2End = movies.get(i+1).getStart();

        }



        return false;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

        System.out.println(MovieNight.canViewAll(movies));
    }
}

class Movie{
    private Date start, end;

    public Movie(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }
}
