/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 *
 * @author shahy
 */
public class RedBlackTreeJava {

    /**
     * @param args the command line arguments
     */
    
  

    public static void main(String[] args) {
        

	RBT tree = new RBT();
	/*System.out.println << endl << endl << "insert 5" << endl << endl;
	tree.insert(7);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 6" << endl << endl;
	tree.insert(3);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 7" << endl << endl;
	tree.insert(18);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 16" << endl << endl;
	tree.insert(10);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 13" << endl << endl;
	tree.insert(22);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 12" << endl << endl;
	tree.insert(8);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 2" << endl << endl;
	tree.insert(11);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 1" << endl << endl;
	tree.insert(26);
	tree.levelOrder(tree.root);
*/
	/*System.out.println << endl << endl << "insert 5" << endl << endl;
	tree.insert(5);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 6" << endl << endl;
	tree.insert(2);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 7" << endl << endl;
	tree.insert(8);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 16" << endl << endl;
	tree.insert(1);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 13" << endl << endl;
	tree.insert(4);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 12" << endl << endl;
	tree.insert(7);
	tree.levelOrder(tree.root);
	System.out.println << endl << endl << "insert 2" << endl << endl;
	tree.insert(9);
	tree.levelOrder(tree.root);
	*/
	System.out.println ("\n"+"\n"+"insert 13"+"\n"+"\n");
	tree.insert(13);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 8"+"\n"+"\n");
	tree.insert(8);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 17"+"\n"+"\n");
	tree.insert(17);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 1"+"\n"+"\n");
	tree.insert(1);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 11"+"\n"+"\n");
	tree.insert(11);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 15"+"\n"+"\n");
	tree.insert(15);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 25"+"\n"+"\n");
	tree.insert(25);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 6"+"\n"+"\n");
	tree.insert(6);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 22"+"\n"+"\n");
	tree.insert(22);
	tree.levelOrder(tree.root);
	System.out.println ("\n"+"\n"+"insert 27"+"\n"+"\n");
	tree.insert(27);
	tree.levelOrder(tree.root);


	System.out.println ("\n");
       
	System.out.println("\n"+"\n"+"delete 8" +"\n"+"\n");
	tree.deletion(8);

	tree.levelOrder(tree.root);
	
}

    }
    
