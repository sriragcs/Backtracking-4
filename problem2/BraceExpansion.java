// Time Complexity : O(k^(n/k)), n -> Length of string; k -> Average Length of each block
// Space Complexity : O(m*k), m -> Number of strings that can be formed, k -> Average Length of each block
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
	List<String> ans;

	public String[] expand(String s) {
		if (s == null || s.length() == 0) {
			return new String[0];
		}

		ans = new ArrayList<>();

		List<List<Character>> blocks = new ArrayList<>();
		int i = 0;

		while (i < s.length()) {
			List<Character> block = new ArrayList<>();
			if (s.charAt(i) == '{') {
				i++;
				while (i < s.length() && s.charAt(i) != '}') {
					if (s.charAt(i) != ',') {
						block.add(s.charAt(i));
					}
					i++;
				}
			} else {
				block.add(s.charAt(i));
			}

			Collections.sort(block);
			blocks.add(block);
			i++;
		}

		backtrack(blocks, 0, new StringBuilder());

		String[] res = new String[ans.size()];
		for (i = 0; i < ans.size(); i++) {
			res[i] = new String(ans.get(i));
		}

		return res;
	}

	private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
		// Base
		if (index == blocks.size()) {
			ans.add(sb.toString());
			return;
		}
		// Logic
		List<Character> block = blocks.get(index);
		for (int i = 0; i < block.size(); i++) {
			// Action
			sb.append(block.get(i));
			// Recurse
			backtrack(blocks, index + 1, sb);
			// Backtrack
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public void print(String[] words) {
		for (String word : words) {
			System.out.println(word);
		}
	}

	public static void main(String[] args) {
		BraceExpansion obj = new BraceExpansion();
		String s = "{a,b}c{d,e}f";

		String[] ans = obj.expand(s);

		System.out.println("List of words that can be formed from the string \'" + s + "\':");
		obj.print(ans);
	}

}
