import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class questions1 {
    /**
     * 7;1 2;2 3;5;0;5;-1;-1
     * 5;1 2 3 4;1 2;3 4;0 4;-1
     */
    public List<Integer> result = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = in.nextInt();
        List<List<Integer>> graph= new ArrayList<>();
        List<Integer> finalStep = new ArrayList<>();
        int count = 0;
        for(int i = 0; i<sum;i++){
            ArrayList<Integer> line = new ArrayList<>();
            String[] thisLine = in.nextLine().split(" ");
            for(String number: thisLine){
                int num = Integer.parseInt(number);
                line.add(num);
            }
            if(thisLine.length == 1 && thisLine[0] == "-1"){
                finalStep.add(i);
            }
        }
//        while(in.hasNextLine()){
//            ArrayList<Integer> line = new ArrayList<>();
//            String[] thisLine = in.nextLine().split(" ");
//            for(String number: thisLine){
//                int num = Integer.parseInt(number);
//                line.add(num);
//            }
//            if(thisLine.length == 1 && thisLine[0] == "-1"){
//                finalStep.add(count);
//            }
//            count++;
//        }
        System.out.print("end");
    }
    public void find(List<List<Integer>> graph, int sum,List<Integer> finalStep){
        //深度搜索，如果都能到达final，那就是对的
        //finalStepNumber: 要求的最终工序
        HashSet<Integer> used = new HashSet<>();
        for(int finalStepNumber: finalStep){
            //遍历
            for(int i = 0; i <= sum;i++){
                //the start of
                dfs(graph, finalStepNumber, i, used);
            }
        }
    }
    //深度搜索直到最底，如果没有的话该如何判断...
    //graph 值
    private void dfs(List<List<Integer>> graph,int finalStep, int line, HashSet<Integer> used){
        List<Integer> thisLine = graph.get(line);


    }
}
