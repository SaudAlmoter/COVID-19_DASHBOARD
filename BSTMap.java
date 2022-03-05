public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {

	class BSTN< K extends Comparable<K>, T >{
		public K Key;
		public T Data;
		public BSTN<K,T> left;
		public BSTN<K,T> right;

		public BSTN(K k, T D) {
			this.Key = k;
			this.Data = D;
			this.left = null;
			this.right = null;
		}//constuctureBSTN

		public BSTN(K k, T D, BSTN<K, T> left, BSTN<K,T> right) {
			this.Key = k;
			this.Data = D;
			this.left = left;
			this.right = right;
		}//constuctureBSTN2
	}//calssBSTN

	private BSTN<K,T> Root;
	private BSTN<K,T> Current;
	private int size;

	public BSTMap() {
		Root = null;
		Current = null;
	}//DconstuctureBSTMap

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}//Size

	//	private int SIZE(BSTN<K,T> N) {
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
		size = 0;
	}//clear

	@Override
	public boolean update(K k, T e) {
		// TODO Auto-generated method stub
		return UPDATE( k,  e , Root );
	}//update
	private boolean UPDATE(K k, T e ,BSTN<K,T> N){	
		BSTN<K,T> U = N;
		if(U.Key.compareTo(k)==0) {
			U.Data = e;
			return true;
		}//if
		else if(U.Key.compareTo(k) > 0)
			return UPDATE(k,  e , N.left);
		else if(U.Key.compareTo(k) < 0)
			return UPDATE(k,  e , N.right);		
		else
			return false;
	}//UPDATEPERFECT

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		// TODO Auto-generated method stub
		BSTN<K,T> U = Root;
		if(U == null) {
			return new Pair<Boolean, T>(false,null);
		}//ifEmpty
		while(U!= null) {
			if(U.Key.compareTo(k)==0)
				return  new Pair<Boolean, T>(true, U.Data);

			else if(U.Key.compareTo(k)>0)
				U = U.left;
			else
				U = U.right;
		}//while
		return 	new Pair<Boolean, T>(false,null);
		/// i changed the return data to null instad of U.data
	}//pairDone

	@Override
	public int nbKeyComp(K k) {
		int keyComp = 0;
		for (BSTN<K, T> U = Root; U != null;) {
			keyComp++;
			if (U.Key.compareTo(k) > 0) {
				U = U.left;
			}//if 
			else if (U.Key.compareTo(k) < 0) {
				U = U.right;
			}//else if 
			else {
				return keyComp;
			}//else
		}//for
		return 0;
	}//nbKeyComp

	@Override
	public boolean insert(K k, T e) {
		// TODO Auto-generated method stub
		BSTN<K,T> N = new BSTN<K,T> (k,e);
		BSTN<K,T> F = Current;
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
			if(Current.Key.compareTo(k) > 0) 
				Current.left = N;
			else
				Current.right = N;
			size++;
		}//else
		Current = F;
		return true;
	}//insertPerfect
	private boolean checkKey(K k) {
		BSTN<K,T> U = Root;
		BSTN<K,T> S = Current;
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
		BSTN<K,T> U = Root;
		BSTN<K,T> S = null;
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
					BSTN<K,T> M = U.right;
					S = U;
					while(M.left != null) {
						S = M;
						M = M.left;
					}//while
					U.Key = M.Key;
					U.Data = M.Data;
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
	}//remove

	@Override
	public List<K> getKeys() {
		// TODO Auto-generated method stub
		List<K> L1 = new LinkedList<K>();
		GET_LIST(Root,L1);
		return L1;
	}//ListDone
	private void GET_LIST(BSTN<K,T> N, List<K> L1){
		if(N == null)
			return;
		else{
			GET_LIST( N.left, L1);
			L1.insert(N.Key);
			GET_LIST( N.right, L1);		
		}
	}//GET_LIST

}//BSTMap
