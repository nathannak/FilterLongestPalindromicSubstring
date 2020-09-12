public class Main {

    /*
    1.) Take all prefixes of the string and choose the longest palindrome between them.
    2.) If chosen prefix has at least two characters, cut this from s and go back to step 1 with the updated string. Otherwise, end the algo with the current string s.
    From: https://leetcode.com/discuss/interview-question/801274/Robinhood-coding-question-2
     */
    public static void main(String[] args) {

        String str =    "HelolWoWorld!";
        String subStr = new String(str);

        while(subStr.length() >= 2) {

            System.out.println("Now processing: " + str);
            subStr = expandFromCenter(str);

            if(subStr.length() < 2) break;

            //cutting from original string
            int index  = str.indexOf(subStr);
            str = str.substring(0,index) + str.substring(index+subStr.length());

        }

        System.out.println("printing final substring: " + subStr);

    }

    public static String expandFromCenter(String str) {

//        put these in res array so we can pass to helper
//        int max = 0;
//        int start = 0;

        // index 0 is start, index 1 is max
        int[] res = new int[2];

        for (int i = 0; i < str.length(); i++) {

            expandFromCenterHelper(str, i, i          ,res);
            expandFromCenterHelper(str, i, i + 1 ,res);

        }

        System.out.println("returning: " + str.substring(res[0],res[0]+res[1]));

        //  str.substring(res[0], res[0] + res[1]) -> str.substring(start,start + max)
        return str.substring(res[0],res[0]+res[1]);

    }

    public static void expandFromCenterHelper(String str, int start, int end, int[] res) {

        while( start >= 0 && end < str.length() && str.charAt(start)==str.charAt(end) ) {

            //note the <- -> directions
            start--;
            end++;

        }

        //adjust back to correct indexes, since they went out of bounds inside while loop
        start++;
        end--;

        if(end-start+1 > res[1]) {
            res[1] = end-start+1; //save max
            res[0] = start;       //save start

            System.out.println("new max is : " + res[1] +  " new start is: "   + res[0]);
        }

    }

}
