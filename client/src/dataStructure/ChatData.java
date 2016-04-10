package dataStructure;

public class ChatData extends SharedData{
	private String lastLine;
	
	public ChatData() {
		lastLine = "";
	}
	
	public void writeChatLine(String line) {
		lastLine = line;
		update();
	}

	public String getLastLine() {
		return lastLine;
	}
}
