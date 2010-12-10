/* Sets.java -- Given a set, list its superset */

/*
 * Copyright © 2010 Jan Minář <rdancer@rdancer.org>
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License version 2 (two),
 *    as published by the Free Software Foundation.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License along
 *    with this program; if not, write to the Free Software Foundation, Inc.,
 *    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */


/*
 * Exercise C-3.14 from [Goodrich 2005]: "Write a recursive Java program that
 * will output all the subsets of a set of _n_ elements (without repeating any
 * subsets)."
 * 
 * Solution: We know that the number of elements ("cardinality") of a set of
 * all subsets ("superset") of any set of n elements is 2^n.  We can represent
 * the all the combinations of subsets by a set of all possible binary strings
 * of length n, with a digit at position k representing the k-th element of
 * the original set.  A one corresponds to the element present, zero to the
 * element absent from the subset -- so that a string of zeroes represents the
 * empty set, a string of ones represents the original set, and so on and so
 * forth.
 * 
 * 
 * [Goodrich 2005] Goodrich, Michael T. and Tamassia, Roberto (2005) Data
 *     Structures and Algorithms in Java. 4th edition, John Wiley & Sons,
 *     ISBN 0471738840
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Sets {
    /** Are we about to print the first element/set? */
    private static boolean setsVirgin = true;
    private static boolean elementsVirgin = true;

    public static void main(String[] args) {

	System.out.println(sets(Arrays.asList(args)));


    	return;
    }


    private static String sets(List<String> list) {
	long bitstring = 0L;

	for (String myString : list) {
	    // XXX make sure there is space
	    bitstring <<= 1;
	    bitstring += 1;
	}


	return "(" + sets(list, bitstring) + ")";
    }

    private static String sets(List<String> list, long bitstring) {
	if (bitstring < 0) {
	    throw new Error("Internal error: bitstring < 0");
	} else if (bitstring == 0) {
	    // The empty set comes last
	    if (list.size() > 0) {
		// With a separator if there have been other subsets
		return "), ø";
	    } else {
		// Otherwise, this will be the only subset
		return "ø";
	    }
	} else {
	    // If this is not the first set we're printing, add the separator
	    // and reset the flag
	    String s = setsVirgin ? "" : "), ";
	    setsVirgin = false;


	    s += "(";
		    // Make sure to set the virgin flag to true
		    elementsVirgin = true;
		    s += elements(list, Long.toBinaryString(bitstring));

	    // Make sure to set the virgin flag to false

	    s += sets(list, bitstring - 1);

	    return s;
	}
    }

    private static String elements(List<String> list, String bitstring) {
	if (bitstring.length() < 0) {
	    throw new Error("Internal error: bitstring < 0");
	} else if (bitstring.length() == 0) {
	    return "";
	} else {
	    String s = "";
	    if (bitstring.charAt(0) == '1') {
		// If this is not the first set we're printing, add the
		// separator and reset the flag
		s += (elementsVirgin ? "" : ", ");
		elementsVirgin = false;

		s += list.get(list.size() - bitstring.length());
	    }

	    s += elements(list, bitstring.substring(1));

	    return s;
	}

    }
}
