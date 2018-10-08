package com.model;

import java.util.ArrayList;
import java.util.Collections;

public class WordPermutations
{
	/**
	 * The method is used to fetch all permutations of different words 
	 * that could be formed by rearranging characters in the provided word.
	 * The word itself is removed in the return list.
	 * Return list is sorted alphabetically.
	 * 
	 * @param word
	 * @return ArrayList of all possible words
	 */
	public ArrayList<String> GetPermutations (String word)
	{
		ArrayList<String> result = new ArrayList<String>();
		System.out.println("*********************");
		DetermineWordPermutations("",word,result);
		if (result.contains(word))
			result.remove(word);
		System.out.println("*********************");		
		for (int i = 0; i < result.size();i++)
		{
			System.out.println(i+1 + ")  " + result.get(i));
		}
		System.out.println("*********************");
		
		//Dictionary dictionary = new Dictionary();
		//return dictionary.searchInUrl(result);
		
		Collections.sort(result);
		return result;
		
	} // End GetPermutations()
	

	/**
	 * Recursive method to determine Permutations
	 * 
	 * @param prefix - Character that are considered for 
	 * @param suffix - Characters that needs to be considered
	 * @param results - String ArrayList of permutations of different words by rearranging characters in the word. 
	 */
	private void DetermineWordPermutations (String prefix, String suffix , ArrayList<String> results)
	{
		// Once all the characters are considered, add suffix to the results.
		if (suffix.length() == 0)
		{
			// if not already exists
			if (!results.contains(prefix.toLowerCase()))
			{	
				//Added in lower case so that the comparison later can happen in lower case.
				results.add(prefix.toLowerCase());
			}
		}
		else
		{
			//For all characters in the suffix
			for (int i = 0; i < suffix.length(); i++)
			{
				// Separate the first character and determine permutations for the remaining string
				DetermineWordPermutations(prefix + suffix.charAt(i),
						suffix.substring(0, i) + suffix.substring(i+1, suffix.length()),
						results);
			}
		}
			
	} //End DetermineWordPermutations()

} // End Class WordPermutations
