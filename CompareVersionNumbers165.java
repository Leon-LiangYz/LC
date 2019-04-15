package StringRelated;
/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.

The . character does not represent a decimal point and is used to separate number sequences.

For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.



Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
Example 4:

Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
Example 5:

Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"


Note:

Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
Version strings do not start or end with dots, and they will not be two consecutive dots.

 */

import org.junit.Test;

public class CompareVersionNumbers165 {
    @Test
    public void test() {
        String version1 = "1.000";
//        String[] str1 = version1.split("");
//        for (String str : str1) {
//            System.out.print(str + " ");
//            //System.out.println();
//            System.out.print(Integer.valueOf(str) + " ");
//        }
        String version2 = "1.10";
        int res = compareVersion(version1, version2);
        System.out.println(res);
    }

    public int compareVersion(String version1, String version2) {
        //input strings are always valid

        int len1 = version1.length();
        int len2 = version2.length();

        int pos1 = 0, pos2 = 0;

        while (pos1 < len1 && pos2 < len2) {
            String[] res1 = getStrAndPos(version1, pos1, len1);
            String subStr1 = res1[0];
            pos1 = Integer.valueOf(res1[1]);

            String[] res2 = getStrAndPos(version2, pos2, len2);
            String subStr2 = res2[0];
            pos2 = Integer.valueOf(res2[1]);

            if (Integer.valueOf(subStr1) > Integer.valueOf(subStr2)) {
                return 1;
            } else if (Integer.valueOf(subStr1) < Integer.valueOf(subStr2)) {
                return -1;
            }
        }

        // when pos1 has not reached to len1 or pos2 has not reached to len2 yet
        if (pos1 < len1) {
            while (pos1 < len1) {
                if (version1.charAt(pos1) == '.') {
                    pos1++;
                    continue;
                }
                if (version1.charAt(pos1++) != '0') { // version 1 is definitely newer if any number appears other than 0
                    return 1;
                }

            }
        } else if (pos2 < len2) {
            while (pos2 < len2) {
                if (version2.charAt(pos2) == '.') {
                    pos2++;
                    continue;
                }
                if (version2.charAt(pos2++) != '0') { //same as above
                    return -1;
                }
            }
        }
        return 0;
    }

    private String[] getStrAndPos(String version, int pos, int length) {
        String[] res = new String[2];
        if (pos < length - 1) { //before reaching the last element
            int end = pos + 1;
            //getting substring
            while (end < length && version.charAt(end) != '.') {
                //omitting sectional leading zero
                if (version.charAt(pos) == '0' && version.charAt(pos + 1) != '.' && pos < pos - 1) {
                    pos++;
                }
                end++;
            }
            res[0] = version.substring(pos, end);
            pos = end + 1;
        } else { // when reaching the last element of the string
            res[0] = version.substring(pos, length);
            pos++;
        }
        res[1] = String.valueOf(pos);
        return res;
    }
}
