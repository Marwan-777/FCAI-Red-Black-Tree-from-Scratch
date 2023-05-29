/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shahy
 */
public class node {
	
		int value;
		node right;
		node left;
		node parent;
		node w;
		node replacement;
		node x;
		boolean color;
       
    public boolean equals(node p) {
    	return (p.value == this.value) && (p.right == this.right ) && (p.left == this.left) && (p.parent == this.parent) && (p.color == this.color); 
    }
}
