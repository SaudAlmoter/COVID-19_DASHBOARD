public class BSTSet<K extends Comparable<K>> implements Set<K> {

	class BSTSN<K extends Comparable<K>>{
		public K Key;
		public BSTSN<K> left;
		public BSTSN<K> right;

		public BSTSN(K k) {
			this.Key = k;
			this.left = null;
			this.right = null;
		}//constuctureBSTSN

		public BSTSN(K k, BSTSN<K> left, BSTSN<K> right) {
			this.Key = k;
			this.left = left;
			this.right = right;
		}//constuctureBSTSN2
	}//ClassBSTSN
	private BSTSN<K> Root;
	private BSTSN<K> Current;
	private int size;

	public BSTSet() {
		Root = null;
		Current = null;
		size=0;
	}//DconstuctureBSTSet
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}//Size

	//	private int SIZE(BSTSN<K> N) {
	//		int S = 1;
	//		if( N == null)
	//			return 0;
	//		else
	//			return SIZE(N.left)+S+SIZE(N.right);
	//	}//SIZE

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}//full

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Root = Current =  null;
	}//clear

	@Override
	public boolean find(K k) {
		// TODO Auto-generated method stub
		BSTSN<K> U = Root;
		BSTSN<K> S = Current;
		if(Root == null)
			return false;
		while(U!= null) {
			S = U;
			if(U.Key.compareTo(k)==0) {
				Current = U;
				return true;
			}//if
			else if(U.Key.compareTo(k)>0)
				U = U.left;
			else
				U = U.right;
		}//while
		Current = S;
		return false;
	}//findDone

	@Override
	public int nbKeyComp(K k) {
		int keyComp = 0;
		for ( BSTSN<K> U = Root; U != null; ) {
			keyComp++;
			if (U.Key.compareTo(k) > 0) {
				U = U.left;
			}///iF
			else if (U.Key.compareTo(k) < 0) {
				U = U.right;
			}//elseIf
			else {
				return keyComp;
			}//else
		}//for
		return 0;
	}//nbKeyComp

	@Override
	public boolean insert(K k) {
		// TODO Auto-generated method stub
		BSTSN<K> N = new BSTSN<K> (k);
		BSTSN<K> F = Current;
		if(Root == null) {
			Root = Current = N;
			size++;
			return true;
		}//Empty
		if(checkKey(k)) {
			Current = F;
			return false;
		}//ifExist
		else {
			if(Current.Key.compareTo(k)>0)
				Current.left = N;
			else
				Current.right = N;
			size++;
		}//else
		Current = F;
		return true;
	}//insertPerfect
	private boolean checkKey(K k) {
		BSTSN<K> U = Root;
		BSTSN<K> S = Current;
		if(Root == null)
			return false;
		while(U!= null) {
			S = U;
			if(U.Key.compareTo(k)==0) {
				Current = U;
				return true;
			}//if
			else if(U.Key.compareTo(k)>0)
				U = U.left;
			else
				U = U.right;
		}//while
		Current = S;
		return false;		
	}//checkKey

	@Override
	public boolean remove(K k) {
		// TODO Auto-generated method stub
		K G = k ;
		BSTSN<K> U = Root;
		BSTSN<K> S = null;
		while(U != null) {
			if(U.Key.compareTo(k)>0) {
				S = U;
				U = U.left;
			}//If>
			else if(U.Key.compareTo(k)<0) {
				S = U;
				U = U.right;
			}//elseIf
			else {
				if((U.left != null)&&(U.right!= null)) {
					BSTSN<K> M = U.right;
					S = U;
					while(M.left != null) {
						S = M;
						M = M.left;
					}//while
					U.Key = M.Key;
					G = M.Key;
					U = M;
				}//iF
				if(U.left != null) 
					U = U.left;
				else
					U = U.right;
				if(S == null)
					Root = U;
				else {
					if(S.Key.compareTo(G)>0)
						S.left = U;
					else
						S.right = U;
				}//else
				Current = Root;
				return true;
			}//else
		}//while
		return false;
	}//removeDone

	@Override
	public List<K> getKeys() {
		// TODO Auto-generated method stub
		List<K> L1 = new LinkedList<K>();
		GET_LIST(Root, L1);
		return L1;
	}//List
	private void GET_LIST(BSTSN<K> N, List<K> L1){
		if(N == null)
			return;
		else{
			GET_LIST( N.left, L1);
			L1.insert(N.Key);
			GET_LIST( N.right, L1);		
		}
	}//GET_LIST

}//BSTSet
