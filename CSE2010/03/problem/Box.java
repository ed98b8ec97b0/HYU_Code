package problem;
import java.util.ArrayList;

public class Box<T> {
	private int count;
	private ArrayList<T> t;

	Box(){
		this.count = 0;
		this.t = new ArrayList<T>();
	}

	public void add(T t){
		this.t.add(t);
		this.count++;
	}

	public void remove(T t){
		for (int i = 0; i < this.count; i++) {
			if (this.t.contains(t)) {
				this.t.remove(t);
				this.count--;
			}
		}
	}

	public void print(){
		System.out.print(this.t.toString() + " Count : " + this.count + "\n");
	}
}
