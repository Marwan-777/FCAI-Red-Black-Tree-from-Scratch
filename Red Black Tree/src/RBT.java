/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
/**
 *
 * @author shahy
 */
public class RBT {
      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author shahy
 */

    /**
     * @param args the command line arguments
     */
    
    public node root ;
    Integer len = 0;
	public void left_rotate(node rotated) 
        {
		if (rotated == root) {
			root = rotated.right;
		}
		node rotatedR = rotated.right;
		rotated.right = rotatedR.left;
		rotatedR.parent = rotated.parent;
		rotatedR.left = rotated;
		rotated.parent = rotatedR;
		if (rotatedR.parent != null) {
			if (rotatedR.parent.right == rotated) {     //old case for the parent
				rotatedR.parent.right = rotatedR;
			}
			else {
				rotatedR.parent.left = rotatedR;
			}
		}
	}
	void right_rotate(node rotated) {
		if (rotated == root) {
			root = rotated.left;
		}
		node rotatedL = rotated.left;
		rotated.left = rotatedL.right;
		rotatedL.parent = rotated.parent;
		rotatedL.right = rotated;
		rotated.parent = rotatedL;
		if (rotatedL.parent != null) {
			if (rotatedL.parent.right == rotated) {
				rotatedL.parent.right = rotatedL;
			}
			else {
				rotatedL.parent.left = rotatedL;
			}
		}

	}

	void insertion(Integer x) {                // inserting the node as in BST
		if (root == null) {
            root = new node() ;
			root.value = x;
                        
			root.parent = null;
			root.left = null;
			root.right = null;
			len++;
			// coloring

			root.color = true; // case 0 (root is black)

		}
		else {
			node current = null;
			node newnode = null;
			current = root;
			while (true) {
				if (x > current.value) {
					if (current.right == null) {
						newnode = new node();
						newnode.value = x;
						newnode.left = null;
						newnode.right = null;
						current.right = newnode;
						newnode.parent = current;
						len++;
						// coloring


						newnode.color = false; // null default color (red)
						return;
					}
					current = current.right;
				}
				if (x < current.value) {
					if (current.left == null) {
						newnode = new node();
						newnode.value = x;
						newnode.left = null;
						newnode.right = null;
						current.left = newnode;
						newnode.parent = current;
						len++;
						// coloring

						newnode.color = false; // null default color (red)

						return;
					}
					current = current.left;
				}
				if (x == current.value) {            // repetition case
					System.out.println(" You tried to enter an exciting element (" + x +")  !!");
					return;

				}
			}
		}
	}
        
	Vector< Vector<node>> paths = new Vector<Vector<node>>();
	Vector <node> path = new Vector <node> ();
	Integer pathNum = 0 ;
	/*
		First vector holds : nodes in a certain path
		Second vector holds : vector of nodes of the certain path
	*/
	void path_iteration(node check, int nodeNum) {      // Fill the paths vector
		 
		if (check == root) {
			pathNum = 0 ;
			paths.clear();
			path.clear();
		}
		if (check == null) {
			return;
		}
               
		path.add(check);
		if (check.left == null && check.right == null) {  // Leaf node (end of path)
			paths.add(new Vector<node>(path.size()));
			for(int i =0 ; i<path.size() ; i++) {
				paths.get( pathNum ).add(i, path.get(i));
			}
			pathNum++;
		}
		path_iteration(check.left, nodeNum + 1);
		path_iteration(check.right, nodeNum + 1);
		path.removeElementAt(path.size()-1);
	}

	 node Find_violation(node check) {       // return the red node that has a red parent
		node current ;
		current = check;
		if (current.color == false) {
			return current;
		}

		path_iteration(check, 0);    
		for (int i = 0; i < paths.size(); i++) {
			for (int j = 0; j < paths.get(i).size() - 1; j++) {
				if (paths.get(i).get(j).color == false && paths.get(i).get(j+1).color == false) {
					return  paths.get(i).get(j+1);
				}
               
			}
		}
		return null;

	}

