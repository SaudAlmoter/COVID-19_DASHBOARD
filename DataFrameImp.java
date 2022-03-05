import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class ConditionTFC implements Condition {

	private String C;

	public ConditionTFC(String C) {
		this.C = C;

	}//constructure
	@Override

	public boolean test(Array<Object> row) {
		// TODO Auto-generated method stub
		for(int V = 0 ; V < row.size(); V++) {
			if(row.get(V).equals(C))
				return true;
		}
		return false;
	}//test
}//ConditionTFC
public class DataFrameImp implements DataFrame {


	public Map <String, Pair<Integer, Class<?>>> DataFrame;
	public Map <String, Pair<Integer, Array<?>>> DataFrameDP;
	public Set <String>columsInfo;
	public int NOCol ;
	public Array<String> Darr;
	public Class<?> cType;

	public DataFrameImp() {
		this.DataFrame = new BSTMap<String, Pair<Integer, Class<?>>>();
		this.DataFrameDP = new BSTMap<String, Pair<Integer, Array<?>>>();
		this.columsInfo = new BSTSet<String>();
		this.Darr = new DynamicArray<String>();
		NOCol = -1;
	}// ConstructureD

	@Override
	public int getNbCols() {
		// TODO Auto-generated method stub
		return DataFrame.size();
	}// getNbColsDone

	@Override
	public boolean addCol(String colName, Class<?> colType) {
		// TODO Auto-generated method stub
		Pair<Integer, Class<?>> PDataFrame = new Pair<Integer, Class<?>>(DataFrame.size()  , colType);
		Pair<Integer, Array<?>> PDataFrameDP = new Pair<Integer, Array<?>>(DataFrame.size() , new DynamicArray<Object>());
		boolean E = DataFrame.insert(colName, PDataFrame);
		if (E) {
			NOCol++;
			columsInfo.insert(colName);
			Darr.add(colName);
			DataFrameDP.insert(colName, PDataFrameDP);
		} // if
		return E;
	}// addColPerfect

	@Override
	public boolean addCol(String colName, Class<?> colType, Array<Object> col) {
		// TODO Auto-generated method stub
		if (DataFrame.size() > 0) {
			if (getNbRows() != col.size())
				return false;
		} // ifNotEmpty
		Pair<Integer, Class<?>> PDataFrame = new Pair<Integer, Class<?>>(DataFrame.size()  , colType);
		Pair<Integer, Array<?>> PDataFrameDP = new Pair<Integer, Array<?>>(DataFrame.size() , col);
		boolean E = DataFrame.insert(colName, PDataFrame);
		if (E) {
			NOCol++;
			columsInfo.insert(colName);
			Darr.add(colName);
			DataFrameDP.insert(colName, PDataFrameDP);
		} // if
		return E;
	}// addColPerfect

	@Override
	public boolean isCol(String colName) {
		// TODO Auto-generated method stub
		return columsInfo.find(colName);
	}// isCol

	@Override
	public int getColInd(String colName) {
		// TODO Auto-generated method stub
		if (!columsInfo.find(colName))
			throw new IllegalArgumentException();
		else
			return DataFrame.retrieve(colName).second.first;
	}// getColInd

	@Override
	public String getColName(int j) {
		// TODO Auto-generated method stub
		return Darr.get(j);
	}// getColName

	@Override
	public Map<String, Pair<Integer, Class<?>>> getColInfo() {
		// TODO Auto-generated method stub
		return DataFrame;
	}// getColInfo

	@Override
	public Array<Object> getCol(int j) {
		// TODO Auto-generated method stub
		String S = Darr.get(j);
		Pair<Boolean, Pair< Integer ,Array<?>>> PArr = DataFrameDP.retrieve(S);
		Array<Object> OArr = (Array<Object>) PArr.second.second;
		if (PArr.first)
			return  OArr;
		else
			return new DynamicArray<Object>();
	}// getCol

