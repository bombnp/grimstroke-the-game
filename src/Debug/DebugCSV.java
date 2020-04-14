package Debug;

public class DebugCSV {
    public static void DisplayCSV(String[][] CSV) {
		// TODO Auto-generated method stub
	    for(String[] str: CSV) {
	    	for(String data: str) {
	    		System.out.print(data+" ");
	    	}
	    	System.out.println();
	    }	
	}
}
