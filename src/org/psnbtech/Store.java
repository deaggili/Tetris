package org.psnbtech;

import java.util.ArrayList;

public class Store {

	private IStore iStore;

	public Store(IStore iStore) {
		this.iStore = iStore;
	}

	// 점수 기록
	public void writeGameStore(ArrayList<String> list) {
		iStore.writeGameStore(list);
	}

	// 점수 읽기
	public ArrayList<String> readGameStore() {
		return iStore.readGameStore();
	}
}
