package yn.graph;

public class DecoratorPattern {
	public static void main(String[] args) {
		// create a decorated Window with horizontal and vertical scrollbars
		Window decoratedWindow = new HorizontalScrollBarDecorator(
				new VerticalScrollBarDecorator(new SimpleWindow()));

		// print the Window's description
		System.out.println(decoratedWindow.getDescription());
	}
}

// the Window interface
interface Window {
	public void draw(); // draws the Window

	public String getDescription(); // returns a description of the Window
}

// implementation of a simple Window without any scrollbars
class SimpleWindow implements Window {
	public void draw() {
		// draw window
	}

	public String getDescription() {
		return "simple window";
	}
}

// abstract decorator class - note that it implements Window
abstract class WindowDecorator implements Window {
	protected Window decoratedWindow; // the Window being decorated

	public WindowDecorator(Window decoratedWindow) {
		this.decoratedWindow = decoratedWindow;
	}

	public void draw() {
		decoratedWindow.draw();
	}
}

// the first concrete decorator which adds vertical scrollbar functionality
class VerticalScrollBarDecorator extends WindowDecorator {
	public VerticalScrollBarDecorator(Window decoratedWindow) {
		super(decoratedWindow);
	}

	public void draw() {
		decoratedWindow.draw();
		drawVerticalScrollBar();
	}

	private void drawVerticalScrollBar() {
		// draw the vertical scrollbar
	}

	public String getDescription() {
		return decoratedWindow.getDescription()
				+ ", including vertical scrollbars";
	}
}

// the second concrete decorator which adds horizontal scrollbar functionality
class HorizontalScrollBarDecorator extends WindowDecorator {
	public HorizontalScrollBarDecorator(Window decoratedWindow) {
		super(decoratedWindow);
	}

	public void draw() {
		decoratedWindow.draw();
		drawHorizontalScrollBar();
	}

	private void drawHorizontalScrollBar() {
		// draw the horizontal scrollbar
	}

	public String getDescription() {
		return decoratedWindow.getDescription()
				+ ", including horizontal scrollbars";
	}
}
