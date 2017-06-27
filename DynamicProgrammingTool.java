/**
 * Created by efeulasakayseyitoglu on 30/01/2017.
 */
import java.util.LinkedList;
import java.util.List;

public class DynamicProgrammingTool {
    LinkedList<String> closestWords = null;
    static int[][] minDistArr = new int[47][47];
    static {
        for(int i = 0; i < minDistArr.length; i++){
            minDistArr[0][i] = i;
            minDistArr[i][0] = i;
        }
    }
    static String prevStr;

    int closestDistance = -1;

    int Distance(String w1, String w2) {

        if(w1.length() == 0)
            return w2.length();
        if(w2.length() == 0)
            return w1.length();

        int newIndex;
        int var = 0;
        if(prevStr != null && !(closestDistance == -1)) {
            for(var = 0; var < prevStr.length() && var < w2.length() ; var++) {
                if(prevStr.charAt(var) != w2.charAt(var)){
                    break;
                }
            }
        }
        newIndex = var;

        for(int i = 1; i < w1.length()+1; i++){
            for(int t = newIndex+1; t < w2.length()+1; t++){

                    if(w1.charAt(i - 1) == w2.charAt(t - 1)){
                        minDistArr[i][t]=minDistArr[i - 1][t - 1];
                    }
                    else{
                        int c1 = Math.min(minDistArr[i - 1][t] + 1, minDistArr[i][t - 1] + 1);
                        minDistArr[i][t] = Math.min(c1, minDistArr[i - 1][t - 1] + 1);
                    }
            }
        }

        prevStr = w2;

        return minDistArr[w1.length()][w2.length()];

    }

    public DynamicProgrammingTool(String w, List<String> wordList) {
        for (String s : wordList) {
            int dist = Distance(w, s);
            if (dist < closestDistance || closestDistance == -1) {
                closestDistance = dist;
                closestWords = new LinkedList<String>();
                closestWords.add(s);
            }
            else if (dist == closestDistance)
                closestWords.add(s);
        }
    }

    int getMinDistance() {
        return closestDistance;
    }

    List<String> getClosestWords() {
        return closestWords;
    }
}