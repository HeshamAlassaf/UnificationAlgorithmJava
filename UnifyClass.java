/*
 * This is an implementation of Unification Algorithm.
 * Also through this code you can convert from 
 * Predicate Calculus Syntax to LIST Syntax
 * 
 * Prepared by : Hesham Saud Alassaf
 * Course Name : Fundamentals of Artificial Intelligence.
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnifyClass {

	
	/*
	 * Here I defined an enum ,so I can identify :
	 * 		E1 as a CONSTANT or a VARIABLE
	 * 		E2 as a CONSTANT or a VARIABLE
	 */
	public enum Type {
		CONSTANT,
		VARIABLE
	}
	
	public static void main(String[] arag){
		// Unification Algorithm
		String EX1 = "";
		String EX2 = "";
		EX1 = convertPcToList(input());
		EX2 = convertPcToList(input());
		unify(EX1,EX2);
	}
	
	// This method will take input from users for EX1 and EX2
	public static String input(){
		System.out.println("Please enter Predicate Calculus Syntax : ");
		Scanner scan = new Scanner(System.in);
		String EX = scan.nextLine();
		// Check if the user input start with a left parenthese or maybe he enter LIST Syntax instead of PC Syntax
		while(EX.startsWith("(")){
			System.out.println("Please enter PC Syntax again, I think you entered the LIST Syntax : ");
			EX = scan.nextLine();
		}
		// Calculate the parentheses to see if they are equal
		int countForLeftParenthese = 0;
		int countForRightParenthese = 0;
		for( int y = 0; y < EX.length() ; y++ ) {
		    if( EX.charAt(y) == '(' ) {
		    	countForLeftParenthese++;
		    }else if(EX.charAt(y) == ')'){
		    	countForRightParenthese++;
		    }
		}
		while(countForLeftParenthese != countForRightParenthese){
			System.out.println("Please enter PC Syntax again, you missed a parenthese : ");
			EX = scan.nextLine();
			countForLeftParenthese = 0;
			countForRightParenthese = 0;
			for( int y = 0; y < EX.length() ; y++ ) {
			    if( EX.charAt(y) == '(' ) {
			    	countForLeftParenthese++;
			    }else if(EX.charAt(y) == ')'){
			    	countForRightParenthese++;
			    }
			}
		}
		return EX;
	}
	
	
	// This method will convert from PC Syntac to LIST Syntax
	public static String convertPcToList(String PcSyntax){
		/*
		 * First I compile the Regular Expression \w+\(|\w+|[A-Z]|\)
		 * This RegEx will identify :
		 * 		1. Predicates
		 * 		2. Function
		 * 		3. Constants
		 * 		4. Variable
		 * you can find more information on what this RegEx is doing
		 * through visiting this web site : http://regexr.com/
		 * in the Expression section type : \w+\(|\w+|[A-Z]|\)
		 * in the Text section type : any PC Syntax 
		 * 
		 */
		Pattern pattern = Pattern.compile("\\w+\\(|\\w+|[A-Z]|\\)");
		/*
		 * This Matcher object will find all the matches in the passed String  
		 * which is PcSyntax, through the compiled RegEx. 
		 * And then put them in a group
		 */
		Matcher matcher = pattern.matcher(PcSyntax);
		/*
		 * Creating an object of Array List ,so I can save all the element 
		 * I found in the matcher and then manipulate them
		 */
		ArrayList<String> arrayList = new ArrayList<String>();
		// Here I check if there is any matches or not 
		while(matcher.find()){
			// Check to see if the whole group length isn't equal to Zero
			if(matcher.group().length() != 0){
				/*
				 * Here in the first iteration of the loop it will add
				 * the first element in the group to the arrayList
				 * and then the second, third, ...etc
				 */
				arrayList.add(matcher.group());
			}
		}
		String fullListSyntax = "";
		/*
		 * This loop is the main thing
		 * What this loop will do is : 
		 * First check if the element in the arrayList contains a "("
		 * which means this element either a predicate or a function 
		 * it will remove the "(" and put it at the begging of the element
		 * otherwise it will add a space to it 
		 * and then it will combine the full List Syntax together
		 */
		for(int i = 0 ; i < arrayList.size() ; i++){
			if(arrayList.get(i).contains("(")){
				String temp = arrayList.get(i);
				temp = temp.replace("(", " ");
				temp = "(" + temp;
				arrayList.set(i, temp);
			}else{
				String temp = arrayList.get(i);
				temp = temp + " ";
				arrayList.set(i, temp);
			}
			fullListSyntax = fullListSyntax + arrayList.get(i);
		}
		System.out.println("After convering from PC Syntax to LIST Syntax here is the result :");
		System.out.println(fullListSyntax);
		System.out.println("------------------------------");
		return fullListSyntax;
	}
	
	// Counter to allow E1 or E2 to enter Type checking
	static int counter = 0;
	// to count processes
	static int process = 0;
	public static String[] unify(String E1, String E2){
		// Initialize a list ,so I can manipulate when checking types for E1 and E2
		String [] list = {"list1", "list2"};
		// Initialize an empty list ,so I can return EMPTY
		String [] emptyList = {"EMPTY", "EMPTY"};
		// Initialize a failure list ,so I can return FAIL
		String [] failList = {"FAIL", "FAIL"};
		// Initialize E1 and E2 Types
		Type E1Type = null;
		Type E2Type = null;
		// ###############################################
		// Here I identify what is the type of E1 and E2
		// check the type of E1
		if(counter == 1 && (!E1.contains("(") || !E1.contains(")") || !E2.contains("(") || !E2.contains(")"))){
			try{
				if(Character.isUpperCase(E1.charAt(0))){
					E1Type = Type.VARIABLE;
				}else{
					E1Type = Type.CONSTANT;
				}
				// check the type of HE2
				if(Character.isUpperCase(E2.charAt(0))){
					E2Type = Type.VARIABLE;
				}else{
					E2Type = Type.CONSTANT;
				}
			}catch(StringIndexOutOfBoundsException e){
				e.getMessage();
			}
		}
		// ###############################################
		// Start Algorithm
		if(E1Type == Type.CONSTANT && E2Type == Type.CONSTANT && counter == 1){
			if(E1.equals(E2))
				return emptyList;
			else
				return failList;
		}else if(E1Type == Type.VARIABLE && counter == 1){
			if(E2.contains(E1))
				return failList;
			else{
				// This will save E2 in index 0 and E1 in index 1
				// E1 = E2;
				list[0] = E2;
				list[1] = E1;
				return list;
			}
		}else if(E2Type == Type.VARIABLE && counter == 1){
			if(E1.contains(E2))
				return failList;
			else{
				// This will save HE1 in index 0 and HE2 in index 1
				// HE2 = HE1;
				list[0] = E1;
				list[1] = E2;
				return list;
			}
		}else if((E1.isEmpty() || E2.isEmpty()))
			return failList;
		else{
			// Initialize the variables
			String HE1 = "";
			Type HE1Type = null;
			boolean HE1Function = false;
			String HE2 = "";
			Type HE2Type = null;
			boolean HE2Function = false;
			// set counter to 1 to allow HE1 and HE2 to access type checking
			counter = 1;
			// ptn1 will compile the regular expression \w+ 
			Pattern ptn1 = Pattern.compile("\\w+");
			/*
			 * mcr1 and mcr2 will search for the first match in E1 and E2
			 * the matches either a predicate, function, variable
			 */
			Matcher mcr1 = ptn1.matcher(E1);
			Matcher mcr2 = ptn1.matcher(E2);
			// ###############################################
			// assign values to HE1 and HE2
			if(mcr1.find())
				// HE1:= first element of E1;
				HE1 = mcr1.group(0);
			else
				return emptyList;
			if(mcr2.find())
				// HE2:= first element of E2;
				HE2 = mcr2.group(0);
			else
				return emptyList;
			// ###############################################
			// Here I identify what is the type of HE1 and HE2
			// check the type of HE1
			if(Character.isUpperCase(HE1.charAt(0))){
				HE1Type = Type.VARIABLE;
			}else{
				HE1Type = Type.CONSTANT;
				// check if the constant is a function
				try{
					if(E1.charAt(E1.indexOf(HE1)-1) == '('){
						HE1Function = true;
					}
				}catch(StringIndexOutOfBoundsException e){
					e.getMessage();
				}
			}
			// check the type of HE2
			if(Character.isUpperCase(HE2.charAt(0))){
				HE2Type = Type.VARIABLE;
			}else{
				HE2Type = Type.CONSTANT;
				// check if the constant is a function
				try{
					if(E2.charAt(E2.indexOf(HE2)-1) == '('){
						HE2Function = true;
					}
				}catch(StringIndexOutOfBoundsException e){
					e.getMessage();
				}
			}
			// ###############################################
			// Initialize the variables
			String[] SUBS1 = {"SUBS1_1", "SUBS1_2"};
			String[] SUBS2 = {"SUBS2_1", "SUBS2_2"};
			String TE1 = "";
			String TE2 = "";
			/*
			 * Here I check if the opposite part a variable or a function 
			 * and then do the rest of the algorithm
			 */
			// if HE1 is a variable and HE2 is a constant (function) then
			if(HE1Type == Type.VARIABLE && HE2Type == Type.CONSTANT && HE2Function){
				// ptn will compile the regular expression \w+\s+\w+
				Pattern ptn = Pattern.compile("\\w+\\s+\\w+");
				// mcr will get the function and its argument  
				Matcher mcr = ptn.matcher(E2);
				// assign the value matched in mcr to HE2
				if(mcr.find())
					HE2 = mcr.group(0);
				// remove HE1 and HE2 from E1 and E2
				E1 = E1.replaceFirst(HE1, "");
				E2 = E2.replaceFirst(HE2, "");
				System.out.println(HE1 + " ----> " + HE2);
				// SUBS1:= unify(HE1,HE2);
				SUBS1 = unify(HE1,HE2);
				// if SUBS1:=FAIL then return FAIL;
				if(SUBS1[0] == "FAIL")
					return failList;
				if(SUBS1[0] == "EMPTY")
					counter = 0;
				// TE1:= apply(SUBS1, rest of E1);
				TE1 = E1.replaceAll(SUBS1[1], SUBS1[0]);
				// TE2:= apply(SUBS1, rest of E2);
				TE2 = E2.replaceAll(SUBS1[1], SUBS1[0]);
				// SUBS2:= unify(TE1,TE2);
				SUBS2 = unify(TE1, TE2);
				// if SUBS2=FAIL then return FAIL
				if(SUBS2[0] == "FAIL")
					return failList;
				// else return composition(SUBS1,SUBS2);
				else{
					System.out.println("++++++++++++ Composition of SUBS1 & SUBS2 Process #" + (++process) +" ++++++++++++");
					System.out.println("{" + SUBS1[0] + "/" + SUBS1[1] + "}");
					System.out.println("{" + SUBS2[0] + "/" + SUBS2[1] + "}");
					System.out.println("++++++++++ End Composition of SUBS1 & SUBS2 Process #" + (process) +" ++++++++++");
				}
			}
			// if HE2 is a variable and HE1 is a constant (function) then
			else if(HE1Type == Type.CONSTANT && HE2Type == Type.VARIABLE && HE1Function){
				// ptn will compile the regular expression \w+\s+\w+
				Pattern ptn = Pattern.compile("\\w+\\s+\\w+");
				// mcr will get the function and its argument
				Matcher mcr = ptn.matcher(E1);
				// assign the value matched in mcr to HE1
				if(mcr.find())
					HE1 = mcr.group(0);
				// remove HE1 and HE2 from E1 and E2
				E1 = E1.replaceFirst(HE1, "");
				E2 = E2.replaceFirst(HE2, "");
				System.out.println(HE1 + " ----> " + HE2);
				// SUBS1:= unify(HE1,HE2);
				SUBS1 = unify(HE1,HE2);
				// if SUBS1:=FAIL then return FAIL;
				if(SUBS1[0] == "FAIL")
					return failList;
				if(SUBS1[0] == "EMPTY")
					counter = 0;
				// TE1:= apply(SUBS1, rest of E1);
				TE1 = E1.replaceAll(SUBS1[1], SUBS1[0]);
				// TE2:= apply(SUBS1, rest of E2);
				TE2 = E2.replaceAll(SUBS1[1], SUBS1[0]);
				// SUBS2:= unify(TE1,TE2);
				SUBS2 = unify(TE1, TE2);
				// if SUBS2=FAIL then return FAIL
				if(SUBS2[0] == "FAIL")
					return failList;
				// else return composition(SUBS1,SUBS2);
				else{
					System.out.println("++++++++++++ Composition of SUBS1 & SUBS2 Process #" + (++process) +" ++++++++++++");
					System.out.println("{" + SUBS1[0] + "/" + SUBS1[1] + "}");
					System.out.println("{" + SUBS2[0] + "/" + SUBS2[1] + "}");
					System.out.println("++++++++++ End Composition of SUBS1 & SUBS2 Process #" + (process) +" ++++++++++");
				}
			}else{
				// remove HE1 and HE2 from E1 and E2
				E1 = E1.replaceFirst(HE1, "");
				E2 = E2.replaceFirst(HE2, "");
				System.out.println(HE1 + " ----> " + HE2);
				// SUBS1:= unify(HE1,HE2);
				SUBS1 = unify(HE1,HE2);
				// if SUBS1:=FAIL then return FAIL;
				if(SUBS1[0] == "FAIL")
					return failList;
				if(SUBS1[0] == "EMPTY")
					counter = 0;
				// TE1:= apply(SUBS1, rest of E1);
				TE1 = E1.replace(SUBS1[1], SUBS1[0]);
				// TE2:= apply(SUBS1, rest of E2);
				TE2 = E2.replace(SUBS1[1], SUBS1[0]);
				// SUBS2:= unify(TE1,TE2);
				SUBS2 = unify(TE1, TE2);
				// if SUBS2=FAIL then return FAIL
				if(SUBS2[0] == "FAIL")
					return failList;
				// else return composition(SUBS1,SUBS2);
				else{
					System.out.println("++++++++++++ Composition of SUBS1 & SUBS2 Process #" + (++process) +" ++++++++++++");
					System.out.println("{" + SUBS1[0] + "/" + SUBS1[1] + "}");
					System.out.println("{" + SUBS2[0] + "/" + SUBS2[1] + "}");
					System.out.println("++++++++++ End Composition of SUBS1 & SUBS2 Process #" + (process) +" ++++++++++");
				}
			}
			// ###############################################
			
		}
		return emptyList;
	}
}
