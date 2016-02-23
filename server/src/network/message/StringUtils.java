package network.message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {
	// Simple split to avoid using regex in String.split()
	public static List<String> split(final String message, final String separator){
		// Return list of tokens
		List<String> tokens = new LinkedList<>();
		
		int curIndex = 0;
		// Loop until message length is reached
		while(curIndex < message.length()){
			// Take the first occurrence of the separator from current index
			int occurIndex = message.indexOf(separator, curIndex);
			// Substring between the two
			if(occurIndex == -1)
				occurIndex = message.length();
			String token = message.substring(curIndex, occurIndex);
			tokens.add(token);
			// Increment index
			curIndex += token.length() + separator.length();
		}
		
		return tokens;
	}
}
