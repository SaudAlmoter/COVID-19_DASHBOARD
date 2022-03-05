public class DynamicArray<T> implements Array<T> {

	T Darr[];
	int Size;
	int Capacity;

	public DynamicArray() {
		Size = 0;
		Capacity = 0;
		Darr = (T[]) new Object[1];
	}// DArrConstructure

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return Size;
	}// size

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return Capacity;
	}// capacity

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if (Size <= i || i < 0) {
			throw new ArrayIndexOutOfBoundsException();	
		}
		return Darr[i];
	}// get

	@Override
	public void set(int i, T e) {
		// TODO Auto-generated method stub
		if (Size <= i || i < 0)
			throw new ArrayIndexOutOfBoundsException();
		Darr[i] = e;
	}// set
	@Override
	public void add(T e) {
		// TODO Auto-generated method stub
		if (Size == 0) {
			Darr = (T[]) new Object[1];
			Darr[0] = e;
			Size = 1;
			Capacity = 1;
		} // IfEmpty
		else {
			if (Size < Capacity) {
				Darr[Size] = e;
				Size++;
			} else {
				T tmp[] = (T[]) new Object[Size];
				for (int w = 0; w < Size; w++) {
					tmp[w] = Darr[w];
				} // for
				Capacity = Capacity * 2;
				Darr = (T[]) new Object[Capacity];
				for (int w = 0; w < Size; w++) {
					Darr[w] = tmp[w];
				} // for
				Darr[Size] = e;
				Size++;
			}///else
		} // elseNotEmpty
	}// add
}// DynamicArray
