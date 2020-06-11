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
class GFG 
{

    static final int Applicants_Number = 6;
    static final int Jobs_Number = 6;

    // A DFS based recursive function that  
    // returns true if a matching for  
    // vertex u is possible 
    public boolean Bipartite_Matching(boolean bpGraph[][], int Applicants_Vertex, boolean[] viewed, int matchR[])
    {
        // Try every job one by one 
        for (int Jobs_Vertex = 0; Jobs_Vertex < Jobs_Number; Jobs_Vertex++)
        {
            // If applicant u is interested  
            // in job v and v is not visited 
            if (bpGraph[Applicants_Vertex][Jobs_Vertex] && !viewed[Jobs_Vertex]) {

                // The application was seen by the 
                viewed[Jobs_Vertex] = true;

                // If job 'v' is not assigned to 
                // an applicant OR previously 
                // assigned applicant for job v (which 
                // is matchR[v]) has an alternate job available. 
                // Since v is marked as visited in the  
                // above line, matchR[v] in the following 
                // recursive call will not get job 'v' again
                
                if (matchR[Jobs_Vertex] < 0 || Bipartite_Matching(bpGraph, matchR[Jobs_Vertex], viewed, matchR))
                {
                    matchR[Jobs_Vertex] = Applicants_Vertex;
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
        // An array to keep track of the  
        // applicants assigned to jobs.  
        // The value of matchR[i] is the  
        // applicant number assigned to job i,  
        // the value -1 indicates nobody is assigned. 
        int[] Matched_Applicant = new int[Jobs_Number];

        // Initially all jobs are available 
        for (int i = 0; i < Jobs_Number; ++i)
        {
            Matched_Applicant[i] = -1;
        }

        // Count of jobs assigned to applicants 
        int result = 0;
        for (int u = 0; u < Applicants_Number; u++)
        {
            // Mark all jobs as not seen  
            // for next applicant. 
            boolean[] viewed = new boolean[Jobs_Number];
            for (int i = 0; i < Jobs_Number; ++i)
            {
                viewed[i] = false;
            }

            // It checks if the applicant is suitable for the job.
            if (Bipartite_Matching(Graph, u, viewed, Matched_Applicant))
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
        GFG m = new GFG();
        System.out.println("Maximum number of Applicants that are suitble for the job : " + m.Maximum_Bipartite_Matching(Graph));
    }
}
