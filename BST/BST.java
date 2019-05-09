/***********************************************************************************************************************
 * BST.java
 * By mtdake
 * HW7, CS415
 *
 * Implementation file for Binary Search Tree class
 **********************************************************************************************************************/
package binarySearchTree;

import java.util.ArrayList; // ArrayList<>
import java.util.LinkedList; // LinkedList<>
import java.util.List; // List<>
import java.util.Queue; // Queue<>
import java.util.Stack; // Stack<>

public class BST {
	
	/**************************
	 * Class data members
	 *************************/
	private BSTNode root; // stores root of BST object
	
	
	/**************************
	 * Constructors
	 *************************/
	// empty constructor
	public BST() {
		root=null;
	}
	
	// array constructor
	public BST(int[] arr) {
		for(int value : arr) {
			if(!contains(value))
				insert(value);
		}
	}
	
	/**************************
	 * Public methods
	 *************************/
	
	// inserts a value into the BST
	public void insert(int value) {
		BSTNode newNode = new BSTNode(value);
		if(root==null) {
			root=newNode;
			return;
		}
		
		BSTNode parent=root;
		BSTNode current=root;
		
		// find parent of new node
		while(current!=null) {
			parent=current;
			if(value==current.value)
				return;
			else if(value<current.value)
				current=current.left;
			else
				current=current.right;
		}
		// insert newNode as left or right child
		if(value<parent.value)
			parent.left=newNode;
		else
			parent.right=newNode;
	} // end insert
	
	/***************************************************************/
	
	// checks to see if the passed in value is present within the BST
	public boolean contains(int value) {
		BSTNode current=root;
		while(current!=null) {
			if(current.value==value)
				return true;
			else if(value<current.value)
				current=current.left;
			else
				current=current.right;
		}
		return false;
	} // end contains

	/***************************************************************/
	
	// deletes the passed in value from the BST
	public void delete(int value) {

		// check if BST is empty
		if(root==null)
			return;
		// check if value exists in BST
		if(!contains(value))
			return;
		
		BSTNode current = root;
		BSTNode parent = null;
		
		// find node of value to be deleted and that node's parent
		while(current.value!=value && current!=null) {	
			parent=current;
			if(value<current.value)
				current=parent.left;
			else
				current=parent.right;
		}
		
		// value to be deleted is a leaf, just delete
		if(current.left==null && current.right==null) {
			
			if(current==root)
				root=null;
			//node to be deleted is left child of parent
			else if(parent.left==current)
				parent.left=null;
			// node to be deleted is right child of parent
			else
				parent.right=null;
			
			return;
		}
		
		// node to be deleted only has a left child
		if(current.right==null && current.left!=null) {
			if(current==root)
				root=current.left;
			else {
				if(parent.right==current)
					parent.right=current.left;
				else
					parent.left=current.left;
			}
			return;
		}
		
		// node to be deleted only has a right child
		if(current.left==null && current.right!=null) {
			if(current==root)
				root=current.right;
			else {
				if(parent.right==current)
					parent.right=current.right;
				else
					parent.left=current.right;
			}
			return;
		}
		
		// node to be deleted has both a left and right child
		if(current.left!=null && current.right!=null) {
			BSTNode[] pair=largestLeftChildAndParent(current);
			BSTNode largestSmallParent = pair[1];
			current.value = pair[0].value;
			if(current.value==largestSmallParent.right.value)
				largestSmallParent.right=null;
			else
				largestSmallParent.left=null;
			return;
		}
	} // end delete

	/***************************************************************/
	
	// create an inOrder list of BST values
	public List<Integer> inOrderTraversal(BSTNode root){
		BSTNode current = root;
		Stack<BSTNode> inOrderStack = new Stack<BSTNode>();
		List<Integer> inOrderList = new ArrayList<Integer>();
		
		// populates the stack with values and right children to be processed
		while(current!=null || !inOrderStack.isEmpty()) {
			if(current!=null) {
				inOrderStack.push(current);
				current=current.left;
			}
			else {
				current=inOrderStack.pop();
				// process value
				inOrderList.add(current.value);
				current=current.right;
			}
		}
		return inOrderList;
	} // end inOrderTraversal

	/***************************************************************/
	
	// toString method to print list of BST values
	public String toString() {
		String values = "";
		List<Integer> inOrderList = inOrderTraversal(root);
		// if BST is empty, do nothing
		if(root==null) {
			return values;
		}
		for(int i=0; i<inOrderList.size(); i++) {
			values += "[" + inOrderList.get(i) + "]";
		}
		return values;
	} // end toString

	/***************************************************************/
	
	// return the height of the BST
	public int getHeight() {
		if(root==null)
			return 0;
		int height=0;
		Queue<BSTNode> treeQueue = new LinkedList<BSTNode>();
		
		treeQueue.add(root);
		
		// continue to loop until return
		do {
			
			// count, initialized by root level, equals number of nodes at the current level
			int count = treeQueue.size();
			if(count==0)
				return height-1;
			height++;
			
			// remove nodes of current level, add nodes of next level
			while(count>0) {
				BSTNode newNode = treeQueue.peek();
				treeQueue.remove();
				if(newNode.left!=null)
					treeQueue.add(newNode.left);
				if(newNode.right!=null)
					treeQueue.add(newNode.right);
				count--;
			} // end while
		}while(true); // end do-while
	} // end getHeight
	
	/**************************
	 * Private methods
	 *************************/
	
	// return BSTNode array containing the largest child from current's left subtree and that child's parent
	private BSTNode[] largestLeftChildAndParent(BSTNode current) {
		BSTNode[] pair = new BSTNode[2];
		BSTNode parent = current;
		
		// shift left
		current=current.left;
		if(current==null)
			return null;
		
		// find largest child and that child's parent
		while(current.right!=null) {
			parent=current;
			current=current.right;
		}

		pair[0] = current;
		pair[1] = parent;
		return pair;
	} // end largestLeftChildAndParent

	/***************************************************************/
	
	// get root node
	public BSTNode getRoot() {
		return root;
	} // end getRoot

	/***************************************************************/
	
	// get node value
	public int getValue(BSTNode node) {
		return node.value;
	}// end getValue
	
	/**************************
	 * Static methods
	 *************************/
	
	// returns a list containing the values present in the BST within the specified range
	public static List<Integer> findMinMax(int min, int max, BST tree){
		BSTNode current = tree.root;
		Stack<BSTNode> inOrderStack = new Stack<BSTNode>();
		List<Integer> range = new ArrayList<Integer>();
		
		// populates the stack with values and right children to be processed
		while(current!=null || !inOrderStack.isEmpty()) {
			if(current!=null) {
				inOrderStack.push(current);
				current=current.left;
			}
			else {
				current=inOrderStack.pop();
				// exclude values outside of provided range
				if(current.value>=min && current.value<=max)
					range.add(current.value);
				current=current.right;
			}
		}
		return range;
	} // end findMinMax
} // end class BST

