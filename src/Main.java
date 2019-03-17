import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {


    public static void main(String[] args) {
        System.out.println("Hello World!");

        float waity;
        int arrival[] = {0,0};
        int burst[] = {4,4} ;
        waity = roundRobin(arrival,burst,3);

        System.out.println(waity);

        //waity = roundRobin({},{},3);

        //System.out.println(waity);

    }


    static public float roundRobin ( int[] arrivalTimes, int[] burstTimes, int quantumTime) {
        //Avoid divide by zero
        if(arrivalTimes.length == 0)
            return 0;

        //processes can be either arriving, running or finished
        List<Process> arrivingProcesses = new ArrayList<Process>();
        Queue<Process> runningProcesses = new LinkedList<Process>();
        List<Process> finishedProcesses = new ArrayList<Process>();

        //Create all processes in arriving
        for(int i = 0; i < arrivalTimes.length; i++){
            arrivingProcesses.add(new Process(arrivalTimes[i], burstTimes[i]));
        }
        //I assume the arrays already list the processes based on priority.
        // If there is another way you want to choose priority then you should sort arrivingProcesses


        int currentTime = 0;

        //Simulate time until the processes are all finished
        while(!(arrivingProcesses.isEmpty() && runningProcesses.isEmpty())){

            //First add any arriving processes to the queue
            for(int i = arrivingProcesses.size()-1; i>= 0; i--){
                if(arrivingProcesses.get(i).arrivalTime <= i){
                    runningProcesses.add(arrivingProcesses.get(i));
                    arrivingProcesses.remove(i);
                }
            }

            //Run the first item in the queue
            if(!runningProcesses.isEmpty())
                runningProcesses.peek().remainingRunTime --;

            currentTime++;

            //finish process if run time is 0
            if(runningProcesses.peek().remainingRunTime == 0){
                runningProcesses.peek().completionTime = currentTime;
                finishedProcesses.add(runningProcesses.remove());
            }

            //if the quantum time is reached, put the process in the back
            if(currentTime%quantumTime == 0 && !runningProcesses.isEmpty()){
                runningProcesses.add(runningProcesses.remove());
            }

        }



        //Calculate total waiting time
        float totalWaitTime = 0;

        for(Process checkProcess : finishedProcesses){
            totalWaitTime += (checkProcess.completionTime - (checkProcess.arrivalTime + checkProcess.burstTime));
        }

        //return the average
        return totalWaitTime / arrivalTimes.length;

    }








}

