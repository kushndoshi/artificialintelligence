import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Graph {
    int a[][];
    boolean visited[];
    static BufferedReader br=new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
    static BufferedWriter fr;
   
    
    Graph(int m){
        a=new int[m][m];
        visited=new boolean[m];
        try {
            fr = new BufferedWriter(new FileWriter("D:/graph.py"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   }

  void getGraph() throws IOException{
      fr.write("import pygraphviz as pgv\nA=pgv.AGraph()\n");
      int choice=0;
      do{
          
          System.out.println("Enter edge");
          String edge=br.readLine();
          a[edge.charAt(0)-65][edge.charAt(1)-65]=1;
          fr.write("A.add_edge('"+edge.charAt(0)+"','"+edge.charAt(1)+"')\n");
          System.out.println("Enter -1 to discontinue");
          choice=Integer.parseInt(br.readLine());

      }while(choice!=-1);
  }

  public static void main(String arg[]) throws IOException
  {
      System.out.println("No of nodes");
      int n=Integer.parseInt(br.readLine());
      Graph g=new Graph(n);
      g.getGraph();
      System.out.println("Enter source and destination");
      String l[]=br.readLine().split("\\s+");
      int k[]=new int[l.length];
      int i=0;
      for(String s:l)
      {
          k[i]=s.charAt(0)-65;
          i++;
      }

      g.dfs(k);
      fr.write("A.layout()\nA.draw('file.png')");
      fr.flush();
      fr.close();

  }


  void dfs(int[]k) throws IOException{
      visited[k[0]]=true;
      for(int i=0;i<a[0].length;i++)
        {
            if(a[k[0]][i]==1 && !visited[i] && k[1]!=i)
            {
                fr.write("e=A.get_edge('"+(char)(k[0]+65)+"','"+(char)(i+65)+"')\n");
                fr.write("e.attr['color']='green'\n");
                fr.flush();
                dfs(new int[]{i,k[1]});
               
            }
            if(k[1]==i)
                break;
        }
       }
  }
