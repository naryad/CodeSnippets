package yn.graph;

public class LCA {

	public static void main(String[] args) {
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node1);
		Node node4 = new Node(4, node1);
		Node node5 = new Node(5, node3);
		Node node6 = new Node(6, node3);
		Node node7 = new Node(7, node3);
		Node node8 = new Node(8, node6);
		Node node9 = new Node(9, node6);
		Node node10 = new Node(10, node7);
		Node node11 = new Node(11, node7);
		Node node12 = new Node(12, node10);
		Node node13 = new Node(13, node10);

		Node commonAncestor = findLowestCommonAncestor(node9, node12);

		System.out.println(commonAncestor.getNumber());
	}

	static class Node {
		Node(int number, Node parent) {
			this.number = number;
			this.parent = parent;
		}

		private int number;
		private Node parent;

		public int calculateNodeHeight() {
			return calculateNodeHeight(this);
		}

		private int calculateNodeHeight(Node node) {
			if (node.getParent() == null) {
				return 1;
			}

			return calculateNodeHeight(node.getParent()) + 1;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getNumber() {
			return number;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getParent() {
			return parent;
		}
	}

	public static Node findLowestCommonAncestor(Node node1, Node node2) {
		int nodeLevel1 = node1.calculateNodeHeight();
		int nodeLevel2 = node2.calculateNodeHeight();

		while (nodeLevel1 > 0 && nodeLevel2 > 0) {
			if (nodeLevel1 > nodeLevel2) {
				node1 = node1.getParent();
				nodeLevel1--;
			} else if (nodeLevel2 > nodeLevel1) {
				node2 = node2.getParent();
				nodeLevel2--;
			} else {
				if (node1 == node2) {
					return node1;
				}

				node1 = node1.getParent();
				node2 = node2.getParent();
				nodeLevel1--;
				nodeLevel2--;
			}
		}

		return null;
	}
}