//Algorithme de levenstein

public class levenshtein {

    public String initialWord;
    public String correctWord;
    public int[][] result;

    public levenshtein(String initialWord, String correctWord) {
        this.initialWord = initialWord;
        this.correctWord = correctWord;
        this.result = new int[this.initialWord.length()+1][this.correctWord.length()+1];   
    }

    public int editlength() {
        int costSub;

        for (int row = 0; row <= this.initialWord.length(); row++) {
            result[row][0]=row;
        }
        for (int col = 0; col <= this.correctWord.length(); col++) {
            result[0][col]=col;
        }

        for (int row = 1; row <= this.initialWord.length(); row++) {
            for (int col = 1; col <= this.correctWord.length(); col++) {
                if (this.initialWord.charAt(row-1) == this.correctWord.charAt(col-1)) {
                    costSub = 0;}
                else { costSub = 1;}
                result[row][col]= Math.min
                    (
                     result[row-1][col] + 1, 
                     Math.min(
                     result[row][col-1] + 1, 
                     result[row-1][col-1]+costSub)); 
            }
        }
        return result[initialWord.length()][correctWord.length()];
    }
}


