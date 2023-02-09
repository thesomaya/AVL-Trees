import java.util.Scanner;

class BSnode 
{
    private int id;
    private String name;
    private double salary;
    private BSnode right, left;
    private int balance;

    public BSnode (int d, String n, double s, BSnode r, BSnode l) 
    {
        // each node contains left & right pointers for next level nodes
        id = d;
        name = n;
        salary = s;
        right = r;
        left = l;
        balance = 0;
    } 
    public int getId ()	// returns the id number
    {
        return id;
    }
    public String getName ()	// returns the name
    {
        return name;
    }
  
    public double getSalary ()	// returns the salary
    {
        return salary;
    }
  
    public BSnode getRight ()	// returns the address of the right element
    {
        return right;
    }
  
    public BSnode getLeft ()	// returns the address of the left element
    {
        return left;
    }
  
    public int getBalance ()	// returns the balance
    {
        return balance;
    }
    public void setId (int d)	// sets the id
    {
        id = d;
    } 
    public void setName (String n)	// sets the name
    {
        name = n;
    } 
    public void setSalary (double s)	// sets the salary
    {
        salary = s;
    } 
    public void setRight (BSnode r)	// sets the address to the right element
    {
        right = r;
    } 
    public void setLeft (BSnode l)	// sets the address to the left element
    {
        left = l;
    } 
    public void setBalance (int b)	// sets the balance
    {
        balance = b;
    } 
    public void printData () 
    {
        System.out.println ("id = " + id);
        System.out.println ("name = " + name);
        System.out.println ("salary = " + salary);
    } 
} 
 
class AVLTree 
{
  
    private BSnode root;		// to point at first node in the tree
    // default constructor
    public AVLTree () 
    {
        root = null;		// initially empty tree
    } 
    public boolean isEmpty ()	// to check whether the tree is empty
    {
        if (root == null)
        return true;
        return false;
    }
  
    public boolean isFull ()	// to check whether the memory is full
    {
        // try adding a new  dummy element
        BSnode temp = new BSnode (10, "AA", 500, null, null);
        if (temp == null)		// if pointer temp is null, the element was not created
            return true;		// which means the memory is full, return true
        temp = null;		// delete the element as it is dummy
        return false;		// return false if not full
    }
 
    public BSnode Search (int d) 
    {
        // to search for an element by ID
        BSnode temp = root;
    
        if (temp == null)
      
        return null;
    
        while (temp != null)
        {				// while temp hasn't reached the end yet    
	        if (temp.getId () == d)	// we found it
	            return temp;		// return the element address
	        if (d > temp.getId ())	// if the id is larger than id of temp , go right
	            temp = temp.getRight ();
	        else
                temp = temp.getLeft ();	// else if the id id less than id of temp , go left
        }
        return null;
    }
  
 
    public int height ()	// height of tree using recursion
    {
        return height (root);
    }
  
    public int height (BSnode temp)	// a function to calculate the height of the tree
    {
        if (temp == null)
        return 0;
        int r = height (temp.getRight ());	// calls the function height, with temp.getRight as a parameter
        int l = height (temp.getLeft ());	// calls the function height, with temp.getLeft as a parameter
        if (r > l)			// checks whether the right or left is greater and returns the larger value plus one
            return 1 + r;
        else
            return 1 + l;
    }
  
 
    public void preOrder ()	// preOrder function using recursion
    {
        preOrder (root);		// calls the function below, and sends root as a parameter
    } 
    public void preOrder (BSnode temp)	// prints the elements of the tree (Root,Left,Right)
    {
        if (temp == null)
           return;
    
        temp.printData ();		// prints the element
        preOrder (temp.getLeft ());	// calls the function preOrder, and sends temp.getLeft as a parameter
        preOrder (temp.getRight ());	// calls the function preOrder, and sends temp.getRight as a parameter
    }
  
    public int countLeaves ()	// count Leaves (has no-children) using recursion
    {
        return countLeaves (root);
    }
  
    public int countLeaves (BSnode temp) 
    {
        if (temp == null)
            return 0;
        // if both getRight and getLeft are null, then the element is a leave (not connected to any children)
        if (temp.getLeft () == null && temp.getRight () == null)
            return 1;
        return countLeaves (temp.getRight ()) + countLeaves (temp.getLeft ());	// adds the value every time
    }
  
    public BSnode min (BSnode temp)	// a function that gets the node of minimum ID
    {
        // getLeft till we reach null
        if (temp == null)
            return null;
    
        while (temp.getLeft () != null)
            temp = temp.getLeft ();
    
        return temp;
    }

    public BSnode remove (int d) // remove using recursion
    {
        return remove (root, d);
    }
  
