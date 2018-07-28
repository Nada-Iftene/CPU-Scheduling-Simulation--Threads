

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/*
 * 
 * 
 */
public class Algorithm {
    ArrayList<ClassInputData> file;

    public Algorithm(ArrayList<ClassInputData> file) {
        this.file = file;
    }

    
 

    protected void shortJobFirstNoPreemptif(ArrayList<ClassInputData> file, DataVisualizationPanel frame, int quantum) {
        int currentTime = 0;
        int numberOfProcess = file.size();
        ArrayList<ClassInputData> waitingList = new ArrayList<>();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

        while (numberOfProcess > 0) {

            System.out.println("current time: " + currentTime);
            System.out.println();
            System.out.println("process filling phase:");

            for (int i = 0; i < file.size(); i++) {
                //if the processus are arriving
                if (file.get(i).getTempArriver() <= currentTime && !file.get(i).isPassed()) {
                    System.out.println("the processus " + file.get(i).getName() + " was added to the waiting list");
                    waitingList.add(file.get(i));
                    file.get(i).setPassed(true);
                    processingModel.setValueAt("" + currentTime, i, 1);
                }
            }
            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {
                int processusToPass = 0;
                int shortestTime = waitingList.get(0).getTempCpu();

                for (int i = 0; i < waitingList.size(); i++) {
                    if (waitingList.get(i).getTempCpu() < shortestTime) {
                        processusToPass = i;
                        shortestTime = waitingList.get(i).getTempCpu();
                    }
                }

                currentTime += quantum;
                System.out.println("processus " + waitingList.get(processusToPass).getName() + " is being processed...");
                waitingList.get(processusToPass).processing(quantum);

                if (waitingList.get(processusToPass).getTempCpu() <= 0) {

                    int indexOfProcessLeaving = 0;
                    while (waitingList.get(processusToPass).getName() != file.get(indexOfProcessLeaving).getName()) {
                        ++indexOfProcessLeaving;
                    }

                    System.out.println("processus " + waitingList.get(processusToPass).getName() + " left the system");
                    file.get(processusToPass).setExitTime(currentTime);
                    waitingList.remove(processusToPass);
                    --numberOfProcess;

                    processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);

                }

            } else {
                System.out.println("no Processus are waiting");
                ++currentTime;
            }
            //---------
            System.out.println();

            if (file.size() > 0) {
                System.out.println("remaining processes: ");
                boolean check = false;

                for (int i = 0; i < file.size(); i++) {
                    if (!file.get(i).isPassed()) {
                        System.out.println("Processus " + file.get(i).getName());
                        check = true;
                    }
                }

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }


    

    protected void roundRobinNonPreemptif(ArrayList<ClassInputData> file, int quantum, DataVisualizationPanel frame) {
    	
        int currentTime = 0;
        int numberOfProcess = file.size();

        ArrayList<ClassInputData> waitingList = new ArrayList<>();
       


        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

        while (numberOfProcess > 0) {

            System.out.println("current time: " + currentTime);
            System.out.println();
            System.out.println("process filling phase:");

            for (int i = 0; i < file.size(); i++) {
                //if the processus are arriving
                if (file.get(i).getTempArriver() <= currentTime && !file.get(i).isPassed()) {
                    System.out.println("the processus " + file.get(i).getName() + " was added to the waiting list");
                    waitingList.add(file.get(i));
                    file.get(i).setPassed(true);
                    processingModel.setValueAt("" + currentTime, i, 1);
                }
            }

            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {

                currentTime += quantum;
                System.out.println("processus " + waitingList.get(0).getName() + " is being processed...");
                waitingList.get(0).processing(quantum);

                if (waitingList.get(0).getTempCpu() > 0) {
                    ClassInputData p = waitingList.get(0);
                    //waitingList.add(waitingList.size() - 1, waitingList.get(0));
                    waitingList.remove(0);
                    waitingList.add(p);
                    System.out.println("processus " + waitingList.get(0).getName() + " is the head of the list");
                    System.out.println("processus " + waitingList.get(waitingList.size() - 1).getName() + " is the tail of the list");
                } else {
                    System.out.println("processus " + waitingList.get(0).getName() + " has left the system");
                    --numberOfProcess;
                    int indexOfProcessLeaving = 0;
                    while (waitingList.get(0).getName() != file.get(indexOfProcessLeaving).getName()) {
                        ++indexOfProcessLeaving;
                    }

                    processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);
                    file.get(0).setExitTime(currentTime);
                    waitingList.remove(0);
                }


            } else {
                System.out.println("No processes available");
                currentTime++;
            }

            //---------
            System.out.println();

            if (file.size() > 0) {
                System.out.println("remaining processes: ");
                boolean check = false;

                for (int i = 0; i < file.size(); i++) {
                    if (!file.get(i).isPassed()) {
                        System.out.println("Processus " + file.get(i).getName());
                        check = true;
                    }
                }

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }
    }
}
