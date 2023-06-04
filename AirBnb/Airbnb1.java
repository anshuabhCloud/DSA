package AirBnb;
import java.util.*;
import java.util.stream.Collectors;

public class Airbnb1 {
    /*

     */
    public static void main(String[] args) {
        HashMap<String,Double> prices = new HashMap<>();
        prices.put("Fruits",2.64);
        prices.put("Fried",2.00);
        prices.put("salad",3.35);
        prices.put("wings",3.55);
        prices.put("Morzi",4.20);
        double target = 4.64;

//        prices.
        List<List<String>> output = geCombination(prices,target);
        for(List<String> out :output)
        {
            for(String ot:out)
            {
                System.out.print(", "+ ot);
            }
            System.out.println("");//new line
        }
    }
    //what will output List of combination run fast time nahi hai run code , run code , it will give error , then fix ,

    public static List<List<String>> geCombination(Map<String,Double> prices,double targetPrice)
    {
        List<List<String>> output = new ArrayList<>();
        List<String> allItem = prices.keySet().stream().collect(Collectors.toList());
        findTargetPrice(new ArrayList<>(),prices,output,targetPrice,0.0,allItem,0);
        return output;
    }
    //writing back tracign finc , adding one item only once
    public static void findTargetPrice(List<String> tempOutput,Map<String,Double> prices,List<List<String>> output,double targetSum,double currentSum,List<String> allItems,int position)
    {
        //calculate sum including current element
        if(currentSum==targetSum)
        {
            output.add(tempOutput.stream().collect(Collectors.toList()));//skip the element if item sum is more than sum
            tempOutput.remove(tempOutput.size()-1);//where is nul
        } else if (currentSum<targetSum&&position<allItems.size()) {//change this
            tempOutput.add(allItems.get(position));//change add the element to array. if ading sum to potiont
            findTargetPrice(tempOutput,prices,output,targetSum,currentSum+prices.get(allItems.get(position)),allItems,position+1);//when add element at position  element to current sum
           if(tempOutput.size()>0){
            tempOutput.remove(tempOutput.size()-1);//removin if size >0
           }//last element
            findTargetPrice(tempOutput,prices,output,targetSum,currentSum,allItems,position+1);//when you dont add elemen//when don't add element at position  element to current sum
        }

    }


}
