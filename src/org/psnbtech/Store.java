package org.psnbtech;

import java.util.ArrayList;

public class Store {

	private IStore iStore;

	public Store(IStore iStore) {
		this.iStore = iStore;
	}

	// ���� ���
	public void writeGameStore(ArrayList<String> list) {
		iStore.writeGameStore(list);
	}

	// ���� �б�
	public ArrayList<String> readGameStore() {
		return iStore.readGameStore();
	}
}
