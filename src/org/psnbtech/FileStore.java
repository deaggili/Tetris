package org.psnbtech;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class FileStore implements IStore {

	// 파일 이름
	private final String RankFile = "rank.txt";

	private FileOutputStream output = null;

	@Override
	public void writeGameStore(ArrayList<String> list) {
		// TODO Auto-generated method stub

		String path = System.getProperty("user.dir") + "\\" + RankFile;

		File file = new File(path);

		try {
			output = new FileOutputStream(path);

			int i = 0;
			for (String item : list) {
				
				if(i == 5) {
					break;
				}
				
				item  = item + "\n";
				output.write(item.getBytes());
				
				i++;
				
			}

			output.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> readGameStore() {

		ArrayList<String> rankList = new ArrayList<String>();
		// TODO Auto-generated method stub

		String path = System.getProperty("user.dir") + "\\" + RankFile;

		File file = new File(path);

		// 파일 여부 확인
		if (file.exists() == false) {
			return rankList;
		}

		try {

			// 주문 내역을 파일에서 읽는다.
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();

			if (line == null) {
				reader.close();
				return rankList;
			}

			rankList.add(line);

			while (line != null) {

				// read next line
				line = reader.readLine();
				if (line != null) {
					rankList.add(line);
				}

			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return rankList;
	}
}