	@Override
	public Array<Object> getCol(String colName) {
		// TODO Auto-generated method stub
		if (columsInfo.find(colName)) {
			Pair<Boolean, Pair< Integer ,Array<?>>> PArr = DataFrameDP.retrieve(colName);
			Array<Object> OArr = (Array<Object>) PArr.second.second;
			return OArr;			
		}
		else
			return new DynamicArray<Object>();
	}// getColDone

	@Override
	public int getNbRows() {
		// TODO Auto-generated method stub
		if (DataFrame.size() > 0) {
			Pair<Boolean, Pair< Integer ,Array<?>>> PArr = DataFrameDP.retrieve(getColName(0));
			return PArr.second.second.size();
		} // if
		return 0;
	}// getNbRows

	@Override
	public void addRow(Array<Object> row) {
		// TODO Auto-generated method stub
		if (getNbCols() != row.size() && getNbCols() != 0)
			throw new IllegalArgumentException();
		else {
			for (int i = 0; i < row.size(); i++) {
				Pair<Boolean, Pair< Integer ,Class<?>>> PClass = DataFrame.retrieve(getColName(i));
				if (!PClass.second.second.isInstance(row.get(i)))
					throw new IllegalArgumentException();
				else
					getCol(i).add(row.get(i));
			} // for
		} // else
	}// addRow

	@Override
	public Array<Object> getRow(int i) {
		// TODO Auto-generated method stub
		Array<Object> rowInfo = new DynamicArray<Object>();
		for (int i1 = 0; i1 < columsInfo.size(); i1++) {
			rowInfo.add(getCol(i1).get(i));
		} // for
		return rowInfo;
	}// getRow

	private boolean isInstanceOf(Array<Object> FArr) {
		for (int y = 0; y < FArr.size(); y++) {
			if (!getCT(y).isInstance(FArr.get(y)))
				return false;
		} // for
		return true;
	}// isInstanceOf

	@Override
	public boolean loadCSV(String fileName) {
		// TODO Auto-generated method stub
		Array<Object> FArr ;
		try {
			DataFrame.clear();
			DataFrameDP.clear();
			columsInfo.clear();
			NOCol = -1;
			Darr = new DynamicArray<String>();

			File FData = new File(fileName);
			Scanner Freader = new Scanner(FData);
			if (!Freader.hasNextLine()) 
				return false;

			String L = Freader.nextLine();
			int NOLine = 1;
			String[] SArr = L.split(",");
			if (!Freader.hasNextLine()) {
				for (int k = 0; k < SArr.length; k++)
					addCol(SArr[k], String.class);
				return true;
			} // if-Colums names

			while (Freader.hasNextLine()) {
				L = Freader.nextLine();
				FArr = new DynamicArray<Object>();
				NOLine++;

				String[] SplitedArr = L.split(",");
				if (SplitedArr.length != SArr.length) {
					DataFrame.clear();
					DataFrameDP.clear();
					columsInfo.clear();
					NOCol = -1;
					Darr = new DynamicArray<String>();
					return false;
				} // iF!=
				for (int y = 0; y < SplitedArr.length; y++) {
					int t = 0;
					try {
						int C = Integer.parseInt(SplitedArr[y]);
						t=1;
						if (NOLine == 2) {
							Class<?> colum = Integer.class;
							Array<Object> CArr = new DynamicArray<Object>();
							CArr.add(C);
							addCol(SArr[y], colum, CArr);
						} // if 2
						else 
							FArr.add(C);

					} catch (Exception ex) {
						try {
							Double D = Double.parseDouble(SplitedArr[y]);
							t=1;

							if (NOLine == 2) {
								Class<?> colum = Class.forName("java.lang.Double");//Double.class
								Array<Object> CArr = new DynamicArray<Object>();
								CArr.add(D);
								addCol(SArr[y], colum, CArr);
							} /// if==2
							else {
								FArr.add(D);
							} // else

						} catch (Exception ex1) {

						} // catchDouble
					} // catchInteger
					try {
						Date Dcol = new SimpleDateFormat("yyyy-MM-dd").parse(SplitedArr[y]);
						t=1;
						if (NOLine == 2) {
							Class<?> colum = Date.class;
							Array<Object> CArr = new DynamicArray<Object>();
							CArr.add(Dcol);

							addCol(SArr[y], colum, CArr);
						} // if==2
						else
							FArr.add(Dcol);
					} catch (Exception e) {

					}// catchDate
					try {
						if (t == 0) {
							if (NOLine == 2) {
								Class<?> colum = Class.forName("java.lang.String");
								Array<Object> CArr = new DynamicArray<Object>();
								CArr.add(SplitedArr[y]);
								addCol(SArr[y], colum, CArr);
							} // NOLine == 2
							else
								FArr.add(SplitedArr[y]);
						} // ifT==0
					} catch (Exception e) {

					}//catchString

				} // for
				if (NOLine > 2 && FArr.size() != getNbCols()) {
					DataFrame.clear();
					DataFrameDP.clear();
					columsInfo.clear();
					NOCol = -1;
					Darr = new DynamicArray<String>();
					return false;
				} 
				if (!isInstanceOf(FArr)) {
					DataFrame.clear();
					DataFrameDP.clear();
					columsInfo.clear();
					NOCol = -1;
					Darr = new DynamicArray<String>();
					return false;
				} // ifisInstanceOf
				if (FArr.size() !=0) {
					addRow(FArr);
				}//FArr.size() !=0
			} // while
		} catch (FileNotFoundException e) {
			return false;
		} // catch
		return true;
	}// loudCSV

