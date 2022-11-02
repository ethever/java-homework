import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	static class Element {
		int level;
		String label, id;

		public Element(int level, String label, String id) {
			this.level = level;
			this.label = label;
			this.id = id;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		scan.nextLine();

		List<Element> doc = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String line = scan.nextLine();
			int level = 0;
			String label, id;

			// 计算当前节点在文档树中的层级
			int count = 0;
			while (line.charAt(count) == '.') {
				count++;
			}
			level = count / 2;
			
			// 处理标签和id
			int index = line.indexOf(" ");
			if (index == -1) {
				label = line.substring(count);
				id = "";
			} else {
				label = line.substring(count, index);
				id = line.substring(index + 1, line.length());
			}
			doc.add(new Element(level, label, id));
		}

		for (int i = 0; i < m; i++) {
			String selector = scan.nextLine();

			List<Integer> list = new ArrayList<>();

			// 若为id选择器或者标签选择器
			if (!selector.contains(" ")) {
				for (int j = 0; j < doc.size(); j++) {
					Element e = doc.get(j);
					if (selector.equalsIgnoreCase(e.label) || selector.equals(e.id)) {
						list.add(j + 1);
					}
				}
			} else {
				String[] selectors = selector.split(" ");
				String last = selectors[selectors.length - 1];

				for (int j = 0; j < doc.size(); j++) {
					// 如果当前元素与后代选择器的最后一个选择器不匹配，则继续循环
					if (!last.equalsIgnoreCase(doc.get(j).label) && !last.equals(doc.get(j).id)) {
						continue;
					}

					int index = selectors.length - 2;
					int level = doc.get(j).level;

					for (int k = j; k >= 0 && index >= 0; k--) {
						Element e = doc.get(k);
						String str = selectors[index];
						// 寻找父节点（level比当前节点小的第一个节点）
						if (e.level < level) {
							level = e.level;
							// 若父节点匹配当前选择器，则继续匹配；否则，向上寻找祖先节点
							if (str.equalsIgnoreCase(e.label) || str.equals(e.id)) {
								index--;
							}
						}
					}
					// 如果完全匹配后代选择器
					if (index < 0) {
						list.add(j + 1);
					}
				}
			}
			System.out.print(list.size());
			for (Integer line : list) {
				System.out.print(" " + line);
			}
			System.out.println();
		}
		scan.close();
	}
}