	void Fix_violation(node violating) {       // handles the red black tree rules violation
		/*
			case 0 : null is root ( handled )
			case 1 : uncle is red -.> recolor parent , grand parent and uncle
			case 2 : uncle is black (triangle) -.> rotate the parent
			case 3 : uncle is black (line) -.> rotate the grand parent , recolor parent and grand parent
		*/
		
		if (violating == root) {
			root.color = !root.color;                // case 0 (root color is black)
			return;

		}
		if (violating == null) {         // if in the recursion call (down) there is no violation
			return;
		}
		node parent,  grandp,  uncle;
		parent = violating.parent;
		grandp = violating.parent.parent;
		uncle = null;
		
		if (grandp == null) {             // as it is the child of the root (no violation)
			return;
		}
		if (grandp.left .equals(parent)) {
			uncle = grandp.right;
		}
		if (grandp.right == parent) {
			uncle = grandp.left;
		}


		if (uncle != null) {
			if (uncle.color == false) {      // case 1
				parent.color = !parent.color;
				grandp.color = !grandp.color;
				uncle.color = !uncle.color;
			}
			else {
				if ((grandp.right == parent && parent.right == violating) || (grandp.left == parent && parent.left == violating)) {       // case 3
					if (parent.left != null) {
						if (parent.left == violating) {
							right_rotate(grandp);
							// recoloring
							parent.color = !parent.color;
							grandp.color = !grandp.color;
						}
					}
					if (parent.right != null) {
						if (parent.right == violating) {
							left_rotate(grandp);
							// recoloring
							parent.color = !parent.color;
							grandp.color = !grandp.color;
						}
					}

				}
				else {          // case 2
					if (parent.left != null) {
						if (parent.left == violating) {
							right_rotate(parent);
						}
					}
					if (parent.right != null) {
						if (parent.right == violating) {
							left_rotate(parent);
						}
					}
				}
			}
		}
		else {
			
			if ((grandp.right == parent && parent.right == violating) || (grandp.left == parent && parent.left == violating)) {       // case 3
				
				if (parent.left != null) {
					if (parent.left == violating) {
						right_rotate(grandp);
						// recoloring
						parent.color = !parent.color;
						grandp.color = !grandp.color;
					}
				}
				if (parent.right != null) {
					if (parent.right == violating) {
						
						left_rotate(grandp);
						// recoloring
						parent.color = !parent.color;
						grandp.color = !grandp.color;
					}
				}

			}
			else {          // case 2
				if (parent.left != null) {
					if (parent.left == violating) {
						right_rotate(parent);
					}

				}
				if (parent.right != null) {
					if (parent.right == violating) {
						left_rotate(parent);
					}
				}
			}

		}
		
		Fix_violation(Find_violation(root));

	}
	void levelOrder(node newroot) {
		Queue<node> myqueue = new LinkedList<>();
		myqueue.add(newroot);
		while (myqueue.size() != 0) {
			if (myqueue.peek().color == true) {
				System.out.print("color : black  , value : ") ;
			}
			else {
				System.out.print ("color : red  , value : ");
			}
			System.out.println(myqueue.peek().value+ " " );
			if (myqueue.peek().left != null)
				myqueue.add(myqueue.peek().left);
			if (myqueue.peek().right != null)
				myqueue.add(myqueue.peek().right);
			myqueue.poll();
		}
	}
	void insert(int x) {
		insertion(x);
		Fix_violation(Find_violation(root));
	}


