class Solution {
    Boolean [][] dp;
    public boolean isMatch(String s, String p) {
         dp = new Boolean [s.length()+1][p.length()+1];
        return solve(s,p, 0,0);
    }

    private boolean solve(String s, String p, int i, int j){
         if(j >= p.length()) return dp[i][j] = i >= s.length(); // if pattern is blank, then input string must be blank., if input string is blank, but pattern is not blank, then there might be a changes that input string and pattern get matched. eg : s = "", p = ".*", here * will be used as zero match with preceding char .
        if(dp[i][j]!= null) return dp[i][j];
        boolean isFirstCharMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'); // conditions to check ,if the front chars matched in input and patterns or not.

       
        if(j+1 < p.length() && p.charAt(j+1) == '*'){
            boolean zeroMatch = solve(s,p,i, j+2);
            boolean oneOrMoreMatch = false;
            if(isFirstCharMatch){ // front char matched in input and patterns(either direct match or matched with .), then go for one or more match.
                oneOrMoreMatch = solve(s,p,i+1, j);
            }
            return dp[i][j] = zeroMatch || oneOrMoreMatch;

        } else if(isFirstCharMatch){ // here current char matches in input and pattern, so mov on to next char in input ans patterns
            return dp[i][j] = solve(s,p,i+1, j+1);
        } else { // otherwise return false, i.e when neither current char doesn't match in input and patterns nor next char is *.
            return dp[i][j] = false;
        }
    }
}
