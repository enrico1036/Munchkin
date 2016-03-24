package dataStructure;

import java.util.ArrayList;

public class SharedData {
	
		protected static ArrayList<DataListener> listeners;
		public static void addDataListener(DataListener listener){
			listeners.add(listener);
		}

		protected static void update() {
			for (DataListener dataListener : listeners) {
				dataListener.dataChanged();
			}
	}


}
