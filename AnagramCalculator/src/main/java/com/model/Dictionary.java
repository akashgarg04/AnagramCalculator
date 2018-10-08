package com.model;

import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.net.HttpURLConnection;

public class Dictionary 
{
	public final String URL = "https://users.cs.duke.edu/~ola/ap/linuxwords";
    private ArrayList<String> dictionary = new ArrayList<String>(); 
	
	/**
	 * Constructor
	 */
    public Dictionary()
	{
    	// Load words from url
		loadFromUrl();
	}
	
    /**
     * This method is used to load the Dictionary from 
     *  URL into the ArrayList dictionary.
     */
	private void loadFromUrl() 
	{
	    try 
	    {
	        URL url;
	        URLConnection urlConnection;
	        DataInputStream inStream;
	        
	        // Create connection
	        url = new URL(URL);
	        urlConnection = url.openConnection();
	        ((HttpURLConnection)urlConnection).setRequestMethod("POST");

	        inStream = new DataInputStream(urlConnection.getInputStream());

	        String eachWord;
	        int grandTotal = 0;
	        while((eachWord = inStream.readLine()) != null) 
	        {
	        	grandTotal++;
	        	dictionary.add(eachWord);      		
	        }

    		System.out.println("Total Words : " + grandTotal);

	        inStream.close();
	    }
	    catch (Exception ex)
	    {
    		System.out.println("Exception happend in reading from the url: "+ URL);
	    }
	    
	} // End loadFromUrl()
	

	/**
	 * The method is used to check if the provided word is present in the dictionary.
	 * The comparison is case insensitive. If the match exists, the word in the 
	 * original case is returned.   
	 * 
	 * @param word
	 * @return Matching word from dictionary in original case 
	 */
	public String doesWordExistsInDictionary (String word)
	{
		String matchingWord = "";
		for (int counter = 0; counter < dictionary.size() && matchingWord.isEmpty()  ; counter++ )
		{
			if (word.equalsIgnoreCase(dictionary.get(counter)))
			{
				matchingWord = dictionary.get(counter);
			}
		}
		
		return matchingWord;
	} //End doesWordExistsInDictionary()

	
	/**
	 * The method is used to search the wordList in the data on the URL. 
	 * 
	 * @param wordList 
	 * @return List of matching words as ArrayList<String>
	 */
	@Deprecated
	public ArrayList<String> searchInUrl(ArrayList<String> wordList) 
	{
		ArrayList<String> matchingWords = new ArrayList<String>();
	    try 
	    {
	        URL url;
	        URLConnection urlConnection;
	        DataInputStream inStream;

	        // Create connection
	        url = new URL(URL);
	        urlConnection = url.openConnection();
	        ((HttpURLConnection)urlConnection).setRequestMethod("POST");

	        inStream = new DataInputStream(urlConnection.getInputStream());

	        String buffer;

	        int grandTotal = 0, matchingCount = 0;
	        while((buffer = inStream.readLine()) != null) 
	        {
	        	grandTotal++;
	        	if (wordList.contains(buffer.toLowerCase()))
	        	{
	        		System.out.println(buffer);
	        		matchingWords.add(buffer);
	        		matchingCount++;
	        	}	        		
	        }
	        
    		System.out.println(matchingCount + " matching out of " + grandTotal);

	        // Close I/O streams
	        inStream.close();	        
	    }
	    catch (Exception ex)
	    {
    		System.out.println("Exception happend in reading from the url: "+ URL);
	    }
	    
        return matchingWords;
	} // End searchInUrl()
	
}// End Class Dictionary
