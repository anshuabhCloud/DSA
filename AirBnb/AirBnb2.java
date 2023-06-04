package AirBnb;

import java.util.*;

/*
smallest window which has all keyword,

look like window problem.
i will keep a window with two pointer , also keep trak count of found keyword, also keep track window size wich has all keyword.

when you all keyword is found in winodw compare with presetn window size the update
so what i am think
have two point for window
increase right point if all keyword is not found , if all keyword is present in window  , then increase left pointer , sinrick the size of widonw
can compare window size with exisiting window ,
keep doing til end , then return window size ,

O(N)
eachpointer move only once , i
can keywrod have duplicates?
likt
like two


 */
public class AirBnb2 {
    public static void main(String[] args) {
        List<String> paragraph = new ArrayList<>();
        paragraph.add("foul");
        paragraph.add("add");
        paragraph.add("add");
        paragraph.add("green");
        paragraph.add("take");
        paragraph.add("foul");
        paragraph.add("add");
        paragraph.add("add");
        paragraph.add("add");
        paragraph.add("green");
        paragraph.add("add");
        paragraph.add("add");

        List<String> keyword = new ArrayList<>();
        keyword.add("foul");
        keyword.add("green");
        System.out.println(findMinimumWindow(paragraph,keyword));

    }

    public static List<String> findMinimumWindow(List<String> paragraph, List<String> keywords) {
        Map<String, Integer> keywordCount = new HashMap<>();
        int windowLeft =-1;
        int windowRight =-1;
        for (String keyword : keywords) {
            keywordCount.putIfAbsent(keyword, 0);//this will add entry with zero
            keywordCount.put(keyword, keywordCount.get(keyword) + 1);// b
        }
        int foundKeyword = 0;
        int minWindow = Integer.MAX_VALUE;
        HashMap<String, Integer> windowKeywordCount = new HashMap<>();
        int left = 0, right = 0;//initialise left with -1
        Set<String> keySet = keywordCount.keySet();//
        /*
        what are team here
         */
        while (true) {
            if ((foundKeyword != keywordCount.keySet().size()) && right < paragraph.size())//moving right le
            {
                String word = paragraph.get(right);//takeing right workd
                if (keySet.contains(word)) {
                    //checking if word is part of keySet
                    windowKeywordCount.putIfAbsent(word,0);//add
                    windowKeywordCount.put(word, windowKeywordCount.get(word) + 1);//increase count;
                    if (windowKeywordCount.get(word) == keywordCount.get(word))// check if new keyword count has matchd key keyword frequesnty in keyword list then increase
                    {
                        foundKeyword++;
                    }
                }
                right++;//move right pointer
            } else if (foundKeyword == keywordCount.keySet().size())//count of found keyword ==
            {
                if(minWindow>(right-left))
                {
                    minWindow=right-left;
                    windowLeft=left;
                    windowRight=right;
                }
                String word = paragraph.get(left);
                if (keySet.contains(word)) {
                    windowKeywordCount.put(word, windowKeywordCount.get(word) - 1);//decrement count;
                    if (windowKeywordCount.get(word) == keywordCount.get(word) - 1)// checking if
                    {
                        foundKeyword--;
                    }
                }
                left++;
            } else
                break;
        }
        List<String> output = new ArrayList<>();
        if(left>0&&right>0)
        {
            for(int i=windowLeft;i<windowRight;i++)
            {
                output.add(paragraph.get(i));
            }
        }
        return output;
    }
}




















