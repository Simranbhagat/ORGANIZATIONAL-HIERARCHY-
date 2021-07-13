import java.io.*; 
import java.util.*; 
import java.util.Vector;
import java.util.Collections;

//avl node
class AVL_Node
 {

 	int id;

    int h;
 	AVL_Node l;
 	AVL_Node r;
 	Node n;
 	
 	
 	public AVL_Node(int iden, Node n){
 		
 		this.id=iden;
 		this.h=0;
 		this.l=null;
 		this.r=null;
 		this.n = n;



 	}

 }
  class AVL{
 	
 	private AVL_Node root_avl;
    AVL(){
        root_avl=null;
    }
 	public boolean Empty(){
 		return (root_avl==null);
 	}
    //height function
    public int height_AVL(AVL_Node x){
    	if (x==null)
    		return -1;
    	else 
            return x.h;
    }
    public int max(int x,int y){
        if(x>y)
            return x;
        else
            return y;
    }
 	//rotations
 	private AVL_Node single_right(AVL_Node x) {
         AVL_Node p= x.l;
         AVL_Node q = p.r;
         p.r=x;


         x.l=q;
          
        
         
         x.h=max(height_AVL(x.r),height_AVL(x.l))+1;
         p.h=max(height_AVL(p.r),height_AVL(p.l))+1;
         return p;
 		}	

    
 	
 	private AVL_Node single_left(AVL_Node x){
 		AVL_Node p= x.r;
        AVL_Node q=p.l;
        p.l=x;  
        x.r=q;
          
        
         
         x.h=max(height_AVL(x.r),height_AVL(x.l))+1;
         p.h=max(height_AVL(p.r),height_AVL(p.l))+1;

       
         return p;


    }

 	




    

    public Node Search(int id){
        
        return Search(id,root_avl);
    
    }
    public Node Search(int id,AVL_Node x) {
        boolean t = false; 
    	while(x!=null && !t)   {
            if(x.id==id)
                {t=true;
                break;}
    	else if(id>x.id)
            x=x.r;
            
        
    	else 
            x=x.l;

}       
    
    if(t)
           return x.n; 
    else

        return null;  
}
  public int balance(AVL_Node x){
    if(x==null)
        return 0;
    return height_AVL(x.l)-height_AVL(x.r);
}

   public AVL_Node Balance2(AVL_Node y)
   {
        int b = balance(y);
        int bl=balance(y.l);
        int br= balance(y.r);
        if(b>1 && (bl==1|| bl==0)){
            return single_right(y);          
        }
        if(b>1 && bl==-1)
        {   y.l = single_left(y.l);
            return single_right(y);           
        }
        if( b<-1 && (br ==0 || br== -1)){    
            return single_left(y);
        }

       
        
        if(b<-1 && br==1)                    
        { y.r = single_right(y.r);
            return single_left(y);
        }
    
    return y;

   }
  







    public void Insert_Avl(int root_avlid,Node n){
        root_avl = Insert_Avl(root_avlid,n,root_avl);
    }
    public AVL_Node Insert_Avl(int id,Node n,AVL_Node y){
    	if(y==null)
 			y = new AVL_Node(id,n);
 		else if(id>y.id)
 			y.r=Insert_Avl(id,n,y.r);
        else
        	y.l=Insert_Avl(id,n,y.l);  

        y.h=max(height_AVL(y.r),height_AVL(y.l))+1;

        return Balance2(y);
}





    public AVL_Node left_most_node(AVL_Node n){
            
            	while(n.l!=null){
            		n=n.l;
                }
            	
            return n;
    }   

     public void deletion_AVL(int id,Node n){
         root_avl = deletion_AVL( id,n,root_avl);
     }
     public AVL_Node deletion_AVL(int id,Node n,AVL_Node root_avl){
        if(root_avl==null)
            return root_avl;

        if(root_avl.id==id){

        if( root_avl.l==null && root_avl.r==null)
        { root_avl=null;

        }
        else if(root_avl.l==null || root_avl.r==null){
            if(root_avl.r==null)
                    root_avl=root_avl.l;
            else
                root_avl=root_avl.r;

        }
         else{ 
            AVL_Node x;
            x=left_most_node(root_avl.r);
            root_avl.id = x.id;
            root_avl.r=deletion_AVL(x.id,n,root_avl.r);

    }
}
        else if(root_avl.id<id)
            root_avl.r=deletion_AVL(id,n,root_avl.r);
    
        else if(id<root_avl.id)
            root_avl.l=deletion_AVL(id,n,root_avl.l);
        
           if(root_avl == null)
                 return root_avl;

            root_avl.h = max(height_AVL(root_avl.r),height_AVL(root_avl.l))+1;
            return Balance2(root_avl);
        
    }
}
        





     
    










// Tree node




class Node{
	int id ;
    Node boss;
    int level;
    Vector<Node> employees;

   
    Node(int id,Node boss){
    	this.id= id;
        employees = new Vector<Node>();
        this.boss = boss;

    	
}
}

    
  




