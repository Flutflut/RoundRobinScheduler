    //Use a class to keep track of the current processes status.
    public class Process{
        public int burstTime;
        public int arrivalTime;
        public int completionTime;
        public int remainingRunTime;

        //Initialize processes with an arrival time and burst time
        public Process( int arrivalTimeValue , int burstTimeValue){
            burstTime = burstTimeValue;
            arrivalTime = arrivalTimeValue;
            completionTime = -1;
            remainingRunTime = burstTime;
        }
    }