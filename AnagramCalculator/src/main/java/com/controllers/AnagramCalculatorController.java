package com.controllers;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.model.*;

@Controller
public class AnagramCalculatorController 
{
	//http://localhost:8080/AnagramCalculator/getWord?word=team
	
	/**
	 * The following method is mapped to the HTTP Request /getWord
	 * 
	 * @param word - Parameter during request submission 
	 * @param model - Model data to be displayed
	 * @return - view name that is displayed
	 */
	@RequestMapping(value="/getWord" )
	public String getAnagramsForWord(@RequestParam ("word") String word , Model model)
	{
		StringBuffer anagram = new StringBuffer();
		System.out.println(word);
		
		//Add attribute to the model for displaying the word
		model.addAttribute("word", word);

		// Invoke an instance of WordPermutations and GetPermutations for word 
		WordPermutations wordPermutationObj = new WordPermutations();
		ArrayList<String> result = wordPermutationObj.GetPermutations(word);
		
		//Invoke/load Dictionary
		Dictionary dictionaryObj = new Dictionary();
		
		// For every word in the result of word permutation
		for (int i=0; i<result.size(); i++)
		{
			// Check if it exists in the dictionary 
			String temp = dictionaryObj.doesWordExistsInDictionary(result.get(i));			
			if(!temp.isEmpty())
			{
				anagram.append(temp);
				anagram.append(", ");
			}
		}
		
		// Remove the string appended to the last anagram
		if (anagram.length() > 0)
			anagram.delete(anagram.length()-2, anagram.length());
		
		//Add attribute to the model for displaying the word		
		model.addAttribute("anagram",anagram.toString());
		
		// if the word is not provided or does not have any anagrams, display 404, page not found
		boolean isSuccess = !word.isEmpty() && (anagram.length() > 0);

		// View name in case of success is word.jsp. failure.jsp is not created leading to page not found error.
		return (isSuccess ? "word" : "failure") ;
	}
}
