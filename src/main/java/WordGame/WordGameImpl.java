package WordGame;


import java.util.*;

/**
 * This is the shell implementation of the WordGame interface.
 * It is the class that you should focus on when developing your solution to the Challenge.
 */
public class WordGameImpl implements WordGame {

    String inputWords;
    ValidWords validWords;
    /*
    ValidWords validWords = new ValidWordsImpl(); //wrong way
    I guess assigning the concrete implementation of ValidWords is undesired,
    we should inject it through the constructor.
     */

    final List<LeaderBoard> leaderList = new ArrayList<>();

    public List<LeaderBoard> getLeaderList() {
        return leaderList;
    }

    public WordGameImpl(String inputWords, ValidWords validWords) {
        this.inputWords = inputWords; //right way inject it through the constructor
        this.validWords = validWords;
    }

    @Override
    public int submitWord(String playerName, String word) {
        int score = word.length();

        //check whether the word is in starting word or not
        Map<Character, Integer> map = new HashMap<>();
        for(int i =0; i < inputWords.length(); i++){
            char c = inputWords.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(map.containsKey(c) && map.get(c) != 0){
                map.put(c,map.get(c)-1);
            }else{
                System.out.println("Error: Number of letters doesn't match to starting word: " + word);
                return 0;
            }
        }

        //Check whether the word is in text file or not
        if(!validWords.contains(word)){
            System.out.println("Error: Word is not in ValidWords collection text file: " + word);
            return 0;
        }

        LeaderBoard submission = new LeaderBoard(playerName,word,score);
        leaderBoardValidation(submission);

        //Collections.sort(leaderList);  //use of Comparable interface to order the object in desc order
        for(LeaderBoard row : leaderList){
            System.out.println(row.playerName + " " + row.getPlayerWord() + " " + row.getPlayerScore());
        }
        System.out.println();

        return score;
    }

    /**
     * To validate the leader board as player plays the game
     * @param eachScore
     * @return
     */
    public boolean leaderBoardValidation(LeaderBoard eachScore){
        //to check if the leader board has the word already in it or not
        for(LeaderBoard row : leaderList){
            if(row.getPlayerWord().equals(eachScore.getPlayerWord())){
                return false;
            }
        }

        //to check the size of the leader board is always less than 10
        //check if the word has more score than that is listed in leader board
        final int maxLeaderBoardLength = 10;
        leaderList.add(eachScore);
        Collections.sort(leaderList);
        System.out.println("Size of the list: "+leaderList.size());
        if(leaderList.size() > maxLeaderBoardLength){
            leaderList.remove(maxLeaderBoardLength);
            return true;
        }
        /*if(leaderList.size() < 10){
            leaderList.add(eachScore);
            return true;
        }else{
            for(int i = 0; i < 10 ; i++){
                if(eachScore.getPlayerScore() > leaderList.get(i).getPlayerScore()){
                    System.out.println(i); //Remember the list is already sorted hince loop runs from higher value to lower
                    leaderList.add(i,eachScore);
                }
                if(leaderList.size() > 10){
                    leaderList.remove(10);
                    break;
                }

            }

        }*/

        return true;
    }
    @Override
    public String getPlayerNameAtPosition(int position) {
        return leaderList.get(position).getPlayerName();
    }

    @Override
    public String getWordEntryAtPosition(int position) {
        return leaderList.get(position).getPlayerWord();
    }

    @Override
    public Integer getScoreAtPosition(int position) {
        return leaderList.get(position).getPlayerScore();
    }

    /**
     * class for the leader board
     */
    public class LeaderBoard implements Comparable<LeaderBoard>{

        //It is much safer to make the objects of this class immutable,
        // all members could be final, no setters are needed.

        private final String playerName;
        private final String playerWord;
        private final int playerScore;

        public LeaderBoard(String playerName, String playerWord, int playerScore) {
            this.playerName = playerName;
            this.playerWord = playerWord;
            this.playerScore = playerScore;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getPlayerWord() {
            return playerWord;
        }

        public int getPlayerScore() {
            return playerScore;
        }

        //use of Comparable interface and override the compareTo() method to sort the object by score in des order
        @Override
        public int compareTo(LeaderBoard compareScore){
            int compareScoreResult = compareScore.getPlayerScore();
            return compareScoreResult - this.getPlayerScore();
        }
    }
}