public class OrgHierarchy implements OrgHierarchyInterface{

//root node
Node root;

int size=0; 
 AVL t= new AVL();




public boolean isEmpty(){
	return (root==null);


		
}

public int size(){
	
     
    	return size;
  

	 }


public int level(int id) throws IllegalIDException, EmptyTreeException{
	

	
		
	if(root==null)
		throw new EmptyTreeException("empty tree");
	else{
		 Node k =  t.Search(id);
        if(k==null)
            throw new IllegalIDException("id out of limit");
        else
            return k.level;
	
 

}
}

	

public void hireOwner(int id) throws NotEmptyException{

	


	if(root==null)
	 { root = new Node(id,null);
	 
	 t.Insert_Avl(id,root);
	 
	 root.level=1;
     size++;

	 
	}
	else
		throw new NotEmptyException("owner asssigned already");




	
}

public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	
    if(isEmpty())
        throw new EmptyTreeException("empty tree");
	
	
	
	else{
        
		 Node k =  t.Search(bossid);
         if(k==null)
            throw new IllegalIDException("id out of limit");
         Node n = new Node(id,k);
         
         k.employees.add(n);
         t.Insert_Avl(id,n);
         n.level= k.level+1;
         size++;
     }
	
}
 

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{

	
	Node k;
		
	if(isEmpty())
		throw new EmptyTreeException("empty tree");
	else
          k = t.Search(id);
     if(k==null||k==root||k.employees != null)
         throw new IllegalIDException("id out of limit");
      else  {  Node boss = k.boss;
               boss.employees.remove(k);
               boss = null;
               t.deletion_AVL(id,k);
             
                  
             



     


	    size--;}
 	
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	//your implementation
	 Node k1,k2;
		if(root==null)
		throw new EmptyTreeException("empty tree");
	else
        k1 = t.Search(id);
        k2 = t.Search(manageid);
        if(k1==null || k2==null || k1==root || k1.level!=k2.level)
            throw new IllegalIDException("out of bound");
        else{k2.employees.addAll(k1.employees);
            for(int i=0;i< k2.employees.size();i++){
                Node n= k2.employees.get(i);
                n.boss = k2;
            }

         k2.boss.employees.remove(k1);
               k1.boss = null;
               t.deletion_AVL(id,k1);

   
        
	size--;}

} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{

	
	
	if(isEmpty())
		throw new EmptyTreeException("empty tree");
    else{
        Node i = t.Search(id);  
        if(i==null)
            throw new IllegalIDException("id out of bound");
        

        else
	        return i.boss.id;

	 

}
}
public Vector<Node> getBosses(int id){

	 Node k = t.Search(id);
	Vector v = new Vector();
	while(k.boss!=root){
		v.add(k.boss);
		k=k.boss;

	}
	return v; 


}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
    int common =0;
    Node k1,k2;
    boolean z= false;
if(isEmpty())
    throw new EmptyTreeException("empty tree");
else{  k1 = t.Search(id1);
         k2 = t.Search(id2);
    if(k1==null || k2==null)
            throw new IllegalIDException("out of bound");
    else{    
    Vector<Node> arr1=getBosses(id1);
    Vector<Node> arr2= getBosses(id2);


    
    for(int i =0;i<arr1.size();i++){
    	for(int j=0;j<arr2.size();j++){
    		if(arr1.get(i)==arr2.get(j))
    			{Node n=arr1.get(i);
                 common=n.id;
                 z=true;
                 break;}
    		
    			
    	}
    }
}
if(z){
   return 0;
}
return common;


}
}

/*public Vector<Node> Bfs(Node nd){
	 Vector<Node> v = new Vector<Node>();
	 Vector<Node> v1 = new Vector<Node>();
     v.add(nd);
	 v1.add(nd);
     


     

     
	 while(!v.isEmpty()){

	 	 Node k= v.firstElement();
         v.remove(v.firstElement());
       if (!k.employees.isEmpty()){
        int s=k.employees.size();
	 	
	 
	 	for(int i=0; i<s;i++){
	 		v.add(k.employees.get(i));
	 	}

	 	

        v1.addAll(v);
        Node first = v.lastElement();
        v.clear();
        v.add(first);
        


        
	 }

        else break;
     
    }

	 return v1;
}*/




public String toString(int id) throws IllegalIDException, EmptyTreeException{
	String s = new String();
    s="could not implement";
    return s;
	
		
	/*if(root==null)
		throw new EmptyTreeException("empty tree");
	else{

   Node k = t.Search(id);
   if(k==null)
    throw new IllegalIDException("id out of bound");
  else{

   Vector<Node>  v2 =Bfs(k);
   System.out.println(v2);
  

   Vector v3= new Vector();
   int t=0;
   int lev=1;

   while(t<v2.size()){
     while(v2.get(t).level==lev ) {
    	
    		v3.add(v2.get(t).id);
        t++;

    	}
        Collections.sort(v3);
        v3.toString();
        for(int f=0;f<v3.size();f++){
            s+=v3.get(f);
        }
        
          s+=(",");
          v3.clear();
   

          lev++;
    }

 

   



    

   return s;

    }
   

	
    }*/  
  }
}