    public BSnode remove (BSnode temp, int d) // a function that removes an element
    {
        if (temp == null) // if reached null, and didn't find
        {
            System.out.println ("Not found");
            return null;
        }
    
        if (d > temp.getId ()) // if the ID is greater than temp
            temp.setRight (remove (temp.getRight (), d)); // setRight of temp equals to the function return
    
        else if (d < temp.getId ())
            temp.setLeft (remove (temp.getLeft (), d));	// setLeft of temp equals to the function return
        else if (temp.getRight () == null)	// if the Right is null
        { 
            if (root == temp) // if the temp we want to remove is the root
                root = root.getLeft (); // let root equal to the left
            else
                temp = temp.getLeft (); // else, if not the root, let temp equal to the left 
            return temp;
        }
    
        else if (temp.getLeft () == null) // if the Left is null
        { 
            if (root == temp) // if the temp we want to remove is the root
                root = root.getRight (); // let root equal to the right
            else
                temp = temp.getRight (); // else, if not the root, let temp equal to the right
            return temp;
        }
    
        else // in case temp has two children
        {
            BSnode Min = min (temp.getRight ()); // call the function min() with right of temp
	        temp.setId (Min.getId ());	// change the ID
	        temp.setName (Min.getName ());	// change the Name
	        temp.setSalary (Min.getSalary ());	// change the Salary
	        temp.setRight (remove (temp.getRight (), Min.getId ())); // call to remove the duplicate element
        }
        
        int b1 = balance (temp); // get the balance 
    
        if (b1 == -2) // if the balance is equal to -2
        {
            int b2 = balance (temp.getLeft ()); // get the balance of the left
            if (b2 == 1) // if the balance is equal to 1, rotate left and then rotate right 
                temp.setLeft (rotateLeft (temp.getLeft ())); // call the function rotate left 
            // this is performed whether b2 = 1 or -1 (in both cases)
            temp = rotateRight (temp); // call the function rotate right
      
            }
            else if (b1 == 2)
            {
                int b2 = balance(temp.getRight()); // get the balance of the right
                if (b2 == -1) // if the balance is equal to -1, rotate right and then rotate left 
                    temp.setRight(rotateRight(temp.getRight())); // call the function rotate right
                // this is performed whether b2 = 1 or -1 (in both cases)
                temp = rotateLeft(temp); // call the function rotate left
                       
            } 
            return temp;
        }
  
    public int balance (BSnode temp) // function  to get the balance 
    {
        int r = height (temp.getRight ()); // gets the height of the right
        int l = height (temp.getLeft ()); // gets the height of the left
        int b = r - l; // gets the balance by subtracting the left from the right
        return b;
    }
 
    public BSnode insert (int d, String n, double s) 
    {
        return insert (root, d, n, s);
    }
  
    public BSnode insert (BSnode temp, int d, String n, double s) 
    {
        if (isFull ()) // checks for memory space
        {
            System.out.println ("No memory.");
            return temp;
        }
        if (temp == null)
        {
            temp = new BSnode (d, n, s, null, null);
            if (root == null)
                root = temp;
            return temp;
        }
    
        if (temp.getId () == d)
        {
            System.out.print ("Existing before");
            return null;
        }
        if (d > temp.getId ())
        {
            temp.setRight (insert (temp.getRight (), d, n, s));
            int b1 = balance (temp);  // get the balance 
            if (b1 == 2) // if the balance is equal to 2
	        {
                int b2 = balance (temp.getRight ());  // get the balance of the right
                if (b2 == -1) // if the balance is equal to -1, rotate right and then rotate left 
                    temp.setRight (rotateRight (temp.getRight ()));
                if (temp == root) // if the temp is equal to root
                    root = rotateLeft (temp); // call the function rotate left and return to the root
	            else
                    temp = rotateLeft (temp); // call the function rotate left and return to temp
            }
        }
        else
        {
            temp.setLeft (insert (temp.getLeft (), d, n, s));
            int b1 = balance (temp); // get the balance 
	
            if (b1 == -2) // if the balance is equal to -2
	        {
                int b2 = balance (temp.getLeft ()); // get the balance of the left
                if (b2 == 1) // if the balance is equal to 1, rotate left and then rotate right 
                    temp.setLeft (rotateLeft (temp.getLeft ()));  // call the function rotate left 
                if (temp == root) // if temp is equal to root
                    root = rotateRight (temp);  // call the function rotate right and return to the root
	            else
                    temp = rotateRight (temp);  // call the function rotate right and return to temp
            }
        }
            return temp;
    }
 
    public BSnode rotateRight (BSnode temp) // rotate right function
    {
        BSnode L = temp.getLeft (); // let L equal to the left
        temp.setLeft (L.getRight ()); // set the left of temp to the right of L
        L.setRight (temp); // set the right of L to temp 
        return L;
    }
    
    public BSnode rotateLeft (BSnode temp)  // rotate left function
    {
        BSnode R = temp.getRight (); // let R equal to the right
        temp.setRight (R.getLeft ()); // set the right of temp to the left of R
        R.setLeft (temp); // set the left of R to temp 
        return R;
    }
} // end class AVLTree

