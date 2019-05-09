/***********************************************************************************************************************
 * BSTTesting.java
 * By Michael Dake
 * HW7, CS415
 *
 * Junit testing file for Binary Search Tree
 **********************************************************************************************************************/

package BSTTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import binarySearchTree.BST;

class BSTTesting {

	@Test
	public void EmptyBSTConstructorTest() {
		BST bst = new BST();
		assertNotNull(bst);
	}
	
	@Test
	public void ArrayBSTConstructorTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		assertNotNull(bst);
	}
	
	@Test
	public void BSTInOrderTraversalTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		List<Integer> control = new ArrayList<Integer>();
		control.add(5);
		control.add(7);
		control.add(10);
		control.add(13);
		control.add(15);
		control.add(20);
		List<Integer> test = bst.inOrderTraversal(bst.getRoot());
		assertEquals(test, control);
	}
	
	@Test
	public void BSTToStringTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		String control = "[5][7][10][13][15][20]";
		String test = bst.toString();
		assertEquals(test, control);
	}
	
	@Test
	public void InsertTest() {
		BST bst = new BST();
		bst.insert(10);
		bst.insert(20);
		bst.insert(5);
		bst.insert(7);
		bst.insert(5);
		bst.insert(13);
		bst.insert(15);
		String control = "[5][7][10][13][15][20]";
		String test = bst.toString();
		assertEquals(test, control);
	}
	
	@Test
	public void DeleteRootTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		bst.delete(10);
		String control = "[5][7][13][15][20]";
		String test = bst.toString();
		assertEquals(test, control);
	}
	
	@Test
	public void DeleteLeafTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		bst.delete(7);
		String control = "[5][10][13][15][20]";
		String test = bst.toString();
		assertEquals(test, control);		
	}
	
	@Test
	public void DeleteInnerNodeNoLeftChildTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		String control = "[7][10][13][15][20]";
		bst.delete(5);
		String test = bst.toString();
		assertEquals(test, control);
	}
	
	@Test
	public void DeleteInnerNodeNoRightChildTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		String control = "[5][7][10][13][15]";
		bst.delete(20);
		String test = bst.toString();
		assertEquals(test, control);
	}	
	
	@Test
	public void DeleteValueDoesNotExistTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		String control = "[5][7][10][13][15][20]";
		bst.delete(8);
		String test = bst.toString();
		assertEquals(test, control);
	}
	
	@Test
	public void DeleteInnerNodeWithLeftAndRightChildTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15, 3, 2, 4};
		BST bst = new BST(arr);
		String control = "[2][3][4][7][10][13][15][20]";
		bst.delete(5);
		String test = bst.toString();
		assertEquals(test, control);
	}
	
	@Test
	public void DeleteFromEmptyBSTTest() {
		BST bst = new BST();
		bst.delete(10);
		String control = "";
		String test = bst.toString();
		System.out.println(test);
		assertEquals(test, control);
	}
	
	@Test
	public void PositiveContainsTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		assertTrue(bst.contains(10));
	}
	
	@Test
	public void NegativeContainsTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		assertFalse(bst.contains(8));
	}
	
	@Test
	public void GetHeightTest() {
		int[] arr = {10, 20, 5, 7, 5, 13, 15};
		BST bst = new BST(arr);
		int height = 4;
		int testHeight = bst.getHeight();
		assertEquals(testHeight, height);
	}

}