	void case_choose(node current)
	{

		if (current.x != null && current.x.color == false)
			case0(current);

		else if (( ( current.x != null && current.x.color == true) || current.x == null) && current.w.color == false)
			case1(current);

		else if (((current.x != null && current.x.color == true) || current.x == null) && current.w.color == true && ((current.w.left!=null &&current.w.left.color == true)|| current.w.left==null) && ((current.w.right != null && current.w.right.color == true) || current.w.right == null))

			    case2(current);


		else if (((current.x != null && current.x.color == true) || current.x == null) && ((current.w != null && current.w.color == true) || current.w == null))
		{

			if ((current.w.parent.left == current.x && current.w.left.color == false && current.w.right.color == true) || (current.w.parent.right == current.x && current.w.right.color == false && ((current.w.left != null && current.w.left.color == true) || current.w.left == null)))
				case3(current);
			else if ((current.w.parent.left == current.x && current.w.right.color == false) || (current.w.parent.right == current.x && current.w.left.color == false))
				case4(current);
		}


	}

	void case0(node deleted)
	{

		deleted.x.color = true;

	}

	void case1(node deleted)
	{
        node parent2 = null;
		deleted.w.color = true;
		deleted.w.parent.color = false;

		if (deleted.w.parent.left == deleted.w)
		{
		    parent2=deleted.w.parent;
                    right_rotate(deleted.x.parent);
                    deleted.w = parent2.left;
                    deleted.w.parent=parent2;
		}
		else if (deleted.w.parent.right == deleted.w)
		{
		    parent2=deleted.w.parent;
		    left_rotate(deleted.w.parent);
			deleted.w = parent2.right;
			deleted.w.parent=parent2;

		}

        case_choose(deleted);

	}

	void case2(node deleted)
	{

		deleted.x.w.color = false;
		deleted.x = deleted.x.parent;
		if (deleted.x.color == true)
		{
			case_choose(deleted);
		}
	}
	void case3(node deleted)
	{

		node old_parent = null;
		old_parent = deleted.w.parent;
		if (deleted.w.parent.left == deleted.x)
		{
			deleted.w.left.color = true;
			deleted.w.color = false;
			right_rotate(deleted.w);
			deleted.w = old_parent.right;


		}

		else if (deleted.w.parent.right == deleted.x)
		{

			deleted.w.right.color = true;
			deleted.w.color = false;
			left_rotate(deleted.w);
			deleted.w = old_parent.left;

		}

		case4(deleted);
	}

