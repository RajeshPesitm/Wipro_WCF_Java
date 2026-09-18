/**
 * 
 */
package Mylistpkg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
public class TestList {
 
	public static void main(String[] args) {
			List numList = new ArrayList();
			numList.add(123);   //Integer
			numList.add(123);   //Integer
			numList.add(1);      //Integer
			numList.add("one");  //String
			numList.add('1');    //Character
			numList.add(true);  //Boolean
			//display
			System.out.println(numList + "Direct");
			//get the object based on index
			int index = 3;
			System.out.println("Value at Index 3 is "+numList.get(index));
			//get the current size
			int size = numList.size();
			//traverse
			System.out.println("traversal using get with help of for loop");
			for(index =0 ;index<size;index++)
				System.out.println(numList.get(index));
			System.out.println("______________");
			System.out.println("traversal using iterator");
			Iterator itr = numList.iterator();
			while(itr.hasNext())
				System.out.println(itr.next());
			System.out.println("______________");
			System.out.println("traversal using enhanced for loop");
			for(Object obj : numList) //for each object obj in numList
				System.out.println(obj);
			System.out.println("using lambda exp to define Consumer interface action method in forEach()");
			numList.forEach(n -> System.out.println(n));
			System.out.println("______________");
			System.out.println("using method ref to define Consumer interface action method in forEach()");
			numList.forEach(System.out::println);
			System.out.println("______________");
			numList.forEach(n -> System.out.println(n));
	}
 
}