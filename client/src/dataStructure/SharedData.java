package dataStructure;

import java.util.ArrayList;

public class SharedData {
	
		protected ArrayList<DataListener> listeners = new ArrayList<DataListener>();
		
		public void addDataListener(DataListener listener){
			listeners.add(listener);
		}

		protected void update() {
			for (DataListener dataListener : listeners) {
				dataListener.dataChanged();
			}
	}


}
