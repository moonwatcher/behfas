Binary Exhaustive Haplotype Fragment Association Search
=======================================================

This is a Java implementation of the Exhaustive Haplotype Fragment Association Search using binary vectors and elementary binary operations like AND, OR and XOR to scan the tree and find the maximum value.

It is released under the terms of the [GNU GPL](http://www.gnu.org/licenses/old-licenses/gpl-2.0.html).
It was written during my short visit to the [Richard Durbin](https://www.sanger.ac.uk/person/durbin-richard/) lab at the [Sanger Institute](http://www.sanger.ac.uk) between 30/08/2006 ant 08/09/2006.
 

Problem statement
=================

Given N individual haplotype sequences h[i] (i = 0..N-1) where h[i][j] (j = 0..M-1) is either 0 or 1, indicating the value at position j in an ordered sequence of M SNP's.
We also know the case/control status c[i] for each individual i, that is whether it comes from a "case", a person with a disease, or a "control", a healthy individual.

The problem is to find position indices x and y (0 <= x <= y < M), and a subsequence s[j] (j = x..y) that maximizes the value Z(s) = (ad - bc)^2

where

 * a = |i such that h[i][j] = s[j] for all j in x..y AND c[i] = 0|
 * b = |i such that h[i][j] = s[j] for all j in x..y AND c[i] = 1|
 * c = |i such that h[i][j] != s[j] for some j in x..y AND c[i] = 0|
 * d = |i such that h[i][j] != s[j] for some j in x..y AND c[i] = 1|

Z(s)/(a+b+c+d)^2 is the chi-squared value used to test for association between the disease states (case/control) and the genetic value. We are therefore trying to find the haplotype fragment with strongest association to the case/control assignments.
