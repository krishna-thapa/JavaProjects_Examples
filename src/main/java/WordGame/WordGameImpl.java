package WordGame;


import java.util.*;

/**
 * This is the shell implementation of the WordGame interface.
 * It is the class that you should focus on when developing your solution to the Challenge.
 */
public class WordGameImpl implements WordGame {

    String inputWord;
    ValidWords validWords = new ValidWordsImpl();
    public static List<LeaderBoard> leaderList = new ArrayList<>();

    public WordGameImpl(String inputWords, ValidWords validWords) {
        this.inputWord = inputWords;
    }

    @Override
    public int submitWord(String playerName, String word) {
        int count = 0;

        //check whether the word is in starting word or not
        Map<Character, Integer> map = new HashMap<>();
        for(int i =0; i < inputWord.length(); i++){
            char c = inputWord.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(map.containsKey(c) && map.get(c) != 0){
                count++;
                map.put(c,map.get(c)-1);
            }else{
                System.out.println("Error: Number of letters doesn't match to starting word: " + word);
                return 0;
            }
        }

        if(count != word.length()){
            System.out.println("Error: Number of letters doesn't match to starting word: " + word);
            return 0;
        }
        //System.out.println("count " + count);

        //Check whether the word is in text file or not
        if(!validWords.contains(word)){
            System.out.println("Error: Word is not in ValidWords collection text file: " + word);
            return 0;
        }

        LeaderBoard eachScore = new LeaderBoard(playerName,word,count);
        leaderBoardValidation(eachScore);

        Collections.sort(leaderList);  //use of Comparable interface to order the object in desc order
        for(LeaderBoard row : leaderList){
            System.out.println(row.playerName + " " + row.getPlayerWord() + " " + row.getPlayerScore());
        }
        System.out.println();

        return count;
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
        if(leaderList.size() < 10){
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

        }

        return false;
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
        private String playerName;
        private String playerWord;
        private int playerScore;

        public LeaderBoard(String playerName, String playerWord, int playerScore) {
            this.playerName = playerName;
            this.playerWord = playerWord;
            this.playerScore = playerScore;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getPlayerWord() {
            return playerWord;
        }

        public void setPlayerWord(String playerWord) {
            this.playerWord = playerWord;
        }

        public int getPlayerScore() {
            return playerScore;
        }

        public void setPlayerScore(int playerScore) {
            this.playerScore = playerScore;
        }

        //use of Comparable interface and override the compareTo() method to sort the object by score in des order
        public int compareTo(LeaderBoard compareScore){
            int compareScoreResult = (compareScore).getPlayerScore();
            return compareScoreResult - this.getPlayerScore();
        }
    }
}
