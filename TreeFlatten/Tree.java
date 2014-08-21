// Given a tree with n (n >0 )  nodes where, every node has a list of key-value pairs , with following properties :
//     1) child node will inherit key-value pair from its parent.
//     2) child node can override key-value pair at parent by creating a replica.
// 	A) You have to print the flattened key-values pairs at given node say m. 1 <= m <= n 
// 	B) Given a key value pair , print all nodes that satisfies this condition.

// 			For example : 
// 			Input :        
// 			n = 5 ,  m= C
                                             
//                                                    A   [ (name : abc), (id:5), (place:Tirupati) ]
//                                                   /  
// 		                                 B  [ (id : 24), ( name : efg) ]  
//                                                 / \
//  			      [(name : xyz) ]  C   D  [(place : Bangalore)]
//                                                   /
//                                                  E

// 			Output : [(name: xyz), (id:24), (place : Tirupati)] 



import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tree {
	public String nodeName;
	public Map<String,Object> attributes;
	public Tree left;
	public Tree right;

	public Tree(){
		nodeName = null;
		attributes = null;
		left = null;
		right = null;
	}
	
	public static Map<String,Object> treeFlatten(Tree root, String key){
		if(root == null)
			return null;
		
		if(root.nodeName == key){
			return root.attributes;
		}
		
		Map<String,Object> leftHash = treeFlatten(root.left, key);
		
		if(leftHash != null){
			for(String keyValue : root.attributes.keySet()){
				if(!leftHash.containsKey(keyValue)){
					leftHash.put(keyValue, root.attributes.get(keyValue));
				}
			}
			return leftHash;
		}
		
		Map<String,Object> rightHash = treeFlatten(root.right, key);
		if(rightHash != null){
			for(String keyValue : root.attributes.keySet()){
				if(!rightHash.containsKey(keyValue)){
					rightHash.put(keyValue, root.attributes.get(keyValue));
				}
			}
			return rightHash;
		}
		
		return null;
	}

	public static void main(String args[]){
		Tree A = new Tree();
		Tree B = new Tree();
		Tree C = new Tree();
		Tree D = new Tree();
		Tree E = new Tree();
		
		A.nodeName = "A";
		B.nodeName = "B";
		C.nodeName = "C";
		D.nodeName = "D";
		E.nodeName = "E";
		
		A.attributes = new HashMap<String, Object>();
		A.attributes.put("name", "abc");
		A.attributes.put("id",5);
		A.attributes.put("place", "Tirupati");
		A.left = B;
		
		B.attributes = new HashMap<String, Object>();
		B.attributes.put("name", "efg");
		B.attributes.put("id",24);
		B.left = C;
		B.right = D;
		
		
		C.attributes = new HashMap<String, Object>();
		C.attributes.put("name", "xyz");
		C.left = E;
		
		D.attributes = new HashMap<String, Object>();
		D.attributes.put("place", "Bangalore");

		System.out.println(Tree.treeFlatten(A, "C"));
	}
}