// String s1, String s2, concate some components in s2
// s1 = "Z|AZ|A" s1 = "AZ"
// s2 = "BAZ" "BACAZ"

// "Z" + "AZ" + "A"

public boolean findPattern(String s1, String s2) {
	char[] chars1 = new char[26];
	char[] chars2 = new char[26];
	int[] map = new int[26];
	for (int i = 0; i < s1.length(); i++) {
		chars1[s1.charAt(i) - 'A']++;
	}
	for (int i = 0; i < s2.length(); i++) {
		chars2[s2.charAt(i) - 'A']++;
		if (map[] == 0) map[s2.charAt(i) - 'A'] = i;
	}
	for (int i = 0; i < 26; i++) {
		if (chars1[i] > 0 && chars2[i] == 0) return false;
	}
	return true;
}

public int getPattern(String s1, String s2) {
	int i = 0; int j = 0; int count = 0;
	while (i < s1.length()) {
		int temp = find(s1.charAt(i), s2, j);
		if (temp > 0) {
			i++;
			j++;
		} else {
			//j = 0;
			j = map[s1.charAt(i)];
			count++;
		}
	}
	return count;
}

public int find(char c, String s2, int j) {
	for (int i = j; i<s2.length(); i++) {
		if (s2.charAt(i) == c) return i;
	}
	return -1;
}