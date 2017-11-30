package files;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.Attribute;
import model.Entity;
import model.indTree.Tree;

public class IndFile extends InfAbstractFile implements InfFile {

	@Override
	public void add(ArrayList<String> objects) {
		// TODO Auto-generated method stub
		
	}

	private Tree tree;
	private String treeFileName;
	private String overZoneFileName;
	private RandomAccessFile raf;
	private int left = 0;
	private int right;
	private int position;
	private Entity entity;

	public IndFile(String path, String headerName, boolean directory) {
//		super(path, headerName, directory);

	}

	public IndFile() {

	}

	@Override
	public void add() {

	}

	@Override
	public void change() {

	}

	@Override
	public void delete() {

	}
	@Override
	public int find(ArrayList<String> list) {
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			j  = binarySearch(left, right, list.get(i), i);
		}
		
		return j;
	}

	public int binarySearch(int l, int r, String x, int i) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			int number = x.compareToIgnoreCase(getSplittedRow(getSpecificRow(mid))[i]);

			switch (number) {
			case 0:
				left = l;
				right = r;
				return mid;
			case 1:
				return binarySearch(mid + 1, r, x, i);
			case -1:
				return binarySearch(l, mid - 1, x, i);
			default:
				break;
			}

		}

		// We reach here when element is not present in array
		return -1;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
		this.right = countLines();
	}

	public int countLines() {
		InputStream is;
		byte[] c = new byte[1024];
		int count = 0;
		int readChars = 0;
		boolean empty = true;
		try {
			is = new BufferedInputStream(new FileInputStream(getPath()));

			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			is.close();
		} catch (IOException e) {

		}
		return (count == 0 && !empty) ? 1 : count;
	}

	public String[] getSplittedRow(String row) {

		ArrayList<String> attributesInStrings = new ArrayList<>();
		int start = 0;
		int end = 0;
		int i = 0;
		String[] line = new String[entity.getAttributes().size()];
		for (Attribute a : entity.getAttributes()) {
			end += a.getLength();
			line[i] = row.substring(start, end);
			start = end;
			i++;
		}
		return line;

	}

	private String getSpecificRow(int row) {
		Stream<String> lines;
		String line = null;
		try {
			lines = Files.lines(Paths.get(getPath()), Charset.forName("ISO-8859-1"));
			line = lines.skip(row).findFirst().get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	// deserijalizacija, parsira datoteku u kojoj se nalazi sacuvano stablo
	// indeksa
	public void openTree(String treeFilePath) {

		ObjectInputStream os = null;
		try {
			os = new ObjectInputStream(new FileInputStream(treeFilePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			tree = (Tree) os.readObject();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}



	@Override
	public void sort(ArrayList<String> string, ArrayList<Integer> integer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ArrayList<String> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
