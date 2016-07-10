public class BST {

		private Node _root;
		
		/**
		 * Constructor for BST. Setting _root to null. 
		 */
		public BST(){
			_root = null;
		}
		
		
		/**
		 * Getter method for instance variable _root
		 * @return: the root of this BST
		 */
		public Node getRoot() {
			return _root;
		}
		

		/**
		 * Setter method for instance variable _root 
		 * @param Node root
		 */
		public void setRoot(Node root) {
			this._root = root;
		}

		
		/**
		 * This method inserts a Node into this BST by calling 
		 * the method insertGivenRoot(root, number). 
		 * NOTE: If the BST is empty then the first statement of 
		 * this method sets the _root to the new root. But if 
		 * the BST is not empty then the first statement of this 
		 * method simply sets the root back to what it already is.
		 * @param Node number
		 */
		public void insert(Node number)
		{
			_root = insertGivenRoot(_root, number);
			System.out.println(number._data + " was inserted sucessfully!");
			
		}
		
		
		/**
		 * This method is called by the insert(number) method
		 * and does the work of inserting a Node into this BST.
		 * @param Node root
		 * @param Node number
		 * @return: the root Node of this BST. If the tree was empty
		 * upon calling this method then this root Node that is returned
		 * is the new root node (i.e. the root node is no longer null). 
		 * But if the tree was not empty upon calling this method 
		 * then this root Node that is returned is simply the 
		 * root of the tree, the same root the tree had before 
		 * calling this method. 
		 */
		public Node insertGivenRoot(Node root, Node number)
		{
			if (root == null)
			{
				root = number;
			}
			
			else
			{
				if (number._data < root._data)
				{
					root._left = insertGivenRoot(root._left, number);
				}
				
				else if (number._data > root._data)
				{
					root._right = insertGivenRoot(root._right, number);
				}
			}
			return root;
				
			
		}
		
		/**
		 * This method searches the BST for a node by calling the 
		 * searchGivenRoot(root, number) method. 
		 * @param Node number
		 * @return: true iff the Node number exists in the BST. 
		 */
		
		public boolean search(Node number)
		{
			//I made this method for convenience so that you
			//don't need to have the root when calling the method search. 
			boolean found = searchGivenRoot(_root, number);
			if (found)
			{
				System.out.println("Found " + number._data + "!");
			}
			else
			{
				System.out.println(number._data + " does not exist in the BST.");
			}
			return found;
		}
		
		
		/**
		 * This method is called by the search(number) method and
		 * does the work of finding out if the Node number
		 * exists in this BST. 
		 * @param Node root
		 * @param Node number
		 * @return: true iff the Node number exists in the BST.
		 */
		public boolean searchGivenRoot(Node root, Node number)
		{
			//base case
			if (root == null)
			{
				return false ;
			}
			
			if (number._data < root._data) 
			{
				return searchGivenRoot(root._left, number);
			}
			else if (number._data > root._data)
			{
				return searchGivenRoot(root._right, number);
			}
			else
			{
				//if here means root._data==number._data
				return true; 
			}	
			
		}
		
		
		/**
		 * This method checks to see if Node number exists in the BST
		 * and if it does then it deletes Node number from this BST
		 * by calling the deleteGivenRoot(root, number) method. 
		 * NOTE: If the Node number is the root of the tree, i.e.
		 * we are deleting the root of the tree then the third 
		 * statement of this method will actually set _root to 
		 * something new. But if we are deleting any node other than
		 * the root node then the third statement of this method simply
		 * sets _root to what it already is. 
		 * @param Node number
		 */
		public void delete(Node number)
		{
			if (!search(number))
			{
				return; //exits this method
			}
			_root = deleteGivenRoot(_root, number);
			System.out.println(number._data + " deleted successfully!");
		}
		
		
		/**
		 * This method is called by delete(number) and does the work
		 * of deleting a Node from this BST. 
		 * @param Node root
		 * @param Node number
		 * @return: the root Node of this BST. If we just deleted the 
		 * root of this BST then this root Node which we are returning 
		 * will be something new, i.e. it will not be equal to the 
		 * original root node. But if we were deleting a node other 
		 * than the root node of this tree then this root node 
		 * which we are returning is simply the root of this BST, i.e.
		 * the same root the tree had before calling this method. 
		 */
		public Node deleteGivenRoot(Node root, Node number)
		{
			if ( number._data == root._data)
			{
				//this means you want to delete the current root 
				//CASE 1: root is a leaf
				if (root._left == null && root._right == null)
				{ 
					return null;
				}
				
				//CASE 2: root has 2 children
				else if (root._left != null && root._right != null)
				{
					DeleteNodeWith2Children(root);
				}
				
				//CASE 3: root has 1 child
				else
				{
					return ChildOfNodeWith1Child(root);
				}
				
			}
			
			else if (number._data < root._data)
			{
				root._left = deleteGivenRoot(root._left, number);
			}
			
			else 
			{
				root._right = deleteGivenRoot(root._right, number);
			}
			
			return root; 
			
		}

		/**
		 * This method deletes a node, which has 2 children, from 
		 * the BST. 
		 * @param Node root
		 */
		public void DeleteNodeWith2Children(Node root)
		{ 
			//find biggest number on left sub tree
			Node biggest_on_left = biggestOnLeftSubTree(root._left);
			int biggest_number = biggest_on_left._data;
			//now make root's data this biggest number
			root._data = biggest_number;
			//now delete this biggest number
			root._left = deleteGivenRoot(root._left, biggest_on_left);	
		}

		
		/**
		 * This method takes in a Node which has 1 child and returns
		 * a pointer to this 1 child. 
		 * @param Node root
		 * @return: a Node pointer to the child of the inputed
		 * node.
		 */
		public Node ChildOfNodeWith1Child(Node root)
		{
			if (root._left == null)
			{
					return root._right;
			}
			else	
			{
					return root._left;
			}
		}
		
		
		/**
		 * This method finds the biggest Node of the BST rooted at 
		 * Node node. 
		 * @param Node node
		 * @return the biggest Node of the BST rooted at Node node. 
		 */
		public Node biggestOnLeftSubTree(Node node)
		{
			//base case
			if (node._right == null)
			{
				return node;
			}
			else
			{
				return biggestOnLeftSubTree(node._right);
			}
		}
		
		/**
		 * This method prints the preorder traversal of the tree rooted at root.
		 * @param Node root
		 */
		public void PreOrderTraversal(Node root)
		{
			if(root == null)
			{
				return;
			}
			System.out.print(root._data + "  ");
			PreOrderTraversal(root._left);
			PreOrderTraversal(root._right);
		}
	
				
}