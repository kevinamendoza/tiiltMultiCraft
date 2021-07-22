package com.multicraft;

public class CustomUndoStack {
	BuildCommandData[] dataArr;
	int insertIndex;
	int size;

	public CustomUndoStack(int size) {
		this.dataArr = new BuildCommandData[size];
		this.insertIndex = 0;
		this.size = size;
	}

	public BuildCommandData pop() throws NoCommandHistoryException {
		if(this.isEmpty()) {
			throw new NoCommandHistoryException("The stack is empty.");
		}
		
		insertIndex-=1;
		
		if(insertIndex < 0) {
			insertIndex = this.size-1;
		}
		
		BuildCommandData data = dataArr[insertIndex];
		dataArr[insertIndex] = null;
		return data;		 
	}

	public BuildCommandData peek() throws NoCommandHistoryException {
		if(this.isEmpty()) {
			throw new NoCommandHistoryException("The stack is empty.");
		}

		int peekIndex = insertIndex - 1;

		if(peekIndex < 0) {
			peekIndex = this.size-1;
		}

		return dataArr[peekIndex];
	}

	public void push(BuildCommandData data) {
		this.dataArr[this.insertIndex] = data;
		this.insertIndex+=1;
		if(this.insertIndex == size) {
			this.insertIndex = 0;
		}
	}
	
	public BuildCommandData getItemAtIndex(int i) {
		return dataArr[i];
	}
	
	public boolean isEmpty() {
		int indexToCheck = insertIndex-1;
		
		if(indexToCheck < 0)
			indexToCheck = this.size-1;

		return dataArr[indexToCheck] == null;
	}
	
	public int getSize() {
		return this.size;
	}
}
