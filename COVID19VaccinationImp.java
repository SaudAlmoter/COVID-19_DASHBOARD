public class COVID19VaccinationImp implements COVID19Vaccination {

	DataFrame df;

	public COVID19VaccinationImp() {
		this.df = new DataFrameImp();
	}// COVID19VaccinationImp

	@Override
	public void setData(DataFrame df) {
		// TODO Auto-generated method stub
		this.df = df;
	}// setData

	@Override
	public DataFrame getPeopleVaccinated(String countryName) {
		// TODO Auto-generated method stub
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		Array<String> Arr = new DynamicArray<String>();
		Arr.add("Date");
		Arr.add("People Vaccinated");

		r = r.filterCols(Arr);

		return r;
	}// getPeopleVaccinated

	@Override
	public DataFrame getPeopleFullyVaccinated(String countryName) {
		// TODO Auto-generated method stub
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		Array<String> Arr = new DynamicArray<String>();
		Arr.add("Date");
		Arr.add("People Fully Vaccinated");

		r = r.filterCols(Arr);

		return r;
	}//getPeopleFullyVaccinated

	@Override
	public DataFrame getPeopleVaccinatedPerHundred(String countryName) {
		// TODO Auto-generated method stub
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		Array<String> Arr = new DynamicArray<String>();
		Arr.add("Date");
		Arr.add("Percentage of People Vaccinated");

		r = r.filterCols(Arr);

		return r;
	}//getPeopleVaccinatedPerHundred

	@Override
	public DataFrame getPercentageOfPeopleFullyVaccinated(String countryName) {
		// TODO Auto-generated method stub
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		Array<String> Arr = new DynamicArray<String>();
		Arr.add("Date");
		Arr.add("Percentage of People Fully Vaccinated");

		r = r.filterCols(Arr);

		return r;	
	}//getPercentageOfPeopleFullyVaccinated

	@Override
	public Set<String> getVaccines(String countryName) {
		// TODO Auto-generated method stub
		Set<String> S = new BSTSet<String>();
		
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		
		Array<String> Arr = new DynamicArray<String>();
		Arr.add("Vaccines");
		DataFrame fr = r.filterCols(Arr);
		Array<Object> PArr = fr.getCol("Vaccines");
		for(int q = 0; q < PArr.size() ; q++ ) {
			String CV = (String) PArr.get(q);
			String []FCV = CV.split("-");
			for(int y = 0 ; y< FCV.length ; y++) {
				S.insert(FCV[y]);
			}//for
		}//for

		return S;
	}//getVaccines

	@Override
	public double getAvgVaccinatedPerDay(String countryName) {
		// TODO Auto-generated method stub
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		return r.mean("People Vaccinated");	
	}//getAvgVaccinatedPerDay

	@Override
	public double getAvgFullyVaccinatedPerDay(String countryName) {
		// TODO Auto-generated method stub
		Condition C = new ConditionTFC(countryName);
		DataFrame r = df.filterRows(C);
		return r.mean("People Fully Vaccinated");		
	}//getAvgFullyVaccinatedPerDay

}// COVID19VaccinationImp