	@Override
	public DataFrame filterCols(Array<String> colNames) {
		// TODO Auto-generated method stub
		DataFrame FDataFrame = new DataFrameImp();
		Array<Object> FDataFrameA = new DynamicArray<Object>();
		for (int i = 0; i < colNames.size(); i++) {
				FDataFrameA = getCol(colNames.get(i));
				FDataFrame.addCol(colNames.get(i), getCT(i), FDataFrameA);
		} // for
		return FDataFrame;
	}

	@Override
	public DataFrame filterRows(Condition cond) {
		// TODO Auto-generated method stub
		DataFrame FDataFrame = new DataFrameImp();
		for (int t = 0; t < getNbCols(); t++) {
			FDataFrame.addCol(getColName(t), getCT(t));
		} // for
		for (int t = 0; t < getNbRows(); t++) {
			Array<Object> FRow = getRow(t);
			if (cond.test(FRow))
				FDataFrame.addRow(FRow);
		} // for
		return FDataFrame;
	}

	private Class<?> getCT(int u) {
		String S = getColName(u);
		Pair<Boolean, Pair<Integer, Class<?>>> p = DataFrame.retrieve(S);
		return p.second.second;
	}//

	private Class<?> getCT(String i) {
		Pair<Boolean, Pair<Integer, Class<?>>> p = DataFrame.retrieve(i);
		if (p.first)
			return p.second.second;
		else
			return null;
	}//

	@Override
	public double mean(String colName) {
		// TODO Auto-generated method stub
		if (!isCol(colName)) {
			throw new IllegalArgumentException();
		} // !isCol
		Class<?> Ctype = getCT(colName);
		if (Double.class.equals(Ctype) || Integer.class.equals(Ctype)) {
			Array<?> CArr = getCol(getColInd(colName));
			double S = 0;
			for (int y = 0; y < CArr.size(); y++) {
				if (Double.class.equals(Ctype)) {
					System.out.print("HJ");
					S = S + (Double) CArr.get(y);
			}
				else
					S = S + (int) CArr.get(y);
			} // for
			if (CArr.size() <= 0)
				return 0.0;
			else {
				double M = S / CArr.size();
				return M;
			} // else
		} // if
		else {
			throw new IllegalArgumentException();
		}
	}// mean
}// DataFrameIMP
