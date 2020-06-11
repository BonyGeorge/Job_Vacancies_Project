package third_project;

/**
 *
 * @author abanoublamie
 */
// A Java program to find maximal 
// Bipartite matching. 
import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class GFG 
{

    static final int Applicants_Number = 6;
    static final int Jobs_Number = 6;

    // A DFS based recursive function that  
    // returns true if a matching for  
    // vertex u is possible 
    boolean bpm(boolean bpGraph[][], int Applicants_Vertex, boolean[] viewed, int matchR[])
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
                if (matchR[Jobs_Vertex] < 0 || bpm(bpGraph, matchR[Jobs_Vertex], viewed, matchR))
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
    int Maximum_Bipartite_Matching(boolean[][] Graph) {
        // An array to keep track of the  
        // applicants assigned to jobs.  
        // The value of matchR[i] is the  
        // applicant number assigned to job i,  
        // the value -1 indicates nobody is assigned. 
        int matchR[] = new int[Jobs_Number];

        // Initially all jobs are available 
        for (int i = 0; i < Jobs_Number; ++i) {
            matchR[i] = -1;
        }

        // Count of jobs assigned to applicants 
        int result = 0;
        for (int u = 0; u < Applicants_Number; u++) {
            // Mark all jobs as not seen  
            // for next applicant. 
            boolean seen[] = new boolean[Jobs_Number];
            for (int i = 0; i < Jobs_Number; ++i) {
                seen[i] = false;
            }

            // Find if the applicant 'u' can get a job 
            if (bpm(Graph, u, seen, matchR)) {
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