	void case4(node deleted)
	{
		deleted.w.color = deleted.w.parent.color;
		deleted.w.parent.color = true;

		if (deleted.w.parent.left == deleted.x)
		{

			deleted.w.right.color = true;
			left_rotate(deleted.w.parent);
		}

		else if (deleted.w.parent.right == deleted.x)
		{



				deleted.w.left.color = true;
			    right_rotate(deleted.w.parent);

		}



	}
	void deletion(int y)
	{
		node current = null;
		current = root;
		if (root == null)
		{
			System.out.println ("the tree is empty");
		}
		else
		{
			//identifying the node to be deleted
			while (current != null)
			{
				if (current.value == y)
				{
					break;
				}
				else if (current.value < y)
				{
					current = current.right;
				}
				else if (current.value > y)
				{
					current = current.left;
				}
			}
			// identifying the replacement node
			node deleted = null;
			deleted = current;
			if (deleted.left == null && deleted.right == null)
			{
				deleted.x = null;
				deleted.replacement = null;
				if (deleted.value == root.value)
				{
					root = null;
				}
				else
				{
					if (deleted.parent.left != null && deleted.parent.left.value == deleted.value)
					{
						deleted.parent.left = deleted.replacement;

						deleted.w = deleted.parent.right;

						 if(deleted.w != null)
                            deleted.w.parent=deleted.parent;
					}
					else if (deleted.parent.right != null && deleted.parent.right.value == deleted.value)
					{

						deleted.parent.right = deleted.replacement;


						deleted.w = deleted.parent.left;
                        if(deleted.w != null)
                            deleted.w.parent=deleted.parent;

					}
				}


			}

			else if (deleted.left != null && deleted.right != null)
			{
				int counter = 0;
				int counter2 = 0;
				current = deleted.right;
				while (current.left != null)
				{
					current = current.left;
					counter++;
				}
				deleted.replacement = current;
				deleted.x = current.right;


				if (deleted.value == root.value)
				{
					if (counter > 0)
					{
						root = deleted.replacement;
						deleted.w=deleted.replacement.parent.right;
						deleted.w.parent=deleted.replacement.parent;
						deleted.replacement.parent = null;
						deleted.replacement.left = deleted.left;
						deleted.replacement.right = deleted.right;
						deleted.right.parent = deleted.replacement;
						deleted.left.parent = deleted.replacement;

					}
					else
					{
						root = deleted.replacement;
						deleted.replacement.parent = null;
						deleted.replacement.left = deleted.left;
						deleted.w = deleted.left;

					}

				}
				else if (deleted.parent.left != null && deleted.parent.left.value == deleted.value)
				{
					if (counter > 0)
					{
						deleted.replacement.parent.left = null;
						deleted.replacement.parent = deleted.parent;
						deleted.parent.left = deleted.replacement;
						deleted.replacement.right = deleted.right;
						deleted.replacement.left = deleted.left;
						deleted.right.parent = deleted.replacement;
						deleted.left.parent = deleted.replacement;



					}
					else
					{
						deleted.parent.left = deleted.replacement;
						deleted.replacement.parent=deleted.parent;
						deleted.replacement.left = deleted.left;
						deleted.w = deleted.left;
						deleted.w.parent = deleted.replacement;




					}


				}
				else if (deleted.parent.right != null && deleted.parent.right.value == deleted.value)
				{

					if (counter > 0)
					{
						deleted.replacement.parent.left = null;
						deleted.replacement.parent = deleted.parent;
						deleted.parent.right = deleted.replacement;
						deleted.replacement.left = deleted.left;
						deleted.replacement.right = deleted.right;
						deleted.left.parent = deleted.replacement;
						deleted.right.parent = deleted.replacement;
					}
					else
					{
						deleted.parent.right = deleted.replacement;
						deleted.replacement.parent=deleted.parent;
						deleted.replacement.left = deleted.left;
						deleted.w = deleted.left;
						deleted.w.parent = deleted.replacement;



					}

				}


			}


			else if (deleted.left == null)
			{
				deleted.x = deleted.right;
				deleted.replacement = deleted.right;
				deleted.x.parent = deleted.parent;

				if (deleted.value == root.value)
				{
					root = deleted.replacement;
				}
				else
				{
					if (deleted.parent.left == deleted)
					{
						deleted.replacement.parent = deleted.parent;
						deleted.parent.left = deleted.replacement;
						deleted.w = deleted.parent.right;

					}
					else if (deleted.parent.right == deleted)
					{
						deleted.replacement.parent = deleted.parent;
						deleted.parent.right = deleted.replacement;
						deleted.w = deleted.parent.left;

					}
				}
			}
			else if (deleted.right == null)
			{
				deleted.x = deleted.left;
				deleted.replacement = deleted.left;
				deleted.x.parent = deleted.parent;

				if (deleted.value == root.value)
				{
					root = deleted.replacement;
				}
				else
				{
					if (deleted.parent.left == deleted)
					{
						deleted.replacement.parent = deleted.parent;
						deleted.parent.left = deleted.replacement;
						deleted.w = deleted.parent.right;

					}
					else if (deleted.parent.right == deleted)
					{
						deleted.replacement.parent = deleted.parent;
						deleted.parent.right = deleted.replacement;
						deleted.w = deleted.parent.left;

					}
				}

			}

			if (deleted.color == true && (deleted.replacement !=null && deleted.replacement.color == false))
			{
				deleted.replacement.color = true;
			}
			else if (deleted.color == true && (deleted.replacement == null ||deleted.replacement.color == true ))//choose the case
			{
				case_choose(deleted);

			}
			else if ((deleted.color == false && deleted.replacement!=null )&& deleted.replacement.color == true)
			{
				deleted.replacement.color = false;
				case_choose(deleted);
			}



		}
	}

	
};