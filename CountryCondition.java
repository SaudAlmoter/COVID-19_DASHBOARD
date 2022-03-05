public class CountryCondition implements Condition {
  private final String CName;

  public CountryCondition(String countryName) {
    this.CName = countryName;
  }

  @Override
  public boolean test(Array<Object> row) {
    return CName.equals(row.get(0));
  }
}
