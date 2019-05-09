/***********************************************************************************************************************
 * BSTNode.java
 * By Michael Dake
 * HW7, CS415
 *
 * Class to define the nodes for BST
 **********************************************************************************************************************/
package binarySearchTree;

public class BSTNode {
	  int value; // holds value of node
	  BSTNode left; // pointer to left child of node
	  BSTNode right; // pointer to right child of node
	 
	  // constructor for node
	  BSTNode(int value) {
	    this.value = value;
	    right = null;
	    left = null;
	 }
} // end class BSTNode
