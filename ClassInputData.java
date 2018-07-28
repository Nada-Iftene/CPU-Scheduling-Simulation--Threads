import java.util.Scanner;

/*
 * 
 * 
 */
public class ClassInputData {
	//declaration
	private int tempArriver;
	private int tempCpu;
	 private int name;
	    private boolean passed;
	    private int exitTime;
	//inisialisation
	public ClassInputData(int name,int tempArriver,int tempCpu,boolean passed){
		this.name = name;
		this.tempArriver= tempArriver;
		this.tempCpu= tempCpu;
		this.passed= passed;
		
	}
	//getters and setters
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public int getExitTime() {
		return exitTime;
	}
	public void setExitTime(int exitTime) {
		this.exitTime = exitTime;
	}
	public int getTempArriver() {
		return tempArriver;
	}
	public void setTempArriver(int tempArriver) {
		this.tempArriver = tempArriver;
	}
	public int getTempCpu() {
		return tempCpu;
	}
	public void setTempCpu(int tempCpu) {
		this.tempCpu = tempCpu;
	}
	//procedure processing
	 public void processing(int timeToProcess) {
	        setTempCpu(getTempCpu() - timeToProcess);

	    }
	 
	

}
