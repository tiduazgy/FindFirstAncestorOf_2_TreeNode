import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zgy on 2017/3/22.
 */
public class FindAncestor {
    public static void main(String[] args){
        System.out.println("输入一串数字");
        ArrayList<BNode> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
    /*    while (input.hasNextInt() == true){
            list.add(new BNode(input.nextInt()));
        }*/
        for(int i = 0;i < 699;i++){
            list.add(new BNode(i));
        }
        BTree tree =new BTree(list);
        //tree.preorder();
        BNode randomNode1 = list.get(513);
        BNode randomNode2 = list.get(514);

        System.out.println(findFirstAncestor_1(randomNode1,randomNode2,tree.root).value+"");
    }

    static BNode findFirstAncestor_1(BNode node1,BNode node2,BNode root){
        if(root == null)return null;
        if(root == node1 || root == node2) return root;
        BNode inleft = findFirstAncestor_1(node1,node2,root.left);
        BNode inright = findFirstAncestor_1(node1,node2,root.right);
        if(inleft != null && inright != null)return root;
        else if(inleft == null){return findFirstAncestor_1(node1,node2,root.right);}
        else{
            return findFirstAncestor_1(node1,node2,root.left);
        }
    }
    static boolean inThisTree(BNode root,BNode target){
        if(root == null)return false;
        else if(root == target)return true;
        else if(inThisTree(root.left,target) || inThisTree(root.right,target))return true;
        else return false;
    }

}

class BNode{
    int value;
    BNode left;
    BNode right;
    BNode(int value){
        this.value = value;
    }
}

class BTree{
    BNode root;
    ArrayList<BNode> nodeList;

    BTree(ArrayList<BNode> list){
        nodeList = list;
        root = list.get(0);
        for(int i = 0;i < list.size();i++){
            int left = i*2+1;
            int right = i*2+2;
            if(left < list.size()){
                list.get(i).left = list.get(left);
            }
            else {break;}

            if( right < list.size()){
                list.get(i).right = list.get(right);
            }
            else {break;}
        }
    }

    public void preorder(){
        preorder(root);
    }

    private void preorder(BNode root){
        if(root != null){
            System.out.print(root.value + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    }