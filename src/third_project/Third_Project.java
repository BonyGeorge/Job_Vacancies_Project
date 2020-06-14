package third_project;

/**
 *
 * @author abanoublamie
 */

import java.util.*;
import java.lang.*;
import java.io.*;

/*

   Problem :

   A matching in a Bipartite graph is a set of the edges chosen in such a
   way that no two edges share an endpoint. A maximum matching is a
   matching of maximum size (maximum number of edges). In a
   maximum matching, if any edge is added to it, it is no longer a
   matching. There can be more than one maximum matchings for a given
   Bipartite graph.
   For example, consider the following problem:
   There are M job applicants and N jobs. Each applicant has a subset of
   jobs that he/she is interested in. Each job opening can only accept one
   applicant and a job applicant can be appointed for only one job. Find an
   assignment of jobs to applicants in such that as many applicants as
   possible get jobs.

 */
class Job_Vacancies 
{

    static int Applicants_Number;
    static int Jobs_Number;

    public Job_Vacancies()
    {
        Applicants_Number = 0;
        Jobs_Number = 0;
    }    
    
    // Functions to get the number of jobs available and number of submitted applicants
    public int insert_Count_Applicants(int Number)
    {
        return this.Applicants_Number = Number;
    }        
    
    public int insert_Count_Jobs(int Number)
    {
        return this.Jobs_Number = Number;
    }        
    
    // Function to check matching between verticies.
    public boolean Bipartite_Matching(boolean[][] Bipartite_Graph, int Applicants_Vertex, boolean[] viewed, int[] Matched)
    {
        for (int Jobs_Vertex = 0; Jobs_Vertex < Jobs_Number; Jobs_Vertex++)
        {
            // Checks if the applicant submitted fo this job or not he must not be in another job.
            if (Bipartite_Graph[Applicants_Vertex][Jobs_Vertex] && !viewed[Jobs_Vertex]) 
            {
                viewed[Jobs_Vertex] = true;
                
                /*
                    If the job isn't assigned to any applicant
                    then the applicant will be assigned for the 
                    job.
                */
                
                if (Matched[Jobs_Vertex] < 0 || Bipartite_Matching(Bipartite_Graph, Matched[Jobs_Vertex], viewed, Matched))
                {
                    Matched[Jobs_Vertex] = Applicants_Vertex;
                    return true;
                }
            }
        }
        return false;
    }

    // Returns maximum number  
    // of matching from M to N 
    public int Maximum_Bipartite_Matching(boolean[][] Graph)
    { 
        int[] Matched_Applicant = new int[Jobs_Number];

        // It initialize all the jobs by available for any applicant to submit in it.
        for (int i = 0; i < Jobs_Number; ++i)
        {
            Matched_Applicant[i] = -1;
        }
 
        int result = 0;
        
        // We count here how many jobs the applicant submitted in.
        for (int Vertex_U = 0; Vertex_U < Applicants_Number; Vertex_U++)
        {
           
            boolean[] viewed = new boolean[Jobs_Number];
            
            for (int i = 0; i < Jobs_Number; ++i)
            {
                viewed[i] = false;
            }
            
            // It checks if the applicant is suitable for the job.
            if (Bipartite_Matching(Graph, Vertex_U, viewed, Matched_Applicant))
            {
                result++;
            }
        }
        
        return result;
    }
}

public class Third_Project {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        // The Bipartaite Graph.
        boolean Graph[][] = new boolean[][]{
            {false, true, true,
                false, false, false},
            {true, false, false,
                true, false, false},
            {false, false, true,
                false, false, false},
            {false, false, true,
                true, false, false},
            {false, false, false,
                false, false, false},
            {false, false, false,
                false, false, true}};

        // Instance of 
        Job_Vacancies m = new Job_Vacancies();
        Scanner number = new Scanner(System.in);
        
        System.out.println("Enter Number of Applicants : ");
        m.insert_Count_Applicants(number.nextInt());
        
        System.out.println("Enter Number of Jobs : ");
        m.insert_Count_Jobs(number.nextInt());
        
        System.out.println("Maximum number of Applicants that are suitble for the job : " + m.Maximum_Bipartite_Matching(Graph));
    }
}
