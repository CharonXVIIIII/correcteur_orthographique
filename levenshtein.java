//Algorithme de levenstein

public class levenshtein {

    public String initialWord;
    public String correctWord;
    public String[][] result;

    public levenshtein(String initialWord, String correctWord) {
        this.initialWord = initialWord;
        this.correctWord = correctWord;
        this.result = new String[this.initialWord.length()+1][this.correctWord.length()+1];   
    }

    public String editlenght(){
        int cost;
        for (int row = 0; row <= this.initialWord.length(); row++) {
            for (int col = 0; col <= this.correctWord.length(); col++) {
                if (row == 0) {
                    this.result[row][col] = Integer.toString(col);
                } else if (col == 0) {
                    this.result[row][col] = Integer.toString(row);
                } else {
                    cost = this.initialWord.charAt(row - 1) == this.correctWord.charAt(col - 1) ? 0 : 1;
                    this.result[row][col] = Integer.toString(Math.min(Math.min(Integer.parseInt(this.result[row - 1][col]) + 1, Integer.parseInt(this.result[row][col - 1]) + 1), Integer.parseInt(this.result[row - 1][col - 1]) + cost));
                }
            }
        }
        return this.result[this.initialWord.length()][this.correctWord.length()];
    }
}


