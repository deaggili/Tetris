package org.psnbtech;

import java.util.ArrayList;


public interface IStore {

	public void writeGameStore(ArrayList<String> list);
	
	public ArrayList<String> readGameStore();
	
}
